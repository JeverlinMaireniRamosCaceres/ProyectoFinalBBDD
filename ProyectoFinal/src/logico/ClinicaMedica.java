package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ClinicaMedica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Paciente> losPacientes;
	private ArrayList<Medico> losMedicos;
	private ArrayList<Consulta> lasConsultas;
	private ArrayList<Enfermedad> lasEnfermedades;
	private ArrayList<Vacuna> lasVacunas;
	private ArrayList<Cita> lasCitas;
	private ArrayList<Usuario>losUsuarios;

	public static final int ROL_ADMIN = 1;
	public static final int ROL_MEDICO = 2;
	public static final int ROL_ADMINISTRATIVO = 3;

	
	public ArrayList<Usuario> getLosUsuarios() {
		return losUsuarios;
	}

	public void setLosUsuarios(ArrayList<Usuario> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public static int codVacuna;
	public static int codPaciente;
	public static int codMedico;
	public static int codEnfermedad;
	public static int codCita;
	public static int codConsulta;
	public static int codUsuario;
	private static Usuario loginUsuario;
	private static ClinicaMedica clinicaMedica = null;
	
	public ClinicaMedica() {
		super();
		losPacientes = new ArrayList<>();
		losMedicos = new ArrayList<>();
		lasConsultas = new ArrayList<>();
		lasEnfermedades = new ArrayList<>();
		lasVacunas = new ArrayList<>();
		lasCitas = new ArrayList<>();
		losUsuarios = new ArrayList<>();
		codVacuna = 1;
		codPaciente = 1;
		codMedico = 1;
		codEnfermedad = 1; 
		codCita = 1;
		codConsulta = 1;
		codUsuario = 1;
	}
	
	public static Usuario getLoginUsuario() {
		return loginUsuario;
	}

	public static void setLoginUsuario(Usuario loginUsuario) {
		ClinicaMedica.loginUsuario = loginUsuario;
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

	public ArrayList<Vacuna> getLasVacunas() {
		return lasVacunas;
	}

	public void setLasVacunas(ArrayList<Vacuna> lasVacunas) {
		this.lasVacunas = lasVacunas;
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

	public static int getCodUsuario() {
		return codUsuario;
	}

	public static void setCodUsuario(int codUsuario) {
		ClinicaMedica.codUsuario = codUsuario;
	}

	/*public Medico buscarMedicoById(String codigo) {
		Medico medico = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getIdPersona().equalsIgnoreCase(codigo)) {
				medico = losMedicos.get(i);
				encontrado = true;
			}
			i++;
		}
		return medico;
	}*/
	
	public Medico buscarMedicoById(String id) {
	    Medico medico = null;
	    String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, p.fechaNacimiento, p.sexo, " +
	                 "m.exequatur " +
	                 "FROM Medico m JOIN Persona p ON m.idPersona = p.idPersona " +
	                 "WHERE m.idPersona = ?";
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            medico = new Medico(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getInt("exequatur")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
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
		Consulta consulta = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < lasConsultas.size()) {
			if(lasConsultas.get(i).getIdConsulta().equalsIgnoreCase(codigo)) {
				consulta = lasConsultas.get(i);
				encontrado = true;
			}
			i++;
		}
		return consulta;
	}
	
	public Enfermedad buscarEnfermedadByCodigo(String codigo) {
		Enfermedad enfermedad = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < lasEnfermedades.size()) {
			if(lasEnfermedades.get(i).getIdEnfermedad().equalsIgnoreCase(codigo)) {
				enfermedad = lasEnfermedades.get(i);
				encontrado = true;
			}
			i++;
		}
		return enfermedad;
    }
	
	public void actualizarEnfermedad(Enfermedad enfermedadActualizada) {
	    for (int i = 0; i < lasEnfermedades.size(); i++) {
	        Enfermedad enfermedad = lasEnfermedades.get(i);
	        if (enfermedad.getIdEnfermedad().equals(enfermedadActualizada.getIdEnfermedad())) {
	            lasEnfermedades.set(i, enfermedadActualizada);
	            return;
	        }
	    }
	    System.out.println("Enfermedad no encontrada para actualizar.");
	}
	
	public void actualizarPaciente(Paciente pacienteActualizado) {
        for (int i = 0; i < losPacientes.size(); i++) {
            Paciente paciente = losPacientes.get(i);
            if (paciente.getIdPersona().equals(pacienteActualizado.getIdPersona())) {
                losPacientes.set(i, pacienteActualizado);
                return; 
            }
        }
        System.out.println("Paciente no encontrado para actualizar.");
    }

	public Paciente buscarPacienteByCedula(String codigo) {
		Paciente paciente = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losPacientes.size()) {
			if(losPacientes.get(i).getCedula().equalsIgnoreCase(codigo)) {
				paciente = losPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return paciente;
	}
	
	public int buscarPacienteByCedulaGetIndex(String cedula) {
		int paciente = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losPacientes.size()) {
			if(losPacientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				paciente = i;
				encontrado = true;
			}
			i++;
		}
		return paciente;
	}

	public boolean cedulaPacienteExiste(String cedula) {
        for (Paciente paciente : losPacientes) {
            if (paciente.getCedula().equals(cedula)) {
                return true; 
            }
        }
        return false; 
    }
	
	public void updatePaciente(Paciente selected) {
		int index = buscarPacienteByCedulaGetIndex(selected.getCedula());
		if(index != 1) {
			losPacientes.set(index, selected);
		}
	}

	public Medico buscarMedicoByCedula(String codigo) {
		Medico medico = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getCedula().equalsIgnoreCase(codigo)) {
				medico = losMedicos.get(i);
				encontrado = true;
			}
			i++;
		}
		return medico;
	}
	
	public int buscarMedicoByCedulaGetIndex(String cedula) {
		int medico = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getCedula().equalsIgnoreCase(cedula)) {
				medico = i;
				encontrado = true;
			}
			i++;
		}
		return medico;
	}
	
	public boolean cedulaMedicoExiste(String cedula) {
        for (Medico medico : losMedicos) {
            if (medico.getCedula().equals(cedula)) {
                return true; 
            }
        }
        return false; 
    }
	
	public void updateMedico(Medico selected) {
		int index = buscarMedicoByCedulaGetIndex(selected.getCedula());
		if(index != -1) {
			losMedicos.set(index, selected);
		}
	}
	
	public boolean idMedicoExiste(String idMedico) {
	    for (Medico medico : losMedicos) {
	        if (medico.getIdPersona().equals(idMedico)) {
	            return true;
	        }
	    }
	    return false;
	}

	
	public Cita buscarCitaByIdCita(String idCita) {
	    Cita cita = null;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasCitas.size()) { 
	        if (lasCitas.get(i).getIdCita().equalsIgnoreCase(idCita)) { 
	            cita = lasCitas.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return cita; 
	}

	public int buscarCitaByIdGetIndex(String idCita) {
	    int index = -1;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasCitas.size()) {
	        if (lasCitas.get(i).getIdCita().equalsIgnoreCase(idCita)) {
	            index = i; 
	            encontrado = true; 
	        }
	        i++;
	    }
	    return index; 
	}
	
	public void eliminarCita(Cita cita) {
	    if (lasCitas.contains(cita)) {
	        lasCitas.remove(cita); 
	    } 
	}
	
	public void updateCita(Cita cita) {
		int index = buscarCitaByIdGetIndex(cita.getIdCita());
		if(index != -1) {
			lasCitas.set(index, cita);
		}
	}

	public boolean existeCita(Date fecha, Date hora, Medico medico) {
		boolean existe = false;
		int i = 0;
		while(!existe && i<lasCitas.size()) {
			if(lasCitas.get(i).getFecha().equals(fecha) && lasCitas.get(i).getHora().equals(hora) && lasCitas.get(i).getMedico().equals(medico)) {
				existe = true;
			}
			i++;
		}
		return existe;
	}

	public Vacuna buscarVacunaByCodigo(String codigo) {
		Vacuna vacuna = null;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasVacunas.size()) { 
	        if (lasVacunas.get(i).getIdVacuna().equalsIgnoreCase(codigo)) { 
	        	vacuna = lasVacunas.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return vacuna; 
	}
	
	public ArrayList<Vacuna> getVacunasGenerales() {
        return lasVacunas;
    }
	
	public boolean updateVacuna(String idVacuna, Vacuna nuevaVacuna) {
	    for (int i = 0; i < lasVacunas.size(); i++) {
	        if (lasVacunas.get(i).getIdVacuna().equalsIgnoreCase(idVacuna)) {
	            lasVacunas.set(i, nuevaVacuna);
	            return true; 
	        }
	    }
	    return false;
	}
	
	public boolean eliminarVacuna(String idVacuna) {
	    for (int i = 0; i < lasVacunas.size(); i++) {
	        if (lasVacunas.get(i).getIdVacuna().equalsIgnoreCase(idVacuna)) {
	            lasVacunas.remove(i);
	            return true;
	        }
	    }
	    return false; 
	}

	public int getCantPacientesPoseenEnfermedad(Enfermedad enfermedad) {
		int cant = 0;
		for(Paciente paciente:losPacientes) {
			if(paciente.getMisEnfermedades().contains(enfermedad)) {
				cant++;
			}
		}
		return cant;
	}

	public boolean confirmarLogin(String usuario, String contrasena) {
		boolean login = false;
		for(Usuario usua : losUsuarios) {
			if(usua.getNombre().equalsIgnoreCase(usuario) && usua.getContrasena().equalsIgnoreCase(contrasena)) {
				loginUsuario = usua;
				login = true;
			}
		}
		return login;
	}

	public void regUser(Usuario aux) {
		losUsuarios.add(aux);
		codUsuario++;		
	}

	public Enfermedad buscarEnfermedadById(String codigo) {
		Enfermedad enfermedad = null;
		boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasEnfermedades.size()) { 
	        if (lasEnfermedades.get(i).getIdEnfermedad().equalsIgnoreCase(codigo)) { 
	        	enfermedad = lasEnfermedades.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return enfermedad;
	}

	public Enfermedad buscarEnfermedadPacienteByCodigo(Paciente paciente, String codigo) {
		ArrayList<Enfermedad> enfermedades = paciente.getMisEnfermedades();
		Enfermedad enfermedad = null;
		boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < enfermedades.size()) { 
	        if (enfermedades.get(i).getIdEnfermedad().equalsIgnoreCase(codigo)) { 
	        	enfermedad = enfermedades.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return enfermedad;
	}

	public void updateUsuario(Usuario selected) {
		int index = buscarUsuarioByID(selected.getCodigo());
		if(index != 1) {
			losUsuarios.set(index, selected);
		}
		
	}

	private int buscarUsuarioByID(String codigo) {
		int usuario = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losUsuarios.size()) {
			if(losUsuarios.get(i).getCodigo().equals(codigo)) {
				usuario = i;
				encontrado = true;
			}
			i++;
		}
		return usuario;
	}

	
	public Usuario buscarUsuarioByCodigo(String codigo) {
		Usuario usuario = null;
		boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < losUsuarios.size()) { 
	        if (losUsuarios.get(i).getCodigo().equals(codigo)) { 
	        	usuario = losUsuarios.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return usuario;
	}
	
	public ArrayList<Paciente> obtenerPacientesDesdeBDD() {
	    ArrayList<Paciente> listaPacientes = new ArrayList<>();
	    String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, " +
	                 "p.fechaNacimiento, p.sexo, pa.estatura, pa.peso " +
	                 "FROM Persona p JOIN Paciente pa ON p.idPersona = pa.idPersona";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        while(rs.next()) {
	            Paciente paciente = new Paciente(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getFloat("estatura"),
	                rs.getFloat("peso")
	            );
	            listaPacientes.add(paciente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaPacientes;
	}
	
	public Paciente buscarPacienteByCedulaBBDD(String cedula) {
	    Paciente paciente = null;
	    String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, " +
	                 "p.fechaNacimiento, p.sexo, pa.estatura, pa.peso " +
	                 "FROM Persona p JOIN Paciente pa ON p.idPersona = pa.idPersona " +
	                 "WHERE p.cedula = ?";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, cedula);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            paciente = new Paciente(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getFloat("estatura"),
	                rs.getFloat("peso")
	            );
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return paciente;
	}
	
	public Enfermedad buscarEnfermedadByIdBBDD(String id) {
	    Enfermedad enfermedad = null;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = PruebaConexionBBDD.getConnection(); 
	        String query = "SELECT * FROM Enfermedad WHERE idEnfermedad = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, id);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String nombre = rs.getString("nombre");
	            int tipo = rs.getInt("idTipoEnfermedad");
	            String sintomas = rs.getString("sintomas");
	            enfermedad = new Enfermedad(id, nombre, sintomas, tipo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) {}
	        try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
	        try { if (conn != null) conn.close(); } catch (SQLException e) {}
	    }

	    return enfermedad;
	}
	
	public ArrayList<Enfermedad> getLasEnfermedadesBBDD() {
	    ArrayList<Enfermedad> enfermedades = new ArrayList<>();
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = PruebaConexionBBDD.getConnection();
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM Enfermedad");

	        while (rs.next()) {
	            String id = rs.getString("idEnfermedad");
	            String nombre = rs.getString("nombre");
	            int tipo = rs.getInt("idTipoEnfermedad");
	            String sintomas = rs.getString("sintomas");
	            Enfermedad enf = new Enfermedad(id, nombre, sintomas, tipo);
	            enfermedades.add(enf);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) {}
	        try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
	        try { if (conn != null) conn.close(); } catch (SQLException e) {}
	    }

	    return enfermedades;
	}

	public ArrayList<Medico> obtenerMedicosDesdeBDD() {
	    ArrayList<Medico> listaMedicos = new ArrayList<>();
	    String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, " +
	                 "p.fechaNacimiento, p.sexo, m.exequatur " +
	                 "FROM Persona p JOIN Medico m ON p.idPersona = m.idPersona";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Medico medico = new Medico(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getInt("exequatur")
	            );
	            listaMedicos.add(medico);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaMedicos;
	}

	public static Vacuna buscarVacunaEnBDPorCodigo(String codigo) {
	    Vacuna vacuna = null;
	    String sql = "SELECT * FROM Vacuna WHERE idVacuna = ?";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, codigo);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            String id = rs.getString("idVacuna");
	            String nombre = rs.getString("nombre");
	            int cantidad = rs.getInt("cant");
	            java.sql.Date fechaSQL = rs.getDate("fechaVencimiento");
	            Date fechaVencimiento = new Date(fechaSQL.getTime()); // convertir a java.util.Date si es necesario
	            int idFabricante = rs.getInt("idFabricante");
	            int idTipoVacuna = rs.getInt("idTipoVacuna");

	            vacuna = new Vacuna(id, fechaVencimiento, nombre, idTipoVacuna, idFabricante, cantidad);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return vacuna;
	}

	public Paciente buscarPacienteByIdBDD(String id) {
	    Paciente paciente = null;
	    String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, " +
	                 "p.fechaNacimiento, p.sexo, pa.estatura, pa.peso " +
	                 "FROM Persona p JOIN Paciente pa ON p.idPersona = pa.idPersona " +
	                 "WHERE p.idPersona = ?";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            paciente = new Paciente(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getFloat("estatura"),
	                rs.getFloat("peso")
	            );
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return paciente;
	}

	public Cita buscarCitaByIdBDD(String idCita) {
	    Cita cita = null;
	    String sql = "SELECT * FROM Cita WHERE idCita = ?";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, idCita);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {

	            Medico medico = ClinicaMedica.getInstance().buscarMedicoById(rs.getString("idMedico"));
	            Paciente paciente = ClinicaMedica.getInstance().buscarPacienteByIdBDD(rs.getString("idPaciente"));

	            cita = new Cita(
	                rs.getString("idCita"),
	                medico,
	                rs.getDate("fecha"),
	                rs.getTime("hora"),
	                rs.getString("motivo"),
	                paciente
	            );
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cita;
	}

	public Medico buscarMedicoPorCedulaBDD(String cedula) {
	    Medico medico = null;
	    String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, p.fechaNacimiento, p.sexo, " +
	                 "m.exequatur " +
	                 "FROM Medico m JOIN Persona p ON m.idPersona = p.idPersona " +
	                 "WHERE p.cedula = ?";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, cedula);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            medico = new Medico(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getInt("exequatur")
	            );
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return medico;
	}

	public String getEspecialidadByMedicoId(String idMedico) {
	    String especialidad = "";
	    String sql = "SELECT e.nombre " +
	                 "FROM Medico_Especialidad me " +
	                 "JOIN Especialidad e ON me.idEspecialidad = e.idEspecialidad " +
	                 "WHERE me.idMedico = ?";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, idMedico);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            especialidad = rs.getString("nombre");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return especialidad;
	}
	
	public ArrayList<Usuario> getUsuariosDesdeBD() {
	    ArrayList<Usuario> usuarios = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = PruebaConexionBBDD.getConnection();
	        String query = "SELECT * FROM Usuario";
	        stmt = conn.prepareStatement(query);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            String id = rs.getString("idUsuario");
	            String nombre = rs.getString("nombre");
	            String contrasena = rs.getString("contrasenia");
	            int rol = rs.getInt("idRol");
	            Usuario usuario = new Usuario(id, nombre,contrasena, rol);
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return usuarios;
	}

	public Usuario buscarUsuarioByCodigoBBDD(String codigo) {
	    Usuario usuario = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = PruebaConexionBBDD.getConnection();
	        String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, codigo);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String id = rs.getString("idUsuario");
	            String nombre = rs.getString("nombre");
	            String contrasena = rs.getString("contrasenia");
	            int rol = rs.getInt("idRol");

	            usuario = new Usuario(id, nombre, contrasena, rol);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return usuario;
	}

	public Medico buscarMedicoPorUsuario(String idUsuario) {
	    Medico medico = null;
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = PruebaConexionBBDD.getConnection();

	        String sql = "SELECT p.idPersona, p.cedula, p.nombre AS nombrePersona, p.apellido, p.telefono, " +
	                     "p.direccion, p.fechaNacimiento, p.sexo, " +
	                     "m.exequatur, " +
	                     "u.idUsuario, u.nombre AS nombreUsuario, u.contrasenia, u.idRol " +
	                     "FROM Medico m " +
	                     "JOIN Persona p ON m.idPersona = p.idPersona " +
	                     "JOIN Usuario u ON m.idUsuario = u.idUsuario " +
	                     "WHERE u.idUsuario = ?";

	        pst = con.prepareStatement(sql);
	        pst.setString(1, idUsuario);
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            String idPersona = rs.getString("idPersona");
	            String cedula = rs.getString("cedula");
	            String nombre = rs.getString("nombrePersona");
	            String apellido = rs.getString("apellido");
	            String telefono = rs.getString("telefono");
	            String direccion = rs.getString("direccion");
	            Date fechaNacimiento = rs.getDate("fechaNacimiento");
	            String sexo = rs.getString("sexo");

	            int exequatur = rs.getInt("exequatur");

	            String codigoUsuario = rs.getString("idUsuario");
	            String nombreUsuario = rs.getString("nombreUsuario");
	            String contrasenia = rs.getString("contrasenia");
	            int idRol = rs.getInt("idRol");

	            Usuario usuario = new Usuario(codigoUsuario, nombreUsuario, contrasenia, idRol);

	            medico = new Medico(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo, exequatur);
	            medico.setUsuario(usuario);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return medico;
	}


}
