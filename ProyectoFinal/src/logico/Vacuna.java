package logico;

import java.util.Date;

public class Vacuna {
	int idVacuna;
	Date fecha;
	String nombreVacuna;
	String tipo;
	
	public int getIdVacuna() {
		return idVacuna;
	}
	public void setIdVacuna(int idVacuna) {
		this.idVacuna = idVacuna;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
