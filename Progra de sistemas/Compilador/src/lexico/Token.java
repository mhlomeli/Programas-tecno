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
//Esta clase pertenece al paquete lexico
package lexico;
//Clase token
public final class Token {

	//En esta variable guardaremos el lexema
	private String lexema;
	//en esta variable guardaremos el gramema
	private int gramema;
	

	//Constructor con parametros
	public Token(String lexema, int gramema){
		//Guardamos el lexema y gramema recividos
		this.lexema = lexema;
		this.gramema = gramema;
	}
	
	
	//Metodo para obtener el Lexema
	public String GetLexema(){
		return lexema;
	}
		
	//Metodo para obtener el gramema
	public int GetGramema(){
		return gramema;
	}
}
