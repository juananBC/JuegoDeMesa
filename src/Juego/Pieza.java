package Juego;

import java.util.ArrayList;

import Excepciones.MovimientoNoValido;

public abstract class Pieza {
	
	// El negro sera invertido de signo en los movimientos
	public  static  enum COLOR{BLANCO, NEGRO};
	private COLOR color;
	
	private ArrayList<Movimiento> movimientos;
	
	public Pieza(COLOR color) {
		this.color = color;
	}
	
	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	public void isValid(Casilla origen, Casilla destino) throws MovimientoNoValido {
		
		int direccion = (color == COLOR.BLANCO)? 1:-1;
		
		int x = destino.getX() - origen.getX();
		int y = (destino.getY() - origen.getY()) * direccion;

		// Obtiene el movimiento realizado
		Movimiento mov = new Movimiento(x, y);
		
		for(Movimiento movimiento : movimientos) {
			
			if(movimiento.comparar(mov)) {				
				return;
			}
		}
		
		throw new MovimientoNoValido("No es posible realizar este movimiento");
		
	}
	
	public abstract Casilla matar(Casilla c);
	public abstract Casilla mover(Casilla origen, Casilla destino);
	
	
	
}
