package Gestor;

import Juego.Pieza;

public class Estado {
	private Pieza mata;
	private int idOrigen, idDestino;
	private int turno;
	
	public Estado(Pieza mata, int idOrigen, int idDestino, int turno){
		this.mata = mata;
		this.idDestino = idDestino;
		this.idOrigen = idOrigen;
		this.turno = turno;
	}

	public Pieza getMata() {
		return mata;
	}

	public void setMata(Pieza mata) {
		this.mata = mata;
	}

	public int getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(int idOrigen) {
		this.idOrigen = idOrigen;
	}

	public int getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	
}
