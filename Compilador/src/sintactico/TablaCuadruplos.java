package sintactico;


public class TablaCuadruplos {

	private int matriz[][] = null;
	private int apuntador = -1;
	
	public TablaCuadruplos() {

		matriz = new int[50][4];
	
	}

	public void insertaCuadruplo(int operando, int operador1, int operador2, int resultado){
		apuntador++;
		matriz[apuntador][0] = operando;
		matriz[apuntador][1] = operador1;
		matriz[apuntador][2] = operador2;
		matriz[apuntador][3] = resultado;
	
	}
	
	public void insertaCuadruplo(int operando, int operador1, int resultado){
		
		apuntador++;
		matriz[0][apuntador] = operando;
		matriz[1][apuntador] = operador1;
		matriz[2][apuntador] = resultado;
	
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
	
	public int[][] toMatriz(){
		
		int matrizCopia [][] = null;
	
		matrizCopia = matriz;
		
		return matrizCopia;
	}
	
}