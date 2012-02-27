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
package sintactico;


public class TablaCuadruplos {

	private int matriz[][] = null;
	private int apuntador = -1;
	
	public TablaCuadruplos() {

		matriz = new int[50][4];
	
	}

	public void insertaCuadruplo(int operando, int operador1, int operador2, int resultado){
		apuntador++;
		matriz[apuntador][0] = operando;
		matriz[apuntador][1] = operador1;
		matriz[apuntador][2] = operador2;
		matriz[apuntador][3] = resultado;
	
	}
	
	public void insertaCuadruplo(int operando, int operador1, int resultado){
		
		apuntador++;
		matriz[0][apuntador] = operando;
		matriz[1][apuntador] = operador1;
		matriz[2][apuntador] = resultado;
	
	}
	
	public int[] renglon(int posicion){
		
		return matriz[posicion];
	}
	
	public boolean vacia(){
		
		return apuntador == -1;
	}
	
	public boolean llena(){
	
		return apuntador == 49;
	}
	
	public int[][] toMatriz(){
		
		int matrizCopia [][] = null;
	
		matrizCopia = matriz;
		
		return matrizCopia;
	}
	
}
