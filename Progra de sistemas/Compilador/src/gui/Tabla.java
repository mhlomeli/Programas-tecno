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
package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabla extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4747357985795923333L;
	public static final int SIMBOLOS = 0;
	public static final int CUADRUPLOS = 1;
	
	private Object[][] datos;
	private JTable tabla;
	private JScrollPane panel;

	private int tipo;
	
	public Tabla(Object [][]datos,int tipo) {
		this.tipo = tipo;
		this.datos = datos;
		init();
	}

	private void init(){
	
		if (tipo == 0){
			
			String[] columnas = {"Nombre","Valor","Tipo"};
			tabla = new JTable(datos,columnas);
		}
		else if (tipo == 1){
			String[] columnas = {"Operador","Operando1","Operando2","Resultado"};
			tabla = new JTable(datos,columnas);
		}
		else{
		
			tabla = new JTable();
		}
		
		panel = new JScrollPane(tabla);
		this.add(panel);
	}
		
}
