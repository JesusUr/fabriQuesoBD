package frontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import logic.Cilindro;
import logic.Empresa;
import logic.Esferico;
import logic.Hueco;
import logic.Queso;

public class ListQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Object[] fila;
	private static JTable table;
	private static DefaultTableModel tableModel;

	public ListQueso() {

		setTitle("Lista de Quesos\r\n");
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
				String[] columnNames = {"Código","Costo_Base", "CostoUnidad", "Cantidad","Tipo Queso"};
				tableModel.setColumnIdentifiers(columnNames);
				scrollPane.setViewportView(table);
				{
					JButton btnSalir = new JButton("Volver al Inicio");
					btnSalir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					btnSalir.setBounds(355, 287, 121, 23);
					panel.add(btnSalir);
				}
				cargarTabla();


			}

		}


	}
	
	public static void cargarTabla() {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		DecimalFormat df = new DecimalFormat("#.00");
		
		try {
			Connection myConnection = Empresa.getInstance().getConnectionSQL();
			CallableStatement callstmt = myConnection.prepareCall("SELECT * FROM Queso");
			ResultSet rs = callstmt.executeQuery();
			while (rs.next()) {
			fila[0] = rs.getString(1);
			fila[1] = rs.getFloat(2);
			fila[2] = rs.getFloat(3);
			fila[3] = rs.getInt(4);
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
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(82);
		columnModel.getColumn(4).setPreferredWidth(100);


	}
}

