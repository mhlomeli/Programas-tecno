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

import utilidades.ManejoArchivos;

import java.awt.BorderLayout;
import java.awt.event.*;
//Declaramos la clase, y heredamos la clas JFrame par poder usarla como frame
public class mainGui extends JFrame{

	/**
	 * 
	 */
	//Este campo fue creado automaticamente por eclipse
	private static final long serialVersionUID = 3896209396498338925L;
	
	/*---Declaramos todos los componentes graficos necesarios---*/
	private JTabbedPane tab;
	private JPanel mainPnl;	
	private JMenuBar barraMenu;
	private JMenu menuArchivo;
	private JMenu menuEditar;
	private JMenu menuAyuda;
	//private JMenu menuLexico;
	private JMenu menuTablas;
		
	private JMenuItem menuINuevo;
	private JMenuItem menuIGuardar;
	private JMenuItem menuIGuardarComo;
	private JMenuItem menuISalir;
	
	private JMenuItem menuICortar;
	private JMenuItem menuICopiar;
	private JMenuItem menuIPegar;
	
	private JMenuItem menuIAcercaDe;
	
	private JMenuItem menuTCuadruplos;
	private JMenuItem menuTSimbolos;
	
	private JToolBar barraHer;
	
	private JButton btnNuevo;
	private JButton btnAbrir;
	private JButton btnCorrer;
	private JButton btnSalir;
	private JButton btnGuardar;

	//Objeto para utilizar la ventana para escoger archivos
	private JFileChooser fc;
	
	//Objeto del analizador lexico
	private sintactico.AnalizadorSintactico  sin;
	//objeto de la clase para manejar archivos
	private ManejoArchivos ma;
	
	//constructor de la clase
	public mainGui(){
		//constructor padre, pasamos por parametro el titulo de la ventana
		super("Pascual 3");
		
	  //Se llama a esta misma clase
		this.initComps();
	}
	
