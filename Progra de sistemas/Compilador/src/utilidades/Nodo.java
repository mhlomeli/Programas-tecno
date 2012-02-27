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
package utilidades;

public class Nodo <type>{
	
	private type elemento;
	
	private Nodo<type> siguiente;
	
	public Nodo(){
		
	}
	
	public Nodo (type e){
		
		this.elemento = e;
	}

	public void setElemento(type elemento) {
		this.elemento = elemento;
	}

	public type getElemento() {
		return elemento;
	}

	public void setSiguiente(Nodo<type> siguiente) {
		this.siguiente = siguiente;
	}

	public Nodo<type> getSiguiente() {
		return siguiente;
	}
}
