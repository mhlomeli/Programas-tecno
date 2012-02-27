//Indicamos que el analizador se encuentra en el paquete lexico
package lexico;
/**
 * Esta clase sirve para realizar el analisis sintactico
 */

//necesitamos algunas clases de el paquete java.io, por eso los importamos
import java.io.*;

//necesitamos tambien la clase ManejoArchivos del paquete utilidades
import utilidades.ManejoArchivos;

//Importamos la clase Cola
import utilidades.Cola;

//Declaramos la clase, y heredamos la clase Thread, para que esta pueda se ejecute como un hilo independiente
public class AnalizadorLexico extends Thread{

	/*Los atributos de esta clase, algunos metodos son todos privados, esto es porque solo son necesarios para esta clase */
	
	//Declarmos un objeto StringBuilder, usaremos esta clase siempre que sea posible, ya que es mas eficiente que la clase String
	//ademas de que tiene metodos que nos pueden ayudar
	private StringBuilder in;
	
	//Esta es una bandera que nos indica cuando se llegado al final del archivo
	private boolean finArchivo = false;
	
	//Aqui guardaremos el nombre del archivo el cual estamos analizando
	private String archivo;
	
	//Esta variable nos sirve para apuntar al caracter dentro del StringBuilder 
	private int index = 0;
	
	//En este objeto guardaremos los tokens detectados durante el analisis
	private StringBuilder tokens;
	
	//Esta es una bandera para indicar si debemos saltar el paso de leer un caracter
	private boolean haySalto = false;
	
	//Este objeto es para guardar los tokens
	private Cola<Token> cola  = new Cola<Token>();

	//Este array contiene todas las palabras reservadas del lenguaje, es final ya que sus valores no van a cambiar
	private final String palabrasR[] = {
			"programa", "fin_si", "hasta", "hacer", "leer",
			"arreglo", "entero", "cadena", "si", "verdadero",
			"entonces","repetir", "para","caracter","variables",
			"sino","escribir","mientras","de","real",
			"falso","booleano"
		};
	
	//Esta es la matriz de transiciones
	private static final int matriz [][] ={
					// L   D    ,   .   _   '   $   /   *   :    ;  =   >   <   +   -   {   }   [   ]   (   )  \n  \t  \b
				/*00*/{4,  1,  126,15, 201,6,  201,8,  116,11, 129,110,16, 17, 114,115,120,121,124,125,122,123,0,  0,  0  },
				/*01*/{100,1,  100,2,  100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100},
				/*02*/{100,3,  200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200,200},
				/*03*/{101,3,  101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101},
				/*04*/{4,  4,  102,102,5,  102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102},
				/*05*/{4,  4,  202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202,202},
				/*06*/{6,  6,  6,  6,  6,  7,  203,6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6  },
				/*07*/{103,103,103,103,103,6,  103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103},
				/*08*/{104,104,104,104,104,104,104,9,  10, 104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,104},
				/*09*/{104,104,104,104,104,104,104,104,10, 104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,104}, 
				/*10*/{11, 11, 11, 11, 10, 11, 205,11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11 }, 
				/*11*/{106,106,106,106,106,106,205,106,106,106,106,107,106,106,106,106,106,106,106,106,106,106,106,106,106}, 
				/*12*/{12, 12, 12, 12, 12, 12, 205,105,13, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 }, 
				/*13*/{11, 11, 11, 11, 11, 11, 11, 14, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11 }, 
				/*14*/{205,205,205,205,205,205,205,105,205,205,205,205,205,205,205,205,205,205,205,205,205,205,205,205,205}, 
				/*15*/{127,127,127,128,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127}, 
				/*16*/{109,109,109,109,109,109,109,109,109,109,109,108,109,109,109,109,109,109,109,109,109,109,109,109,109},
				/*17*/{113,113,113,113,113,113,113,113,113,113,113,112,111,113,113,113,113,113,113,113,113,113,113,113,113}, 
				};
	
	public AnalizadorLexico(String fileName) throws FileNotFoundException{
	/**
	 * El constructor de la clase recive como parametro un String con la ruta del archivo a analizar, despues la usa para inicializar
	 * un objeto ManejoArchivos. El contructor arroja excepciones tipo FileNotFoundException por si la ruta del archivo no es correcta
	 */
		
		//Guardamos en un atributo el nombre del archivo
		archivo = fileName;
		
		//El metodo leerArchivo de la clase ManejoArchivos nos regresa un StringBuilder el cual lo guardaremos en el objeto in que fue declarado
		//anteriormente
		in = new ManejoArchivos(fileName).leerArchivo();
	}
	
