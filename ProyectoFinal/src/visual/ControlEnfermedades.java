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
import logico.Enfermedad;
import logico.EnfermedadCRUD;

public class ControlEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] row;	
	private Enfermedad enfermedad;
	private int index = -1;
	private JButton btnDetalle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ControlEnfermedades dialog = new ControlEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ControlEnfermedades() {
		setTitle("Control de enfermedades");
		setBounds(100, 100, 654, 402);
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
								btnDetalle.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								ClinicaMedica.getInstance();
								enfermedad = ClinicaMedica.buscarEnfermedadByCodigoBDD(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"C�digo", "Nombre", "Tipo", "Pacientes Diagnosticados"};
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
				btnDetalle = new JButton("Ver detalle");
				btnDetalle.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(enfermedad != null) {
							DetalleControlEnfermedades dce = new DetalleControlEnfermedades(enfermedad);
							dce.setModal(true);
							dce.setVisible(true);
							
						}
					}
				});
				btnDetalle.setEnabled(false);
				btnDetalle.setActionCommand("OK");
				buttonPane.add(btnDetalle);
				getRootPane().setDefaultButton(btnDetalle);
			}
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
		loadEnfermedades();
	}
	private void loadEnfermedades() {
		modelo.setRowCount(0);
	    ArrayList<Object[]> enfermedades = EnfermedadCRUD.obtenerEnfermedadesConPacientes();
	    
	    for(Object[] enfermedad : enfermedades) {
	        modelo.addRow(new Object[] {
	            enfermedad[0], // cod
	            enfermedad[1], // nombre
	            enfermedad[2], // tipo
	            enfermedad[3]  // cantidad pacientes
	        });
	    }
	}
}
