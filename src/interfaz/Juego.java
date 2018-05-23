package interfaz;

import Juego.COLOR;
import Juego.Casilla;
import Juego.Pieza;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gestor.Controlador;
import Juego.Tablero;

public class Juego {

	public static enum DIRECCION{ARRIBA, DERECHA, ABAJO, IZQUIERDA};
	
	private static final Color NEGRO = new Color(169, 169, 169);
	private static final Color BLANCO = new Color(229, 229, 229);
	private static final Color SELECCIONADA =  new Color(72, 209, 204);

	private  int TAM_CASILLA, PADDING_X, PADDING_Y;
	private DIRECCION orientacion;
	private JLabel casillaSeleccionada;
	private Controlador controlador;
	private JPanel jpTablero;
	
	public Juego(Controlador controlador, JPanel jpTablero) {
		this.controlador = controlador;
		this.jpTablero = jpTablero;		
		this.casillaSeleccionada = null;		
		
		this.orientacion = DIRECCION.ARRIBA;		
		pintarTablero();		
	}

	private void calcPadding(JPanel jpTablero) {
		int size = Math.min(jpTablero.getWidth(), jpTablero.getHeight());
		TAM_CASILLA = (int)( size / Tablero.TAMANO);
		PADDING_X = jpTablero.getWidth() -  jpTablero.getHeight();
		PADDING_X = (PADDING_X < 0)? 0: PADDING_X / 2;

		PADDING_Y = jpTablero.getHeight() - jpTablero.getWidth();
		PADDING_Y = (PADDING_Y < 0)? 0: PADDING_Y / 2;
	}
	
	
	public void pintarTablero() {
		this.jpTablero.removeAll();
		calcPadding(jpTablero);

		for (int y = 0; y < Tablero.TAMANO; y++) {
			for (int x = 0; x < Tablero.TAMANO; x++) {
				JLabel jCasilla = pintarCasilla(x, y);					
				jpTablero.add(jCasilla);				
			}
		}
	}
	
	
	private JLabel pintarCasilla(int x, int y) {
		COLOR color = controlador.getColorCasilla(x, y);

		JLabel jCasilla = new JLabel("");
		
		Color c;
		if(color == COLOR.BLANCO) {
			c = BLANCO; 
		}else {
			c = NEGRO;
		}


		jCasilla.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));		
		jCasilla.setIconTextGap(1);
		jCasilla.setOpaque(true);
		jCasilla.setBackground(c);
		jCasilla.setName(controlador.getCasilla(x, y).getId()+"");		
		jCasilla.addMouseListener(eventClickCasilla());
		setBounds(x, y, jCasilla);
		
		Pieza pieza = controlador.getPieza(x, y);
		if(pieza != null) {
			ponerFicha(jCasilla, getNombrePieza(pieza));
		}
		return jCasilla;
	}
	
