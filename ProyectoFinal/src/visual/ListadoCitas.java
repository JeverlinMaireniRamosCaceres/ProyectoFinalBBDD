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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.ClinicaMedica;
import logico.Medico;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoCitas extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private int index = -1;
	private JButton btnDetalle;
	private JButton btnModificar;
	private Cita selected;
	private static Object[] row;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoCitas dialog = new ListadoCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public ListadoCitas() {
		setTitle("Listado de citas");
		setBounds(100, 100, 619, 398);
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
								btnDetalle.setEnabled(true);
								btnModificar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarCitaByIdCita(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"ID", "Paciente", "Medico", "Fecha", "Hora", "Motivo"};
					modelo.setColumnIdentifiers(identificadores);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
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
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroCita rc = new RegistroCita(selected);
						rc.setModal(true);
						rc.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
			    btnEliminar.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			            if (index >= 0 && selected != null) {
			                int confirm = javax.swing.JOptionPane.showConfirmDialog(
			                    null,
			                    "¿Estás seguro de que deseas eliminar esta cita?",
			                    "Confirmación de eliminación",
			                    javax.swing.JOptionPane.YES_NO_OPTION
			                );

			                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
			                    ClinicaMedica.getInstance().eliminarCita(selected);
			                    javax.swing.JOptionPane.showMessageDialog(null, "Cita eliminada con éxito.");
			                    
			                    loadCitas();
			
			                    btnEliminar.setEnabled(false);
			                    btnModificar.setEnabled(false);
			                }
			            } else {
			                javax.swing.JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna cita.");
			            }
			        }
			    });
			    btnEliminar.setEnabled(false);
			    buttonPane.add(btnEliminar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			loadCitas();
		}
	}
	public static void loadCitas() {
		modelo.setRowCount(0);
		ArrayList<Cita> ci = ClinicaMedica.getInstance().getLasCitas();
		row = new Object[table.getColumnCount()];
		for(Cita cita : ci) {
			row[0] = cita.getIdCita();
			row[1] = cita.getNombrePersona();
			row[2] = cita.getMedico().getApellido();
			row[3] = cita.getFecha();
			row[4] = cita.getHora();
			row[5] = cita.getHora();
			modelo.addRow(row);
		}
	}
}