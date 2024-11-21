package logico;

import java.util.ArrayList;
import java.util.Date;

public class HistoriaClinica {
	int idHistoriaClinica;
	String etnia;
	String antecedentes;
	Date fecha;
	ArrayList<Enfermedad> lasEnfermedades;
	ArrayList<Consulta> lasConsultas;
	
	public int getIdHistoriaClinica() {
		return idHistoriaClinica;
	}
	public void setIdHistoriaClinica(int idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}
	public String getEtnia() {
		return etnia;
	}
	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}
	public String getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}
	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}
	public ArrayList<Consulta> getLasConsultas() {
		return lasConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
}
