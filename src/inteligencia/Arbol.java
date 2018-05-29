package inteligencia;

import Juego.Tablero;

public class Arbol {

	
	private Nodo raiz;
	
	public Arbol(Tablero t) {
		Estado estado = new Estado(t, -1, -1, Integer.MIN_VALUE);		
		raiz = new Nodo(null, estado);
	}

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}
		
	
}
