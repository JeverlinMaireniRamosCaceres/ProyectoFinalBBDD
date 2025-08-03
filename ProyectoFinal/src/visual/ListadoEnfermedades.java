package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Enfermedad;
import logico.EnfermedadCRUD;

public class ListadoEnfermedades extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private int index = -1;
	private DefaultTableModel modelo;
	private Object[] row;
	private Enfermedad selected;
	private JButton btnModificar;
	private JButton btnEliminar;

	public static void main(String[] args) {
		try {
			ListadoEnfermedades dialog = new ListadoEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListadoEnfermedades() {
		setTitle("Listado de enfermedades");
		setBounds(100, 100, 611, 375);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		modelo = new DefaultTableModel();
		String[] identificadores = {"Codigo", "Nombre", "Tipo"};
		modelo.setColumnIdentifiers(identificadores);
		table.setModel(modelo);
		scrollPane.setViewportView(table);

		row = new Object[modelo.getColumnCount()];

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = table.getSelectedRow();
				if (index >= 0) {
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
					String codigo = table.getValueAt(index, 0).toString();
					selected = EnfermedadCRUD.buscarEnfermedadPorCodigo(codigo);
				}
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setActionCommand("OK");
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					RegistroEnfermedad re = new RegistroEnfermedad(selected, ListadoEnfermedades.this);

					re.setModal(true);
					re.setVisible(true);
				}
			}
		});
		buttonPane.add(btnModificar);
		getRootPane().setDefaultButton(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            if (index >= 0 && selected != null) {
	                int confirm = javax.swing.JOptionPane.showConfirmDialog(
	                    null,
	                    "¿Estás seguro de que deseas eliminar esta enfermedad?",
	                    "Confirmación de eliminación",
	                    javax.swing.JOptionPane.YES_NO_OPTION
	                );

	                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
	                    //ClinicaMedica.getInstance().eliminarCita(selected);
	                    if(EnfermedadCRUD.eliminarEnfermedad(selected.getIdEnfermedad())) {
		                    javax.swing.JOptionPane.showMessageDialog(null, "Enfermedad eliminada con éxito.");
		                    loadEnfermedades();
	                    } else {
		                    javax.swing.JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar eliminar la enfermedad.");
	                    }
	                
	                    
	                    /*btnEliminar.setEnabled(false);
	                    btnModificar.setEnabled(false);*/
	                }
	            } else {
	                javax.swing.JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna enfermedad.");
	            }
			}
		});
		btnEliminar.setEnabled(false);
		buttonPane.add(btnEliminar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("Cancel");
		btnCancelar.addActionListener(e -> dispose());
		buttonPane.add(btnCancelar);


		loadEnfermedades();
	}

	public void loadEnfermedades() {
		modelo.setRowCount(0);
		ArrayList<Enfermedad> enf = EnfermedadCRUD.obtenerEnfermedades();
		for (Enfermedad enfermedad : enf) {
			row[0] = enfermedad.getIdEnfermedad();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getTipoNombre(); 
			modelo.addRow(row);
		}
	}
}
