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
