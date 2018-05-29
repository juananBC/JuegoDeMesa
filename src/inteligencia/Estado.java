package inteligencia;

import Juego.Tablero;

public class Estado {

	private Tablero tablero;
	private int origen, destino;
	private int valor;
	
	public Estado(Tablero tablero, int origen, int destino, int valor) {
		this.tablero = tablero;
		this.origen = origen;
		this.destino = destino;
		this.valor = valor;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
}
