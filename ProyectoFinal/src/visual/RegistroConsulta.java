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
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodConsulta;
	private JTextField txtMedico;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroConsulta dialog = new RegistroConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroConsulta() {
		setTitle("Consulta");
		setBounds(100, 100, 554, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00E9dico:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 68, 183, 57);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				txtMedico = new JTextField();
				txtMedico.setBounds(10, 26, 163, 20);
				panel_1.add(txtMedico);
				txtMedico.setEditable(false);
				txtMedico.setColumns(10);
			}
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 11, 508, 46);
			panel.add(panel_2);
			panel_2.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				lblNewLabel.setBounds(10, 17, 46, 14);
				panel_2.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				txtCodConsulta = new JTextField();
				txtCodConsulta.setBounds(66, 14, 182, 20);
				panel_2.add(txtCodConsulta);
				txtCodConsulta.setEditable(false);
				txtCodConsulta.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Fecha:");
				lblNewLabel_1.setBounds(258, 17, 46, 14);
				panel_2.add(lblNewLabel_1);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				JSpinner spnFecha = new JSpinner();
				spnFecha.setBounds(314, 14, 182, 20);
				panel_2.add(spnFecha);
				spnFecha.setEnabled(false);
				spnFecha.setModel(new SpinnerDateModel(new Date(1732161600000L), null, null, Calendar.DAY_OF_YEAR));
			}
			{
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(null, "Paciente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_3.setBounds(213, 68, 305, 57);
				panel.add(panel_3);
				panel_3.setLayout(null);
				{
					textField = new JTextField();
					textField.setBounds(10, 26, 151, 20);
					panel_3.add(textField);
					textField.setEditable(false);
					textField.setColumns(10);
				}
				
				JButton btnNewButton = new JButton("Ampliar datos");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DetallePaciente dp = new DetallePaciente();
						dp.setModal(true);
						dp.setVisible(true);
					}
				});
				btnNewButton.setBounds(171, 25, 124, 23);
				panel_3.add(btnNewButton);
			}
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos generales de la consulta:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_3.setBounds(10, 136, 508, 212);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Diagn\u00F3stico:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(10, 33, 70, 14);
			panel_3.add(lblNewLabel_3);
			
			textField_2 = new JTextField();
			textField_2.setBounds(90, 30, 408, 20);
			panel_3.add(textField_2);
			textField_2.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Indicaci\u00F3n:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(10, 61, 70, 14);
			panel_3.add(lblNewLabel_4);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(90, 61, 408, 113);
			panel_3.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_4.add(scrollPane, BorderLayout.CENTER);
			
			JTextArea textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("\u00BFEs relevante?");
			chckbxNewCheckBox.setBounds(90, 181, 137, 23);
			panel_3.add(chckbxNewCheckBox);
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
