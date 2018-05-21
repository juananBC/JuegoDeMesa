package Juego;

import java.util.List;

import Excepciones.MovimientoNoValido;
import Juego.Movimiento.MOVIMIENTOS;

public abstract class Pieza {
	
	// El negro sera invertido de signo en los movimientos
	public static enum COLOR{BLANCO, NEGRO};
	public static enum NOMBRE{REY, REINA, PEON, CABALLO, ALFIL, TORRE};
	
	private COLOR color;
	private int distancia; // Cantidad de pasos que puede moverse
	private boolean libre;		// Moverse en cualquier direccion (true), o adelante (false)
	private boolean puedeSaltar;// Puede botar piezas (true), o son obstaculos (false)
	private NOMBRE nombre; 
	
	public NOMBRE getNombre() {
		return nombre;
	}


	public void setNombre(NOMBRE nombre) {
		this.nombre = nombre;
	}

	private List<MOVIMIENTOS> movimientos;
	private List<MOVIMIENTOS> matar;
	
	public Pieza(COLOR color, int distancia, boolean libre, boolean puedeSaltar, NOMBRE nombre) {
		this.color = color;
		this.distancia = distancia;
		this.libre = libre;
		this.puedeSaltar = puedeSaltar;
		this.nombre = nombre;
	}
	
	
	public Pieza(COLOR color, int distancia, NOMBRE nombre) {
		this.color = color;
		this.distancia = distancia;
		this.libre = true;
		this.puedeSaltar = false;
		this.nombre = nombre;
	}


	/**
	 * 
	 * @param origen
	 * @param destino
	 * @throws MovimientoNoValido
	 */
	public boolean isValid(Casilla origen, Casilla destino)  {
		
		int direccion = (color == COLOR.BLANCO)? 1:-1;
		
		int x = destino.getX() - origen.getX();
		int y = (destino.getY() - origen.getY()) * direccion;

		// Obtiene el movimiento realizado
		Movimiento mov = new Movimiento(x, y);
		
		if(movimientoValido(mov, origen, destino) || matarValido(mov, origen, destino)) {	
			 return true;	
		}
		
		return false;
	}
	
	
	public boolean movimientoValido(Movimiento m, Casilla origen, Casilla destino) {
		for(MOVIMIENTOS movimiento : movimientos) {	
			
			if(m.checkMovimiento(movimiento, this)) {				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean matarValido(Movimiento m, Casilla origen, Casilla destino) {
		for(MOVIMIENTOS movimiento : matar) {				
			if(m.checkMovimiento(movimiento, this)) {				
				return true;
			}
		}		
		return false;
	}
	
	public abstract boolean matar(Casilla origen, Casilla destino);
	public abstract Casilla mover(Casilla origen, Casilla destino);



	public COLOR getColor() {
		return color;
	}



	public void setColor(COLOR color) {
		this.color = color;
	}



	public List<MOVIMIENTOS> getMovimientos() {
		return movimientos;
	}



	public void setMovimientos(List<MOVIMIENTOS> movimientos) {
		this.movimientos = movimientos;
	}



	public List<MOVIMIENTOS> getMatar() {
		return matar;
	}



	public void setMatar(List<MOVIMIENTOS> matar) {
		this.matar = matar;
	}
	
	
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	
	public boolean isLibre() {
		return libre;
	}
	

	public void setPuedeSaltar(boolean puedeSaltar) {
		this.puedeSaltar = puedeSaltar;
	}
	
	public boolean isPuedeSaltar() {
		return puedeSaltar;
	}
	
	
	
}
