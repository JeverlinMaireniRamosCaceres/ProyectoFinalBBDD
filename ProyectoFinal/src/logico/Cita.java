package logico;

import java.util.Date;

public class Cita {

	private String idCita;
	private String nombrePersona;
	private Medico medico;
	private String fecha;
	private String hora;
	private String motivo;
	
	public Cita(String idCita, String nombrePersona, Medico medico, String fecha, String hora, String motivo) {
		super();
		this.idCita = idCita;
		this.nombrePersona = nombrePersona;
		this.medico = medico;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
	}

	public String getIdCita() {
		return idCita;
	}

	public void setIdCita(String idCita) {
		this.idCita = idCita;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
