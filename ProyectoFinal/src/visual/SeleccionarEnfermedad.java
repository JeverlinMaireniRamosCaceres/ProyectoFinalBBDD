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

public class SeleccionarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel modelo;
	private static Object[] row;
	private static JTable table;
	private int index = -1;
	private Enfermedad selected;
	private JButton btnSeleccionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarEnfermedad dialog = new SeleccionarEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarEnfermedad() {
		setTitle("Seleccionar m\u00E9dico");
		setBounds(100, 100, 512, 328);
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
								btnSeleccionar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarEnfermedadByIdBBDD(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Codigo", "Nombre", "Tipo"};
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
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selected!=null) {
							dispose();
						}
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
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
		ArrayList<Enfermedad> enf = ClinicaMedica.getInstance().getLasEnfermedadesBBDD();
		row = new Object[table.getColumnCount()];
		for(Enfermedad enfermedad : enf) {
			row[0] = enfermedad.getIdEnfermedad();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getTipo();
			modelo.addRow(row);
		}
	}
	
	public Enfermedad getSelectedEnfermedad() {
		return selected;
	}

}
