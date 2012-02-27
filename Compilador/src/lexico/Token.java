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
