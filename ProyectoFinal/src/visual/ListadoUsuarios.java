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
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Usuario;
import logico.UsuarioCRUD;

public class ListadoUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private int index = -1;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnVerDetalles;
	private JButton btnModificar;
	private JButton btnEliminar;
	private Usuario selected;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoUsuarios dialog = new ListadoUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoUsuarios() {
		setTitle("Listado usuarios");
		setBounds(100, 100, 560, 345);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = table.getSelectedRow();
							if(index>=0) {
								btnVerDetalles.setEnabled(true);
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarUsuarioByCodigoBBDD(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"C�digo", "Nombre","Rol"};
					modelo.setColumnIdentifiers(identificadores);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerDetalles = new JButton("Ver detalles");
				btnVerDetalles.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DetalleUsuario du = new DetalleUsuario(selected);
						du.setModal(true);
						du.setVisible(true);
					}
				});
				btnVerDetalles.setEnabled(false);
				buttonPane.add(btnVerDetalles);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroUsuario ru = new RegistroUsuario(selected);
						ru.setModal(true);
						ru.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.setEnabled(false);
					btnEliminar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (selected != null) {
								int confirm = javax.swing.JOptionPane.showConfirmDialog(
									null,
									"�Deseas eliminar este usuario?",
									"Confirmar eliminaci�n",
									javax.swing.JOptionPane.YES_NO_OPTION
								);
								if (confirm == javax.swing.JOptionPane.YES_OPTION) {
									boolean eliminado = UsuarioCRUD.eliminarUsuarioSeguro(selected.getCodigo());
									if (eliminado) {
										javax.swing.JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
										loadUsuarios();
										btnEliminar.setEnabled(false);
										btnModificar.setEnabled(false);
										btnVerDetalles.setEnabled(false);
									} else {
										javax.swing.JOptionPane.showMessageDialog(null,
											"No se puede eliminar este usuario porque est� asignado a un m�dico.",
											"Error", javax.swing.JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
					});
					buttonPane.add(btnEliminar);

				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			loadUsuarios();
		}
	}

	public static void loadUsuarios() {
		modelo.setRowCount(0);
		ArrayList<Usuario> usua = ClinicaMedica.getInstance().getUsuariosDesdeBD();
		row = new Object[table.getColumnCount()];
		for(Usuario usuario:usua) {
			row[0] = usuario.getCodigo();
	        row[1] = usuario.getNombre();
	        row[2] = usuario.getRol();
	        modelo.addRow(row);
		}
		
	}



}