	private String imprimirMensaje(int token){
		/**
		 * Este metodo recive un entero y dependiendo del caso regresa un mensaje, ya sea de aceptacion o de error
		 */
		String mensaje;
		switch (token){
		
		//Casos de aceptacion
		case 100:{
			mensaje = "Constante entera";
			}break;
			
		case 101:{
			mensaje = "Constante real";
			}break;
			
		case 102:{
			mensaje = "Identificador";
			}break;
			
		case 103:{
			mensaje = "Letrero";
			}break;
			
		case 104:{
			mensaje = "Operador aritmetico de division";
			}break;
			
		case 105:{
			mensaje = "Comentario";
			}break;
			
		case 106:{
			mensaje = "Delimitador :";
			}break;
			
		case 107:{
			mensaje = "Operador de asignacion :=";
			}break;
			
		case 108:{
			mensaje = "Operador Relacional >=";
			}break;
			
		case 109:{
			mensaje = "Operador Relacional >";
			}break;
			
		case 110:{
			mensaje = "Operador Relacional =";
			}break;
			
		case 111:{
			mensaje = "Operador Relacional <>";
			}break;
			
		case 112:{
			mensaje = "Operador Relacional <=";
			}break;
			
		case 113:{
			mensaje = "Operador Relacional <";
			}break;
			
		case 114:{
			mensaje = "Operador aritmetico de suma";
			}break;
			
		case 115:{
			mensaje = "Operador aritmetico de resta";
			}break;
			
		case 116:{
			mensaje = "Operador aritmetico de multiplicacion";
			}break;
			
		case 117:{
			mensaje = "Operador logico Y";
			}break;
			
		case 118:{
			mensaje = "Operador logico NO";
			}break;
			
		case 119:{
			mensaje = "Operador logico O";
			}break;
			
		case 120:{
			mensaje = "Delimitador {";
			}break;
			
		case 121:{
			mensaje = "Delimitador }";
			}break;
			
		case 122:{
			mensaje = "Delimitador (";
			}break;
			
		case 123:{
			mensaje = "Delimitador )";
			}break;
			
		case 124:{
			mensaje = "Delimitador [";
			}break;
			
		case 125:{
			mensaje = "Delimitador ]";
			}break;
			
		case 126:{
			mensaje = "Delimitador ,";
			}break;
			
		case 127:{
			mensaje = "Delimitador .";
			}break;
			
		case 128:{
			mensaje = "Delimitador ..";
			}break;
			
		case 129:{
			mensaje = "Delimitador ;";
			}break;
			
			//Esto tuvo que ser agregado
		case 152:{
			mensaje = "Fin de archivo $";
			}break;
			
			
	
		//Casos de error
		case 200:{
			mensaje = "Constante real mal formada, se esperaba un digito despues del punto";
			}break;
			
		case 201:{
			mensaje = "Simbolo no definido";
			}break;
			
		case 202:{
			mensaje = "Identificador mal formado";
			}break;
			
		case 203:{
			mensaje = "Letrero mal formado";
			}break;
			
		case 204:{
			mensaje = "Operador logico O mal formado";
			}break;
			
		case 205:{
			mensaje = "Comentario mal formado";
			}break;
			
		case 206:{
			mensaje = "Operador logico Y mal formado";
			}break;
			
		default: {
			mensaje = null;
		}
			
		}//switch
		
		return mensaje;
	}
	
	private int buscaColumna( char c){
		/**
		 * Este metodo recive como parametro un char, y nos regresa la columna de la tabla de transicion al que representa.
		 */
	
		int columna;
		switch (c){
		
		case ',': {
			columna = 2;
		}break;
		
		case '.': {
			columna = 3;
		}break;
		
		case '_': {
			columna = 4;
		}break;
		
		//Como no se puede poner una comilla entre comillas, se usa su valor en ascii y se convierte a char
		case (char) 39: {
			columna = 5;
		}break;
		
		case '$': {
			columna = 6;
		}break;
		
		case '/': {
			columna = 7;
		}break;
		
		case '*': {
			columna = 8;
		}break;
		
		case ':': {
			columna = 9;
		}break;
		
		case ';': {
			columna = 10;
		}break;
		
		case '=': {
			columna = 11;
		}break;
		
		case '>': {
			columna = 12;
		}break;
		
		case '<': {
			columna = 13;
		}break;
		
		case '+': {
			columna = 14;
		}break;
		
		case '-': {
			columna = 15;
		}break;
		
		case '{': {
			columna = 16;
		}break;
		
		case '}': {
			columna = 17;
		}break;
		
		case '[': {
			columna = 18;
		}break;
		
		case ']': {
			columna = 19;
		}break;
		
		case '(': {
			columna = 20;
		}break;
		
		case ')': {
			columna = 21;
		}break;
		
		case '\n': {
			columna = 22;
		}break;
		
		case '\t': {
			columna = 23;
		}break;
		
		case ' ': {
			columna = 24;
		}break;
		
		default: {
			//Si no es ninguno de los anteriores, preguntamos si es un digito
			if (Character.isDigit(c)){
				columna = 1;
			}
			//Si no lo es, entonces es un caracter
			else{
				columna = 0;
			}
		
		}break;
		
		
		}//fin del switch
		return columna;
	}
	
