package logico;

import java.util.Date;

public class Paciente extends Persona {

	protected HistoriaClinica miHistorial;
	protected float estatura;
	protected float peso;
	
	public Paciente(int idPersona, String cedula, String nombre, String apellido, String telefono, String direccion,
			Date fechaNacimiento, int edad, char sexo, HistoriaClinica miHistorial, float estatura, float peso) {
		super(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, edad, sexo);
		this.miHistorial = miHistorial;
		this.estatura = estatura;
		this.peso = peso;
		
	}

	public HistoriaClinica getMiHistorial() {
		return miHistorial;
	}

	public void setMiHistorial(HistoriaClinica miHistorial) {
		this.miHistorial = miHistorial;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	
}
