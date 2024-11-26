package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.Enfermedad;

public class RegistroEnfermedad extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox cbxTipo;
    private JTextArea txtASintomas;

    // Constructor para registrar una nueva enfermedad
    public RegistroEnfermedad() {
        setTitle("Registro de Enfermedad");
        setBounds(100, 100, 474, 292);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        initializeComponents();
    }

    // Constructor para modificar una enfermedad existente
    public RegistroEnfermedad(Enfermedad enfermedad) {
        this(); // Llama al constructor por defecto
        // Si venimos a modificar, cargamos los datos de la enfermedad
        txtCodigo.setText(enfermedad.getIdEnfermedad());
        txtNombre.setText(enfermedad.getNombre());
        cbxTipo.setSelectedItem(enfermedad.getTipo());
        txtASintomas.setText(enfermedad.getSintomas());
    }

    private void initializeComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Añadir los componentes del formulario (texto, combobox, etc.)
        JLabel lblNewLabel = new JLabel("Código:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(10, 22, 56, 14);
        panel.add(lblNewLabel);

        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBounds(76, 19, 362, 20);
        panel.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(10, 63, 56, 14);
        panel.add(lblNewLabel_1);

        txtNombre = new JTextField();
        txtNombre.setBounds(76, 60, 144, 20);
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Tipo:");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(242, 63, 46, 14);
        panel.add(lblNewLabel_2);

        cbxTipo = new JComboBox();
        cbxTipo.setModel(new DefaultComboBoxModel(new String[]{"<Seleccione>", "Infecciosa", "Crónica", "Genética", "Autoinmune", "Cardiovascular", "Respiratoria", "Metabólica", "Neurológica", "Psiquiátrica", "Endocrina", "Gastrointestinal", "Renal"}));
        cbxTipo.setBounds(294, 60, 144, 20);
        panel.add(cbxTipo);

        JLabel lblNewLabel_3 = new JLabel("Síntomas:");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(10, 99, 59, 14);
        panel.add(lblNewLabel_3);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(76, 99, 362, 94);
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane, BorderLayout.CENTER);

        txtASintomas = new JTextArea();
        scrollPane.setViewportView(txtASintomas);

        // Botones de acción
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Guardar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear o modificar enfermedad
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                String sintomas = txtASintomas.getText();
                String tipo = cbxTipo.getSelectedItem().toString();

                if (codigo.isEmpty() || nombre.isEmpty() || sintomas.isEmpty() || tipo.equals("<Seleccione>")) {
                    JOptionPane.showMessageDialog(RegistroEnfermedad.this, "Por favor, complete todos los campos.");
                } else {
                    Enfermedad enfermedad = new Enfermedad(codigo, nombre, sintomas, tipo);
                    if (txtCodigo.isEditable()) {  // Crear nueva enfermedad
                        ClinicaMedica.getInstance().insertarEnfermedad(enfermedad);
                    } else {  // Modificar existente
                        ClinicaMedica.getInstance().actualizarEnfermedad(enfermedad);
                    }
                    dispose(); // Cierra el diálogo
                }
            }
        });
        buttonPane.add(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }
}
