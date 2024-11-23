package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class RegistroMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroMedico dialog = new RegistroMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroMedico() {
		setTitle("Registro de m\u00E9dico");
		setBounds(100, 100, 572, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel label = new JLabel("C\u00F3digo:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBounds(18, 14, 56, 14);
			panel.add(label);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(84, 11, 131, 20);
			panel.add(txtCodigo);
			
			JLabel label_1 = new JLabel("C\u00E9dula:");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(224, 14, 56, 14);
			panel.add(label_1);
			
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(289, 11, 243, 20);
			panel.add(txtCedula);
			
			JLabel label_2 = new JLabel("Nombre:");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setBounds(18, 51, 56, 14);
			panel.add(label_2);
			
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(92, 49, 175, 20);
			panel.add(txtNombre);
			
			JLabel label_3 = new JLabel("Apellido:");
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			label_3.setBounds(284, 52, 56, 14);
			panel.add(label_3);
			
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(357, 49, 175, 20);
			panel.add(txtApellido);
			
			JLabel label_4 = new JLabel("Tel\u00E9fono:");
			label_4.setHorizontalAlignment(SwingConstants.RIGHT);
			label_4.setBounds(17, 88, 57, 14);
			panel.add(label_4);
			
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(84, 86, 144, 20);
			panel.add(txtTelefono);
			
			JLabel label_5 = new JLabel("Direcci\u00F3n:");
			label_5.setHorizontalAlignment(SwingConstants.RIGHT);
			label_5.setBounds(237, 89, 65, 14);
			panel.add(label_5);
			
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(311, 86, 221, 20);
			panel.add(txtDireccion);
			
			JLabel label_6 = new JLabel("Fecha Nacimiento:");
			label_6.setHorizontalAlignment(SwingConstants.RIGHT);
			label_6.setBounds(9, 125, 109, 14);
			panel.add(label_6);
			
			JSpinner spnFechaNacim = new JSpinner();
			spnFechaNacim.setModel(new SpinnerDateModel(new Date(1732248000000L), null, null, Calendar.DAY_OF_YEAR));
			spnFechaNacim.setBounds(128, 123, 129, 20);
			panel.add(spnFechaNacim);
			
			JLabel label_7 = new JLabel("Edad:");
			label_7.setHorizontalAlignment(SwingConstants.RIGHT);
			label_7.setBounds(266, 126, 36, 14);
			panel.add(label_7);
			
			txtEdad = new JTextField();
			txtEdad.setEditable(false);
			txtEdad.setColumns(10);
			txtEdad.setBounds(311, 123, 56, 20);
			panel.add(txtEdad);
			
			JLabel label_8 = new JLabel("Sexo:");
			label_8.setHorizontalAlignment(SwingConstants.RIGHT);
			label_8.setBounds(377, 126, 36, 14);
			panel.add(label_8);
			
			JComboBox cbxSexo = new JComboBox();
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
			cbxSexo.setBounds(423, 123, 108, 20);
			panel.add(cbxSexo);
			
			JLabel lblNewLabel = new JLabel("Especialidad:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(10, 162, 87, 14);
			panel.add(lblNewLabel);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			comboBox_1.setBounds(107, 159, 164, 20);
			panel.add(comboBox_1);
			
			JLabel lblNewLabel_1 = new JLabel("Exequatur:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(281, 162, 65, 14);
			panel.add(lblNewLabel_1);
			
			JSpinner spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spinner_1.setBounds(357, 159, 175, 20);
			panel.add(spinner_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
