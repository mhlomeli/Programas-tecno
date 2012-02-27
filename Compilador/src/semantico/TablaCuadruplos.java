package semantico;
public class TablaCuadruplos {

	private int matriz[][] = null;
	private int apuntador = -1;
	
	
	public TablaCuadruplos() {

		matriz = new int[50][4];
	
	}

	public int getApuntador(){
		
		return apuntador;
	}
	
	protected void insertaCuadruplo(int operando, int operador1, int operador2, int resultado){
		/**
		 * Metodo para insertar un cuadruplo, *no usar*
		 */
		apuntador++;
		matriz[apuntador][0] = operando;
		matriz[apuntador][1] = operador1;
		matriz[apuntador][2] = operador2;
		matriz[apuntador][3] = resultado;
	
	}
	
	protected void insertaCuadruplo(int operando, int operador1, int resultado){
		/**
		 *  *No usar*
		 */
		apuntador++;
		matriz[0][apuntador] = operando;
		matriz[1][apuntador] = operador1;
		matriz[2][apuntador] = resultado;
	
	}
	
	//
	/*protected void insertar(int...parametros){
		//TODO cambiar esto
		apuntador++;
		int i = 0;
		//Por cada parametro en parametros
		//forEach(parametros)
		for (int parametro : parametros){
			matriz[apuntador][i] = parametro;
			//No es lo más elegante...
			
			if (i > 3){
				break;
			}
			i++;
		}
		
	}*/
	
	protected void insertar(int...parametros){
		
		apuntador++;
		int i = 0;
		//Por cada parametro en parametros
		for (int parametro : parametros){
			matriz[apuntador][i] = parametro;
			//No es lo más elegante...
			
			if (i > 3){
				break;
			}
			i++;
		}
		
	}
	
	public int[] renglon(int posicion){
		
		return matriz[posicion];
	}
	
	public boolean vacia(){
		
		return apuntador == -1;
	}
	
	public boolean llena(){
	
		return apuntador == 49;
	}
	
	public int [][] toIntMatriz(){
		//Copia defensiva
		return this.matriz.clone();
	}
	
	public String[][] toMatriz(){
		
		String matrizCopia [][] = new String[matriz.length][matriz[0].length];
	
		for (int i = 0; i < matriz.length;i++){
			for (int j = 0; j < matriz[0].length; j++){
				matrizCopia[i][j] = String.valueOf( matriz[i][j]); 
			}
		}
		
		return matrizCopia;
	}
	
}