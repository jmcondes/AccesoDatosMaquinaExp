package MaquinaExp;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
/**
 * Clase que crea la interfaz de administrador.
 * @author Jose Manuel Condes Moreno
 *
 */
@SuppressWarnings("serial")
public class InterfazAdmin extends JFrame{
	
	JPanel panel_productos;
	Object[][] botones_productos;
	Object[][] botones_monedas;
	private JPanel panel;
	private JTextArea visorMaquinaAdmin;
	private JPanel visor;
	private JPanel monedas;
	private JPanel panel_3;
	private JPanel panel_2;
	private int numProductos;
	private int numMonedas;
	private Main m;
	private JPanel panel_4;
	private JPanel panel_monedas;
	private JPanel panel_6;
	private GridBagConstraints gbc_btnNewButtonProductos;
	private GridBagConstraints gbc_btnNewLabelProductos;
	private GridBagConstraints gbc_btnNewButtonMonedas;
	private JTextField txtCantidadProducto;
	private JTextField txtCantidadMonedas;
	private JLabel lblCantidadproductos;
	private JButton btnAddProduct;
	private JLabel lblCantidadMonedas;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnInformacion;
	private JButton btnSalir;
	private int precioProductoNuevo;
	private int cantidadProductoNuevo;

	public InterfazAdmin(Clasificador cls){
		getContentPane().setBackground(Color.DARK_GRAY);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
		
		panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		panel.add(panel_6);
		panel_productos = new JPanel();
		panel_6.add(panel_productos);
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
		
		visorMaquinaAdmin = new JTextArea();
		panel_2.add(visorMaquinaAdmin);
		visorMaquinaAdmin.setWrapStyleWord(true);
		visorMaquinaAdmin.setBackground(Color.LIGHT_GRAY);
		visorMaquinaAdmin.setRows(30);
		visorMaquinaAdmin.setColumns(50);
		visorMaquinaAdmin.setForeground(Color.BLUE);
		
		monedas = new JPanel();
		monedas.setSize(180, 100);
		panel_3.add(monedas);
		monedas.setBackground(Color.DARK_GRAY);
		
		txtCantidadProducto = new JTextField();
		txtCantidadProducto.setColumns(10);
		
		txtCantidadMonedas = new JTextField();
		txtCantidadMonedas.setColumns(10);
		
		btnAddProduct = new JButton("A\u00F1adir Producto");
		btnAddProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!txtNombre.getText().equals("")){
					try{
						precioProductoNuevo = Integer.parseInt(txtPrecio.getText());
						cantidadProductoNuevo = Integer.parseInt(txtCantidad.getText());
						m.addNuevoProducto();
						resetTextFields();
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "El precio y la cantidad de productos deben ser valores numéricos.", "Error en la entrada de datos.", 2);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "El nombre del producto no puede estar vacío.", "Error en la entrada de datos.", 2);
				}
			}
		});
		
		lblCantidadproductos = new JLabel("Cantidad Productos");
		lblCantidadproductos.setForeground(Color.LIGHT_GRAY);
		lblCantidadproductos.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblCantidadMonedas = new JLabel("Cantidad Monedas");
		lblCantidadMonedas.setForeground(Color.LIGHT_GRAY);
		lblCantidadMonedas.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.LIGHT_GRAY);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(Color.LIGHT_GRAY);
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setForeground(Color.LIGHT_GRAY);
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtCantidad = new JTextField();
		txtCantidad.setText("");
		txtCantidad.setColumns(10);
		
		btnInformacion = new JButton("Informaci\u00F3n");
		btnInformacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				m.mostrarInformacionAdmin("VentanaAdmin");
			}
		});
		
		btnSalir = new JButton("SALIR");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m.cerrarInterfazAdmin();
				m.iniciarTodo();
			}
		});
		
		GroupLayout gl_monedas = new GroupLayout(monedas);
		gl_monedas.setHorizontalGroup(
			gl_monedas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_monedas.createSequentialGroup()
					.addContainerGap(39, Short.MAX_VALUE)
					.addGroup(gl_monedas.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_monedas.createSequentialGroup()
							.addComponent(btnAddProduct)
							.addGap(27)
							.addComponent(lblNombre)
							.addGap(9))
						.addGroup(gl_monedas.createSequentialGroup()
							.addGroup(gl_monedas.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCantidadproductos)
								.addComponent(lblCantidadMonedas))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_monedas.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtCantidadProducto)
						.addComponent(txtCantidadMonedas)
						.addGroup(gl_monedas.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_monedas.createParallelGroup(Alignment.LEADING)
								.addComponent(btnInformacion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
					.addGap(29)
					.addComponent(lblPrecio, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_monedas.setVerticalGroup(
			gl_monedas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_monedas.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_monedas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidadproductos)
						.addComponent(txtCantidadProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_monedas.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCantidadMonedas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCantidadMonedas))
					.addGap(18)
					.addGroup(gl_monedas.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddProduct)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrecio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnInformacion)
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addComponent(btnSalir)
					.addContainerGap())
		);
		monedas.setLayout(gl_monedas);
		visor.setLayout(new BoxLayout(visor, BoxLayout.Y_AXIS));
		visor.add(panel_3);
		
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
			gbc_btnNewButtonMonedas.gridy = j;
			gbc_btnNewButtonMonedas.gridwidth = 1;
			gbc_btnNewButtonMonedas.gridheight = 1;
			panel_monedas.add((JButton) botones_monedas[0][j], gbc_btnNewButtonMonedas);
		}
	}
	
	private class listenerProductos implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent tecla) {

			try{
				for (int i = 0; i < numProductos; i++) {
					if (tecla.getSource() == botones_productos[0][i] && ((JButton) botones_productos[0][i]).isEnabled()) {
						visorMaquinaAdmin.setText("Ha seleccionado " + ((JLabel) botones_productos[1][i]).getText());
						m.reponerProductos(Integer.parseInt(((JButton) botones_productos[0][i]).getText()), ((JLabel) botones_productos[1][i]).getText());
						resetTextFields();
					}				
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Introduzca la cantidad de productos a reponer.", "Error en la entrada de datos.", 2);
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
			try{
				for (int j = 0; j < 6; j++) {
					if (teclaMoneda.getSource() == botones_monedas[0][j] && ((JButton) botones_monedas[0][j]).isEnabled()) {
						m.reponerMonedas(Integer.parseInt(((JButton) botones_monedas[0][j]).getText()));
						resetTextFields();
					}
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Introduzca la cantidad de monedas a reponer.", "Error en la entrada de datos.", 2);
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
	
	public void setTextoVisorAdmin(String texto){
		this.visorMaquinaAdmin.setText(texto);
	}
	
	public String getTextoVisor(){
		return visorMaquinaAdmin.getText();
	}
	
	public void setMain(Main main){
		this.m = main;
	}
	
	public String getNombreProducto(){
		return txtNombre.getText();
	}
	
	public int getPrecioProducto(){		
		return precioProductoNuevo;
	}
	
	public int getCantidadProducto(){
		return cantidadProductoNuevo;
	}
	
	public int getCantidadRepProducto(){
		return Integer.parseInt(txtCantidadProducto.getText());
	}
	
	public int getCantidadRepMoneda(){
		return Integer.parseInt(txtCantidadMonedas.getText());
	}
	
	public void resetTextFields(){
		txtNombre.setText("");
		txtCantidad.setText("");
		txtPrecio.setText("");
		txtCantidadProducto.setText("");
		txtCantidadMonedas.setText("");
	}
}
