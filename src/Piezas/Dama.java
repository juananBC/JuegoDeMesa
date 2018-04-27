package Piezas;

import java.util.ArrayList;

import Juego.Casilla;
import Juego.Movimiento;
import Juego.Pieza;

public class Dama extends Pieza {

	
	public Dama(COLOR color) {		
		super(color);
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();		
		movimientos.add(new Movimiento(1, 1));	
		movimientos.add(new Movimiento(-1, 1));
		
		setMovimientos(movimientos);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Casilla mover(Casilla origen, Casilla destino) {	
		
		
		Movimiento posicion = new Movimiento(c.getX(), c.getY());
		
		Casilla destino;
		return null;
	}

	@Override
	public Casilla matar(Casilla c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
