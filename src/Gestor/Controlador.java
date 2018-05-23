package Gestor;

import Juego.*;

public class Controlador {

	private Jugador player1, player2;
	private Tablero tablero;
	
	public Controlador() {

		player1 = new Jugador();
		player2 = new Jugador();
		
		tablero = new Tablero();	
	}
	
	public void mover(int idOrigen, int idDestino) {	
		
		tablero.mover(idOrigen, idDestino);
	}
	
	
}