	private char leeCaracter() throws IOException{
		/**
		 * El metodo leeCaracter nos regresa un caracter leido desde el StringBuilder donde se encuentra el texto del archivo a analizar
		 */
		//Si index, el cual nos sirve de apundor al caracter leido es igual que el tamaño del StringBuilder 
		if (index == in.length()){
			//Entonces llegamos al fin de archivo, y lo indicamos poniendo la bandera finArchivo como verdadera
			finArchivo = true;
			//Regresamos un $ para indicarlo
			return '$';
		}
		
		//si no
		else{
			//leemos un caracter del StringBuilder en la posicion index, este se guardara como su representacion ascii
			int c = in.charAt(index);
			//aumentamos en un index
			index++;
			//regresamos el caracter correspondiente al ascii leido
			return (char) c;
		
		}
	}
	
	private void validaSalto(int edo){
		/**
		 * Este metodo recive el estado del token, y si esta entre los valores 100 y 103 o es 106 entonces activa la bandera haySalto
		 * esto es porque los estados 100, 101, 102, 103 ó 106 corresponden a tokens: Enteros, reales, identificadores, letreros, o dos puntos
		 * respectivamente por lo que despues de leer uno hay que dar un pequeño salto para aseguarnos de leer correctamente 
		 * el siguiente caracter
		 */

		if ((edo >= 100)&&(edo <= 104) || edo == 106){
			haySalto = true;
		
		}

	}
	
	private int verificaPalabra(String token){
		/**
		 * Este metodo verifica si el token dado es una palabra reservada.
		 */
		
		//Bandera que nos indica si el token recivido coincide con alguna palabra reservada
		boolean seEncontro = false;
		int nToken = 130;
		//Se busca la palabra recorriendo todo el vector de palabras reservadas
		for (int i = 0; i < 22; i++){
			//Si el token es igual a alguna de ellas
			if (token.equals(palabrasR[i])){
				//Se activa la bandera
				seEncontro =true;
				nToken+= i;
				//y se para el ciclo
				break;
			}
		}
		
		//se regresa la bandera
		
		if (seEncontro){
			return nToken;
		}
		else{
			return -1;
		}
	}
	
	//Metodo que regresa una instancia de la clase Token, que contiene el ultimo token hallado asd 
	public Token GetToken(){
		return cola.pop();
	}
	
	//Metodo que nos indica si todavia hay tokens
	public boolean HayTokens (){
		
		return !cola.vacia();
		
	}
	
	public Token showToken(){
		return cola.show();
	}
	 
	//Este metodo nos inidica si un caracter se va o no a guardar en el lexema
	private boolean seGuarda(char caracter, int edo){
		
		boolean validaEdo = (edo < 100)||(edo > 104);
		boolean validaCar = (edo == 6||((caracter != '\n')&&(caracter != '\t')&&(caracter != ' ')))&& (caracter != '\'');
		return validaEdo && validaCar;
		
		//No guardaremos caracteres con un estado entre 100 y 104, ni saltos de linea, tabulaciones o espacios en blanco
		//return (((edo < 100)||(edo > 104))&&(caracter != '\n')&&(caracter != '\t')&&(caracter != ' '));
		//return (edo < 100)||((caracter != '\n')&&(caracter != '\t')&&(caracter != ' '));			
		//return (((edo < 100)||(edo > 104))&&(caracter != '\n')&&(caracter != '\t')&&(caracter != '\''));

	}
	
