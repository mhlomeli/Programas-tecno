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

public class Simbolo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -915926646113878029L;
	private String nombre;
	private String valor;
	private int tipo;
	
	public Simbolo (String nombre, String valor, int tipo){
		
		this.setNombre(nombre);
		this.setValor(valor);
		this.setTipo(tipo);
	
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
	
	public void setTipo(int tipo){
		
		this.tipo = tipo;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public String getValor(){
		return valor;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public String[] toArray(){
		String array [] = {nombre, valor, String.valueOf(tipo)};
		return  array;
	}
}
