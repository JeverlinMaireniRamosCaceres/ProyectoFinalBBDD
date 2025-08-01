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
import logico.Vacuna;
import logico.VacunaCRUD;

public class SeleccionarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSeleccionar;
	private static JTable table;
	private int index = -1;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private Vacuna selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarVacuna dialog = new SeleccionarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarVacuna() {
		setTitle("Seleccionar vacuna");
		setBounds(100, 100, 556, 354);
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
								selected = ClinicaMedica.buscarVacunaEnBDPorCodigo(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"C�digo", "Nombre","Tipo", "Fabricante", "Cantidad"};
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
						if(selected != null) {
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
		loadVacunas();
	}
	public static void loadVacunas() {
		modelo.setRowCount(0);
		ArrayList<Vacuna> vac = VacunaCRUD.obtenerVacunasDesdeBDD();
		row = new Object[table.getColumnCount()];
		for(Vacuna vacunas:vac) {
			if(vacunas.getCantidad() > 0) {
				row[0] = vacunas.getIdVacuna();
		        row[1] = vacunas.getNombreVacuna();
		        row[2] = vacunas.getTipo();
		        row[3] = vacunas.getFabricante();
		        row[4] = vacunas.getCantidad();
		        modelo.addRow(row);
			}
		}
	}
	public Vacuna getSelectedVacuna() {
		return selected;
	}
}
