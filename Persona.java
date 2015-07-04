package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Persona implements Serializable {
	private String nombre;
	private String apellido;
	private String email;
	private String user;
	private String contrasenia;
	private String confirmacion;
	private String pregunta;
	private String respuesta;
	private static Persona instance;

	private Persona() {
		this.nombre=this.apellido=this.email=this.user=this.contrasenia=this.confirmacion=null;
		}

	public static Persona getInstance() {
		if (instance == null) {
			instance = new Persona();
		}
		return instance;
	}

	public void setAtributos(String nomb, String ap, String mail,
			String usuario, String pass, String cPass, String question, String answer) {
		this.setNombre(nomb);
		this.setApellido(ap);
		this.setEmail(mail);
		this.setUser(usuario);
		this.setContrasenia(pass);
		this.setConfirmacion(cPass);
		this.setPregunta(question);
		this.setRespuesta(answer);
	}

	public Usuario crearUsuario() throws Exception {
		// Usuario va a tener el usuario que devuelva el servidor
		Usuario auxiliar = null;
		Socket sc = null;
		ObjectOutputStream salida = null;
		ObjectInputStream entrada = null;
		try {
			/** MANDO UNA PREGUNTA AL SERVIDOR Y QUE ME DEVUELVA EL NUEVO USUARIO CREADO*/
			sc = new Socket("127.0.0.1", 9008);
			salida = new ObjectOutputStream(sc.getOutputStream());
			salida.writeObject(this);
			entrada = new ObjectInputStream(sc.getInputStream());
			auxiliar = (Usuario)entrada.readObject();
		} catch (IOException e){
			/**SI NO PUEDO CONECTARME ARROJA UNA EXCEPCION*/
			throw new Exception();
		}
		return auxiliar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	@Override
	public String toString(){
		return this.apellido + "  "+ this.nombre;
	}
}
