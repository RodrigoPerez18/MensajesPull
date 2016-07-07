package Vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
 *
 */
public final class Mensajes extends JFrame implements ActionListener{
	// Crea ña instancia
	private static final Mensajes mensajes=new Mensajes();
	Conexion instConex=Conexion.instanciaConexion();
	
	/**
	 * atributos de la clase
	 */
	private JLabel lbTit =new  JLabel("Mensajes Pull");
	private JLabel lbSubT=new JLabel("Escribe tu comentario a publicar en el Pull");
	private JTextField txtMensaje=new JTextField();
	private Container c=getContentPane();
	private JButton btnEnviar=new JButton("Enviar");
	private JButton btnSesion=new JButton("Salir");
	private JTextPane Jpane=new JTextPane();
	private JComboBox cmbContactos=new JComboBox();
	private String usuario, comentario;
	private String mens;
	private ResultSet resultado=null;
	
	//Instancia de la clase principal
	//Imagen
	private ImageIcon salir=new ImageIcon(super.getClass().getResource("/Icons/salir.png"));

	/**
	 * Constructor de la clase de tipo private
	 */
	private Mensajes(){
		super.setTitle("Mensajes Pull");
		super.setSize(450, 450);
		setLocationRelativeTo(null);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cargarObjetos();
	}
	/**
	 * Método que contiene los objetos y la ubicación de los mismos
	 */
	private void cargarObjetos() {
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		getContentPane();
		
		lbTit.setBounds(150, 10, 150, 40);
		c.add(lbTit);
		
		Jpane.setBounds(10,50, 420, 280);
		Jpane.setForeground(Color.GREEN);
		Jpane.disable();
		Jpane.setDisabledTextColor(Color.BLUE);
		c.add(Jpane);
		
		lbSubT.setBounds(60, 330, 250, 30);
		c.add(lbSubT);
		
//		cmbContactos.setBounds(215, 330, 120, 25);
//		cmbContactos.addItem("Hola");
//		cmbContactos.setSelectedItem(Color.MAGENTA);
//		cmbContactos.setBackground(Color.WHITE);
//		cmbContactos.setForeground(Color.BLUE);
//		c.add(cmbContactos);
		
		txtMensaje.setBounds(60, 360, 240, 30);
		c.add(txtMensaje);
		
		btnEnviar.setBounds(305, 360, 75,30);
		btnEnviar.addActionListener(this);
		c.add(btnEnviar);
		
		btnSesion.setBounds(360, 2, 80, 40);
		btnSesion.setBorder(null);
		btnSesion.setBackground(Color.WHITE);
		btnSesion.setBorder(null);
		btnSesion.setFocusable(false);
		btnSesion.setIcon(salir);
		btnSesion.addActionListener(this);
		c.add(btnSesion);
		llenarPane();
	}
	/**
	 * Acciones del Botón y realiza las validaciones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSesion){
			int i=JOptionPane.showConfirmDialog(this,"¿Deseas Cerrar Sesión?","Cerrar Sesión",JOptionPane.YES_NO_OPTION);
			if(i==0){
				Principal principal=Principal.instanciaPrincipal();
			
				principal.setVisible(true);
				principal.limpiarCajas();
				this.dispose();
				
			}else if(i==1){
				
			}	
		}else if(e.getSource()==btnEnviar){
			if(txtMensaje.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Necesitas Ingresar un Mensaje");
			}else{
				//usuario=lbTit.getText();
				comentario=txtMensaje.getText();
				instConex.enviarMensaje(comentario, usuario);
				txtMensaje.setText(null);
				llenarPane();
			}
		}
		
	}
	/**
	 * Método que recibe y muestra el nombre del usuario asi como la venta
	 * @param nom Recibe el nombre del usuario
	 */
	public void vista(String nom){
		mensajes.setVisible(true);
		lbTit.setText("Bienvenido "+nom);
		usuario=nom;
		
	}
	/**
	 * Método que llena muestra los mensajes
	 */
	public void llenarPane(){
		mens="";
		resultado=instConex.consulta();
		try {
			while (resultado.next()) {
				mens=mens +resultado.getString(5)+ "\t\t" + resultado.getString(4)+ "<--> " + resultado.getString(3)+ " " + resultado.getString(2)+"\n";
				
				
			}
			Jpane.setText(mens);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Método que retorna la instancia
	 * @return regresa la instancia
	 */
	public static Mensajes instanciaMensajes(){
		return mensajes;
	}

}

