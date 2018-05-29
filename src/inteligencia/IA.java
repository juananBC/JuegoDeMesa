package inteligencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import Gestor.Controlador;
import Gestor.Estado;
import Juego.COLOR;
import Juego.Casilla;
import Juego.Movimiento.MOVIMIENTOS;
import Juego.Tablero;
import Piezas.Rey;

public class IA {

	private static final boolean MAX = true;
	private static final boolean MIN = false;
	
	private Arbol arbol;
	private int profundidad;
	private Controlador controlador;
	private COLOR color; 
	
	private BufferedWriter f;
	public IA(Controlador controlador, int profundidad, COLOR color) {
		this.profundidad = profundidad;
		this.controlador = controlador;
		this.color = color;
		
		 try {
			f = new BufferedWriter(new FileWriter("C:\\Users\\JNBN007\\Desktop\\workspace\\JuegoDeMesa\\resources\\log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public void calculaMovimiento(Tablero t) {
		
		Tablero tablero = t.copiarEstado();
		
		arbol = new Arbol(color);	
		calcNodosHijos(arbol.getRaiz(), profundidad);
		
		
		//minimax(arbol.getRaiz(), profundidad, MAX);
	}
	
	
	public int minimax(Nodo nodo, int profundidad, boolean isMaximizador) {
		
		if(profundidad == 0 || nodo.isTerminal()) 
			return nodo.getValor();
		
		int mejorValor;
		int valorActual;
		if(isMaximizador) {
			mejorValor = Integer.MIN_VALUE;
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, profundidad - 1, MIN);
				mejorValor = Math.max(valorActual,  mejorValor);
			}	
			
		} else { // Minimizador
			mejorValor = Integer.MAX_VALUE;
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, profundidad - 1, MAX);
				mejorValor = Math.min(valorActual,  mejorValor);
			}	
		}
		
		return mejorValor;
	}
	
	
	private Nodo calcNodosHijos(Nodo parent, int prof) {
		
		if(prof == 0) return null;
		
		
		for (int y = 0; y < Tablero.TAMANO; y++) {
			for (int x = 0; x < Tablero.TAMANO; x++) {
				
				// Casilla actual
				Casilla casilla = controlador.getCasilla(x, y); 				

				if(casilla.isOcupada() && casilla.getPieza().getColor() != parent.getEstado().getColor()) {	
//					System.out.println( casilla.getPieza().getColor() + "   " + parent.getEstado().getColor());

					String TAB = "";
					for(int i = profundidad - prof; i > 0; i--) TAB += "     ";
					
					Set<Integer> movs = controlador.getOpcionesMover(casilla.getId());	
					for(int idDestino: movs) {
						if (!vuelveAtras(parent, idDestino)) {
							
						
							if(controlador.mover(casilla.getId(), idDestino)) {

								
								
								
								
								
								Estado estado = controlador.getUltimoMovimiento();
								
								try {
									f.append(TAB + prof +  "  MOVIMIENTOS DE " + estado.getIdOrigen() + " -> " +  estado.getIdDestino() +  "  color: " + estado.getColor() +"\n");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
								Nodo hijo = new Nodo(parent, estado);	
								parent.addHijo(calcNodosHijos(hijo, prof - 1));
								controlador.revertir();
							}
							
						}
						
					}
				}
			}
		}
		
		return parent;
		
	}
	
	/**
	 * Comprueba si el movimiento a realizar es el que conduce hacia el nodo padre. 
	 * @return
	 */
	private boolean vuelveAtras(Nodo parent, int idDestino) {
		return idDestino == parent.getEstado().getIdOrigen();
	}
}
