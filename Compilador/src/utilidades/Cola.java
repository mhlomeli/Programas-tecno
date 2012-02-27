package utilidades;

public class Cola <type>{

	private Nodo<type> cabeza;
	private Nodo<type> ultimo;
	private int nElementos;
	private boolean estaLlena = false;
	
	public Cola() {
		cabeza = null;
		ultimo = null;
		nElementos = 0;
	
	}

	public void push(type elemento){
		
		Nodo<type> nuevo = new Nodo<type>(elemento);
		
		estaLlena = (nuevo == null);
					
		if (cabeza == null){
			cabeza = nuevo;
		}
		
		else{
			ultimo.setSiguiente(nuevo);
		}
		ultimo = nuevo;
	    ++nElementos;

	}
	
	public type pop(){
	
	type aux = cabeza.getElemento();
	cabeza = cabeza.getSiguiente();
    --nElementos;
    
    return aux;
	}
	
	public type show(){
		return cabeza.getElemento();
	}
	
	public boolean vacia(){
		
		return nElementos == 0;
	}
	
	public int getNElementos(){
		
		return nElementos;
	}
	
	public boolean llena(){
	
		return estaLlena;
	}
	
}
