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

//Esta clase forma parte del paquete ejecucion
package ejecucion;
//Importamos las clases necesarias
import semantico.TablaCuadruplos;
import semantico.TablaSimbolos;
import java.io.*;


/**
 * Clase Pvm - Pascual virtual machine
 * Clase que sirve para ejecutar un programa fuente, en forma de cuadruplos
 */
public class Pvm{

	/**
	 * Constructor de la clase
	 * @param cuadruplo.- Recivimos la tabla de cuadruplos a ejecutar
	 * @param simbolos.- Recivimos la tabla de simbolos, con los simbolos del programa
	 */
	public Pvm (TablaCuadruplos cuadruplos, TablaSimbolos simbolos){
		
		//Guardamos los parametros en atributos locales
		this.cuadruplos = cuadruplos;
		this.simbolos = simbolos;
		
	}
	
	
	/**
	 * Metodo ejecutar.
	 * El método ejecutar sirve para ejecutar cada uno de los cuadruplos en la tabla de cuadruplos
	 */
	public void ejecutar (){
		//Ciclo principal, recorre toda la tabla de cuadruplos
		for (int i = 0; i <= cuadruplos.getApuntador();i++){
			
			//Si el cuadruplo es un operador aritmetico o logico
			if ((( cuadruplos.renglon(i)[0] > 106 && 
					cuadruplos.renglon(i)[0] <= 116) && 
					cuadruplos.renglon(i)[0] != 107)||
					cuadruplos.renglon(i)[0] == 104)
			{
				//Se realiza la operación, y se asigna el resultado.
				this.asignar(cuadruplos.renglon(i)[3],this.operacion(i));
			}
			
			//Si no es una operación de cualquier tipo
			else{
			
				//Entonces vemos que tipo de cuadruplo es
				switch (cuadruplos.renglon(i)[0]){

				//Asignacion
				case 107:
				{
					this.asignar(i); // i = numero de cuadruplo en la tabla
				}break;

				//Escribir
				case 5000:
				{
					this.escribir(i); // i = numero de cuadruplo en la tabla
				}break;

				//Leer
				case 4000:
				{
					//Asignamos lo que nos regrese el método leer, en el simbolo indicado
					this.asignar(cuadruplos.renglon(i)[1], this.leer());
				}break;
				
				//Salto en falso
				case 1000:{		
				 i = this.saltoCondicional(i, false); // ponemos a i en la direccion del salto
				}break;
				
				//Slato obligatorio
				case 2000:{
					i = this.saltoObligatorio(i);// ponemos a i en la direccion del salto
				}break;
				
				//Salto en verdadero
				case 3000:{
					i = this.saltoCondicional(i, true);// ponemos a i en la direccion del salto
				}break;
				}
			}
		}
		
	}
	
	/**
	 * Metodo escribir
	 * @param cuadruplo .-Recivimos la direccion del cuadruplo
	 */
	private void escribir(int cuadruplo){
		
		//Guardamos la direccion del simbolo que se escribira
		int dirId = this.cuadruplos.renglon(cuadruplo)[1];
		//Guardamos el valor de dicho identificador
		String texto = this.simbolos.getSimbolo(dirId).getValor();
		//Imprimimos el texto
		System.out.println(texto);
	}
	
	/**
	 * Metodo leer
	 * @return regresa un String con el valor leido
	 */
	private String leer(){
		
		//Variable que contendra el texto leido
		String texto = "";
		
		//Intentamos leer
		try {
			texto = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			// Hay erro, lo imprimimos
			e.printStackTrace();
		}
		
		return texto;
	}
	
	/**
	 * Método asignar
	 * @param cuadruplo.- direccion del cuadruplo actual
	 */
	private void asignar (int cuadruplo){
		
		//Obtenemos los identificadores que vamos a usar
		int idA = this.cuadruplos.renglon(cuadruplo)[2];
		int idB = this.cuadruplos.renglon(cuadruplo)[3];
		
		//Guardamos el valor del primer identificador
		String valorA = this.simbolos.getSimbolo(idA).getValor();
		//Lo asignamos al segundo identificador
		this.simbolos.getSimbolo(idB).setValor(valorA);
	}
	
