package Piezas;

import java.util.ArrayList;
import java.util.List;

import Juego.Casilla;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Pieza;

public class Peon extends Pieza {

	public Peon(COLOR color) {
		super(color, 2, false, false, NOMBRE.PEON);
		
		List<MOVIMIENTOS> movimientos = new ArrayList<MOVIMIENTOS>();	
		movimientos.add(MOVIMIENTOS.HORIZONTAL);
		setMovimientos(movimientos);	
		
		List<MOVIMIENTOS> matar = new ArrayList<MOVIMIENTOS>();	
		matar.add(MOVIMIENTOS.DIAGONAL);
		setMatar(matar);			
	}

	@Override
	public boolean matar(Casilla origen, Casilla destino) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Casilla mover(Casilla origen, Casilla destino) {
		
		// Despues del primer movimiento, los peones solo pueden moverse una posicion
		this.setDistancia(1);
		return null;
	}

}
