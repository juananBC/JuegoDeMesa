package Piezas;

import java.util.ArrayList;
import java.util.List;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Pieza;
import Juego.Tablero;
import Juego.Movimiento.MOVIMIENTOS;

public class Reina  extends Pieza{

	public Reina(COLOR color) {
		super(color, Tablero.TAMANO - 1, NOMBRE.REINA);		
		
		// Movimientos en diagonal
		List<MOVIMIENTOS> movimientos = new ArrayList<MOVIMIENTOS>();		
		movimientos.add(MOVIMIENTOS.HORIZONTAL);	
		movimientos.add(MOVIMIENTOS.DIAGONAL);
		
		setMovimientos(movimientos);		
		setMatar(movimientos);			
	}

	@Override
	public Casilla mover() {
		// TODO Auto-generated method stub
		return null;
	}

}