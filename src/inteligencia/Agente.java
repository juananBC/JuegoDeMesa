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
import Juego.Pieza;
import Juego.Tablero;
import Piezas.Rey;

public class Agente {

	private static final boolean MAX = true;
	private static final boolean MIN = false;
	
	private Arbol arbol;
	private int profundidad;
	private Controlador controlador;
	private COLOR color; 
	
	private BufferedWriter fichero;
	
	public Agente(Controlador controlador, int profundidad, COLOR color) {
		this.profundidad = profundidad;
		this.controlador = controlador;
		this.color = color;
		
		 try {
			fichero = new BufferedWriter(new FileWriter("C:\\Users\\JNBN007\\Desktop\\workspace\\JuegoDeMesa\\resources\\resultado.log"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public Estado calculaMovimiento(Controlador controlador) {
			
		this.controlador = controlador;
		arbol = new Arbol(color);	
		try {
			fichero.write("");
		} catch (IOException e) {
		}
		
		
		generarHijos(arbol.getRaiz(), profundidad);		
//		Estado estado = minimax(arbol.getRaiz(), profundidad, MAX);		
//		return estado;
		try {
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Estado minimax(Nodo nodo, int prof, boolean isMaximizador) {

		if(prof == 0 || nodo.isTerminal() )
			return nodo.getEstado(); //getValor();
		
		Estado mejorValor;
		Estado valorActual;		
//		int puntuacion = nodo.getEstado().getPuntuacion();
		
		if(isMaximizador) {
			COLOR c =  (color == COLOR.BLANCO)? COLOR.NEGRO: COLOR.BLANCO;
			mejorValor = new Estado(controlador.getTurno(), c, Integer.MIN_VALUE);
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, prof - 1, MIN);
				mejorValor = Estado.max(valorActual,  mejorValor);
			}	
			
//			nodo.getEstado().setPuntuacion(puntuacion + mejorValor.getPuntuacion());
			
		} else { // Minimizador
			mejorValor = new Estado(controlador.getTurno(), color, Integer.MAX_VALUE);
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, prof - 1, MAX);
				mejorValor = Estado.min(valorActual,  mejorValor);				
			}	
			
//			nodo.getEstado().setPuntuacion(mejorValor.getPuntuacion() - puntuacion );
		}
		
		return mejorValor;
	}
	
	
	private Nodo generarHijos(Nodo parent, int prof) {
		
		if(prof == 0 || parent.isTerminal()) {
			Estado estado = parent.getEstado();
			Pieza pieza = estado.getMata();
			if(pieza != null) 		
				 estado.setPuntuacion(pieza.getValorMuerte());


			if( parent.isTerminal() && pieza != null)
				System.out.println("MATA: "+prof + "   " + pieza.getNombre() );
			
			// TODO: Hay que mirar de sumar/restar las muestres de toda la rama
//			if (parent.hasParent()) {
//				if (estado.getColor() == color) {
//					estado.actualizaPuntos(parent.getParent().getEstado().getPuntuacion());
//				} else {
//					estado.actualizaPuntos((-1) * parent.getParent().getEstado().getPuntuacion());
//				}
//			}
			
			parent.setTerminal();
			return null;
		}
		
		
		for (int y = 0; y < Tablero.TAMANO; y++) {
			for (int x = 0; x < Tablero.TAMANO; x++) {
				
				// Casilla actual
				Casilla casilla = controlador.getCasilla(x, y); 				

				if(piezaPosible(casilla)) {
//					System.out.println( casilla.getPieza().getColor() + "   " + controlador.getJugadorActual().getColor());
					//parent.getEstado().getColor()) {	
//					System.out.println( casilla.getPieza().getColor() + "   " + parent.getEstado().getColor());

					Set<Integer> movs = controlador.getOpcionesMover(casilla.getId());	
					for(int idDestino: movs) {	
						
						if (!vuelveAtras(parent, idDestino)) {	
							if(controlador.mover(casilla.getId(), idDestino)) {
								
								Estado estado = controlador.getUltimoMovimiento();
								
								
						writeLog(estado, prof);
						
								Nodo hijo = new Nodo(parent, estado);	
								hijo = generarHijos(hijo, prof - 1);
								parent.addHijo(hijo);
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
	
	
	private boolean piezaPosible(Casilla casilla) {
		return casilla.isOcupada() && casilla.getPieza().getColor() == controlador.getJugadorActual().getColor();
	}
	
	private void writeLog(Estado estado, int prof) {
		String TAB = "";
		for(int i = profundidad - prof; i > 0; i--) TAB += "\t";
		try {			
			Pieza pieza = controlador.getPieza(estado.getIdDestino());
			fichero.append(TAB + prof +  " NODO " + pieza.getNombre() + " "+ estado.getColor() + ": "+ estado.getIdOrigen() + " -> " +  estado.getIdDestino() 
			+   " ("+estado.getPuntuacion()+ ")\n");
		} catch (Exception e) {
			System.out.println("Error escritura");
			e.printStackTrace();
		}
	}
}
