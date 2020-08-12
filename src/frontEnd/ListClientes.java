package frontEnd;

import java.awt.BorderLayout;



import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import logic.Cliente;
import logic.Empresa;



import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ListClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Object[] fila;
	private static JTable table;
	private static DefaultTableModel tableModel;


	public ListClientes() {
		setTitle("Lista de Clientes\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListClientes.class.getResource("/iconos/cow (3).png")));
		setBounds(100, 100, 509, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBackground(SystemColor.inactiveCaption);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 11, 460, 265);
				scrollPane.setOpaque(false);
				panel.add(scrollPane);

				table = new JTable();
				table.setBackground(Color.WHITE);
				table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.inactiveCaption, SystemColor.activeCaption, SystemColor.textInactiveText, SystemColor.activeCaptionBorder));
				tableModel = new DefaultTableModel();
				String[] columnNames = {"Cédula","Nombre", "Apellido", "Dirección","Teléfono"};
				tableModel.setColumnIdentifiers(columnNames);
				scrollPane.setViewportView(table);
				{
					JButton btnSalir = new JButton("Volver al Inicio");
					btnSalir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					btnSalir.setBounds(355, 286, 121, 23);
					panel.add(btnSalir);
				}
				cargarTabla();


			}

		}


	}

	public static void cargarTabla() {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		
		try {
			Connection myConnection = Empresa.getInstance().getConnectionSQL();
			CallableStatement callstmt = myConnection.prepareCall("SELECT * FROM CLIENTE");
			ResultSet rs = callstmt.executeQuery();
			while (rs.next()) {
			fila[0] = rs.getString(1);
			fila[1] = rs.getString(2);
			fila[2] = rs.getString(3);
			fila[3] = rs.getString(4);
			fila[4] = rs.getString(5);
			tableModel.addRow(fila);
			}
		} catch (SQLException e) {
			System.out.println("Error agregarfact");
			e.printStackTrace();
		}

		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(120);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(97);
	}
}
