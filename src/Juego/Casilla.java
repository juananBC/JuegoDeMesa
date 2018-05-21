package Juego;

public class Casilla {

	private boolean ocupada;
	private Pieza pieza;
	private int id;
	private int x,y;
	

	public Casilla(Pieza pieza, int id, int x, int y) {
		this.ocupada = false;
		this.pieza = pieza;
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public Casilla(int id, int x, int y) {
		this.ocupada = false;
		this.pieza = null;
		this.id = id;
		this.x = x;
		this.y = y;
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
		this.pieza = pieza;
	}
	
}
