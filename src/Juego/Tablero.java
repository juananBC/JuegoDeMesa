package Juego;

public class Tablero {

	private int tamano;
	private Casilla[][] casillas;
	
	
	
	public Tablero(int tamano) {
		this.tamano = tamano;
		
		this.casillas = new Casilla[tamano][tamano];
//		this.casillas = new Casilla[tamano*tamano];
	}
	
	
	
}
