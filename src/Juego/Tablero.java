package Juego;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JLabel;

import Excepciones.MovimientoNoValido;
import Juego.Pieza.NOMBRE;
import Piezas.*;

public class Tablero {

	public static final int TAMANO = 8;
	private static final int TAMANO_TOTAL = TAMANO * TAMANO;
	
	private Casilla[][] casillas;
	
	
	public Tablero() {
		this.casillas = new Casilla[TAMANO][TAMANO];
		
		for (int y = 0; y < Tablero.TAMANO; y++) {
			for (int x = 0; x < Tablero.TAMANO; x++) {
				casillas[x][y] =  new Casilla(x, y);			
			}
		}
	}
	
	
	public List<Casilla> getMovimientos(Casilla c) {
		List<Casilla> lista = new ArrayList<Casilla>();
		
//		Pieza p = c.getPieza();
//		for(Movimiento mov: p.getMovimientos()) {
//			
//		}
//		
		
		return lista;
	}


	/**
	 * Obtiene la casilla a partir del identificador
	 * @param casilla
	 * @return
	 */
	public Casilla getCasilla(int casilla) {
		if(casilla < 0 || casilla >= TAMANO_TOTAL) return null;
		
		int x = (int) Math.floor(casilla / TAMANO);
		int y = casilla % TAMANO;
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
	
	public void updateCasilla(Casilla casilla, int x, int y) {
		this.casillas[x][y] = casilla;
	}
	

	
}
