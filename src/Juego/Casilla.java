package Juego;


public class Casilla {

	// El negro sera invertido de signo en los movimientos
	public static enum COLOR{BLANCO, NEGRO};
	
	private boolean ocupada;
	private Pieza pieza;
	private int id;
	private int x,y;
	private COLOR color;


	public Casilla(Pieza pieza, int id, int x, int y) {
		this.ocupada = false;
		this.pieza = pieza;
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public Casilla(int id, int x, int y, COLOR color) {
		this.ocupada = false;
		this.pieza = null;
		this.id = id;
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void liberar() {
		ocupada = false;
	}
	

	public COLOR getColor() {
		return color;
	}

	public void setColor(COLOR color) {
		this.color = color;
	}
	
	public Pieza ocupar(Pieza pieza) {		
		Pieza p = null;
		if(ocupada == true) {
			p = getPieza();
		}
		
		ocupada = true;
		this.pieza = pieza;
		
		return p;
	}

	 
	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		if(pieza == null) this.ocupada = false;
		else this.ocupada = true;
		
		this.pieza = pieza;
	}
	
}
