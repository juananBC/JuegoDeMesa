package inteligencia;

import Gestor.Controlador;
import Juego.COLOR;
import Juego.Tablero;
import Piezas.Rey;

public class IA {

	private static final boolean MAX = true;
	private static final boolean MIN = false;
	
	private Arbol arbol;
	private int profundidad;
	
	public IA(int profundidad) {
		this.profundidad = profundidad;
	}
	
	
	public void calculaMovimiento(Tablero t, COLOR color) {
		
		Tablero tablero = t.copiarEstado();
		arbol = new Arbol(t);	
		
		Controlador controller = new Controlador(tablero, 3);
		//minimax(arbol.getRaiz(), profundidad, MAX);
	}
	
	
	public int minimax(Nodo nodo, int profundidad, boolean isMaximizador) {
		
		if(profundidad == 0 || nodo.isTerminal()) 
			return nodo.getValor();
		
		int mejorValor;
		int valorActual;
		if(isMaximizador) {
			mejorValor = Integer.MIN_VALUE;
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, profundidad - 1, MIN);
				mejorValor = Math.max(valorActual,  mejorValor);
			}	
			
		} else { // Minimizador
			mejorValor = Integer.MAX_VALUE;
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, profundidad - 1, MAX);
				mejorValor = Math.min(valorActual,  mejorValor);
			}	
		}
		
		return mejorValor;
	}
}
