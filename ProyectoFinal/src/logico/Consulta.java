package logico;

import java.util.Date;

public class Consulta {
	
	private Date fecha;
	private Medico medico;
	private Paciente paciente;
	private String motivo;
	private String diagnostico;
	private String indicacion;
	
	public Consulta(Date fecha, Medico medico, Paciente paciente, String motivo, String diagnostico,
			String indicacion) {
		super();
		this.fecha = fecha;
		this.medico = medico;
		this.paciente = paciente;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.indicacion = indicacion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getIndicacion() {
		return indicacion;
	}
	public void setIndicacion(String indicacion) {
		this.indicacion = indicacion;
	}
}