	private void initComps (){
		/*
		 * Este metodo inicializa los componentes graficos
		 */
		
		/*Inicializamos los objetos*/
		

		
		tab = new JTabbedPane();
		mainPnl = new JPanel();
		
		/*Partes del menu superior*/
		barraMenu = new JMenuBar();
		menuArchivo = new JMenu("Archivo");
		menuEditar = new JMenu("Editar");
		menuAyuda = new JMenu("Ayuda");
		menuTablas = new JMenu("Tablas");
		//menuLexico = new JMenu("Lexico");
		
		//areaTexto = new JTextArea(25, 60);
		//scroll = new JScrollPane(areaTexto);
		
		menuINuevo = new JMenuItem("Nuevo");
		menuIGuardar = new JMenuItem("Guardar");
		menuIGuardarComo = new JMenuItem("Guardar como");
		menuISalir = new JMenuItem("Salir");
		
		menuICortar = new JMenuItem("Cortar");
		menuICopiar = new JMenuItem("Copiar");
		menuIPegar = new JMenuItem("Pegar");
		
		menuIAcercaDe = new JMenuItem("Acerca de");
		
		menuTCuadruplos = new JMenuItem("Mostrar cuadruplos");
		menuTSimbolos = new JMenuItem("Mostrar Simbolos");
		
		barraHer = new JToolBar();
		ClassLoader clr = this.getClass().getClassLoader();
		
		//Botones de la barra de herramientas con sus respectivos iconos
		ImageIcon icono = new ImageIcon(clr.getResource("gui/Iconos/nuevo.png"));
		btnNuevo = new JButton(icono);
		btnAbrir = new JButton (new ImageIcon(clr.getResource("gui/Iconos/abrir.png")));
		btnCorrer = new JButton(new ImageIcon(clr.getResource("gui/Iconos/correr.png")));
		btnSalir = new JButton(new ImageIcon(clr.getResource("gui/Iconos/parar.png")));
		btnGuardar = new JButton(new ImageIcon(clr.getResource("gui/Iconos/guardar.png")));
		
		fc = new JFileChooser();
		
		ma = new ManejoArchivos();
		
		menuArchivo.add(menuINuevo);
		menuArchivo.addSeparator();
		menuArchivo.add(menuIGuardar);
		menuArchivo.add(menuIGuardarComo);
		menuArchivo.addSeparator();
		menuArchivo.add(menuISalir);
		
		menuEditar.add(menuICortar);
		menuEditar.add(menuICopiar);
		menuEditar.add(menuIPegar);
		
		menuAyuda.add(menuIAcercaDe);
		
		//menuLexico.add(menuLTokens);
		//menuLexico.add(menuLErrores);
		
		menuTablas.add(menuTCuadruplos);
		menuTablas.add(menuTSimbolos);

		barraMenu.add(menuArchivo);
		barraMenu.add(menuEditar);

		
		barraMenu.add(menuTablas);
		barraMenu.add(menuAyuda);
		
		barraHer.add(btnNuevo);
		barraHer.add(btnAbrir);
		barraHer.add(btnCorrer);
		barraHer.add(btnGuardar);
		barraHer.add(btnSalir);		
		
		barraHer.setFloatable(false);
	 
        mainPnl.setLayout(new BorderLayout());
        mainPnl.add("North", barraHer);
      	mainPnl.add("Center", tab);
		
      	//tab.addTab("archivo",scroll);
		
      
		
		//Se agrega una escucha al boton abrir
		btnAbrir.addActionListener(new ActionListener(){
			//si se realiza una accion en el boton...
			public void actionPerformed (ActionEvent e){
				//se llama a este metodo
				abre();
			}
			
		});
		//Se agrega una escucha al boton correr
		btnCorrer.addActionListener(new ActionListener(){
			
			public void actionPerformed (ActionEvent e){
				corre();
			}
			
		});
		//Se agrega una escucha al boton guardar
		btnGuardar.addActionListener(new ActionListener(){
			
			public void actionPerformed (ActionEvent e){
				guardar();
			}
			
		});
		//Se agrega una escucha al boton nuevo
		btnNuevo.addActionListener(new ActionListener(){
			
			public void actionPerformed (ActionEvent e){
				nuevo();
			}
			
		});
		//Se agrega una escucha al boton salir
		btnSalir.addActionListener(new ActionListener(){
			
			public void actionPerformed (ActionEvent e){
				cerrar();
			}
			
		});
		
		this.menuINuevo.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				nuevo();
			}
		});
		
		this.menuIGuardar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				guardar();
			}
		});
		
		this.menuIGuardarComo.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				guardar();
			}
		});
		
		this.menuISalir.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.exit(0);
			}
		});
		
		menuTCuadruplos.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				abreCuadruplos();
			}
		});
		
		menuTSimbolos.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				abreSimbolos();
			}
		});
		
		menuIAcercaDe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tab.addTab("Acerca de", new Creditos());
			}
		});
		this.setJMenuBar(barraMenu);
		this.setContentPane(mainPnl);
		this.confFrame();
	}
	
	//Aqui se incia la ejecucion del programa
	public static void main (String args[]) throws InterruptedException{
		
		
		
		   mainGui app = new mainGui();
	}
	
	//Metodo para abrir un archivo de texto
	private void abre(){
		
		//Si se selecciono algun archivo
		int returnVal = fc.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	
	       //Se inicializa el objeto para manejar archivos
	       ma.setRutaArchivo(fc.getSelectedFile().getAbsolutePath());
	       nuevo(ma.leerArchivo().toString(),fc.getSelectedFile().getName(),fc.getSelectedFile().getAbsolutePath());
	    	
	    }
	}
	
	// metodo para realizar el analisis lexico
	private void corre(){
		
		if (this.tab.getComponentCount() > 0){
			
		
		
		String archivo = ((AreaTexto) tab.getSelectedComponent()).getArchivo();
		//Si no se ha guardado el archivo
		if (archivo == null || archivo.equals("")){
			//Se guarda
			guardar();
		}
		//intentamos correr el analizador lexico
			try {
				sin = new sintactico.AnalizadorSintactico(archivo);
				 if (sin.sintactico()){//Se inician los analisis
					 sin.ejecutar();
					 
				 }
				/*try {
					sin.join();//Esto es para qeu espermos a que el hilo termine para corre otro
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(this,e.toString());//se imprimen los errores capturados
				}*/
			} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Archivo no encontrado"+e.toString());//Se imprime un mensaje si no se encontro el archivo
			}
		}
	}
	
	//metodo para guardar un archivo
	private void guardar(){
		ManejoArchivos ma = null;
		String archivo = ((AreaTexto) tab.getSelectedComponent()).getArchivo();
		//Si hay algun archivo abierto
		if (archivo != null){
			//Se pregunta si se va a sobre escribir
			int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea sobre escribir el archivo: "+archivo+"?");
			
			if (respuesta == JOptionPane.OK_OPTION){
			
				ma = new ManejoArchivos(archivo);
				ma.escribir(((AreaTexto) tab.getSelectedComponent()).getTexto());
			}
		}
		
		//Si el archivo es nuevo, se abre un dialogo para guardarlo, y se escribe alli
		else{
			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				archivo = fc.getSelectedFile().getAbsolutePath();
				 ma = new ManejoArchivos(archivo);
				 ma.escribir(((AreaTexto) tab.getSelectedComponent()).getTexto());


			}
		}
		
	}
	
	//Metodo para abrir un nuevo archivo
	private void nuevo(){
		tab.addTab("Archivo",new AreaTexto());
	}
	
	private void nuevo (String texto, String nombre, String ruta){
		tab.addTab(nombre,new AreaTexto(texto,ruta));
	}
	
	private void abreCuadruplos(){
		//JFrame frm = new JFrame("Cuadruplos");
		Tabla cuad = new Tabla(sin.getCuadruplos(),Tabla.CUADRUPLOS);
		tab.addTab("Cuadruplos", cuad);
	}
	
	private void abreSimbolos(){
		//JFrame frm2 = new JFrame("Simbolos");
		Tabla cuad2 = new Tabla(sin.getSimbolos(),Tabla.SIMBOLOS);
		tab.addTab("Simbolos",cuad2);
	}
	
	private void cerrar(){	
		if (tab.getComponentCount() > 0){
		tab.remove(tab.getSelectedComponent());
		}
		else{
			System.exit(0);
		}
	}
	
	private void confFrame(){
		
		//Accion al cerrar el frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Medidas de la ventana
		this.setSize(800,600);
		//No es posible redimencionarla
		this.setResizable(false);		
		
		//Se inicia la ventana
		this.setVisible(true);
	}
	
}
