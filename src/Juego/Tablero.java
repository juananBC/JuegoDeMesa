package Juego;

import java.util.ArrayList;
import java.util.List;

import Excepciones.MovimientoNoValido;

public class Tablero {

	public static final int TAMANO = 8;
	
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
	}
	
	

	/**
	 * Realiza el movimiento desde la casilla de origen a la casilla de destino
	 */
	public void  mover(Casilla origen, Casilla destino) throws MovimientoNoValido  {		
		if(!origen.isOcupada()) throw new MovimientoNoValido("La casilla de origen no tiene pieza");;
		
		Pieza pieza = origen.getPieza();		
		
		if(pieza.isValid(origen, destino)) {
			
			if(!pieza.isPuedeSaltar() && hayObstaculos(origen, destino)){
				throw new MovimientoNoValido("Hay alguna pieza en el recorrido seleccionado.");				
			}
			
			origen.liberar();
			Pieza mata = destino.ocupar(pieza);
			if(mata != null) {
				
			}
		}	
		
		throw new MovimientoNoValido("Movimiento no permitido");
		
	}

	
	/**
	 * Comprueba si en las casillas que separan 'origen' y 'destino' hay alguna
	 * ficha que impida hacer el movimiento.
	 */
	private boolean hayObstaculos(Casilla origen, Casilla destino) {
		Casilla tmp;
		
		// Origen y destino
		int xo = origen.getX();
		int yo = origen.getY();		
		int xd = destino.getX();
		int yd = destino.getY();
		
		int deltaX = xd - xo;
		int deltaY = yd - yo;		

		// Dirección de la separación de las casillas.
		int dirX = (deltaX == 0)? 0 : (deltaX < 0)? -1:1;
		int dirY = (deltaY == 0)? 0 : (deltaY < 0)? -1:1;		
		int limitMax = Math.abs(Math.max(deltaX, deltaY)) - 1;

		for (int i = 1; i < limitMax; i++) {
			tmp = getCasilla(xo + i*dirX, yo + i*dirY);
			if (tmp.isOcupada()) return true;
		}
		return false;
		
			
	}
	
	public List<Casilla> getMovimientos(Casilla c) {
		List<Casilla> lista = new ArrayList<Casilla>();
		
		Pieza p = c.getPieza();
		for(Movimiento mov: p.getMovimientos()) {
			
		}
		
		
		return lista;
	}


	/**
	 * Obtiene la casilla a partir del identificador
	 * @param casilla
	 * @return
	 */
	public Casilla getCasilla(int casilla) {
		int y = (int) Math.floor(casilla / tamano);
		int x = casilla % tamano;
		return casillas[x][y];
	}
	
	/**
	 * Obtiene la casilla a partir de la posicion X e Y
	 * @param casilla
	 * @return
	 */
	public Casilla getCasilla(int x, int y) {
		return this.casillas[x][y];
	}
	
	
	
	
	
}
