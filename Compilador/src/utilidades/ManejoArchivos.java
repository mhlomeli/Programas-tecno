//Esta clase forma parte del paquete utilidades
package utilidades;

//Se necesitan las clases del paquete java.io para poder manejar archivos
import java.io.*;

//Se declara la clase
public class ManejoArchivos {

	//Atributo privado donde se guarda la ruta del archivo que se va a manejar
	private String rutaArchivo;
	
	//Constructor vacio
	public ManejoArchivos() {
		setRutaArchivo(null);
	}
	
	//Constructor donde se especifica la ruta del archivo
	public ManejoArchivos(String rutaArchivo){
		this.setRutaArchivo(rutaArchivo);
	}

	//Metodo para establecer la ruta del archivo
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	
	//Metodo para obtener la ruta del archivo
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	
	//Metodo para escribir una cadena de texto dentro de un archivo
	public void escribir(String texto){
		
		//Necesitamos un objeto de la clase PrintWriter
		PrintWriter out =null;
		
		//intentamos inicializarla
		try {
			//Se realiza asi para ahorrar memoria
			out = new PrintWriter( new BufferedWriter(new FileWriter(this.rutaArchivo)));
		}
		catch (IOException e){
			//si algo falla, entonces lo imprimimos aqui
			e.printStackTrace();
		}
		
		//si el objeto out si fue creado, entonces...
		if (out != null){
			//Se escribe el texto
			out.write(texto);
			//Se lleva del buffer interno del objeto al archivo
			out.flush();
			//Se cierra el archivo
			out.close();
		}
	
	
	}
	
	//Metodo para leer un archivo, nos regresa un objeto StringBuilder con el contenido del archivo
	public StringBuilder leerArchivo(){
		
		//Buffer que contendra los bytes del archivo
		byte [] buffer = new byte[128];	
		//Objeto StringBuilder para ir guardando el contenido segun se lee
		StringBuilder sb = new StringBuilder();
		//Archivo para extraer los bytes del archivo
		FileInputStream fis = null;
		
		try {
			//Se intenta leer el archivo
			fis = new FileInputStream(this.rutaArchivo);
			//si algo falla
		} catch (FileNotFoundException e) {
			//Se imprime el error
			e.printStackTrace();
		}
		
		//Intentamos leer el contenido
		try {
		//variable para apuntar a la poscion que vamos a leer dentro del buffer
		int i = 0;
		//Aqui guardamos el valor ascii del caracter
		int cs = 1;
		//Mientras haya bytes que leer
			while (fis.available() > 0) {
				//Si el contenido es mayor que nuestro array
				if (fis.available() > 128) {
					//entonces se llena hasta la mitad
					fis.read(buffer, 0, 64);
				} else {
					//si no, es llena todo el array
					fis.read(buffer, 0, fis.available());
				}
				//Se regresan las variables a su valor inicial
				cs =1;
				i = 0;
				
				//Mientras el valro ascii leido no sea 0 se continua, esto es porque un valor 0 nos indicaria fin de archivo
				while(cs != 0){
					//Se hace una conversion de byte a int  y se guarda
					cs = (int)buffer[i];
					//Se guarda el caracter
					if (cs != 0){
						sb.append((char)buffer[i]);
					}
				
					i++;
					
				}
			}
			
			
		} catch (IOException e) {
			//Se imprimen los errores atrapados
			e.printStackTrace();
		}
		
		return sb;
		
	}
	
	public String getNombre(){
		return new File(rutaArchivo).getName();
	}
	

}
