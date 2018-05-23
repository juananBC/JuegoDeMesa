package interfaz;

import Juego.Casilla;
import Juego.Casilla.COLOR;
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

import Juego.Tablero;

public class Juego {

	private int TAM_CASILLA, PADDING_X, PADDING_Y;
	private static final Color NEGRO = new Color(169, 169, 169);
	private static final Color BLANCO = new Color(229, 229, 229);
	private static final Color SELECCIONADA =  new Color(72, 209, 204);

	private JLabel casillaSeleccionada;
	private Tablero tablero;
	private JPanel jpTablero;
	
	public Juego(Tablero t, JPanel jpTablero) {
		this.tablero = t;
		this.jpTablero = jpTablero;		
		this.casillaSeleccionada = null;		
		
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
				Casilla casilla = tablero.getCasilla(x, y);
				System.out.println(casilla.getX() + " , " + casilla.getY() + "     " + casilla.getId());
				JLabel jCasilla = pintarCasilla(x, y, casilla);					
				jpTablero.add(jCasilla);				
			}
		}
	}
	
	
	private JLabel pintarCasilla(int x, int y, Casilla casilla) {
		COLOR color = casilla.getColor();

		JLabel jCasilla = new JLabel("");
		
		Color c;
		if(color == COLOR.BLANCO) {
			c = BLANCO; 
		//	jCasilla.setName(COLOR.BLANCO.toString());

		}else {
			c = NEGRO;
		//	jCasilla.setName(COLOR.NEGRO.toString());

		}

		jCasilla.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));		
		jCasilla.setBounds(PADDING_X + x * TAM_CASILLA, PADDING_Y + y * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
		jCasilla.setIconTextGap(1);
		jCasilla.setOpaque(true);
		jCasilla.setBackground(c);
		jCasilla.setName(casilla.getId()+"");
		
		jCasilla.addMouseListener(eventClickCasilla());
		
		Pieza pieza = casilla.getPieza();
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
	
	private ImageIcon ponerFicha(JLabel jLabel, String pieza){
		System.out.println(pieza);
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
			public void mouseClicked(MouseEvent arg0) {
				int id;
				Casilla origen = null, destino = null;
				Pieza pieza = null;
				
				// Casilla en la que se ha hecho click
				JLabel casillaActual = (JLabel) arg0.getComponent();
				id = Integer.parseInt(casillaActual.getName());
				destino = tablero.getCasilla(id);
				
				// Es un movimiento de una pieza
				if (casillaSeleccionada != null) {
					id = Integer.parseInt(casillaSeleccionada.getName());
					origen = tablero.getCasilla(id);
					pieza = origen.getPieza();
					
					if (origen.getColor() == Casilla.COLOR.BLANCO) {
						casillaSeleccionada.setBackground(BLANCO);
					} else {
						casillaSeleccionada.setBackground(NEGRO);
					}	
					
					// Mover pieza
					if(tablero.mover(origen.getId(), destino.getId())) {
						casillaSeleccionada.setIcon(null);
						ponerFicha(casillaActual, getNombrePieza(pieza));		
					}
					

					casillaSeleccionada = null;
					
					
				} else {
					// Seleccion de una pieza
					if(destino.isOcupada()) {
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
}
