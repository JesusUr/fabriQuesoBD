package frontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import logic.Empresa;
import logic.Factura;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListFactura extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private static Object[] fila;
	private static JTable table;
	private static DefaultTableModel tableModel;

	public ListFactura() {
		setTitle("Lista de Facturas\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListClientes.class.getResource("/iconos/cow (3).png")));
		setBounds(100, 100, 513, 369);
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
				scrollPane.setBounds(10, 11, 466, 265);
				scrollPane.setOpaque(false);
				panel.add(scrollPane);

				table = new JTable();
				table.setBackground(Color.WHITE);
				table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.inactiveCaption, SystemColor.activeCaption, SystemColor.textInactiveText, SystemColor.activeCaptionBorder));
				tableModel = new DefaultTableModel();
				String[] columnNames = {"Código","Cédula", "Total Factura", "Cant. Queso"};
				tableModel.setColumnIdentifiers(columnNames);
				scrollPane.setViewportView(table);
				{
					JButton btnNewButton = new JButton("Volver al Inicio");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();						}
					});
					btnNewButton.setBounds(356, 287, 121, 23);
					panel.add(btnNewButton);
				}
				cargarTabla();
			}

		}


	}

	public static void cargarTabla() {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		DecimalFormat df = new DecimalFormat("#0.00");
	
		try {
			Connection myConnection = Empresa.getInstance().getConnectionSQL();
			CallableStatement callstmt = myConnection.prepareCall("SELECT * FROM Factura");
			ResultSet rs = callstmt.executeQuery();
			while (rs.next()) {
			fila[0] = rs.getString(1);
			fila[1] = rs.getString(2);
			fila[2] = rs.getFloat(3);
			fila[3] = rs.getInt(4);
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
		columnModel.getColumn(0).setPreferredWidth(86);
		columnModel.getColumn(1).setPreferredWidth(122);
		columnModel.getColumn(2).setPreferredWidth(122);
		columnModel.getColumn(3).setPreferredWidth(133);
	}
}

