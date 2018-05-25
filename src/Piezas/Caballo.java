package Piezas;

import java.util.ArrayList;
import java.util.List;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Movimiento;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Pieza;

public class Caballo extends Pieza{

	public Caballo(COLOR color) {
		super(color, 0, true, true, NOMBRE.CABALLO);

		List<MOVIMIENTOS> movimientos = new ArrayList<MOVIMIENTOS>();		
		movimientos.add(MOVIMIENTOS.L);		
		setMovimientos(movimientos);		
		setMatar(movimientos);	
	}

	@Override
	public Casilla mover() {
		// TODO Auto-generated method stub
		return null;
	}

}
