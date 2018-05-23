package Piezas;

import java.util.ArrayList;
import java.util.List;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Movimiento;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Pieza;

public class Rey extends Pieza {

	public Rey(COLOR color) {
		super(color, 2, NOMBRE.REY);

		List<MOVIMIENTOS> movimientos = new ArrayList<MOVIMIENTOS>();		
		movimientos.add(MOVIMIENTOS.HORIZONTAL);
		movimientos.add(MOVIMIENTOS.DIAGONAL);		
		setMovimientos(movimientos);		
		setMatar(movimientos);	
		
	}

	@Override
	public boolean matar(Casilla origen, Casilla destino) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Casilla mover(Casilla origen, Casilla destino) {
		// TODO Auto-generated method stub
		return null;
	}

}
