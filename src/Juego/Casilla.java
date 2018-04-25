package Juego;

public class Casilla {

	private boolean ocupada;
	private Pieza pieza;
	private int id;
	
	public Casilla(Pieza pieza, int id) {
		this.ocupada = false;
		this.pieza = pieza;
		this.id = id;
	}
	
	public void liberar() {
		ocupada = false;
	}
	
	public void ocupar(Pieza pieza) {
		ocupada = true;
		this.pieza = pieza;
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
