package logico;

import java.util.ArrayList;

public class HistoriaClinica {
	
	private ArrayList<Consulta> lasConsultas;
	
	public HistoriaClinica() {
		super();
		lasConsultas = new ArrayList<>();
		
	}
	
	public ArrayList<Consulta> getLasConsultas() {
		return lasConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
}
