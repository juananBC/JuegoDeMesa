package Gestor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import Juego.*;
import Juego.Pieza.NOMBRE;
import Piezas.Alfil;
import Piezas.Caballo;
import Piezas.Peon;
import Piezas.Reina;
import Piezas.Rey;
import Piezas.Torre;

public class Controlador {

	private Jugador jugador1, jugador2;
	private Tablero tablero;
	private int turno;
	private Properties prop;
	
	public Controlador() {

		prop = new Properties();
		
		try {
			prop.load(new FileInputStream("C:\\Users\\JNBN007\\Desktop\\workspace\\JuegoDeMesa\\resources\\config"));
		} catch (IOException e) {}
		
		turno = 0;
		jugador1 = new Jugador();
		jugador2 = new Jugador();
		
		tablero = new Tablero();	
		colocarPiezas();
	}
	

	public Jugador getJugadorTurno() {
		Jugador jugador;		
		if(turno % 2 == 0) jugador = jugador1;
		else jugador = jugador2;
		
		turno++;
		return jugador;
	}
	
	
	public boolean mover(int idOrigen, int idDestino) {
		
		Casilla origen = tablero.getCasilla(idOrigen);
		Casilla destino = tablero.getCasilla(idDestino);
		
		if(puedeMover(origen, destino)) {

			Pieza pieza = origen.getPieza();			
			origen.liberar();

			Pieza mata = destino.ocupar(pieza);
			if(mata != null) {
				
			}
			
			pieza.mover(origen, destino);
			guardaMovimiento(origen, destino);
			return true;
		} 
		
		return false;
	}
	
	/**
	 * Realiza el movimiento desde la casilla de origen a la casilla de destino.
	 * Comprueba que es el movimiento valido de la pieza, si puede botar obstaculos
	 * o que no haya obstaculos en caso de no poder botar
	 */
	private boolean puedeMover(Casilla origen, Casilla destino) { 
	
		if(origen == null || destino == null || !origen.isOcupada()) return false;		
		
		Pieza pieza = origen.getPieza();		
		return (pieza.isValid(origen, destino) && (pieza.isPuedeSaltar() || !hayObstaculos(origen, destino)));
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
		int limitMax = Math.max(Math.abs(deltaX), Math.abs(deltaY));

		for (int i = 1; i < limitMax; i++) {
			tmp = tablero.getCasilla(xo + i*dirX, yo + i*dirY);
			if (tmp.isOcupada()) return true;
		}
		return false;			
	}
	
	
	
	
	private void guardaMovimiento(Casilla origen, Casilla destino) {
		tablero.updateCasilla(origen, origen.getX(), origen.getY());
		tablero.updateCasilla(destino, destino.getX(), destino.getY());
	}
	
	
	private void colocarPiezas() {		
		for (int x = 0; x < Tablero.TAMANO; x++) {
			for (int y = 0; y < Tablero.TAMANO; y++) {
				ponerPieza(x ,y);					
			}
		}	
	}
	
	private boolean ponerPieza(int x, int y) {

		if(y > 1 && y < 6 ) return false;
		
		Casilla casilla = tablero.getCasilla(x, y);	
		Pieza pieza = null;	
		COLOR color = (y <= 1) ? COLOR.BLANCO : COLOR.NEGRO;

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
		if(pieza == null ) return false;
		
		casilla.ocupar(pieza);
		tablero.updateCasilla(casilla, x, y);
		return true;
	}


	public Casilla getCasilla(int x, int y) {
		return tablero.getCasilla(x, y);
	}
	
	public Pieza getPieza(int x, int y) {
		return tablero.getCasilla(x, y).getPieza();
	}
	
	public Pieza getPieza(int id) {
		return tablero.getCasilla(id).getPieza();
	}
	
	public boolean isCasillaOcupada(int id) {
		return tablero.getCasilla(id).isOcupada();
	}
	
	public COLOR getColorCasilla(int id) {
		return tablero.getCasilla(id).getColor();
	}
	
	public COLOR getColorCasilla(int x, int y) {
		return tablero.getCasilla(x, y).getColor();
	}
	
	
	public Casilla getCasilla(int id) {
		return tablero.getCasilla(id);
	}
	
	public Jugador getJugador1() {
		return jugador1;
	}


	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}


	public Jugador getJugador2() {
		return jugador2;
	}


	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}


	public Tablero getTablero() {
		return tablero;
	}


	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}


	public int getTurno() {
		return turno;
	}


	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	
}
