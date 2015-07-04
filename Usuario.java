package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Usuario implements Serializable {
	private static Usuario instance;
	private String user;
	private String pass;
	private int idUsuario;
	private int idPerfil;
	
	public Usuario(){
		this.pass=null;
		this.user=null;
		this.idPerfil=this.idUsuario=-1;
	}
	
	public static Usuario getInstance(){
		if(instance==null){
			instance=new Usuario();
		}
		return instance;
	}
	
/** ***********************************************************************************************
 * 
 * 
 * 
 * 							COMIENZO DE LOS GETTERS Y SETTERS
 * 
 * 
 * *************************************************************************************************/
	public void setUser(String us){
		this.user =us;
	}
	public void setPass(String passw){
		this.pass =passw;
	}
	public void setIdPerfil(int id){
		this.idPerfil=id;
	}
	public void setIdUsuario(int id){
		this.idUsuario=id;
	}
	public int getIdUsuario(){
		return this.idUsuario;
	}
	public int getIdPerfil(){
		return this.idPerfil;
	}
	public String getUser(){
		return this.user;
	}
	public String getPass(){
		return this.pass;
	}
	
	public Usuario loguearse()throws Exception{
		boolean b= false;
//		Usuario va a tener el usuario que devuelva el servidor
		Usuario auxiliar=null;
		Socket sc = null;
//		Va a tener falso siempre que no se devuelva nada.
		ObjectOutputStream salida=null;
		ObjectInputStream entrada=null;
		try {
			sc= new Socket("127.0.0.1",9009);
			salida=new ObjectOutputStream(sc.getOutputStream());
			salida.writeObject(this);
			entrada=new ObjectInputStream(sc.getInputStream());
			auxiliar=(Usuario)entrada.readObject();

		} catch (IOException e) {
			throw new Exception();
		}
		return auxiliar;
	}
	
	public String toString(){
		return this.user+"   "+ this.pass+" Hola estoy logueado";
	}
		
}
