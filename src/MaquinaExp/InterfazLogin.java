package MaquinaExp;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Vista de la ventana del login.
 * @author Jose Manuel Condes Moreno.
 *
 */
public class InterfazLogin {

	private JFrame frame;
	private JTextField txt_Usuario;
	private JPasswordField txt_Password;
	private JButton btnLogin;
	private JButton btnCancelar;
	private Administrador admin;

	/**
	 * Create the application.
	 */
	public InterfazLogin() {
		initialize();
	}
	
	public void cerrarVentana(){
		frame.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial", Font.BOLD, 14));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsuario.setForeground(Color.LIGHT_GRAY);
		
		txt_Usuario = new JTextField();
		txt_Usuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblPassword.setForeground(Color.LIGHT_GRAY);
		
		txt_Password = new JPasswordField();
		
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				admin.iniciarAdmin(getUsuario(), getPassword());
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarVentana();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUsuario)
							.addGap(18)
							.addComponent(txt_Usuario))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCancelar))
								.addComponent(txt_Password))))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(txt_Usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txt_Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnCancelar))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 351, 203);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public String getUsuario(){
		return txt_Usuario.getText();
	}
	
	public String getPassword(){
		char[] pass = txt_Password.getPassword();
		String password = new String(pass);
		return password;
	}
	
	public void setAdministrador(Administrador admin){
		this.admin = admin;
	}
	
	public void ventanaEmergente(String msg){
		JOptionPane.showMessageDialog(null, msg, "Usuario o password incorrectos.", 2);
	}
}
