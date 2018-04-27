package Jugadores;

import Excepciones.*;
import Juego.Casilla;
import Juego.Pieza;
import Juego.Tablero;

public class Jugador {

	private int numFichas;
	
	public Jugador (int fichas) {
		this.numFichas = fichas;
	} 
	
	
	public void mover(Tablero t, Casilla origen, Casilla destino) throws MovimientoNoValido {
		
		if(!origen.isOcupada()) throw new MovimientoNoValido("Movimiento fuera de tablero");
		
		Pieza pieza = origen.getPieza();
		pieza.mover
		
	}
	
}
