package Arbol;

import java.util.ArrayList;
import java.util.List;

public class Nodo<E> {

	private E value;
	private List<Nodo<E>> hijos;
	private Nodo<E> parent;
	
	public Nodo(Nodo<E> parent, E value) {
		this.parent = parent;
		this.value = value;
		this.hijos = new ArrayList<Nodo<E>>();
		
	}
	
	
}
