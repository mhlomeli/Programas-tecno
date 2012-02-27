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

import javax.swing.*;

public class AreaTexto extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6500163860868778014L;
	
	public AreaTexto(){
		super();
		init("");
	}
	
	public AreaTexto(String texto){
		super();
		init(texto);
	}
	
	public AreaTexto(String texto,String archivo){
		super();
		init(texto);
		this.archivo = archivo;
	}
	
	//Metodo para inicializar los componentes
	private void init(String texto){
		area = new JTextArea(35, 72);
		//area = new JTextArea(50,120);
		scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll);
		this.area.setText(texto);
	}
	
	
	//Metodos para las acciones
	protected void limpiar(){
		area.setText("");
	}
	
	protected void setTexto(String texto){
		area.setText(texto);
	}
	
	protected String getTexto(){
		
		return area.getText();
	}
	
	protected void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	protected String getArchivo() {
		return archivo;
	}
	
	protected void esEditable(boolean b){
		area.setEditable(b);
	}

	protected void concatenar(String texto){
		area.setText(area.getText()+texto);
	}
	
	//Declaraciones de los componentes
	
	//Area de texto
	private JTextArea area;
	//Scroll
	private JScrollPane scroll;
	//Nombre del archivo que se esta editando
	private String archivo; 
	
}
