package Juego;

public abstract class Pieza {
	
	// El negro sera invertido de signo en los movimientos
	public  static  enum COLOR{BLANCO, NEGRO};
	private COLOR color;
	
	public Pieza(COLOR color) {
		this.color = color;
	}
	
	public abstract Casilla mover(Casilla c);
	
	
}
