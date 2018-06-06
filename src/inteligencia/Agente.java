package inteligencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import Fichero.Log;
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
	
	private Log log;
	
	public Agente(Controlador controlador, int profundidad, COLOR color) {
		this.profundidad = profundidad;
		this.controlador = controlador;
		this.color = color;
		
		
	
	}
	
	
	public Estado calculaMovimiento(Controlador controlador) {
			
		this.controlador = controlador;
		arbol = new Arbol(color);	
		
		log = new Log();
		profundidad = 3;
		generarHijos(arbol.getRaiz(), profundidad);		
		
//		System.out.println("ARBOL");
//		for(Nodo nodo: arbol.getRaiz().getHijos()) {
//			System.out.println(nodo.getEstado().getIdOrigen() + " " + nodo.getEstado().getIdDestino() + "   ");
//			for(Nodo hijo: nodo.getHijos()) {
//				System.out.println(" ++ " +hijo.getEstado().getIdOrigen() + " " + hijo.getEstado().getIdDestino() + "   ");
//			}
//		}
		
		System.out.println();
		
//		Estado estado = minimax(arbol.getRaiz(), profundidad , MAX);		
		
		Nodo nodo =  minimax(arbol.getRaiz(), profundidad , MAX);
		while(nodo.getParent() != arbol.getRaiz()) {
			nodo = nodo.getParent();
		}
		
		Estado estado = nodo.getEstado();
		log.cerrar();
		

		
		
		return estado;

	}
	

	public Nodo minimax(Nodo nodo, int prof, boolean isMaximizador) {

		if(prof < 0 || nodo.isTerminal() || nodo.getHijos().isEmpty() )
			return nodo; //getValor();
		
		Nodo mejorValor;
		Nodo valorActual;	
//		Estado estadoNodo = nodo.getEstado();

//		mejorValor = nodo.getHijos().get(0).getEstado();
		
		if(isMaximizador) {
			COLOR c =  (color == COLOR.BLANCO)? COLOR.NEGRO: COLOR.BLANCO;
			mejorValor = new Nodo(nodo, new Estado(controlador.getTurno(), c, Integer.MIN_VALUE));
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, prof - 1, MIN);
				System.out.println("MAX " + valorActual.getEstado().getColor() + " - " + mejorValor.getEstado().getColor() + "\n" 
						+ valorActual.getEstado().getTurno() + " - " + mejorValor.getEstado().getTurno() + "\n" );
				mejorValor = Nodo.max(valorActual,  mejorValor);
			}	
			
			updateValor(mejorValor, MIN);

//			estadoNodo.actualizaPuntos(mejorValor.getPuntuacion());
			
		} else { // Minimizador
			mejorValor = new Nodo(nodo, new Estado(controlador.getTurno(), color, Integer.MAX_VALUE));
			
			for(Nodo hijo: nodo.getHijos()) {
				valorActual = minimax(hijo, prof - 1, MAX);
				System.out.println("MIN " + valorActual.getEstado().getColor() + " - " + mejorValor.getEstado().getColor() + "\n" 
						+ valorActual.getEstado().getTurno() + " - " + mejorValor.getEstado().getTurno() + "\n" );
				mejorValor = Nodo.min(valorActual,  mejorValor);				
			}
			System.out.print(mejorValor.getValor() + "  ");
			updateValor(mejorValor, MAX);			
			System.out.println(mejorValor.getValor());
//			estadoNodo.actualizaPuntos(-mejorValor.getPuntuacion());
//			nodo.getEstado().setPuntuacion(mejorValor.getPuntuacion() - puntuacion );
		}
		
//		nodo.setEstado(estadoNodo);

		return mejorValor;
	}

	private void updateValor(Nodo nodo, boolean isMax) {
		
		Nodo parent = nodo.getParent();
		if(nodo.hasParent()) {
			int tipo = (isMax)? 1:-1;
			parent.getEstado().actualizaPuntos(tipo*nodo.getValor() );
		}
	}
	
