package Gestor;

import Juego.COLOR;
import Juego.Pieza;

public class Estado {
	private Pieza mata;
	private int idOrigen, idDestino;
	private int turno;
	private int puntuacion;
	private COLOR color;
	
	public Estado(Pieza mata, int idOrigen, int idDestino, int turno, COLOR color){
		this.mata = mata;
		this.idDestino = idDestino;
		this.idOrigen = idOrigen;
		this.turno = turno;
		this.color = color;
		this.puntuacion = (mata == null)? 0:mata.getValorMuerte();
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

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public COLOR getColor() {
		return color;
	}

	public void setColor(COLOR color) {
		this.color = color;
	}
	
	
}
