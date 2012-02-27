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


