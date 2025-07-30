package logico;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idCita;

	private Medico medico;
	private Date fecha;
	private Time hora;
	private String motivo;
	private Paciente paciente;
	
	public Cita(String idCita, Medico medico, Date fecha, Time hora, String motivo, Paciente paciente) {
		super();
		this.idCita = idCita;
		this.medico = medico;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
		this.paciente = paciente;
	}

	public String getIdCita() {
		return idCita;
	}

	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