//	private HashMap<String,ImageIcon> cargarImagenes(){
//		HashMap<String,ImageIcon> mapa = new HashMap<String,ImageIcon>();
//)
//		for(Pieza.NOMBRE pieza: Pieza.NOMBRE.values()) {
//			mapa.put(pieza+COLOR.BLANCO.name(), pieza+"-N");
//		}
//		return mapa;
//	}
	
	
	private void setBounds(int x, int y, JLabel jCasilla) {
		int xPos = x;
		int yPos = y;

		switch(orientacion) {
		case ARRIBA:
			xPos = x;
			yPos = y; 
			break;
		case ABAJO:
			xPos = 7 - x;
			yPos = 7 - y; 
			break;
		case IZQUIERDA:
			xPos = y;
			yPos = x; 
			break;
		case DERECHA:
			xPos =  7 - y;
			yPos =  7 - x; 
			break;
			
		}
		
		xPos = PADDING_X + xPos * TAM_CASILLA;
		yPos = PADDING_Y + yPos * TAM_CASILLA; 
		jCasilla.setBounds(xPos, yPos, TAM_CASILLA, TAM_CASILLA);
	}
	
	private ImageIcon ponerFicha(JLabel jLabel, String pieza){
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\JNBN007\\Desktop\\workspace\\JuegoDeMesa\\resources\\img\\"+pieza+".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(jLabel.getWidth(), jLabel.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	    imageIcon = new ImageIcon(newimg);  // transform it back
	    
	    jLabel.setIcon(imageIcon);
	    
	    return imageIcon;
	}

	private String getNombrePieza(Pieza pieza) {
		return  pieza.getNombre() + "-" + pieza.getColor().toString().charAt(0);
	}
	
	private MouseAdapter eventClickCasilla() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int idOrigen, idDestino;
				Pieza piezaDestino, piezaOrigen = null;
				
				// Casilla en la que se ha hecho click
				JLabel casillaActual = (JLabel) arg0.getComponent();
				idDestino = Integer.parseInt(casillaActual.getName());
				
				// Es un movimiento de una pieza
				if (casillaSeleccionada != null) {
					idOrigen = Integer.parseInt(casillaSeleccionada.getName());
					piezaOrigen = controlador.getPieza(idOrigen);
					
					if (controlador.getColorCasilla(idOrigen) == COLOR.BLANCO) {
						casillaSeleccionada.setBackground(BLANCO);
					} else {
						casillaSeleccionada.setBackground(NEGRO);
					}	
					
					piezaDestino = controlador.getPieza(idDestino);					
					if(piezaDestino != null && piezaDestino.getColor() == piezaOrigen.getColor()) {
						casillaSeleccionada = casillaActual;
						casillaSeleccionada.setBackground(SELECCIONADA);
					} else {

						// Mover pieza
						if (controlador.mover(idOrigen, idDestino)) {
							casillaSeleccionada.setIcon(null);
							ponerFicha(casillaActual, getNombrePieza(piezaOrigen));
						}

						casillaSeleccionada = null;
					}
					
					
				} else {
					// Seleccion de una pieza
					if(controlador.isCasillaOcupada(idDestino)) {
						casillaSeleccionada = casillaActual;
						casillaSeleccionada.setBackground(SELECCIONADA);
					}
				}

				
				
				
				
				
				
//				
//				System.out.println("MUEVE: " );
//
//				
//				// Matar enemigo o mover aliado
//				if (destino.isOcupada()) {
//					System.out.println("MATAR " + destino.getPieza().getNombre());
//
//					// Matar enemigo
//					if(casillaSeleccionada != null) {
//						id = Integer.parseInt(casillaSeleccionada.getName());
//						destino = tablero.getCasilla(id);
//						pieza = destino.getPieza();
//						
//						casillaSeleccionada.setIcon(null);	
//						casillaSeleccionada = null;
//						
//						ponerFicha(casillaActual, getNombrePieza(pieza));
//
//					// Mover aliado
//					} else {
//						// Libera la casilla anterior
//						casillaSeleccionada = casillaActual;
//						casillaSeleccionada.setBackground(SELECCIONADA);
//					}
//					
//
//				// Casilla libre --> movimiento de aliado	
//				} else {	
//					
//					
//					if(casillaSeleccionada == null) return;
//
//					id = Integer.parseInt(casillaSeleccionada.getName());
//					origen = tablero.getCasilla(id);
//					pieza = origen.getPieza();
// 
//					casillaSeleccionada.setIcon(null);
//					ponerFicha(casillaActual,  getNombrePieza(pieza));
//					
//					casillaSeleccionada = null;
//				}
//				
			}
		};
	}
	
	
	private void posiblesMovimiento(Casilla casilla) {
		Pieza pieza = casilla.getPieza();
		pieza.mover(casilla, new Casilla(5,5));
	}

	public DIRECCION getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(DIRECCION orientacion) {
		this.orientacion = orientacion;
	}
	
	
	
}