	/**
	 * Metodo asignar
	 * @param posicion.- Posicion del cuadruplo actual
	 * @param valor .- Valor a asignar
	 */
	private void asignar (int posicion, String valor){
		
		//ASignamos directamente el valor en el identificador dado
		this.simbolos.getSimbolo(posicion).setValor(valor);
		
	}
	
	/**
	 * Método Operacion
	 * @param cuadruplo Direccion de cuadruplo actual
	 * @return regresa el resultado de la operacion
	 */
	private String operacion (int cuadruplo){
			
			//Aqui se guardara el resultado de a operacion
			String resultado = null;
			
			//Extraemos los valores de los operandos, y la operacion
			int opr = this.cuadruplos.renglon(cuadruplo)[0];
			int op1 = this.cuadruplos.renglon(cuadruplo)[1];
			int op2 = this.cuadruplos.renglon(cuadruplo)[2];
			
			op1 = Integer.parseInt(this.simbolos.getSimbolo(op1).getValor());			
			op2 = Integer.parseInt( this.simbolos.getSimbolo(op2).getValor());
			
			switch (opr){
			case 114:{//Si es una suma
				resultado = String.valueOf(op1+op2);
				break;
			}
			case 115:{//Si es una resta
				resultado = String.valueOf(op1-op2);
				break;
			}
			case 116:{//Si es una multiplicacion
				resultado = String.valueOf(op1*op2);
				break;
			}
			case 104:{//Si es una división
				resultado = String.valueOf(op1/op2);
				break;
			}
			case 110:{//Si es una suma
				resultado = String.valueOf(op1 == op2);
				break;
			}
			case 113:{//Si es una resta
				resultado = String.valueOf(op1 < op2);
				break;
			}
			case 109:{//Si es una multiplicacion
				resultado = String.valueOf(op1 > op2);
				break;
			}
			case 112:{//Si es una división
				resultado = String.valueOf(op1 <= op2);
				break;
			}
			case 108:{//Si es una resta
				resultado = String.valueOf(op1 >= op2);
				break;
			}
			case 111:{//Si es una multiplicacion
				resultado = String.valueOf(op1 != op2);
				break;
			}
			
			
			}
			
		return resultado;
	}
	
	/**
	 * Método saltoObligatoria
	 * @param dirCuad Direccion del cuadruplo actual
	 * @return regresa la direccion a saltar
	 */
	private int saltoObligatorio(int dirCuad){
		//Regresa la nueva posicion a saltar, es -1 porque esta direccion se aumentara
		// al llegar al for
		return this.cuadruplos.renglon(dirCuad)[3] -1;
				
	}
	
	/**
	 * Metodo salto condicional
	 * Sirve para dar un salto en verdadero o en falso
	 * 
	 * @param dirCuad direccion actual del cuadruplo
	 * @param condicion Si el salto es en verdadero o en falso
	 * @return regresa la direccion a saltar
	 */
	private int saltoCondicional(int dirCuad, boolean condicion){
		
		//Obtenemos el valor del identificador a evaluar
		boolean salta = Boolean.valueOf(this.simbolos.getSimbolo(this.cuadruplos.renglon(dirCuad)[1]).getValor());
		
		//Si la condicion dada se cumple
		if (salta == condicion){
			//Regresa la nueva posicion a saltar, es -1 porque esta direccion se aumentara
			// al llegar al for
			return this.cuadruplos.renglon(dirCuad)[3] -1;
		//Si no
		}else{
			//Regresa la direccion del cuadruplo actual
			return dirCuad;
		}
	}
	
	/**
	 * Atributo local donde se guarda la tabla de cuadruplos
	 */
	private TablaCuadruplos cuadruplos;
	/**
	 * Atributo local donde se guarda la tabla de simbolos
	 */
	private TablaSimbolos simbolos;
	
}
