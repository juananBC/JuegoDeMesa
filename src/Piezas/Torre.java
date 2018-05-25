package Piezas;

import java.util.ArrayList;
import java.util.List;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Pieza;
import Juego.Tablero;

public class Torre  extends Pieza{

	public Torre(COLOR color) {
		super(color, Tablero.TAMANO - 1, NOMBRE.TORRE);		
		
		// Movimientos en diagonal
		List<MOVIMIENTOS> movimientos = new ArrayList<MOVIMIENTOS>();		
		movimientos.add(MOVIMIENTOS.HORIZONTAL);
		
		setMovimientos(movimientos);		
		setMatar(movimientos);			
	}


	@Override
	public Casilla mover() {
		// TODO Auto-generated method stub
		return null;
	}

}