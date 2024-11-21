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
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtMedico;
	private JTextField txtEspecialidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroCita dialog = new RegistroCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroCita() {
		setTitle("Registrar Cita");
		setBounds(100, 100, 466, 319);
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
			panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n general de la cita:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 11, 421, 110);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				lblNewLabel.setBounds(8, 27, 55, 14);
				panel_1.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(69, 24, 342, 20);
			panel_1.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(8, 58, 55, 14);
			panel_1.add(lblNewLabel_1);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(71, 55, 340, 20);
			panel_1.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Fecha:");
			lblNewLabel_3.setBounds(18, 86, 46, 14);
			panel_1.add(lblNewLabel_3);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JSpinner spnFecha = new JSpinner();
			spnFecha.setBounds(72, 83, 136, 20);
			panel_1.add(spnFecha);
			spnFecha.setModel(new SpinnerDateModel(new Date(1732161600000L), null, null, Calendar.DAY_OF_YEAR));
			
			JLabel lblNewLabel_5 = new JLabel("Hora:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(219, 86, 46, 14);
			panel_1.add(lblNewLabel_5);
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1732161600000L), null, null, Calendar.HOUR));
			spinner.setBounds(275, 83, 136, 20);
			panel_1.add(spinner);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Informaci\u00F3n del m\u00E9dico:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 132, 420, 92);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("M\u00E9dico:");
			lblNewLabel_2.setBounds(14, 63, 46, 14);
			panel_2.add(lblNewLabel_2);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			
			txtMedico = new JTextField();
			txtMedico.setEditable(false);
			txtMedico.setBounds(74, 60, 113, 20);
			panel_2.add(txtMedico);
			txtMedico.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Especialidad:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(201, 63, 76, 14);
			panel_2.add(lblNewLabel_4);
			
			txtEspecialidad = new JTextField();
			txtEspecialidad.setEditable(false);
			txtEspecialidad.setBounds(291, 60, 113, 20);
			panel_2.add(txtEspecialidad);
			txtEspecialidad.setColumns(10);
			
			JButton btnSeleccionar = new JButton("Seleccionar");
			btnSeleccionar.setBounds(20, 26, 390, 23);
			panel_2.add(btnSeleccionar);
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
