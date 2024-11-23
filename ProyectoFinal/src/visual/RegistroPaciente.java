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
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class RegistroPaciente extends JDialog {

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
			RegistroPaciente dialog = new RegistroPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroPaciente() {
		setTitle("Registro de paciente");
		setBounds(100, 100, 560, 297);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setBounds(9, 23, 56, 14);
				panel.add(lblNewLabel);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setBounds(74, 19, 131, 20);
				panel.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("C\u00E9dula:");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_1.setBounds(215, 22, 56, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setBounds(281, 19, 243, 20);
				panel.add(txtCedula);
				txtCedula.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Nombre:");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_2.setBounds(9, 60, 56, 14);
				panel.add(lblNewLabel_2);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(73, 57, 175, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Apellido:");
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_3.setBounds(281, 60, 56, 14);
				panel.add(lblNewLabel_3);
			}
			{
				txtApellido = new JTextField();
				txtApellido.setBounds(349, 57, 175, 20);
				panel.add(txtApellido);
				txtApellido.setColumns(10);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Tel\u00E9fono:");
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_4.setBounds(8, 97, 57, 14);
				panel.add(lblNewLabel_4);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setBounds(74, 94, 144, 20);
				panel.add(txtTelefono);
				txtTelefono.setColumns(10);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Direcci\u00F3n:");
				lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_5.setBounds(228, 97, 65, 14);
				panel.add(lblNewLabel_5);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setBounds(303, 94, 221, 20);
				panel.add(txtDireccion);
				txtDireccion.setColumns(10);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Fecha Nacimiento:");
				lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_6.setBounds(0, 134, 116, 14);
				panel.add(lblNewLabel_6);
			}
			{
				JSpinner spinner = new JSpinner();
				spinner.setModel(new SpinnerDateModel(new Date(1732161600000L), null, null, Calendar.MILLISECOND));
				spinner.setBounds(126, 131, 122, 20);
				panel.add(spinner);
			}
			{
				JLabel lblNewLabel_7 = new JLabel("Edad:");
				lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_7.setBounds(258, 134, 35, 14);
				panel.add(lblNewLabel_7);
			}
			{
				txtEdad = new JTextField();
				txtEdad.setEditable(false);
				txtEdad.setBounds(303, 131, 56, 20);
				panel.add(txtEdad);
				txtEdad.setColumns(10);
			}
			{
				JLabel lblNewLabel_8 = new JLabel("Sexo:");
				lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_8.setBounds(369, 134, 40, 14);
				panel.add(lblNewLabel_8);
			}
			{
				JComboBox cbxSexo = new JComboBox();
				cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
				cbxSexo.setBounds(419, 131, 105, 20);
				panel.add(cbxSexo);
			}
			{
				JLabel lblNewLabel_9 = new JLabel("Estatura:");
				lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_9.setBounds(9, 171, 56, 14);
				panel.add(lblNewLabel_9);
			}
			{
				JSpinner spnEstatura = new JSpinner();
				spnEstatura.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				spnEstatura.setBounds(74, 168, 175, 20);
				panel.add(spnEstatura);
			}
			{
				JLabel lblNewLabel_10 = new JLabel("Peso:");
				lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_10.setBounds(281, 171, 46, 14);
				panel.add(lblNewLabel_10);
			}
			{
				JSpinner spnPeso = new JSpinner();
				spnPeso.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				spnPeso.setBounds(337, 168, 175, 20);
				panel.add(spnPeso);
			}
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
