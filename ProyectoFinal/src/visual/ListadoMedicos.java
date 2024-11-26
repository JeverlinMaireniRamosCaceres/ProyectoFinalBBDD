package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import logico.Medico;

public class ListadoMedicos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	ClinicaMedica clinicaMedica = ClinicaMedica.getInstance();
        clinicaMedica.insertarMedico(new Medico("M001", "123456789", "Ana", "Pérez", "8095555555", "Calle A", new Date(), 35, "Femenino", "Cardiología", 1234));
        clinicaMedica.insertarMedico(new Medico("M002", "987654321", "Juan", "Gómez", "8094444444", "Calle B", new Date(), 40, "Masculino", "Pediatría", 5678));
        try {
            ListadoMedicos dialog = new ListadoMedicos();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoMedicos() {
        setTitle("Listado de médicos");
        setBounds(100, 100, 613, 372);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        // Panel principal
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        // ScrollPane y Tabla
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Especialidad", "Exequatur"}, 0);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnDetalle = new JButton("Ver detalle");
        btnDetalle.setEnabled(false);
        buttonPane.add(btnDetalle);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        buttonPane.add(btnModificar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        // Cargando los datos en la tabla
        cargarMedicos();
    }

    private void cargarMedicos() {
        tableModel.setRowCount(0); // Limpiando la tabla
        for (logico.Medico medico : ClinicaMedica.getInstance().getLosMedicos()) {
            tableModel.addRow(new Object[]{
                medico.getIdPersona(),
                medico.getNombre() + " " + medico.getApellido(),
                medico.getEspecialidad(),
                medico.getExequatur()
            });
        }
    }
}

