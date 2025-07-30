package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idEnfermedad;
	private String nombre;
	private String sintomas;
	private int idTipoEnfermedad;
	private boolean curada;
	
	public Enfermedad(String idEnfermedad, String nombre, String sintomas, int tipo) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.idTipoEnfermedad = tipo;
	}
	
	
	public boolean isCurada() {
		return curada;
	}

	public void setCurada(boolean curada) {
		this.curada = curada;
	}



	public String getIdEnfermedad() {
		return idEnfermedad;
	}
	public void setIdEnfermedad(String idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSintomas() {
		return sintomas;
	}
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	public int getTipo() {
		return idTipoEnfermedad;
	}
	public void setTipo(int tipo) {
		this.idTipoEnfermedad = tipo;
	}
	
	
}
