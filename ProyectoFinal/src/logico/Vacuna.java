package logico;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idVacuna;
	private Date fechaVencimiento;
	private String nombreVacuna;
	private int idTipo;
	private int idFabricante;
	private int cantidad;
	
	public Vacuna(String idVacuna, Date fecha, String nombreVacuna, int idTipo, int idFabricante, int cantidad) {
		super();
		this.idVacuna = idVacuna;
		this.fechaVencimiento = fecha;
		this.nombreVacuna = nombreVacuna;
		this.idTipo = idTipo;
		this.idFabricante = idFabricante;
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdVacuna() {
		return idVacuna;
	}
	public void setIdVacuna(String idVacuna) {
		this.idVacuna = idVacuna;
	}
	public Date getFecha() {
		return fechaVencimiento;
	}
	public void setFecha(Date fecha) {
		this.fechaVencimiento = fecha;
	}
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	public int getTipo() {
		return idTipo;
	}
	public void setTipo(int tipo) {
		this.idTipo = tipo;
	}

	public int getFabricante() {
		return idFabricante;
	}

	public void setFabricante(int fabricante) {
		this.idFabricante = fabricante;
	}
	
	
}
