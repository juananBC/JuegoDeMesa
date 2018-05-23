package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Juego.Tablero;

import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUI {

	private JFrame frmAjedrez;

	private static Juego juego;
	private static JPanel jpTablero;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Inicio del ajedrez");
					GUI window = new GUI();
					window.frmAjedrez.setVisible(true);
					
					juego = new Juego(new Tablero(), jpTablero);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GUI() {
		initialize();

	}

	private void initialize() {
		frmAjedrez = new JFrame();
		frmAjedrez.setTitle("Ajedrez");
		frmAjedrez.setBounds(100, 100, 521, 516);
		frmAjedrez.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjedrez.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setForeground(Color.YELLOW);
		frmAjedrez.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel jpControles = new JPanel();
		jpControles.setBackground(Color.RED);
		frmAjedrez.getContentPane().add(jpControles, BorderLayout.SOUTH);
		
		jpTablero = new JPanel();
		jpTablero.setBackground(new Color(255, 255, 255));
		frmAjedrez.getContentPane().add(jpTablero, BorderLayout.CENTER);
		jpTablero.setLayout(null);
		jpTablero.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				juego.pintarTablero();
			}
		});
		

		
		
		
		
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setSize(new Dimension(3, 0));
//		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//		lblNewLabel.setBounds(53, 60, 242, 230);
//		lblNewLabel.setIconTextGap(1);
//		lblNewLabel.setOpaque(true);
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\JNBN007\\Desktop\\workspace\\JuegoDeMesa\\resources\\img\\pieza.png"));
//		lblNewLabel.setBackground(new Color(220, 220, 220));
//		jpTablero.add(lblNewLabel);
		
		

	}
}
