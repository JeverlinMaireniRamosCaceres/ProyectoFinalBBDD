package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Paciente;
import logico.PruebaConexionBBDD;
import logico.Vacuna;

public class ListadoVacunas extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private Paciente paciente;
	private int index = -1;
	private Vacuna selected;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoVacunas dialog = new ListadoVacunas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public ListadoVacunas(Paciente pac) {
		paciente = pac;
		setTitle("Listado de vacunas");
		setBounds(100, 100, 582, 355);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
				        index = table.getSelectedRow();
				        if (index >= 0) {
				            btnModificar.setEnabled(true);

				            btnEliminar.setEnabled(true);
				            String codigo = table.getValueAt(index, 0).toString();
				            selected = ClinicaMedica.getInstance().buscarVacunaEnBDPorCodigo(codigo); // Actualiza 'selected'
				        }
					}
				});
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					modelo = new DefaultTableModel();
					String[] identificadores = {"Código", "Nombre", "Tipo", "Fabricante", "Fecha Vencimiento"};
					modelo.setColumnIdentifiers(identificadores);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		} 
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				{
					btnModificar = new JButton("Modificar");
					btnModificar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (selected != null) {
								RegistroVacuna re = new RegistroVacuna(selected);

								re.setModal(true);
								re.setVisible(true);
							}
						}
					});
					btnModificar.setEnabled(false);
					buttonPane.add(btnModificar);
				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadVacunas();
	}
	
	/*private void loadVacunas() {
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

		modelo.setRowCount(0);
		ArrayList<Vacuna> vac = ClinicaMedica.getInstance().obtenerVacunasDePacienteBDD(paciente.getIdPersona());
		row = new Object[table.getColumnCount()];
		for(Vacuna vacunas:vac) {
			row[0] = vacunas.getIdVacuna();
		    row[1] = vacunas.getNombreVacuna();
	        row[2] = vacunas.getTipo();
		    row[3] = vacunas.getFabricante();
		    row[4] = dateFormatter.format(vacunas.getFecha());
		    modelo.addRow(row);
		}
	}*/
	
	private void loadVacunas() {
	    modelo.setRowCount(0);
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	    String sql = "SELECT v.idVacuna, v.nombre, v.fechaVencimiento, " +
	                 "tv.nombre AS nombreTipo, f.nombre AS nombreFabricante " +
	                 "FROM Historial_Vacuna hv " +
	                 "JOIN Vacuna v ON hv.idVacuna = v.idVacuna " +
	                 "JOIN Tipo_Vacuna tv ON v.idTipoVacuna = tv.idTipoVacuna " +
	                 "JOIN Fabricante f ON v.idFabricante = f.idFabricante " +
	                 "WHERE hv.idPaciente = '" + paciente.getIdPersona() + "'";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         java.sql.Statement stmt = conn.createStatement();
	         java.sql.ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Object[] row = new Object[5];
	            row[0] = rs.getString("idVacuna");
	            row[1] = rs.getString("nombre");
	            row[2] = rs.getString("nombreTipo");
	            row[3] = rs.getString("nombreFabricante");
	            row[4] = (rs.getDate("fechaVencimiento") != null)
	                   ? dateFormatter.format(rs.getDate("fechaVencimiento"))
	                   : "Fecha no disponible";
	            modelo.addRow(row);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        javax.swing.JOptionPane.showMessageDialog(this,
	            "Error al cargar vacunas del paciente: " + e.getMessage(),
	            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
	    }
	}


}