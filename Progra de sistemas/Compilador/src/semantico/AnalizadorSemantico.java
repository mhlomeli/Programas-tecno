/*    Copyright 2010 Miguel Hernández Lomelí
*    This file is part of 8Reinas.
*
*    Compilador is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Compilador is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with Compilador.  If not, see <http://www.gnu.org/licenses/>
*/
package semantico;

import java.util.ArrayList;
import utilidades.Pila;
import ejecucion.Pvm;

public class AnalizadorSemantico 
{
	//metodo de comparacion de tipos
	private  int valor=0; //guarda el valor de la interseccion de ren y col
	private TablaCuadruplos tblCuadruplos;
	private TablaSimbolos tblSimbolos;
	private Pila<Integer> operandos;
	private Pila<Integer> operadores;
	private Pila<Integer> saltos;
	private ArrayList<String>listaIds;
	private Pvm mVirtual;
	
	public AnalizadorSemantico(){
		
		this.tblCuadruplos = new TablaCuadruplos();
		this.tblSimbolos= new TablaSimbolos();
		this.operadores = new Pila<Integer>();
		this.operandos= new Pila<Integer>();
		this.listaIds = new ArrayList<String>();
		this.saltos = new Pila<Integer>();

	}
	
	//Estas no creo que se utilicen
	protected void setValor (int value){
		this.valor = value;
		
	}
	
	protected int getValue(){
		return this.valor;
	}
	
	/*Acciones*/
	
	/**
	 * Acción 723
	 * Inserta el nuevo identificador en la lista de identificadores
	 */
	public void insertaIdLista(String identificador){
	
		listaIds.add(identificador);
	}
	
	/**
	 * Accion 700
	 * Inserta el nuevo identificador en la tabla de simbolos
	 */
	public boolean insertaId(int tipo){
		
		boolean idNoDuplicado = true;
		
		for(int i = 0; i< listaIds.size(); i++){
			idNoDuplicado = tblSimbolos.setSimbolo(listaIds.get(i), "", Conversores.convierteTipos(tipo));

		}
		
		listaIds.clear();
		
		/*for (String identificador:listaIds){
			idNoDuplicado = tblSimbolos.setSimbolo(identificador, "", tipo);
		}*/
		return idNoDuplicado;
	}
	
	/**
	 * Accion 701
	 * Inserta un identificador a la pila de operandos
	 * Verifica si el identificador ya se ha declarado 
	 * @return boolean 
	 */
	public boolean insertaIdPila(String identificador){
	
		int resultado = tblSimbolos.busqueda(identificador);
		
		if (resultado == -1){
			return false;
		}
		else{
			this.operandos.push(resultado);
			return true;
		}
		
	}
	
	/**
	 * Acción: 702
	 * Inserta un operador a la pila de operadores
	 * @param Token del operador que se va a insertar
	 */
	public void insertaOperador(int token){
		
		
		operadores.push(token);
		
	}
	
	/**
	 * <b>Acción 703</b>
	 * Inserta una constante de cualquier tipo en la tabla de simbolos 
	 */
	public void insertaConstante(String lexema, int gramema){

		int pos = this.tblSimbolos.busqueda(lexema);
		if (pos == -1){
			this.tblSimbolos.setSimbolo(lexema, lexema, gramema);
			this.operandos.push(this.tblSimbolos.busqueda(lexema));
		}
		else{
			this.operandos.push(pos);
		}
		
	}
	
	/**
	 * Acción: 704
	 * Genera el cuadruplo para operadores +, -, *, /
	 * Verifica si es posible la accion
	 * @param token de la operacion a realizar
	 * @return boolean que indica si se pudo realizar o no
	 */
	
	public boolean generaCuadOp(){
		return this.generaCuadOp(false);
	}
	
