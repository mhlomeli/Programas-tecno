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

public class TablaSimbolos {

	private Simbolo[] simbolos;
	private int apu;
	//Numero de variables temporables, util para generar nombres 
	private int nTemp = -1;
	
	public TablaSimbolos() {
		apu = 0;
		simbolos = new Simbolo[100];
	}

	public boolean setSimbolo(String nombre, String valor, int tipo) {

		if (this.busqueda(nombre)== -1){
			simbolos[apu] = new Simbolo(nombre, valor, tipo);
			apu++;
			return true;
		}
		else{
			
			return false;
		}
	}

	public boolean setSimbolo(String nombre, String valor, int tipo, int posicion) {

		if (this.busqueda(nombre) == -1){
		simbolos[posicion] = new Simbolo(nombre, valor, tipo);
		return true;
		}
		
		else{
			return false;
		}
	}

	public Simbolo getSimbolo() {

		return simbolos[apu - 1];

	}

	public Simbolo getSimbolo(int posicion) {

		return simbolos[posicion];

	}

	public int busqueda(String Nombre) {

		for (int i = 0; i < apu; i++) {

			if ((simbolos[i].getNombre()).equals(Nombre)) {
				return i;

			}

		}
		return -1;
	}

	public boolean modifica(String nombre, String nuevoNombre,
			String nuevoValor, int nuevoTipo) {

		int pos = busqueda(nombre);

		if (pos != -1) {
			setSimbolo(nuevoNombre, nuevoValor, nuevoTipo, pos);
			return true;
		} else {
			return false;
		}
	}

	
	protected String generaNombre(){
		nTemp++;
		return "T"+nTemp;
	}
	
	public String [][] toTable(){
		String [][] matriz = new String[apu][3];
		
		for (int i = 0; i < apu; i++){
			matriz[i] = this.simbolos[i].toArray();
		}
		
		return matriz;
	}
	
}
