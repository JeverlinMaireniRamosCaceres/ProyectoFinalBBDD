package logico;

import java.util.Date;

public class Cita {

	private String nombrePersona;
	private Medico medico;
	private Date fecha;
	
	public Cita(String nombrePersona, Medico medico, Date fecha) {
		super();
		this.nombrePersona = nombrePersona;
		this.medico = medico;
		this.fecha = fecha;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
