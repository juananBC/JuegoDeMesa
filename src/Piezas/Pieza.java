package Piezas;

public abstract class Pieza {
	
	public  static  enum COLOR{BLANCO, NEGRO};
	private COLOR color;
	
	public Pieza(COLOR color) {
		this.color = color;
	}
	
	
	
}
