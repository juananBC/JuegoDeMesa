package Piezas;

import java.util.ArrayList;
import java.util.List;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Movimiento;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Pieza;
import Juego.Tablero;

public class Alfil extends Pieza{
	
	public Alfil(COLOR color) {
		super(color, Tablero.TAMANO - 1, NOMBRE.ALFIL);		
		
		// Movimientos en diagonal
		List<MOVIMIENTOS> movimientos = new ArrayList<MOVIMIENTOS>();		
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
