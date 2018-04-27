package Juego;

/**
 * Indica cuantas posiciones en X e Y se puede mover una pieza
 */
public class Movimiento {

	private int x, y;
	
	public Movimiento(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public Movimiento mover(int xInicio, int yInicio) {
		int xFinal = xInicio + x;
		int yFinal = yInicio + y;
		
		Movimiento m = new Movimiento(xFinal, yFinal);
		return m;		
	}
	
	
	public boolean comparar(Movimiento mov) {
		return mov.x == this.x && mov.y == this.y;
	}
	
	
}
