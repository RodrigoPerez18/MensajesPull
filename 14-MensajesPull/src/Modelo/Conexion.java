package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * 
 * @author Rodrigo Perez
 * este es singleton
 */
// 1.- crear una clase de tipo final
public final class Conexion {

	//2.- instanciar el objeto de tipo private static final
	private static final Conexion conexion=new Conexion();
	
	// Atributos para la conexion a la BD
	
	private String usuario="root";
	private String pass="";
	private String url="jdbc:mysql://localhost/pull";
	private ResultSet resultado=null;
	public Connection conex=null;
	private Statement sentencia;
	private String consulta;
	private String user, contra;
	private String res="no";
	
	
	//3.- Se crea el contructor de la clase de tipo private
	/**
	 * Constructor de la clase
	 */
	private Conexion(){
		
		/**
		 * Manejador de errores
		 */
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conex=DriverManager.getConnection(url,usuario,pass);
			//System.out.println("Exito al Conectar la Base de Datos");
					
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al conectar");
		}
	}
	/**
	 * Ingresa los datos
	 */
	public void Registrar(String usuario, String pass){
		try {
			consulta="insert into usuario values (null, '"+usuario+"','"+pass+"');";
			sentencia=conex.prepareStatement(consulta);
			sentencia.execute(consulta);	
			JOptionPane.showMessageDialog(null, "Contacto Registrado");
			} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, "El usuario ya existe");
		}
	}
	/**
	 * Método que realiza la consulta del Pull
	 * @return
	 */
	public ResultSet consulta(){
		try {
			consulta ="select * from mensajes";
			sentencia=conex.prepareStatement(consulta);
			resultado=sentencia.executeQuery(consulta);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pueden mostrar los mensajes");
		}
		return resultado;
		
	}
	/**
	 * Métodoq que envia el mensaje y recibe dos parametros
	 * @param comentario es el mensaje a enviar
	 * @param Usua es la persona que lo envia
	 */
	public void enviarMensaje(String comentario, String Usua){
		try {
			consulta="insert into mensajes values (null, curdate(), curtime(), '"+Usua+"','"+comentario+"'  )";
			sentencia=conex.prepareStatement(consulta);
			sentencia.execute(consulta);	
			
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, "Mensaje no enviado");
		}
	}
	/**
	 * Método que valida que el usuario se encuentre registrado
	 * @param usuario nombre del usuario
	 * @param pass contraseña del usuario
	 * @return regresa la autorización para iniciar sesión
	 */
	public String validar(String usuario, String pass){
		
		try {
			consulta="select * from usuario where usuario='"+usuario+ "'";
			sentencia=conex.prepareStatement(consulta);
			resultado=sentencia.executeQuery(consulta);
			if(resultado.next()){
				if(usuario.equals(resultado.getString(2))){
					if(pass.equals(resultado.getString(3))){
						res="si";
					}else{
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "El usuario NO EXISTE o NO SE HA REGISTRADO");
			}
			} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, "El usuario NO EXISTE o NO SE HA REGISTRADO");
		}
		return res ;
	}

	//4.- crear metodo public static que retorne instancia
	/**
	 * 
	 * @return la instancia de la clase
	 */
	public static Conexion instanciaConexion(){
		return conexion;
	}

}
