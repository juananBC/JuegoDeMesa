package Piezas;

import java.util.HashSet;
import java.util.Set;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Pieza;

public class Peon extends Pieza {

	public Peon(COLOR color) {
		super(color, 2, false, false, NOMBRE.PEON);
		
		Set<MOVIMIENTOS> movs = new HashSet<MOVIMIENTOS>();	
		movs.add(MOVIMIENTOS.HORIZONTAL);
		this.setMovimientos(movs);	
		
		Set<MOVIMIENTOS> matar = new HashSet<MOVIMIENTOS>();	
		matar.add(MOVIMIENTOS.DIAGONAL);
		
		this.setMatar(matar);			
	}

	@Override
	public Casilla mover() {
		// Despues del primer movimiento, los peones solo pueden moverse una posicion
		if(getNumPasos() > 0)	this.setDistancia(1);
		else this.setDistancia(2);
		
		return null;
	}

}
