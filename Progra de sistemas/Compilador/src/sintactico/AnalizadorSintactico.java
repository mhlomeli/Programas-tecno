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
package sintactico;//Esta clase pertenece al paquete sintactico

//Importamos las clases necesarias
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import lexico.AnalizadorLexico;
import utilidades.Pila;
import semantico.AnalizadorSemantico;

public class AnalizadorSintactico extends Thread{

	
	//Matriz sintactica
	private static final int matriz[][] = {
			
	   //0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43  44  45  46  47  48  49  50 		
/*00*/	{0,  0,  300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*01*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,1,  300,300,300,300},
/*02*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,2,  300,300,300,300},
/*03*/	{300,3,  300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*04*/	{300,5,  300,300,300,300,300,300,300,300,300,4,  300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*05*/	{300,6,  300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*06*/	{300,300,300,8,  7,  300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*07*/	{300,300,300,300,300,300,300,300,10, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,9,  9,  9,  9,  9,  300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,20 },
/*08*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,11, 12, 13, 14, 15, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*09*/	{300,300,300,300,300,300,300,300,300,300,300,16, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*10*/	{300,17 ,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,17, 17, 300,300,300,25, 300,17, 17, 17, 17, 17,300,300,300,300,300},
/*11*/	{300,19, 300,300,300,300,300,300,19, 300,300,300,18, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,19, 19, 300,18, 18, 300,300,19, 19, 19, 19, 19,300,300,300,300,300},
/*12*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*13*/	{300,300,300,300,300,300,21, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,21, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*14*/	{300,300,300,22, 300,300,300,300,300,300,23, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*15*/	{300,24, 300,300,300,300,300,300,24, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,24, 30, 300,300,24 ,25, 300,24, 27, 26, 28, 29, 300,300,300,300,300},
/*16*/	{300,31, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*17*/	{300,32, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*18*/	{300,34, 300,33, 33, 33, 300,300,33, 300,33, 300,33, 33 ,33 ,33, 33, 33, 33, 33, 33, 33, 33, 33, 33 ,300,300,300,300,300,300,300,300,300,300,300,300,300,300,33, 33, 33, 300,300,300,300,300,300,300,33, 300},
/*19*/	{300,35, 300,300,300,300,300,300,300,300,300,35, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,35, 35, 35, 35, 35, 300,35, 300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*20*/	{300,300,300,37, 300,300,300,300,300,300,36, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*21*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,38, 300,300,300,300,300,300,300,300},
/*22*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,39, 300,300,300,300,300,300,300,300,300,300,300},
/*23*/	{300,40, 300,300,40, 300,300,300,300,300,300,300,300,300,300,300,300,40, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*24*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,41, 300,300,300,300,300,300,300},
/*25*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,42, 300,300,300,300,300,300},
/*26*/	{300,43, 300,300,300,300,300,300,43, 300, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*27*/	{300,300,300,45, 300,300,300,300,44, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*28*/	{300,300,300,300,300,300,300,46, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,46, 300,300,300,300,300},
/*29*/	{300,47, 300,300,300,300,300,300,300,47, 300,47, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,47, 47, 47, 47, 47, 300,47, 300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*30*/	{300,300,300,48, 300,300,300,300,49, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*31*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,50, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*32*/	{300,300,300,300,300,300,300,300,300,300,300,51, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,51, 300,300,300,300,300,300,300,300,300,52, 300,300,300},
/*33*/	{300,53, 300,300,300,300,300,300,300,300,300,53, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,53, 53, 53, 53, 53, 300,53, 300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*34*/	{300,300,300,55, 300,55, 300,300,55, 300,55, 300,55, 300,300,55, 55, 54, 54, 54, 54, 54, 54, 54, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,55, 55, 55, 300,300,300,300,300,300,300,300,300},
/*35*/	{300,56, 300,300,300,300,300,300,300,300,300,56, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,56, 56, 56, 56, 56, 300,56, 300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*36*/	{300,300,300,57, 300,57, 300,300,57, 300,57, 300,300,58, 59, 57, 57, 57, 57, 57, 57, 57, 57, 57, 60, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,57, 57, 57, 300,300,300,300,300,300,300,300,300},
/*37*/	{300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,61, 62, 63, 64, 65, 66, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*38*/	{300,67, 300,300,300,300,300,300,300,300,300,67, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,67, 67, 67, 67, 67, 300,67, 300,300,300,300,300,300,300,300,300,300,300,300,300,300},
/*39*/	{300,300,300,68, 300,68, 300,300,68, 300,68, 300,300,68 ,68 ,69, 70, 68, 68, 68, 68, 68, 68, 71, 68 ,300,300,300,300,300,300,300,300,300,300,300,300,300,300,68, 68, 68, 300,300,300,300,300,300,300,300,300},
/*40*/	{300,73, 300,300,300,300,300,300,300,300,300,72, 300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,300,76, 77, 78, 74, 75, 300,79, 300,300,300,300,300,300,300,300,300,300,300,300,300,300}
	};
	
	/*
	 * Esta la tabla de producciones
	 * en cada renglon se encuentra una produccion
	 */
	
	private static final int producciones[][] = {
	/*00*/{130,102,129,1,127},
	/*01*/{2,9},
	/*02*/{144,3},
	/*03*/{5,106,7,700,129,4},
	/*04*/{ },
	/*05*/{3},
	/*06*/{102,723,6},
	/*07*/{ },
	/*08*/{126,5},
	/*09*/{8},
	/*10*/{12},
	/*11*/{136},
	/*12*/{149},
	/*13*/{137},
	/*14*/{143},
	/*15*/{151},
	/*16*/{120,10,121},
	/*17*/{15,129,11},
	/*18*/{ },
	/*19*/{10},
	/*20*/{135, 124, 13, 125, 148, 8}, //{135,13,148,8},
	/*21*/{100,128,100,14},
	/*22*/{126,13},
	/*23*/{ },
	/*24*/{16},
	/*25*/{22},
	/*26*/{24},
	/*27*/{21},
	/*28*/{25},
	/*29*/{28},
	/*30*/{31},
	/*31*/{17,107,708,33, 709},
	/*32*/{102,701,18},
	/*33*/{ },
	/*34*/{19},
	/*35*/{33,20},
	/*36*/{ },
	/*37*/{126,19},
	/*38*/{141,710,10,132,33,717},
	/*39*/{142,23,133,120,10,121,722},
	/*40*/{102,720,107,702,33,709,142,33,721},
	/*41*/{147,710,33,711,133,120,10,121,712},
	/*42*/{134,713,122,26,123},
	/*43*/{17,715,27},
	/*44*/{ },
	/*45*/{126,26},
	/*46*/{146,714,122,29,123},
	/*47*/{33,716,30},
	/*48*/{126,29},
	/*49*/{ },
	/*50*/{138,33,140,711,120,10,121,32,131,719},
	/*51*/{145,718,120,10,121},
	/*52*/{ },
	/*53*/{35,34},
	/*54*/{37,702,35,704},
	/*55*/{ },
	/*56*/{38,36},
	/*57*/{ },
	/*58*/{114,702,35,704},
	/*59*/{115,702,35,704},
	/*60*/{119},
	/*61*/{110},
	/*62*/{113},
	/*63*/{109},
	/*64*/{112},
	/*65*/{108},
	/*66*/{111},
	/*67*/{40,39},
	/*68*/{ },
	/*69*/{116,702,38,704},
	/*70*/{104,702,38,704},
	/*71*/{117,38},
	/*72*/{120,33,121},
	/*73*/{17},
	/*74*/{139, 703},
	/*75*/{150, 703},
	/*76*/{100, 703},
	/*77*/{101, 703},
	/*78*/{103, 703},
	/*79*/{108,17}
	};
	
	//Creamos un objeto de tipo pila que use objetos de la clase Integer
	private Pila<Integer> pila;
	

	//Creamos un objeto de la clase AnalizadorLexico
	private AnalizadorLexico lex;
	
	private AnalizadorSemantico semantico;
	
	//Aqui se guarda el nombre del archivo
	private String archivo;
	//Metodo para insertar una produccion a la pila predictiva
	private void inserta(int produccion){
		//Sacamos el ultimo elemento del tope de la pila
		pila.pop();
		//Obtenemos el tamaño de la produccion
		int n = producciones[produccion].length - 1;
		//Por cada elemento que se encuentre el la produccion
		for (int i = n; i >= 0; i--){
			//Lo metemos a la pila, en orden inverso
			pila.push(producciones[produccion][i]);
			
		}
		
	}
	
	//metodo para buscar una columna
	private int buscaColumna(int token){
		
		//Aqui estan los tokens en orden como aparecen en la tabla sintactica
		int columnas[] = {
				130,102,127,126,106,129,128,
				122,123,124,125,120,121,114,
				115,116,104,110,113,109,112,
				108,111,117,119,136,149,137,
				143,151,100,101,103,139,150,
				138,118,145,132,142,133,140,
				141,147,134,146,144,131,152,107,135};
		
		//Recorremos el arreglo
		for (int i = 0; i< 51; i++){
			//Cuando encontremos el que buscamos
			if (columnas[i] == token){
				return i;//Regresamos el numero de columna
			}
			
		}
		//Si no encontramos nada, entonces hay un error
		return -1;
		
	}
	//Metodo que realiza el analisis sintactico
	public boolean sintactico ()throws Exception{
				
		//inicializamos la pila predictiva 
		pila = new Pila<Integer>();
		//Metemos el token correspondiente a fin de archivo
		pila.push(152);
		//metemos la produccion 0
		pila.push(0);
		//intentamos...
		try{
			//Realizar el analisis lexico
		lex = new AnalizadorLexico(archivo);
		}
		//Si no encontramos el archivo a leer...
		catch (FileNotFoundException e){
			//Lanzamos una excepcion
			throw e;
		}
		//Si el metodo lexico del objeto lex nos regresa un valor false
		if (!lex.lexico()){
		//Lanzamos un cuadro de dialogo para indicar al usuario que hay un error lexico
			JOptionPane.showMessageDialog(null, "Error lexico");
			return false;
		}
			
		//Obtenemos el primer token
		int token = lex.GetToken().GetGramema();
		
		String contador = null;
		
		String lexema = "";
		String ultimoLex ="";
		int ultimoGra = 0;
		//Iniciamos en la produccion 0
		int produccion = 0;
		
		boolean resultado = false;
		
		//Mientras haya tokens que leer
 		while (this.lex.HayTokens()){
			
			//Si llegamos a un 300, eso significa error sintactico y detenemos todo
			if (produccion == 300){
				javax.swing.JOptionPane.showMessageDialog(null, "Error sintactico");
				resultado = false;
				break;
			}
			
			//Si el tope de la  pila y el token son 152 eso significa que hemos llegado al fin de archivo
			if ((pila.show() == 152) && (token == 152)){
			
				//Avisamos al usuario que el programa es correcto sintacticamente
				javax.swing.JOptionPane.showMessageDialog(null, "No hay errores sintacticos");
				resultado = true;
				break;
			}
			
			//Si en el tope de la pila hay una accion
			if (pila.show() >= 700){
				
				int accion = pila.pop();
				
				switch (accion){
				case 723: {
					semantico.insertaIdLista(ultimoLex);
				} break;
				
				case 700: {
					if (!semantico.insertaId(ultimoGra)){
						javax.swing.JOptionPane.showMessageDialog(null, "Error semantico");
					}
				}
				break;
				
				case 701: {
					semantico.insertaIdPila(ultimoLex);
				}break;
				
				case 702: {
					semantico.insertaOperador(ultimoGra);
				}break;
				
				case 703: {
					semantico.insertaConstante(ultimoLex, ultimoGra);
				}break;
				
				case 704: {
					semantico.generaCuadOp();
				}break;
				
				case 708: {
					semantico.insertaOperador(ultimoGra);
				}break;
				
				case 709: {
						semantico.generaAsignacion();
				}break;
				
				case 710: {
					semantico.insertaSalto();
				}break;
				
				case 711: {
					semantico.generaSaltoFalso();
				}break;
				
				case 712: {
					semantico.generaSaltoOb();
				}break;
				
				case 713: {
					semantico.insertaLeer();
				}break;
				
				case 714: {
					semantico.insertaEscribir();
				}break;
				
				case 715: {
					semantico.generaLeer(ultimoLex);
				}break;
				
				case 716: {
					semantico.generaEscribir(ultimoLex);
				}break;
				
				case 717: {
					semantico.generaSaltoRepetir();
				}break;
				case 718: {
					semantico.generaSaltoObligatorio();
				}break;
				
				case 719: {
					semantico.llenaDireccion();
				}break;
				
				case 720:{
					contador = ultimoLex;
					semantico.insertaIdPila(contador);
					
				}break;
				
				case 721: {
					//710
					semantico.insertaSalto();
					//702
					semantico.insertaOperador(113);
					//701
					semantico.insertaIdPila(contador);					
					//704					
					semantico.generaCuadOp(true);
					//711
					semantico.generaSaltoFalso();
					
				}break;
				
				case 722: {
					//703
					semantico.insertaOperador(114);
					//703
					semantico.insertaConstante("1", 1);
					//704
					semantico.generaCuadOp();
					//702
					semantico.insertaOperador(107);
					semantico.insertaIdPila(contador);
					semantico.generaAsignacion(true);
					semantico.generaSaltoOb();
				}break;
				}
			}
						
			else {
				
				//Si en el tope de la pila esta un Terminal
				if (pila.show() > 99){
					
					
					//Si el token es diferente al de la pila, hay error
					if (token != pila.show()){
						javax.swing.JOptionPane.showMessageDialog(null, "Error sintactico");
						resultado = false;
						break;
					}
					
					//Si son iguales, los sacamos
					else{
						//lexema
						ultimoGra = token;
						ultimoLex = lexema;
						lexema = lex.showToken().GetLexema();	
						token = lex.GetToken().GetGramema();
						pila.pop();
					}
					
				}
				
				//Si es un no terminal
				else{
					
					if (pila.show() < 100){ 
						//Buscamos la produccion en la tabla de sintaxis
						produccion = matriz[pila.show()][buscaColumna(token)];
						//Insertamos la produccion obtenida
						inserta(produccion);
						
					}
				}
				
			}
			
		}
		//Si pila y token son = 152 eso significa fin de archivo
		if (token == 152){
		
			//Hay que cambiar esto
			javax.swing.JOptionPane.showMessageDialog(null, "No hay errores sintacticos");
			resultado = true;
		}
		return resultado;
	}
	
	public AnalizadorSintactico(String archivo){
		
		this.archivo = archivo;
		semantico = new AnalizadorSemantico();
	}
	
	public void run (){
		//intenta correr el metodo lexico
		try {
			sintactico();
		}catch (FileNotFoundException fnfe){
			
			//Si ocurre algun error este se captura aqui, y se imprime en una ventana
			javax.swing.JOptionPane.showMessageDialog(null, "Archivo no encontrado");
			
		}catch (Exception e) {
			//Si ocurre algun error este se captura aqui, y se imprime en una ventana
			javax.swing.JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public String[][] getCuadruplos(){
		return semantico.getCuadruplos();
	}
	public Object[][] getSimbolos(){
		return semantico.getSimbolos();
	}
	
	public void ejecutar(){
		this.semantico.ejecutar();
	}
	
}//Class

