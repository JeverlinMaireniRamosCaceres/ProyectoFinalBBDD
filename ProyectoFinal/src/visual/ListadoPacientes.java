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
import logico.Paciente; 

public class ListadoPacientes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private ArrayList<Paciente> pacientes; // Lista de pacientes

    public static void main(String[] args) {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente("1", "123456789", "Juan", "Pérez", "123-456-7890", "Dirección", null, 30, "M", 1.75f, 70f));
        pacientes.add(new Paciente("2", "987654321", "Ana", "López", "098-765-4321", "Dirección 2", null, 25, "F", 1.60f, 55f));

        try {
            ListadoPacientes dialog = new ListadoPacientes(pacientes);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ListadoPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
        setTitle("Listado de pacientes");
        setBounds(100, 100, 592, 389);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        // Panel donde se alojará la tabla
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        // Scroll y JTable
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        scrollPane.setViewportView(table);

        // Configuración de la tabla
        String[] columnNames = { "ID", "Nombre", "Apellido", "Edad", "Teléfono" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Cargando los datos de los pacientes en la tabla
        for (Paciente p : pacientes) {
            model.addRow(new Object[]{
                p.getIdPersona(),
                p.getNombre(),
                p.getApellido(),
                p.getEdad(),
                p.getTelefono()
            });
        }
        table.setModel(model);

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Botón Ver detalle
        JButton btnDetalles = new JButton("Ver detalle");
        btnDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para mostrar detalles del paciente seleccionado
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String pacienteId = (String) table.getValueAt(selectedRow, 0);
                    // Llama a la clase o ventana de detalles pasando el ID del paciente
                    // Ejemplo:
                    // new DetallePaciente(pacienteId).setVisible(true);
                }
            }
        });
        btnDetalles.setEnabled(false); // Lo habilitaremos cuando se seleccione un paciente
        buttonPane.add(btnDetalles);

        // Botón Modificar
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para modificar los datos del paciente seleccionado
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String pacienteId = (String) table.getValueAt(selectedRow, 0);
                    // Llama a la ventana para modificar el paciente
                    // Ejemplo:
                    // new ModificarPaciente(pacienteId).setVisible(true);
                }
            }
        });
        btnModificar.setEnabled(false); // Lo habilitaremos cuando se seleccione un paciente
        buttonPane.add(btnModificar);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo
            }
        });
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);

        // Añadir un listener para habilitar los botones cuando se seleccione una fila
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() != -1;
            btnDetalles.setEnabled(rowSelected);
            btnModificar.setEnabled(rowSelected);
        });
    }
}
