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
