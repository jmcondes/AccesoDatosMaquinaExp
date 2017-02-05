package MaquinaExp;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

/**
 * Clase que crea la interfaz principal.
 * @author Jose Manuel Condes Moreno
 *
 */
@SuppressWarnings("serial")
public class Interfaz extends JFrame{
	
	private Clasificador clasificador;
	JPanel panel_productos;
	Object[][] botones_productos;
	Object[][] botones_monedas;
	private JPanel panel;
	private JTextArea visorMaquina;
	private JPanel visor;
	private JPanel monedas;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_2;
	private int numProductos;
	private int numMonedas;
	private Main m;
	private JPanel panel_4;
	private JButton btn_Retornar;
	private JButton btn_Informacion;
	private JButton btn_Administrador;
	private JPanel panel_5;
	private JPanel panel_monedas;
	private GridBagConstraints gbc_btnNewButtonProductos;
	private GridBagConstraints gbc_btnNewLabelProductos;
	private GridBagConstraints gbc_btnNewButtonMonedas;
	private JButton btn_Salir;

	public Interfaz(Clasificador cls){
		getContentPane().setBackground(Color.DARK_GRAY);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.clasificador = cls;
		this.numProductos = cls.getDispensadores().size();
		this.numMonedas = cls.getDepositos().length;
		botones_productos = new Object[2][numProductos];
		botones_monedas = new JButton[1][numMonedas];
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		panel.add(panel_5);
		panel_productos = new JPanel();
		panel_5.add(panel_productos);
		panel_productos.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_productos.setForeground(Color.LIGHT_GRAY);
		panel_productos.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl_botones_productos = new GridBagLayout();
		panel_productos.setLayout(gbl_botones_productos);
		
		JPanel principalIzq = new JPanel();
		panel.add(principalIzq);
		principalIzq.setLayout(new BoxLayout(principalIzq, BoxLayout.Y_AXIS));
		
		visor = new JPanel();
		visor.setBackground(Color.DARK_GRAY);
		principalIzq.add(visor);
		
		panel_3 = new JPanel();
		panel_3.setSize(200, 180);
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_3.add(panel_2);
		
		visorMaquina = new JTextArea();
		panel_2.add(visorMaquina);
		visorMaquina.setWrapStyleWord(true);
		visorMaquina.setBackground(Color.LIGHT_GRAY);
		visorMaquina.setRows(30);
		visorMaquina.setColumns(40);
		visorMaquina.setForeground(Color.BLUE);
		
		panel_1 = new JPanel();
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setBackground(Color.DARK_GRAY);
		visor.setLayout(new BoxLayout(visor, BoxLayout.Y_AXIS));
		visor.add(panel_3);
		
		monedas = new JPanel();
		monedas.setSize(180, 100);
		panel_3.add(monedas);
		monedas.setBackground(Color.DARK_GRAY);
		monedas.setLayout(new GridLayout(0, 4, 0, 2));
		
		btn_Retornar = new JButton("Retornar Dinero");
		monedas.add(btn_Retornar);
		btn_Retornar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_Retornar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				m.retornarDinero();
			}
		});
		btn_Retornar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		btn_Informacion = new JButton("Informaci\u00F3n");
		monedas.add(btn_Informacion);
		btn_Informacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m.mostrarInformacion("VentanaPrincipal");
			}
		});
		btn_Informacion.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btn_Administrador = new JButton("Administrador");
		monedas.add(btn_Administrador);
		btn_Administrador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m.loginAdministrador();
			}
		});
		btn_Administrador.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btn_Salir = new JButton("Salir");
		btn_Salir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(-1);
			}
		});
		monedas.add(btn_Salir);
		visor.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_monedas = new JPanel();
		panel_4.add(panel_monedas);
		panel_monedas.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_monedas.setForeground(Color.LIGHT_GRAY);
		panel_monedas.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl_panel_monedas = new GridBagLayout();
		panel_monedas.setLayout(gbl_panel_monedas);
		
		for (int i = 0; i < numProductos; i++) {
			botones_productos[0][i] = new JButton(Integer.toString(cls.getDispensadores().get(i).getId()));
			((JButton) botones_productos[0][i]).addMouseListener(new listenerProductos());
			((JButton) botones_productos[0][i]).setAlignmentY(Component.TOP_ALIGNMENT);
			gbc_btnNewButtonProductos = new GridBagConstraints();
			gbc_btnNewButtonProductos.fill = GridBagConstraints.BOTH;
			gbc_btnNewButtonProductos.insets = new Insets(0, 0, 2, 2);
			gbc_btnNewButtonProductos.gridx = 0;
			gbc_btnNewButtonProductos.gridy = i;
			gbc_btnNewButtonProductos.gridwidth = 1;
			gbc_btnNewButtonProductos.gridheight = 1;
			panel_productos.add((JButton) botones_productos[0][i], gbc_btnNewButtonProductos);
			botones_productos[1][i] = (new JLabel((cls.getDispensadores().get(i).getNombre())));
			gbc_btnNewLabelProductos = new GridBagConstraints();
			gbc_btnNewLabelProductos.fill = GridBagConstraints.BOTH;
			gbc_btnNewLabelProductos.insets = new Insets(0, 0, 2, 2);
			gbc_btnNewLabelProductos.gridx = 1;
			gbc_btnNewLabelProductos.gridy = i;
			gbc_btnNewButtonProductos.gridwidth = 1;
			gbc_btnNewButtonProductos.gridheight = 1;
			panel_productos.add((JLabel) botones_productos[1][i], gbc_btnNewLabelProductos);
			((JLabel) botones_productos[1][i]).setForeground(Color.LIGHT_GRAY);
			((JLabel) botones_productos[1][i]).setFont(new Font("Arial", Font.PLAIN, 12));
		}

		for (int j = 0; j < this.numMonedas; j++) {
			botones_monedas[0][j] = new JButton(Integer.toString(cls.getDepositos()[j].getValor()));
			((JButton) botones_monedas[0][j]).addMouseListener(new listenerMonedas());
			((JButton) botones_monedas[0][j]).setAlignmentY(Component.TOP_ALIGNMENT);
			gbc_btnNewButtonMonedas = new GridBagConstraints();
			gbc_btnNewButtonMonedas.fill = GridBagConstraints.BOTH;
			gbc_btnNewButtonMonedas.insets = new Insets(0, 0, 2, 2);
			gbc_btnNewButtonMonedas.gridx = 0;
			gbc_btnNewButtonMonedas.gridy = j + 1;
			gbc_btnNewButtonMonedas.gridwidth = 1;
			gbc_btnNewButtonMonedas.gridheight = 1;
			panel_monedas.add((JButton) botones_monedas[0][j], gbc_btnNewButtonMonedas);
		}
	}
	
	private class listenerProductos implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent tecla) {
			for (int i = 0; i < numProductos; i++) {
				if (tecla.getSource() == botones_productos[0][i] && ((JButton) botones_productos[0][i]).isEnabled()) {
					visorMaquina.setText("Ha seleccionado " + ((JLabel) botones_productos[1][i]).getText());
					m.realizarSeleccion(((JButton) botones_productos[0][i]).getText());
				}				
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class listenerMonedas implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent teclaMoneda) {			
			for (int j = 0; j < 6; j++) {
				if (teclaMoneda.getSource() == botones_monedas[0][j] && ((JButton) botones_monedas[0][j]).isEnabled()) {
					visorMaquina.setText(((JButton) botones_monedas[0][j]).getText());
					clasificador.insertarMoneda(clasificador, ((JButton) botones_monedas[0][j]).getText());
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public void setTextoVisor(String texto){
		this.visorMaquina.setText(texto);
	}
	
	public String getTextoVisor(){
		return visorMaquina.getText();
	}
	
	public void setMain(Main main){
		this.m = main;
	}
}
