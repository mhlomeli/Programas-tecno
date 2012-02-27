//Esta clase es parte del paquete utilidades
package utilidades;
//Declaracion de la clase, aqui vamos a usar generics
public class Pila  <type>{

	//Declaramos un nodo de tipo Type, que sera definido en tiempo de ejecucion
	private Nodo<type> ultimo;
	
	//Esta variable nos idica cuantos elementos tiene la pila
	private int nElementos;
	
	//Constructor
	public Pila() {
		nElementos = 0;//Inicializamos la variable en 0
		}
	
	//metodo para insertar un elemento tipo Type en la pila
	public void push(type e){
       
		//Creamos un nodo temporal
	    Nodo<type> aux = new Nodo<type>();
	    //en el nodo temporal guardamos el elemnto que recivimos
		aux.setElemento(e);
		//lo enlazamos al ultimo nodo de la pila
		aux.setSiguiente(ultimo);
		//Ahora el ultimo nodo sera el nodo temporal
		ultimo = aux;
		//aumentamos uno, el numero de nodos 
		++nElementos;
	}
	
	//metodo para sacar un elemento del tope de la pila
	public type pop(){
		       
		//Sacamos el elemento guardado en el ultimo nodo
        type e = ultimo.getElemento();
        //Ahora el ultimo nodo es el que le seguia
        ultimo = ultimo.getSiguiente();
        //le quitamos uno al numero de elementos
        --nElementos;
        //Regresamos el elemento
        return e;
	}
	
	//Metodo para mostrar lo qeu hay en el tope de la pila, pero sin sacarlo
	public type show(){
		//Regresamos el elemento del ultimo nodo
		return ultimo.getElemento();
	}
	
	//metodo para comprobar si la pila esta vacia
	public boolean vacia(){
		
		//Si el numero de elementos es igual a cero, entonces la pila esta vacia
		return nElementos == 0;
	}
	
	//metodo para obtener el numero de elementos
	public int getNElementos(){
		//Regresamos el numero de elementos
		return nElementos;
	}

}


