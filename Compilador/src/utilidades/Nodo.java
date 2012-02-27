package utilidades;

public class Nodo <type>{
	
	private type elemento;
	
	private Nodo<type> siguiente;
	
	public Nodo(){
		
	}
	
	public Nodo (type e){
		
		this.elemento = e;
	}

	public void setElemento(type elemento) {
		this.elemento = elemento;
	}

	public type getElemento() {
		return elemento;
	}

	public void setSiguiente(Nodo<type> siguiente) {
		this.siguiente = siguiente;
	}

	public Nodo<type> getSiguiente() {
		return siguiente;
	}
}