package logico;

import java.util.ArrayList;

public class ClinicaMedica {
	
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Consulta> lasConsultas;
	
	public ClinicaMedica(ArrayList<Persona> lasPersonas, ArrayList<Consulta> lasConsultas) {
		super();
		this.lasPersonas = lasPersonas;
		this.lasConsultas = lasConsultas;
	}
	
	public ArrayList<Persona> getLasPersonas() {
		return lasPersonas;
	}
	public void setLasPersonas(ArrayList<Persona> lasPersonas) {
		this.lasPersonas = lasPersonas;
	}
	public ArrayList<Consulta> getLasConsultas() {
		return lasConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
	
}