	public boolean lexico() throws IOException{
	/**
	 * Metodo principal de la clase, este es el que realiza el analisis lexico, arroja excepciones IOException
	 */
		//Esta variable nos idica el estado, se inicia en cero
		int edo = 0;
		//Aqui se indica la columa en la que nos encontramos en la matriz de transicion
		int col = 0;
		//El caracter que se va a analizar
		char car = 0;
		
		
		//En el objeto lexema se iran guardando el lexema
		StringBuilder lexema = new StringBuilder();
		//Se inicialisa el objeto tokenes, este contiene el titulo de las dos columnas que iran en el archivo de los tokens
		tokens= new StringBuilder("Lexema\tGramema\n\n");
		//Se inicialisa el objeto errores, este contiene el titulo de las dos columnas que iran en el archivo de los errores
		StringBuilder errores= new StringBuilder("Lexema\tError\n\n");

		//ciclo principal del metodo, este iterara mientras que la bandera finArchivo se encuentre apagada
		while (!finArchivo){
			
			//Ponemos a estado en 0, para iniciar el recorrido de la matriz
			edo = 0;
			//limpiamos el contenido del objeto lexema
			lexema.delete(0, lexema.length());
			//Mientras que estado se encuentre entre 0 y 17
			while ((edo >= 0) && (edo <= 17)){
			
				//Si no se ha indicado que hay que saltar este paso, se lee un caracter	
				if (!haySalto){
					car = leeCaracter();
				}
				
				//Se busca la columna correspondiente al caracter 
				col = buscaColumna(car);
				//Se busca el valor de estado dentro de la matriz de transicion
				edo = matriz[edo][col];
				
				//Si la bandera haySalto est activada, la desactivamos
				if(haySalto){
					haySalto = false;
				}
				//Si no validamos si para la siguiente iteracion hay que saltar el paso de leerCaracter
				else{
					validaSalto(edo);
				}
				//Si estado no se encuentra dentro del rango 100 - 104, entonces
				if (seGuarda(car,edo)){ //No queremos guardar los saltos de linea
					//El caracter se guarda dentro del lexema
					lexema.append(car);
					}
				//Lo anterior se realiza ya que algunos tokens se guardarian con el siguiente caracter
					
			}
			
			//Si el estado es mayor o igual que 100, entonces terminal y hay que imprimirlo en el archvio
			if (edo >= 100){
				
				//Si es mayor o igual que 200, entonces es un mensaje de error
				if (edo >= 200){
					
					//El lexema se guarda en el StringBuilder de los errores
					errores.append(lexema.toString());
					//Se guarda el mensaje y se le da un poco de formato
					errores.append("\t");
					errores.append(imprimirMensaje(edo));
					errores.append("\n");
					//return false;
				}
				
				//Si no, entonces es un token
				else{

					//primero verificamos si el token dado es una palabra reservada
					int resultado = verificaPalabra(lexema.substring(0));
					if (resultado != -1){
						
						//Creamos un nuevo token con la informacion dada
						Token token = new Token(lexema.toString(),resultado);
						
						//Lo agregamos a la cola
						cola.push(token);
						
					}
					
					//Si el lexema tiene menos de tres letras, entonces es posible que sea un operador logico
					else if (lexema.length() < 3){
					
						//Creamos un nuevo token con la informacion dada
						Token token;
						
						String c = lexema.toString();
						//Guardamos el lexema en el token
						
						//si el operador es igual a alguno de los operadores, se escribe el mensaje correspondiente
						if ((c.equals("o"))||(  c.equals("O"))){
							token = new Token(c,119);
						}
						
						else if (( c.equals("y") )||( c.equals("Y"))){
							token = new Token(c,117);
						}
						else if (( c.equals("no") )||( c.equals("No"))||(  c.equals("nO"))||( c.equals("NO"))){
							token = new Token(c,118);
						}
						else{
							//Si no es ningun operador, entonces hay que guardar el mensaje del token correspondiente
							token = new Token(c,edo);
						}
						
						//Lo agregamos a la cola
						cola.push(token);

					}
					
					else{
						//Si no es ninguna de las anteriores, entonces se guarda el mensaje correspondiente
						if (edo != 105){
							
						Token token = new Token(lexema.toString(), edo);
							cola.push(token);
						}
					}
				}				

			}
		
			}//While archivo
			
			//Se mete a la cola el fin de archivo
			cola.push(new Token("Fin de archivo", 152));
		
			//Se inicializa el objeto ma para escribir en un archivo que se llame igual que el archivo al que se analiza, solo que con
			//la extencion .lexico
			ManejoArchivos ma = new ManejoArchivos(this.archivo+".lexico");
			ma.escribir(tokens.toString());
			//Se inicializa el objeto ma para escribir en un archivo que se llame igual que el archvio al que se analiza, solo que con
			//la extencion .errores
			ma.setRutaArchivo(this.archivo+".errores");
			ma.escribir(errores.toString());
	 
			return true;
		}//lexico()
	
	//Este metodo se hereda de la clase Thread, sirve para que esta clase corra como un hilo independiente
	public void run (){
		//intenta correr el metodo lexico
		try {
			lexico();
		} catch (IOException e) {
			//Si ocurre algun error este se captura aqui, y se imprime en una ventana
			javax.swing.JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
}//Class<>