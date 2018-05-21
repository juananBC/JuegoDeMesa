package interfaz;

import Juego.Casilla;
import Juego.Casilla.COLOR;
import Juego.Pieza;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.Tablero;

public class Juego {

	private int TAM_CASILLA, PADDING_X, PADDING_Y;
	private static final Color NEGRO = new Color(169, 169, 169);
	private static final Color BLANCO = new Color(229, 229, 229);

	public Juego(Tablero t, JPanel jpTablero) {

		jpTablero.removeAll();
		
		int size = Math.min(jpTablero.getWidth(), jpTablero.getHeight());
		TAM_CASILLA = (int)( size / Tablero.TAMANO);
		PADDING_X = jpTablero.getWidth() -  jpTablero.getHeight();
		PADDING_X = (PADDING_X < 0)? 0: PADDING_X / 2;

		PADDING_Y = jpTablero.getHeight() - jpTablero.getWidth()  ;
		PADDING_Y = (PADDING_Y < 0)? 0: PADDING_Y / 2;
		
		for (int y = 0; y < Tablero.TAMANO; y++) {
			for (int x = 0; x < Tablero.TAMANO; x++) {
				Casilla casilla = t.getCasilla( x, y);
				JLabel jCasilla = pintarCasilla(x, y, casilla);	
				
				jpTablero.add(jCasilla);				
			}
		}
	}

	public JLabel pintarCasilla(int x, int y, Casilla casilla) {
		COLOR color = casilla.getColor();
		Color c = (color == COLOR.BLANCO) ? BLANCO : NEGRO;

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));		
		
		lblNewLabel.setBounds(PADDING_X + x * TAM_CASILLA, PADDING_Y + y * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
		lblNewLabel.setIconTextGap(1);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(c);
		
		Pieza pieza = casilla.getPieza();
		if(pieza != null) {
			ImageIcon img = ponerFicha(lblNewLabel, pieza.getNombre() + "-" + pieza.getColor().toString().charAt(0));
			lblNewLabel.setIcon(img);
		}
		return lblNewLabel;
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
	    
	    return imageIcon;
	}

}
