package logico;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private String contrasena;
	private int idRol;
	//private Medico medicoRelacionado;
	
	public Usuario(String codigo,String nombre, String contrasena, int idRol) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.idRol = idRol;

	}

	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getRol() {
		return idRol;
	}

	public void setRol(int idRol) {
		this.idRol = idRol;
	}
	
	
	
}
