package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Enfermedad;
import logico.EnfermedadCRUD;


public class DetalleControlEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Enfermedad enfermedad;
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] row;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleControlEnfermedades dialog = new DetalleControlEnfermedades(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleControlEnfermedades(Enfermedad enf) {
		setTitle("Listado de paciented diagnosticados con "+enf.getNombre());
		enfermedad = enf;
		setBounds(100, 100, 604, 377);
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
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					modelo = new DefaultTableModel();
					String[] identificadores = {"C�dula", "Nombre", "Apellido", "Tel�fono"};
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
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadPacientes();
	}
	private void loadPacientes() {
		modelo.setRowCount(0);
		/*ArrayList<Paciente> pac = ClinicaMedica.getInstance().getLosPacientes();
		row = new Object[table.getColumnCount()];
		for(Paciente paciente:pac) {
			if(paciente.getMisEnfermedades().contains(enfermedad)) {
				row[0] = paciente.getCedula();
		        row[1] = paciente.getNombre();
		        row[2] = paciente.getApellido();
		        row[3] = paciente.getTelefono();
		        modelo.addRow(row);
			}
		}*/
        ArrayList<Object[]> pacientes = EnfermedadCRUD.obtenerPacientesPorEnfermedad(enfermedad.getIdEnfermedad());
        
        // Llenar la tabla con los datos
        for(Object[] paciente : pacientes) {
            modelo.addRow(paciente);
        }
	}

}
