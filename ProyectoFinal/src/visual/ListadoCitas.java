package visual;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logico.ClinicaMedica;
import logico.Cita;
import logico.Paciente;
import logico.Persona;
import logico.Medico;

public class ListadoCitas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

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

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        // ScrollPane y tabla
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID Cita", "Paciente", "Médico", "Fecha", "Motivo"}, 0);
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

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.setActionCommand("OK");
        buttonPane.add(btnEliminar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        // Cargando las citas
        cargarCitas();
    }

    private void cargarCitas() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Cita cita : ClinicaMedica.getInstance().getLasCitas()) {
            tableModel.addRow(new Object[]{
                cita.getIdCita(),
                cita.getNombrePersona(),
                cita.getMedico(),
                sdf.format(cita.getFecha()),
                cita.getMotivo()
            });
        }
    }
}
