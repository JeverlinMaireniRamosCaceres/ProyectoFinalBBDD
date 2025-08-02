package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Vacuna;
import logico.VacunaCRUD;

public class RegistroVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JComboBox cbxTipo;
	private JSpinner spnFechaVen;
	private JComboBox cbxFabricante;
	private JSpinner spnCantidad;
	private Vacuna selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroVacuna dialog = new RegistroVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroVacuna(Vacuna vacuna) {
		
		selected = vacuna; 
		if(selected == null) {
			setTitle("Registro de vacuna");
		} else {
			setTitle("Modificar vacuna");
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 507, 236);
		setLocationRelativeTo(null);
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
			
			JLabel lblNewLabel_1 = new JLabel("Fecha de vencimiento:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(186, 22, 148, 14);
			panel.add(lblNewLabel_1);
			
			spnFechaVen = new JSpinner();
			spnFechaVen.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaVen, "dd/MM/yy");
			spnFechaVen.setEditor(dateEditor);
			spnFechaVen.setBounds(344, 19, 128, 20);
			panel.add(spnFechaVen);
			
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(4, 53, 56, 14);
			panel.add(lblNewLabel_2);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(74, 50, 203, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			String nuevoCodigo = VacunaCRUD.generarCodigoVacuna();
			txtCodigo.setText(nuevoCodigo);
			
			JLabel lblNewLabel_3 = new JLabel("Tipo:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(291, 53, 46, 14);
			panel.add(lblNewLabel_3);
			
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tuberculosis", "Hepatitis B", "Polio", "Neumococo", "Sarampi\u00F3n", "Papera", "Varicela", "T\u00E9tano", "Difteria", "Fiebre amarilla", "COVID-19"}));
			cbxTipo.setBounds(351, 50, 121, 20);
			panel.add(cbxTipo);
			
			JLabel lblNewLabel_4 = new JLabel("Fabricante:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(-4, 121, 69, 14);
			panel.add(lblNewLabel_4);
			
			cbxFabricante = new JComboBox();
			cbxFabricante.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Pfizer-BioNTech", "Moderna", "AstraZeneca", "Johnson & Johnson ", "Sinovac Biotech", "Sinopharm", "GSK ", "Sanofi Pasteur", "Merck & Co.", "Serum Institute of India"}));
			cbxFabricante.setBounds(74, 118, 380, 20);
			panel.add(cbxFabricante);
			
			JLabel lblNewLabel_5 = new JLabel("Cantidad:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(-14, 90, 80, 14);
			panel.add(lblNewLabel_5);
			
			spnCantidad = new JSpinner();
			spnCantidad.setBounds(74, 87, 80, 20);
			panel.add(spnCantidad);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				if(selected != null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if (selected == null) {
							String codigo = txtCodigo.getText();
							String nombre = txtNombre.getText();
						    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						    Date fechaVen = (Date)(spnFechaVen.getValue());
						    int tipo = cbxTipo.getSelectedIndex();
						    int fabricante = cbxFabricante.getSelectedIndex();
						    int cantidad = new Integer(spnCantidad.getValue().toString());
							
							Vacuna vacuna = new Vacuna(codigo,fechaVen,nombre,tipo,fabricante,cantidad);
							boolean exito = VacunaCRUD.insertarVacuna(vacuna);
							if (exito) {
							    JOptionPane.showMessageDialog(null, "Vacuna registrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							    clean();
							} else {
							    JOptionPane.showMessageDialog(null, "Error al registrar la vacuna", "Error", JOptionPane.ERROR_MESSAGE);
							}
							clean();
						}
						else {
						    // Validar que no esté seleccionado "<Seleccione>"
						    if (cbxTipo.getSelectedIndex() <= 0 || cbxFabricante.getSelectedIndex() <= 0) {
						        JOptionPane.showMessageDialog(null, "Seleccione tipo y fabricante válidos", 
						                                   "Error", JOptionPane.ERROR_MESSAGE);
						        return;
						    }
						    
						    // Actualizar objeto (restando 1 a los índices)
						    selected.setNombreVacuna(txtNombre.getText());
						    selected.setFecha((Date)spnFechaVen.getValue());
						    selected.setTipo(cbxTipo.getSelectedIndex() - 1); // Ajuste clave
						    selected.setFabricante(cbxFabricante.getSelectedIndex() - 1); // Ajuste clave
						    selected.setCantidad((int)spnCantidad.getValue());
						    
						    boolean exito = VacunaCRUD.actualizarVacuna(selected);
						    
						    if (exito) {
						        JOptionPane.showMessageDialog(null, "Vacuna actualizada exitosamente");
						        dispose();
						    } else {
						        JOptionPane.showMessageDialog(null, "Error al actualizar");
						    }
				            
						}
					}

					private void clean() {
						String nuevoCodigo = VacunaCRUD.generarCodigoVacuna();
						txtCodigo.setText(nuevoCodigo);
						txtNombre.setText("");
						cbxTipo.setSelectedIndex(0);
						spnCantidad.setValue(0);
						cbxFabricante.setSelectedIndex(0);
						spnFechaVen.setValue(new Date());
						
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadVacuna();
	}
	private void loadVacuna() {
		if (selected != null) {
			txtCodigo.setText(selected.getIdVacuna());
			txtNombre.setText(selected.getNombreVacuna());
			spnCantidad.setValue(selected.getCantidad());
			spnFechaVen.setValue(selected.getFecha());
	        cbxTipo.setSelectedIndex(selected.getTipo()); 
	        cbxFabricante.setSelectedIndex(selected.getFabricante());
		}
	}
}
