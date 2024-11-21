package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroVacuna dialog = new RegistroVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroVacuna() {
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 507, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(14, 22, 46, 14);
			panel.add(lblNewLabel);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(74, 19, 121, 20);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Fecha de aplicaci\u00F3n:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(209, 22, 121, 14);
			panel.add(lblNewLabel_1);
			
			JSpinner spnFechaApl = new JSpinner();
			spnFechaApl.setModel(new SpinnerDateModel(new Date(1732161600000L), null, null, Calendar.DAY_OF_YEAR));
			spnFechaApl.setEnabled(false);
			spnFechaApl.setBounds(344, 19, 128, 20);
			panel.add(spnFechaApl);
			
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(4, 53, 56, 14);
			panel.add(lblNewLabel_2);
			
			textField = new JTextField();
			textField.setBounds(74, 50, 203, 20);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Tipo:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(291, 53, 46, 14);
			panel.add(lblNewLabel_3);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			comboBox.setBounds(351, 50, 121, 20);
			panel.add(comboBox);
			
			JLabel lblNewLabel_4 = new JLabel("Fabricante:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(11, 84, 69, 14);
			panel.add(lblNewLabel_4);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			comboBox_1.setBounds(91, 81, 380, 20);
			panel.add(comboBox_1);
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
