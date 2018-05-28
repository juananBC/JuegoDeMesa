package Arbol;

public class Arbol<E> {

	
	private Nodo<E> raiz;
	
	public Arbol(E item) {
		raiz = new Nodo<E>(null, item);
	}
	
	
}
