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

public class Cola <type>{

	private Nodo<type> cabeza;
	private Nodo<type> ultimo;
	private int nElementos;
	private boolean estaLlena = false;
	
	public Cola() {
		cabeza = null;
		ultimo = null;
		nElementos = 0;
	
	}

	public void push(type elemento){
		
		Nodo<type> nuevo = new Nodo<type>(elemento);
		
		estaLlena = (nuevo == null);
					
		if (cabeza == null){
			cabeza = nuevo;
		}
		
		else{
			ultimo.setSiguiente(nuevo);
		}
		ultimo = nuevo;
	    ++nElementos;

	}
	
	public type pop(){
	
	type aux = cabeza.getElemento();
	cabeza = cabeza.getSiguiente();
    --nElementos;
    
    return aux;
	}
	
	public type show(){
		return cabeza.getElemento();
	}
	
	public boolean vacia(){
		
		return nElementos == 0;
	}
	
	public int getNElementos(){
		
		return nElementos;
	}
	
	public boolean llena(){
	
		return estaLlena;
	}
	
}
