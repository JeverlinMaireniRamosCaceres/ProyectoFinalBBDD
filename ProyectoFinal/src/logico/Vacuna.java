package logico;

import java.util.Date;

public class Vacuna {
	
	private int idVacuna;
	private Date fecha;
	private String nombreVacuna;
	private String tipo;
	private String fabricante;
	
	public Vacuna(int idVacuna, Date fecha, String nombreVacuna, String tipo, String fabricante) {
		super();
		this.idVacuna = idVacuna;
		this.fecha = fecha;
		this.nombreVacuna = nombreVacuna;
		this.tipo = tipo;
		this.fabricante = fabricante;
	}
	
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	
}
