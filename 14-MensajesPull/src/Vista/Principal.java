package Vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Modelo.Conexion;

/**
 * 
 * @author Rodrigo Perez 
 * 1-Final class
 */
public final class Principal extends JFrame implements ActionListener{
/**
 * 2- crear la variable instanca
 */
	private static final Principal principal=new Principal();
	Conexion instConex=Conexion.instanciaConexion();
	/**
	 * atributos de la clase
	 */
	private JLabel lbTit=new JLabel("Mensajes Pull");
	private JLabel lbUsuario=new JLabel("Usuario");
	private JLabel lbContra=new JLabel("Contraseña");
	private JTextField txtUsuario=new JTextField();
	private JPasswordField txtContra=new JPasswordField();
	private JButton btnSesion=new JButton("Entrar");
	private JButton btnRegistrarse=new JButton("Registrarse");
	private Container c=getContentPane();
	private String usuario, pass;
	// Instancia de la clase Mensjaes
	Mensajes mensajes=Mensajes.instanciaMensajes();
	
	/**
	 * Constructor de la clase de tipo final
	 */
	private Principal(){
		super.setTitle("Mensajes Pull");
		super.setSize(300,250);
		setLocationRelativeTo(null);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cargarObjetos();
		limpiarCajas();
	}
	/**
	 * Se cargan los objetos y se asigna la ubicación
	 */
	private void cargarObjetos() {
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		
		lbTit.setBounds(110, 10, 100, 40);
		c.add(lbTit);
		
		lbUsuario.setBounds(20, 60, 80, 30);
		c.add(lbUsuario);
		
		txtUsuario.setBounds(100, 60, 170, 30);
		c.add(txtUsuario);
		
		lbContra.setBounds(20, 100, 80, 30);
		c.add(lbContra);
		txtContra.setBounds(100, 100, 170, 30);
		
		c.add(txtContra);
		
		btnSesion.setBounds(140, 140, 80, 30);
		btnSesion.setBackground(Color.GREEN);
		btnSesion.addActionListener(this);
		c.add(btnSesion);
		
		btnRegistrarse.setBounds(125, 175, 110, 30);
		btnRegistrarse.setBackground(Color.ORANGE);
		btnRegistrarse.addActionListener(this);
		c.add(btnRegistrarse);
		
	}
	/**
	 * Acción de los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSesion){
			if(txtUsuario.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Necesitas ingresar el USUARIO");
			}else if(txtContra.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Necesitas ingresar la CONTRASEÑA");
			}else{
				usuario=txtUsuario.getText();
				pass=txtContra.getText();
				String resp=instConex.validar(usuario, pass);
				if(resp.equals("si")){
					mensajes.vista(usuario);
					this.dispose();
				}
			}
		}if(e.getSource()==btnRegistrarse){
			if(txtUsuario.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Necesitas ingresar el USUARIO");
			}else if(txtContra.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Necesitas ingresar la CONTRASEÑA");
			}else{
				usuario=txtUsuario.getText();
				pass=txtContra.getText();
				instConex.Registrar(usuario, pass);
				limpiarCajas();
				
				}
			}
	}
	
	/**
	 * Limpia las cajas
	 */
	public void limpiarCajas(){
		txtUsuario.setText(null);
		txtContra.setText(null);
	}
	/**
	 * Método que retorna la instancia de la clase
	 * @return retorna la instancia
	 */
	public static  Principal instanciaPrincipal(){
		return principal;
		
	}

}
