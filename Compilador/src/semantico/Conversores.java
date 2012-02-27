package semantico;

public class Conversores {

	/**
	 * Tipos de datos según su posición
	 */
	private static final String tipos [] = {"","Entero","Real","Booleano","Cadena","Caracter"};
	
	/**
	 * Convierte los lexemas de un operador por su gramema equivalente 
	 */
	public static String covOperadores(int token){
		
		String gramema = null;
		
		switch (token){
		
	case 104:{
		gramema = "/";
		}break;
		
	case 107:{
		gramema = ":=";
		}break;
		
	case 108:{
		gramema = ">=";
		}break;
		
	case 109:{
		gramema = ">";
		}break;
		
	case 110:{
		gramema = "=";
		}break;
		
	case 111:{
		gramema = "<>";
		}break;
		
	case 112:{
		gramema = "<=";
		}break;
		
	case 113:{
		gramema = "<";
		}break;
		
	case 114:{
		gramema = "+";
		}break;
		
	case 115:{
		gramema = "-";
		}break;
		
	case 116:{
		gramema = "*";
		}break;
		
	case 117:{
		gramema = "Y";
		}break;
		
	case 118:{
		gramema = "NO";
		}break;
		
	case 119:{
		gramema = "O";
		}break;
		
	case 1000:{
		gramema = "Salto en falso";
	}break;
		
	case 2000:{
		gramema = "Salto obligatorio";
	}break;
	
	case 3000:{
		gramema = "Salto verdadera";
	}break;
	
	case 4000:{
		gramema = "Leer";
	}break;
	
	case 5000:{
		gramema = "Escribir";
	}break;
	
	default:{
		gramema = String.valueOf(token);
		}
	}
		
		return gramema;
		
	}
	
	/**
	 * Convierte  los 0 a vacios
	 */
	public static String limpiaCeros (String token){
		
		//Si es 0, regresa un espacio vacio
		if (token.equals("0")){
			return "";
		}
		//Si no
		else
		{
			//Regresa el mismo token
			return token;
		}
		
	}
	
	/**
	 * limpia los espacios vacios
	 */
	public static String limpiaVacios (String token){
		
		
		
		//Si el token es igual a -1 regresa "", si no regresa el token
		return token.equals("-1") ? "" : token;
		
	}
	
	/**
	 * Convierte un gramema dado, de un tipo de datos, a la representación usada para la comprovación de datos
	 * @param token gramema a convertir
	 * @return El tipo de datos para analizar
	 */
	
	public static int convierteTipos(int token){
		
		int tipo;
		
		switch (token){
		
		case 136:{
			tipo = 1;
		}break;
		
		case 149:{
			tipo = 2;
		}break;
		
		case 151:{
			tipo = 3;
		}break;
		
		case 137:{
			tipo = 4;
		}break;
		
		case 143:{
			tipo = 5;
		}break;
		
		default:{
			tipo = 0;
			}break;
		
		}
		
		return tipo;
	}
	
	/**
	 * Regresa la representación String de un tipo de datos dado
	 * @param tipo el tipo de datos
	 * @return la representación en String (Lexema) del tipo de datos
	 */
	public static String convTipoLex(int tipo){
		
		return tipos[tipo];
	}
	
	/**
	 * Convierte los valores booleandos
	 * @param res resultado en booleano
	 * @return resultado en boolenano tipo pascual
	 */
	public static String convBool (String res){
		
		if (res.equals("true")){
			return "verdadero";
		}
		
		else if (res.equals("false")){
			return "falso";
		}
		else{
			return res;
		}
	}
	/**
	 * Metodo para dar formato a una matriz
	 * @param matriz.- Matriz a dar formato
	 * @return matriz con formato
	 */
	public static String[][] formMatriz(String [][] matriz){
		int tipo = 0;
		//Ciclo for each
		//Por cada renglon en la matriz
		for (String [] renglon : matriz ){
			
			//Cambiamos los tipos
			tipo = Integer.parseInt(renglon[2]);
			//Cambiamos los tipos de datos
			tipo = constTipos(tipo);
			renglon[2] = Conversores.convTipoLex(tipo);
			//Cambiamos los valores
			renglon[1] = Conversores.convBool(renglon[1]);
		}
		
		return matriz;
	}
	/**
	 * Convierte el tipo de las constantes
	 * @param valor .- Valor del tipo de datos
	 * @return Valor .-El valor correcto a evaluar
	 */
	private static int constTipos(int valor){
		
		//Aqui se va a guardar
		int res = 0;
		if (valor >= 100){
			switch (valor){
			
			case 100:{
				res = 1;
			}break;
			
			case 102:{
				res =  2;
			}break;
			
			case 103:{
				res = 4;
			}break;
			
			}	
		}
		else{
			return valor;
		}
		
		return res;
	}
	
}
