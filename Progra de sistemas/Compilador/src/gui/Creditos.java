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

import java.awt.Color;

import javax.swing.*;

public class Creditos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 678282366357619929L;

	public Creditos(){
		super();
		this.init();
	}
	


	private JLabel img;
	private JTextArea texto;
	
	private void init(){
				
		texto = new JTextArea(
				"Este proyecto fue echo por:\n\n"+
				"Hernández Lomelí Miguel Ángel\n"+
				"Ortega Ávila Gustavo Daniel\n"+
				"Páez López Lorenzo Antonio\n"
				);
		
		texto.setEditable(false);
		this.add(texto);
		
		ImageIcon escudo = new ImageIcon(this.getClass().getClassLoader().getResource("gui/escudo.png"));
		
		img = new JLabel(escudo);
		this.add(img);
		this.setBackground(new Color(255,255,255));
	}
	
}


