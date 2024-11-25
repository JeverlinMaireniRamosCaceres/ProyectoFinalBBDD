package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logico.Paciente;
import logico.Vacuna;

public class ListadoVacunas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Paciente paciente = new Paciente("1", "123456789", "Juan", "Pérez", "123-456-7890", "Dirección", new Date(), 30, "M", 1.75f, 70f);
	    paciente.getMisVacunas().add(new Vacuna("1", new Date(), "Vacuna A", "Tipo A", "Fabricante A", 1));
	    paciente.getMisVacunas().add(new Vacuna("2", new Date(), "Vacuna B", "Tipo B", "Fabricante B", 1));
		try {
			ListadoVacunas dialog = new ListadoVacunas(paciente);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Create the dialog with a specific Paciente.
     */
    public ListadoVacunas(logico.Paciente paciente) {
        setTitle("Listado de vacunas");
        setBounds(100, 100, 582, 355);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JScrollPane scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Tipo", "Fabricante", "Cantidad", "Fecha"}, 0);
                    table = new JTable(tableModel);
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
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					JButton btnAgregar = new JButton("Agregar");
					btnAgregar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegistroVacuna rv = new RegistroVacuna();
							rv.setModal(true);
							rv.setVisible(true);
						}
					});
					buttonPane.add(btnAgregar);
				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}

        }
        
        // Cargando las vacunas del paciente en la tabla
        cargarVacunas(paciente.getMisVacunas());
    }

    /**
     * Método para cargar las vacunas en la tabla.
     */
    private void cargarVacunas(List<logico.Vacuna> vacunas) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (logico.Vacuna vacuna : vacunas) {
            tableModel.addRow(new Object[]{
                vacuna.getIdVacuna(),
                vacuna.getNombreVacuna(),
                vacuna.getTipo(),
                vacuna.getFabricante(),
                vacuna.getCantidad(),
                vacuna.getFecha().toString()
            });
        }
    }
}

