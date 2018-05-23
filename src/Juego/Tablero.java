package Juego;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Excepciones.MovimientoNoValido;
import Juego.Casilla.COLOR;
import Juego.Pieza.NOMBRE;
import Piezas.*;

public class Tablero {

	public static final int TAMANO = 8;
	
	private int tamano;
	private Casilla[][] casillas;
	
	private Properties prop = new Properties();
	
	public Tablero() {
		
		try {
			prop.load(new FileInputStream("C:\\Users\\JNBN007\\Desktop\\workspace\\JuegoDeMesa\\resources\\config"));
		} catch (IOException e) {
		}
		
		System.out.println(prop);
		
		this.tamano = TAMANO;		
		this.casillas = new Casilla[tamano][tamano];

		for (int x = 0; x < tamano; x++) {
			for (int y = 0; y < tamano; y++) {
				Casilla casilla = new Casilla(x, y);
				ponerPieza(casilla);
				ponerCasilla(casilla, x, y);
			}
		}
	}
	
	public void ponerPieza(Casilla casilla) {
		Pieza pieza = null;		
		int x = casilla.getX();
		int y = casilla.getY();
		
		if(y > 1 && y < 6 ) {
			return;
		}
		
		Pieza.COLOR color = (y <= 1) ? Pieza.COLOR.BLANCO : Pieza.COLOR.NEGRO;

		if (y == 1 || y == 6) {
			pieza = new Peon(color);
			
		} else {
			NOMBRE nombreCase = NOMBRE.valueOf(prop.getProperty(x + ""));
			switch (nombreCase) {
			case CABALLO:
				pieza = new Caballo(color);
				break;
			case TORRE:
				pieza = new Torre(color);
				break;
			case REY:
				pieza = new Rey(color);
				break;
			case REINA:
				pieza = new Reina(color);
				break;
			case ALFIL:
				pieza = new Alfil(color);
				break;
			default:

				break;
			}
		}
		if(pieza == null ) return;
		casilla.ocupar(pieza);
	}
	
	

	/**
	 * Realiza el movimiento desde la casilla de origen a la casilla de destino
	 */
	public boolean mover(int  idOrigen, int idDestino) { // throws MovimientoNoValido  {

		Casilla origen = getCasilla(idOrigen);
		Casilla destino = getCasilla(idDestino);
		
		if(origen == null || destino == null) return false;
		
		if(!origen.isOcupada()) return false; //throw new MovimientoNoValido("La casilla de origen no tiene pieza");;
		
		Pieza pieza = origen.getPieza();		
		
		if(pieza.isValid(origen, destino)) {
			
			if(!pieza.isPuedeSaltar() && hayObstaculos(origen, destino)){
				return false;
//				throw new MovimientoNoValido("Hay alguna pieza en el recorrido seleccionado.");				
			}
			origen.liberar();
			System.out.println(" ocupada" +origen.isOcupada());
//			System.out.println(" nombre " + origen.getPieza().getNombre());
			Pieza mata = destino.ocupar(pieza);
			if(mata != null) {
				
			}
			
			pieza.mover(origen, destino);
			guardaMovimiento(origen, destino);
			return true;
		}	
		
		
		return false;
//		throw new MovimientoNoValido("Movimiento no permitido");
		
	}

	
	/**
	 * Comprueba si en las casillas que separan 'origen' y 'destino' hay alguna
	 * ficha que impida hacer el movimiento.
	 */
	private boolean hayObstaculos(Casilla origen, Casilla destino) {
		Casilla tmp;
		Pieza p = origen.getPieza();
		
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
		int limitMax = Math.max(Math.abs(deltaX), Math.abs(deltaY));

		for (int i = 1; i < limitMax; i++) {
			tmp = getCasilla(xo + i*dirX, yo + i*dirY);
			if (tmp.isOcupada()) return true;
		}
		return false;
		
			
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
		if(casilla < 0 || casilla >= tamano*tamano) return null;
		
		int x = (int) Math.floor(casilla / tamano);
		int y = casilla % tamano;
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
	
	public void ponerCasilla(Casilla casilla, int x, int y) {
		this.casillas[x][y] = casilla;
	}
	
	
	public void guardaMovimiento(Casilla origen, Casilla destino) {
		ponerCasilla(origen, origen.getX(), origen.getY());
		ponerCasilla(destino, destino.getX(), destino.getY());
	}
	
}
