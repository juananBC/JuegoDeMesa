package Juego;

import Excepciones.MovimientoNoValido;

public class Jugador {

	private int numFichas;

	public Jugador() {
		this.numFichas = 16;
	}

	public void mover(Tablero tablero, int origen, int destino) throws MovimientoNoValido {

		// if(!origen.isOcupada()) throw new MovimientoNoValido("Movimiento fuera de
		// tablero");

	}
	
	
	public void matar() {
		if(numFichas > 0) numFichas--;
	}

}
