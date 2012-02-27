package semantico;

public class Simbolo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -915926646113878029L;
	private String nombre;
	private String valor;
	private int tipo;
	
	public Simbolo (String nombre, String valor, int tipo){
		
		this.setNombre(nombre);
		this.setValor(valor);
		this.setTipo(tipo);
	
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
	
	public void setTipo(int tipo){
		
		this.tipo = tipo;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public String getValor(){
		return valor;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public String[] toArray(){
		String array [] = {nombre, valor, String.valueOf(tipo)};
		return  array;
	}
}
