package semantico;

public class TablaSimbolos {

	private Simbolo[] simbolos;
	private int apu;
	//Numero de variables temporables, util para generar nombres 
	private int nTemp = -1;
	
	public TablaSimbolos() {
		apu = 0;
		simbolos = new Simbolo[100];
	}

	public boolean setSimbolo(String nombre, String valor, int tipo) {

		if (this.busqueda(nombre)== -1){
			simbolos[apu] = new Simbolo(nombre, valor, tipo);
			apu++;
			return true;
		}
		else{
			
			return false;
		}
	}

	public boolean setSimbolo(String nombre, String valor, int tipo, int posicion) {

		if (this.busqueda(nombre) == -1){
		simbolos[posicion] = new Simbolo(nombre, valor, tipo);
		return true;
		}
		
		else{
			return false;
		}
	}

	public Simbolo getSimbolo() {

		return simbolos[apu - 1];

	}

	public Simbolo getSimbolo(int posicion) {

		return simbolos[posicion];

	}

	public int busqueda(String Nombre) {

		for (int i = 0; i < apu; i++) {

			if ((simbolos[i].getNombre()).equals(Nombre)) {
				return i;

			}

		}
		return -1;
	}

	public boolean modifica(String nombre, String nuevoNombre,
			String nuevoValor, int nuevoTipo) {

		int pos = busqueda(nombre);

		if (pos != -1) {
			setSimbolo(nuevoNombre, nuevoValor, nuevoTipo, pos);
			return true;
		} else {
			return false;
		}
	}

	
	protected String generaNombre(){
		nTemp++;
		return "T"+nTemp;
	}
	
	public String [][] toTable(){
		String [][] matriz = new String[apu][3];
		
		for (int i = 0; i < apu; i++){
			matriz[i] = this.simbolos[i].toArray();
		}
		
		return matriz;
	}
	
}
