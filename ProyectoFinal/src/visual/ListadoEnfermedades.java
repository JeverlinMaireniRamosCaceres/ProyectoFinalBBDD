package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

public class ListadoEnfermedades extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoEnfermedades dialog = new ListadoEnfermedades();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoEnfermedades() {
        setTitle("Listado de enfermedades");
        setBounds(100, 100, 611, 375);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        // ScrollPane y tabla
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Síntomas", "Tipo"}, 0);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        buttonPane.add(btnModificar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        // Cargando enfermedades
        cargarEnfermedades();
    }

    private void cargarEnfermedades() {
        tableModel.setRowCount(0); // Limpiando la tabla
        for (Enfermedad enfermedad : ClinicaMedica.getInstance().getLasEnfermedades()) {
            tableModel.addRow(new Object[]{
                enfermedad.getIdEnfermedad(),
                enfermedad.getNombre(),
                enfermedad.getSintomas(),
                enfermedad.getTipo()
            });
        }
    }
}
