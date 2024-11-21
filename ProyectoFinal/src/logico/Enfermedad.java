package logico;

public class Enfermedad {
	
	private int idEnfermedad;
	private String nombre;
	private String sintomas;
	private String tipo;
	
	public Enfermedad(int idEnfermedad, String nombre, String sintomas, String tipo) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.tipo = tipo;
	}
	
	public int getIdEnfermedad() {
		return idEnfermedad;
	}
	public void setIdEnfermedad(int idEnfermedad) {
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