	public boolean generaCuadOp(boolean inverso){
		
		int op2 = this.operandos.pop();
		int op1 = this.operandos.pop();
		int opr = this.operadores.pop();
				
		//Realizamos la comprobacion de tipos	
		int tipoRes = this.comprobacionTipos(opr, op1, op2);
		
		//Si hay un error
		if (tipoRes == 800){
			javax.swing.JOptionPane.showMessageDialog(null, "Error semantico, tipos de datos incompatibles");
			return false;
		}
		else{
			//Generamos un nombre para la variable temporal
			String nombre = this.tblSimbolos.generaNombre();
		
						
			//Insertamos el nuevo simbolo,
			this.tblSimbolos.setSimbolo(nombre,"", tipoRes);
			//Obtenemos la direccion de la tabla de simbolos
			int direccion = this.tblSimbolos.busqueda(nombre);
			//Insetamos el nuevo cuadruplo
			if (inverso){
				this.tblCuadruplos.insertar(opr,op2,op1,direccion);
			}
			else{
				this.tblCuadruplos.insertar(opr,op1,op2,direccion);
			}
			
			//Insertamos la direccion de la nueva variable temporal a la pila de operandos
			this.operandos.push(direccion);
			
			return true;
		}
		
	}
	

	/**
	 * Accion 709
	 * Generamos el cuadruplo de asignacion
	 */
	public void generaAsignacion(){
		this.generaAsignacion(false);
	}
	
	public void generaAsignacion(boolean inverso){
		
		//Sacamos el operador, que debe ser :=
		operadores.pop();
		//Sacamos el valor que se va a asignar 
		int op1 = this.operandos.pop();
		int op2 = this.operandos.pop();
		//Insertamos el nuevo cuadruplo, el -1 significa vacio
		//TODO: revisar el orden del op1 y op2
		if (inverso){
			tblCuadruplos.insertar(107,-1,op2,op1);
		}
		else{
			tblCuadruplos.insertar(107,-1,op1,op2);
		}
	}
	
	/**
	 * Acción 710
	 * Mete a la pila de saltos la dirección de próximo cuadruplo a generar (Ap+1)
	 */
	public void insertaSalto(){
		
		
		saltos.push(tblCuadruplos.getApuntador()+1);
	}
	
	/**
	 * Acción 711
	 * Genera el cuadruplo de salto en falso, dejando pendiente la dirección a saltar, 
	 * y mete a la pila de saltos el número del cuadruplo actual.
	 */
	public void generaSaltoFalso(){
		
		
		// -2 significa: dirección pendiente
		
		tblCuadruplos.insertar(1000, operandos.pop(), -1, -2);
		saltos.push(tblCuadruplos.getApuntador());
		
	}
	
	/**
	 * Acción 712
	 * genera el cuadruplo de salto obligatorio y rellena la dirección a saltar pendiente en el salto en falso
	 * anterior con Ap+1
	 */
	public void generaSaltoOb(){
		
		
		//Primero rellenamos la dirección pendiente
		this.llenaDirSalto(2);
		
		//Ahora generamos el cuadruplo correspondiente
		this.tblCuadruplos.insertar(2000,-1,-1,this.saltos.pop());
	}
	
	
	/**
	 * Acción 713
	 * Inserta el operador leer a la pila de operadores
	 */
	public void insertaLeer(){
		
		//this.operadores.push(4000);
		
	}
	
	/**
	 * Acción 714
	 * Inserta el operador escribir a la pila de operadores
	 */
	public void insertaEscribir(){
		//this.operadores.push(4000);
	}
	
	/**
	 * Acción 715
	 * Genera el cuadruplo de leer
	 * @param identificador que se va a leer
	 */
	
	public void generaLeer(String identificador){
		
		this.insertaIdPila(identificador);
		this.tblCuadruplos.insertar(4000,this.operandos.pop(), -1,-1);
		
		//Pone un marcador donde deben sustituirlos lexemas
	}
	
	/**
	 * Acción 716
	 * Genera el cuadruplo de escribir, actualmente solo funciona para identificadores :(
	 * @param Identificador que se va a leer
	 */
	public void generaEscribir(String identificador){
		
		this.insertaIdPila(identificador);
		this.tblCuadruplos.insertar(5000,this.operandos.pop(), -1,-1);
		
		//Pone un marcador donde deben sustituirlos lexemas

	}

