package Juego;

public class Tablero {

	private int tamano;
	private Casilla[][] casillas;
	
	
	
	public Tablero(int tamano) {
		this.tamano = tamano;
		
		this.casillas = new Casilla[tamano][tamano];
		
		for(int y = 0; y < tamano; y++) {
			for(int x = 0; x < tamano; x++) {			
				this.casillas[x][y] = new Casilla(x+y, x, y);
			}	
		}
		
//		this.casillas = new Casilla[tamano*tamano];
	}
	
	

	public void mover(Casilla origen, Casilla destino) {
		
		if(!origen.isOcupada()) return;
		
		Pieza pieza = origen.getPieza();
		
		pieza.isValid(origen, destino);
		
	}
	
	public Casilla getCasilla(int casilla) {
		int y = (int) Math.floor(casilla / tamano);
		int x = casilla % tamano;
		return casillas[x][y];
	}
	
	
}
