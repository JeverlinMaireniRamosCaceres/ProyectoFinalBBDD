package logico;

import java.util.Date;

public class Medico extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int idEspecialidad;
	protected int exequatur;
	protected Usuario usuario;
	
	public Medico(String idPersona, String cedula, String nombre, String apellido, String telefono, String direccion,
			Date fechaNacimiento, int edad, String sexo, int idEspecialidad, int exequatur) {
		super(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, edad, sexo);
		this.idEspecialidad = idEspecialidad;
		this.exequatur = exequatur;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getEspecialidad() {
		return idEspecialidad;
	}

	public void setEspecialidad(int especialidad) {
		this.idEspecialidad = especialidad;
	}

	public int getExequatur() {
		return exequatur;
	}

	public void setExequatur(int exequatur) {
		this.exequatur = exequatur;
	}
	

}