	/**
	 * <b>Acción 717</b> 
	 * Genera el cuadruplo de salto en falso para el estatuto
	 * repetir con la direccion que se encuentra en el tope de la pila de saltos
	 */
	public void generaSaltoRepetir(){
		
		this.tblCuadruplos.insertar(1000,this.operandos.pop(), -1, this.saltos.pop());
		
		//Pone un marcador donde deben sustituirlos lexemas
	}

	/**
	 * <b>Acción 718</b>
	 * Genera salto en obligatorio dejando la dirección de salto pendiente
	 * además llena la dirección pendiente del salto anterior con Ap+1
	 */	
	public void generaSaltoObligatorio(){
		
		//Primero llenamos la dirección pendiente
		this.llenaDirSalto(2);
		
		//Insertamos el cuadruplo de salto en salto obligatorio
		this.tblCuadruplos.insertar(2000, -1,-1,-2);
		
		//Insertamos la dirección de este salto a la pila de saltos
		this.saltos.push(this.tblCuadruplos.getApuntador());
		
	}
	
	/**
	 * <b>Acción 719</b>
	 * Llenamos la dirección de salto pendiente con Ap+1
	 */
	public void llenaDireccion(){
		
		//Llenamos la dirección pendiente
		this.llenaDirSalto();
	}
	
	/**
	 * Regresa una representacion Object[][] de la tabla de simbolos
	 * @return Object[][]
	 */
	public String[][] getSimbolos(){
	
		return Conversores.formMatriz(this.tblSimbolos.toTable());
		
	}
	
	/**
	 * Regresa una representacion de tipo String[][] de la tabla de cuadruplos
	 * además de que limpía los espacios vacios
	 * @return
	 * String[][]
	 */
	public String[][] getCuadruplos(){
		
		 String[][] tabla2 = tblCuadruplos.toMatriz();
		 int n = tblCuadruplos.getApuntador()+1;
		
		
		for(int i = 0; i<n; i++){
			//Sustituye los operadores
			tabla2[i][0] = Conversores.covOperadores(Integer.parseInt(tabla2[i][0]));
			//Sustituimos los lexemas por los gramemas, o al reves , me confundo
			tabla2[i][1] = convIdentificador(tabla2[i][1]);
			tabla2[i][2] = convIdentificador(tabla2[i][2]);
			tabla2[i][3] = covResultado(i);
		}
		
		
		//Limpiamos los espacios vacios
		for (int i = 0; i<=n; i++){
			for (int j = 0; j < 4; j++){
				tabla2[i][j] = Conversores.limpiaVacios(tabla2[i][j]);
			}
		}
		
		//limpia los ceros
		for (int i = n; i < 50; i++){
			for (int j = 0; j < 4; j++){
				tabla2[i][j] = Conversores.limpiaCeros(tabla2[i][j]);
			}
		}
		
			
		return tabla2;
	}

	//TODO: escribir el javadoc
	public void ejecutar (){
		this.mVirtual = new Pvm (this.tblCuadruplos,this.tblSimbolos);
		mVirtual.ejecutar();
	}
	
	
	
