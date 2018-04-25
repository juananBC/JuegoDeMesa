package Juego;

public class Tablero {

	private int tamano;
	private Casilla[][] casillas;
	
	
	
	public Tablero(int tamano) {
		this.tamano = tamano;
		
		this.casillas = new Casilla[tamano][tamano];
//		this.casillas = new Casilla[tamano*tamano];
	}
	
	
	public void mover(int casillaOrigen) {
		Casilla origen = getCasilla(casillaOrigen);		
	}
	
	
	public Casilla getCasilla(int casilla) {
		int y = (int) Math.floor(casilla / tamano);
		int x = casilla % tamano;
		return casillas[x][y];
	}
	
	
}