//	public Estado minimax2(Nodo nodo, int prof, boolean isMaximizador) {
//
//		if(prof < 0 || nodo.isTerminal() || nodo.getHijos().isEmpty() )
//			return nodo.getEstado(); //getValor();
//		
//		Estado mejorValor;
//		Estado valorActual;	
////		Estado estadoNodo = nodo.getEstado();
//
////		mejorValor = nodo.getHijos().get(0).getEstado();
//		
//		if(isMaximizador) {
//			COLOR c =  (color == COLOR.BLANCO)? COLOR.NEGRO: COLOR.BLANCO;
//			mejorValor = new Estado(controlador.getTurno(), c, Integer.MIN_VALUE);
//			
//			for(Nodo hijo: nodo.getHijos()) {
//				valorActual = minimax(hijo, prof - 1, MIN);
//				mejorValor = Estado.max(valorActual,  mejorValor);
//			}	
//			
////			estadoNodo.actualizaPuntos(mejorValor.getPuntuacion());
//			
//		} else { // Minimizador
//			mejorValor = new Estado(controlador.getTurno(), color, Integer.MAX_VALUE);
//			
//			for(Nodo hijo: nodo.getHijos()) {
//				valorActual = minimax(hijo, prof - 1, MAX);
//				mejorValor = Estado.min(valorActual,  mejorValor);				
//			}	
//			
////			estadoNodo.actualizaPuntos(-mejorValor.getPuntuacion());
////			nodo.getEstado().setPuntuacion(mejorValor.getPuntuacion() - puntuacion );
//		}
//		
////		nodo.setEstado(estadoNodo);
//
//		return mejorValor;
//	}
	
	
	private Nodo generarHijos(Nodo parent, int prof) {
		
		if(prof == 0 || parent.isTerminal()) {
			Estado estado = parent.getEstado();
			Pieza pieza = estado.getMata();
			if(pieza != null) 	{	
				 estado.setPuntuacion(pieza.getValorMuerte());
			}

//			if( parent.isTerminal() && pieza != null)
//				System.out.println("MATA: "+prof + "   " + pieza.getNombre() );
			
			// TODO: Hay que mirar de sumar/restar las muertes de toda la rama
//			if (parent.hasParent()) {
//				System.out.print("Padre = " + parent.getParent().getEstado().getPuntuacion() + "   hijo = " + estado.getPuntuacion());
//				if (estado.getColor() == color) {
//					estado.actualizaPuntos(parent.getParent().getEstado().getPuntuacion());
//				} else {
//					estado.actualizaPuntos((-1) * parent.getParent().getEstado().getPuntuacion());
//				}
//				System.out.println("  Padre = " + parent.getParent().getEstado().getPuntuacion() + "   hijo = " + estado.getPuntuacion());
//
//			}
			
			parent.setTerminal();
			return parent;
		}
		
		
		for (int y = 0; y < Tablero.TAMANO; y++) {
			for (int x = 0; x < Tablero.TAMANO; x++) {
				
				Casilla casilla = controlador.getCasilla(x, y); 				

				if(piezaPosible(casilla)) {
					
					Set<Integer> movs = controlador.getOpcionesMover(casilla.getId());	
					for(int idDestino: movs) {	
						
						if(!vuelveAtras(parent, idDestino)) {	
							if(controlador.mover(casilla.getId(), idDestino)) {
								
								Estado estado = controlador.getUltimoMovimiento();								
								estado.setProfundidad(profundidad - prof);
		
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
		Pieza pieza = controlador.getPieza(estado.getIdDestino());
		log.append(TAB + prof + " NODO " + pieza.getNombre() + " " + estado.getColor() + ": " + estado.getIdOrigen()
				+ " -> " + estado.getIdDestino() + " (" + estado.getPuntuacion()+ ")\n");
	
	}
}
