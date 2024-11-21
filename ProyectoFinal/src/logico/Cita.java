package logico;

import java.util.Date;

public class Cita {

	private Paciente paciente;
	private Medico medico;
	private Date fecha;
	private String diagnostico;
	private String tratamiento;
	private boolean tratado;
	private boolean importante;
	
	public Cita(Paciente paciente, Medico medico, Date fecha, String diagnostico, String tratamiento, boolean tratado,
			boolean importante) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.tratado = tratado;
		this.importante = importante;
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

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public boolean isTratado() {
		return tratado;
	}

	public void setTratado(boolean tratado) {
		this.tratado = tratado;
	}

	public boolean isImportante() {
		return importante;
	}

	public void setImportante(boolean importante) {
		this.importante = importante;
	}
	
	
	
}
