package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import logico.PacienteCRUD;

public class ListadoPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static JTable table;
	private int index = -1;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnDetalles;
	private Paciente selected;
	private JButton btnModificar;
	private JButton btnEliminar; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPacientes dialog = new ListadoPacientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPacientes() {
		setTitle("Listado de pacientes");
		setBounds(100, 100, 592, 389);
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
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = table.getSelectedRow();

							if(index >= 0) {
								btnDetalles.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarPacienteByCedulaBBDD(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Cédula", "Nombre", "Apellido", "Teléfono"};
					modelo.setColumnIdentifiers(identificadores);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDetalles = new JButton("Ver detalle");
				btnDetalles.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DetallePaciente dp = new DetallePaciente(selected);
						dp.setModal(true);
						dp.setVisible(true);
					}
				});
				btnDetalles.setEnabled(false);
				buttonPane.add(btnDetalles);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroPaciente rp = new RegistroPaciente(selected);
						rp.setModal(true);
						rp.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
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
					btnEliminar.addActionListener(new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					        if (selected != null) {
					            int confirm = javax.swing.JOptionPane.showConfirmDialog(null,
					                "¿Estás seguro de que deseas eliminar al paciente?",
					                "Confirmar eliminación",
					                javax.swing.JOptionPane.YES_NO_OPTION);

					            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
					                boolean exito = PacienteCRUD.eliminarPacienteCompleto(selected.getIdPersona());
					                if (exito) {
					                    javax.swing.JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");
					                    loadPacientes();
					                    btnEliminar.setEnabled(false);
					                    btnModificar.setEnabled(false);
					                    btnDetalles.setEnabled(false);
					                } else {
					                    javax.swing.JOptionPane.showMessageDialog(null,
					                        "No se puede eliminar el paciente. Tiene consultas registradas.",
					                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
					                }
					            }
					        }
					    }
					});
					buttonPane.add(btnEliminar);


				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			loadPacientes();
		}
	}
	public void loadPacientes() {
		modelo.setRowCount(0);
		ArrayList<Paciente> pac = ClinicaMedica.getInstance().obtenerPacientesDesdeBDD();
		row = new Object[table.getColumnCount()];
		for(Paciente paciente:pac) {
			row[0] = paciente.getCedula();
	        row[1] = paciente.getNombre();
	        row[2] = paciente.getApellido();
	        row[3] = paciente.getTelefono();
	        modelo.addRow(row);
		}
	    table.revalidate();
	    table.repaint();
	}
}