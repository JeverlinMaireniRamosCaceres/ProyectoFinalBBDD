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
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallePaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetallePaciente dialog = new DetallePaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetallePaciente() {
		setTitle("Detalle del paciente");
		setBounds(100, 100, 613, 402);
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
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Datos del paciente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 11, 567, 225);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel label = new JLabel("C\u00F3digo:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setBounds(31, 34, 56, 14);
				panel_1.add(label);
				
				textField = new JTextField();
				textField.setEditable(false);
				textField.setColumns(10);
				textField.setBounds(96, 30, 131, 20);
				panel_1.add(textField);
				
				JLabel label_1 = new JLabel("C\u00E9dula:");
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setBounds(237, 33, 56, 14);
				panel_1.add(label_1);
				
				textField_1 = new JTextField();
				textField_1.setEditable(false);
				textField_1.setColumns(10);
				textField_1.setBounds(303, 30, 243, 20);
				panel_1.add(textField_1);
				
				JLabel label_2 = new JLabel("Nombre:");
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setBounds(31, 71, 56, 14);
				panel_1.add(label_2);
				
				textField_2 = new JTextField();
				textField_2.setEditable(false);
				textField_2.setColumns(10);
				textField_2.setBounds(95, 68, 175, 20);
				panel_1.add(textField_2);
				
				JLabel label_3 = new JLabel("Apellido:");
				label_3.setHorizontalAlignment(SwingConstants.RIGHT);
				label_3.setBounds(303, 71, 56, 14);
				panel_1.add(label_3);
				
				textField_3 = new JTextField();
				textField_3.setEditable(false);
				textField_3.setColumns(10);
				textField_3.setBounds(371, 68, 175, 20);
				panel_1.add(textField_3);
				
				textField_4 = new JTextField();
				textField_4.setEditable(false);
				textField_4.setColumns(10);
				textField_4.setBounds(325, 105, 221, 20);
				panel_1.add(textField_4);
				
				JLabel label_4 = new JLabel("Direcci\u00F3n:");
				label_4.setHorizontalAlignment(SwingConstants.RIGHT);
				label_4.setBounds(250, 108, 65, 14);
				panel_1.add(label_4);
				
				textField_5 = new JTextField();
				textField_5.setEditable(false);
				textField_5.setColumns(10);
				textField_5.setBounds(96, 105, 144, 20);
				panel_1.add(textField_5);
				
				JLabel label_5 = new JLabel("Tel\u00E9fono:");
				label_5.setHorizontalAlignment(SwingConstants.RIGHT);
				label_5.setBounds(30, 108, 57, 14);
				panel_1.add(label_5);
				
				JLabel label_6 = new JLabel("Fecha Nacimiento:");
				label_6.setHorizontalAlignment(SwingConstants.RIGHT);
				label_6.setBounds(22, 145, 109, 14);
				panel_1.add(label_6);
				
				JSpinner spinner = new JSpinner();
				spinner.setEnabled(false);
				spinner.setBounds(141, 142, 129, 20);
				panel_1.add(spinner);
				
				textField_6 = new JTextField();
				textField_6.setEditable(false);
				textField_6.setColumns(10);
				textField_6.setBounds(336, 142, 56, 20);
				panel_1.add(textField_6);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setEnabled(false);
				comboBox.setBounds(456, 142, 90, 20);
				panel_1.add(comboBox);
				
				JLabel label_7 = new JLabel("Sexo:");
				label_7.setHorizontalAlignment(SwingConstants.RIGHT);
				label_7.setBounds(400, 145, 46, 14);
				panel_1.add(label_7);
				
				JLabel label_8 = new JLabel("Edad:");
				label_8.setHorizontalAlignment(SwingConstants.RIGHT);
				label_8.setBounds(280, 145, 46, 14);
				panel_1.add(label_8);
				
				JLabel label_9 = new JLabel("Estatura:");
				label_9.setHorizontalAlignment(SwingConstants.RIGHT);
				label_9.setBounds(31, 182, 56, 14);
				panel_1.add(label_9);
				
				JSpinner spinner_1 = new JSpinner();
				spinner_1.setEnabled(false);
				spinner_1.setBounds(96, 179, 175, 20);
				panel_1.add(spinner_1);
				
				JLabel label_10 = new JLabel("Peso:");
				label_10.setHorizontalAlignment(SwingConstants.RIGHT);
				label_10.setBounds(303, 182, 46, 14);
				panel_1.add(label_10);
				
				JSpinner spinner_2 = new JSpinner();
				spinner_2.setEnabled(false);
				spinner_2.setBounds(359, 179, 175, 20);
				panel_1.add(spinner_2);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historial de vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 247, 276, 57);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JButton btnAbrirHVacunas = new JButton("Abrir");
			btnAbrirHVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListadoVacunas lv = new ListadoVacunas();
					lv.setModal(true);
					lv.setVisible(true);
				}
			});
			btnAbrirHVacunas.setBounds(10, 23, 256, 23);
			panel_1.add(btnAbrirHVacunas);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historial m\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(301, 247, 276, 57);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JButton btnAbrirHMedico = new JButton("Abrir");
			btnAbrirHMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HistorialMedico hm = new HistorialMedico();
					hm.setModal(true);
					hm.setVisible(true);
				}
			});
			btnAbrirHMedico.setBounds(10, 23, 256, 23);
			panel_2.add(btnAbrirHMedico);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
