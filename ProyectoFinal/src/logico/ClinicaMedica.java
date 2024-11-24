package logico;

import java.util.ArrayList;

public class ClinicaMedica {
	
	private ArrayList<Paciente> losPacientes;
	private ArrayList<Medico> losMedicos;
	private ArrayList<Consulta> lasConsultas;
	private ArrayList<Enfermedad> lasEnfermedades;
	private ArrayList<Vacuna> lasVacunas;
	private ArrayList<Cita> lasCitas;
	public static int codVacuna;
	public static int codPaciente;
	public static int codMedico;
	public static int codEnfermedad;
	public static int codCita;
	public static int codConsulta;
	private static ClinicaMedica clinicaMedica = null;
	
	public ClinicaMedica() {
		super();
		losPacientes = new ArrayList<>();
		losMedicos = new ArrayList<>();
		lasConsultas = new ArrayList<>();
		lasEnfermedades = new ArrayList<>();
		lasVacunas = new ArrayList<>();
		lasCitas = new ArrayList<>();
		codVacuna = 1;
		codPaciente = 1;
		codMedico = 1;
		codEnfermedad = 1; 
		codCita = 1;
		codConsulta = 1;
	}
	
	public static ClinicaMedica getInstance() {
		if(clinicaMedica==null) {
			clinicaMedica = new ClinicaMedica();
		}
		return clinicaMedica;
	}
	
	public static int getCodVacuna() {
		return codVacuna;
	}

	public static void setCodVacuna(int codVacuna) {
		ClinicaMedica.codVacuna = codVacuna;
	}

	public static int getCodPaciente() {
		return codPaciente;
	}

	public static void setCodPaciente(int codPaciente) {
		ClinicaMedica.codPaciente = codPaciente;
	}

	public static int getCodMedico() {
		return codMedico;
	}

	public static void setCodMedico(int codMedico) {
		ClinicaMedica.codMedico = codMedico;
	}

	public static int getCodEnfermedad() {
		return codEnfermedad;
	}

	public static void setCodEnfermedad(int codEnfermedad) {
		ClinicaMedica.codEnfermedad = codEnfermedad;
	}

	public static int getCodCita() {
		return codCita;
	}

	public static int getCodConsulta() {
		return codConsulta;
	}

	public static void setCodConsulta(int codConsulta) {
		ClinicaMedica.codConsulta = codConsulta;
	}

	public static void setCodCita(int codCita) {
		ClinicaMedica.codCita = codCita;
	}

	public static ClinicaMedica getClinicaMedica() {
		return clinicaMedica;
	}

	public static void setClinicaMedica(ClinicaMedica clinicaMedica) {
		ClinicaMedica.clinicaMedica = clinicaMedica;
	}

	public ArrayList<Consulta> getLasConsultas() {
		return lasConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
	
	public ArrayList<Paciente> getLosPacientes() {
		return losPacientes;
	}

	public void setLosPacientes(ArrayList<Paciente> losPacientes) {
		this.losPacientes = losPacientes;
	}

	public ArrayList<Medico> getLosMedicos() {
		return losMedicos;
	}

	public void setLosMedicos(ArrayList<Medico> losMedicos) {
		this.losMedicos = losMedicos;
	}

	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}

	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}
	
	public ArrayList<Cita> getLasCitas() {
		return lasCitas;
	}

	public void setLasCitas(ArrayList<Cita> lasCitas) {
		this.lasCitas = lasCitas;
	}

	public void insertarPaciente(Paciente paciente) {
		losPacientes.add(paciente);
		codPaciente++;
	}
	
	public void insertarMedico(Medico medico) {
		losMedicos.add(medico);
		codMedico++;
	}
	
	public void insertarEnfermedad(Enfermedad enfermedad) {
		lasEnfermedades.add(enfermedad);
		codEnfermedad++;
	}
	
	public void insertarVacuna (Vacuna vacuna) {
		lasVacunas.add(vacuna);
		codVacuna++;
	}

	public void insertarCita (Cita cita) {
		lasCitas.add(cita);
		codCita++;
	}

	public Medico buscarMedicoById(String codigo) {
		Medico medico = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getIdPersona().equalsIgnoreCase(codigo)) {
				medico = losMedicos.get(i);
				encontrado = true;
			}
		}
		return medico;
	}

	public void insertarConsulta(Consulta consulta) {
		lasConsultas.add(consulta);
		codConsulta++;
	}
	
	public void insertarConsultaEnHistorial(Consulta consulta, Paciente paciente) {
		paciente.getMiHistorial().getLasConsultas().add(consulta);
	}

	public Consulta buscarConsultaById(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}
}