	/**
	 * Nos indica si la operacion es posible, y que tipo de datos deberá tener el resultado
	 * @param opera Operador aritmetico
	 * @param TipoOper1 Tipo del operador 1
	 * @param TipoOper2 Tipo del operador 2
	 * @return El tipo del operador que deberá tener el resultado u 800 si hay un error semantico
	 */
	private int comprobacionTipos(int opera, int TipoOper1, int TipoOper2) 
	{ 
		
		if (opera > 107 && opera < 114){
			return 3;
		}
		
		int col = 0, ren = 0, result = 0, i = 0; // ren es para encontrar el
													// renglon, col para columna

		int matriz[][] = {
			 // op1 op2 +*- / //1.-enteros
				{ 1, 1, 1, 2 }, // 2.-reales
				{ 1, 2, 2, 2 }, // 3.-booleano
				{ 1, 3, 800, 800 }, // 4.-cadena
				{ 1, 4, 800, 2 }, // 5.-caracter
				{ 1, 5, 800, 800 }, // 800.- error
				{ 2, 1, 2, 2 },
				{ 2, 2, 2, 2 }, 
				{ 2, 3, 800, 800 },
				{ 2, 4, 800, 800 }, 
				{ 2, 5, 800, 800 },
				{ 3, 1, 800, 800 },
				{ 3, 2, 800, 800 }, 
				{ 3, 3, 800, 800 }, 
				{ 3, 4, 800, 800 },
				{ 3, 5, 800, 800 }, 
				{ 4, 1, 800, 800 }, 
				{ 4, 2, 800, 800 },
				{ 4, 3, 800, 800 },
				{ 4, 4, 800, 800 }, 
				{ 4, 5, 800, 800 },
				{ 5, 1, 800, 800 }, 
				{ 5, 2, 800, 800 }, 
				{ 5, 3, 800, 800 },
				{ 5, 4, 800, 800 }, 
				{ 5, 5, 800, 800 },

		};
		switch (opera) {
		case 104:// comprobacion de la operacion de divicion /
		{
			col = 3;
			break;
		}
		case 114: // comprobacion para la suma +
		{
			col = 2;
			break;
		}
		case 115: // comprobacion para la resta -
		{
			col = 2;
		}
		case 116: // comprobacion para la multiplicaci�n *
		{
			col = 2;
			break;
		}
		}
		while (i <= 7)// mientras que i sea menor que el numero de columnas
		{

			if ((matriz[i][0] == TipoOper1) && (matriz[i][1] == TipoOper2))
				ren = i;
			i++;
		}
		result = matriz[ren][col];// guarda el valor que hay en la interseccion
		if (result == 800) {
			valor = 0;
			System.out.println("error semantico");
		} else {
			valor = result;
		}
		return valor;
	}

	/**
	 * Convierte la dirección de la tabla de simbolos a el nombre del identificador
	 */
	private String convIdentificador(String dirMemoria){
		
		
		if ( ! dirMemoria.equals("") && !dirMemoria.equals("-1")){
		
			String nombre = null;

			try{
				nombre = tblSimbolos.getSimbolo(Integer.parseInt(dirMemoria)).getNombre();
			}
			catch (NumberFormatException nfe){
				javax.swing.JOptionPane.showMessageDialog(null, "Error, no es posible realizar la conversion deseada\n\n"
						+nfe.toString());
			}
			catch (ArrayIndexOutOfBoundsException indexE){
				javax.swing.JOptionPane.showMessageDialog(null, "El identificador no existe\n\n"
						+indexE.toString());
			}
			return nombre;
		}
		else{
			
			return dirMemoria;
			
		}
		
	}
	
	private String covResultado (int cuad){
		int oper = this.tblCuadruplos.renglon(cuad)[0];
		String res = String.valueOf(this.tblCuadruplos.renglon(cuad)[3]);
		if (oper >= 1000 && oper <= 3000){
			return res;
		}
		else{
			return this.convIdentificador(res);
		}
		
	}
	
	/**
	 * Llenamos la dirección de salto pendiente con Ap+n
	 * @param Número de direcciónes a partir de la dirección actual
	 */
	private void llenaDirSalto(int n){
		if(this.tblCuadruplos.renglon(this.saltos.show())[3] == -2){
			this.tblCuadruplos.renglon(this.saltos.pop())[3] = this.tblCuadruplos.getApuntador()+n;
		}
		else{
			try{
			throw  new Exception("Error, la direccion a saltar es incorrecta") ;
			}
			catch (Exception e){
				javax.swing.JOptionPane.showMessageDialog(null, "Error al lanza la excepcion\n\n"+e.toString());
			}
		}
	}
	
	/**
	 * Llenamos la dirección de salto pendiente con Ap+1
	 */
	private void llenaDirSalto(){
		this.llenaDirSalto(1);
	}
	
}
