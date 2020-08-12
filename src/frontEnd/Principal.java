package frontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;


import logic.Cilindro;
import logic.Cliente;
import logic.Empresa;
import logic.Esferico;
import logic.Factura;
import logic.Hueco;
import logic.Queso;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.SwingConstants;

/*
 * ORDEN DE EJECUCIÓN: PRIMERO EJECUTAR EL SERVIDOR DENTRO DEL FOLDER SOCKET
 * EL MISMO UTILIZA EL PUERTO 8880
 * ELABORADO POR JESÚS UREÑA, FRANCICOS MARCELINO Y JOMARY
 * 
 *  */
public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombreCli;
	private JTextField txtTelef;
	private JTextField txtDireccion;
	private JTextField txtCodeFactura;
	private JTextField txtCostoTotal;
	private String holder;
	private String holder1;
	private JButton btnAnnadir;
	private JButton btnEliminar;
	private JButton btnFacturar_1;

	private static DefaultTableModel tableModel;
	private static DefaultTableModel tableModel_1;
	private static Object[] fila;
	private static Object[] fila_1;
	private static ArrayList<Queso> quesoFactura = new ArrayList<>();
	private float sumaFactura=0;
	private boolean check=false;
	private boolean check2=false;

	private static JTable table;
	private static JTable table_1;
	private JTextField txtLey;
	private JTextField txtPagar;
	private JTextField txtITBIS;
	private JTextField txtApellido;
	private boolean haycli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Empresa.getInstance().loadInitData(Empresa.getInstance());


		try {
			Principal frame = new Principal();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Principal() throws java.text.ParseException {

		/* AGREGAR ESTO CUANDO APRENDA A USARLO
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream queseria;
				ObjectOutputStream guardarqueseria;
				try {
					queseria = new  FileOutputStream("fabriQuesos.dat");
					guardarqueseria = new ObjectOutputStream(queseria);
					guardarqueseria.writeObject(Empresa.getInstance());

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		}); */
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/iconos/cow (3).png")));
		setTitle("F\u00E1brica de Quesos La Habana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 970, 608);
		setLocationRelativeTo(null);
		MaskFormatter CedulaFormat = null;
		MaskFormatter TelFormat = null;
		try {
			CedulaFormat = new MaskFormatter("###-#######-#");
			TelFormat  = new MaskFormatter("###-###-####");
		}catch (Exception e) {

		}
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Producto");
		menuBar.add(mnNewMenu);

		JMenuItem mntmAgregarProducto = new JMenuItem("Agregar Producto");
		mntmAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegQueso regques = new RegQueso();
				regques.setLocationRelativeTo(null);
				regques.setVisible(true);
				cargarTabla();

			}
		});
		mnNewMenu.add(mntmAgregarProducto);

		JMenuItem mntmListaDeProductos = new JMenuItem("Lista de Productos");
		mntmListaDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListQueso milis = new ListQueso();
				milis.setModal(true);
				milis.setLocationRelativeTo(null);
				milis.setVisible(true);
				cargarTabla();
			}
		});
		mnNewMenu.add(mntmListaDeProductos);

		JMenu mnNewMenu_1 = new JMenu("Cliente");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmListaDeClientes = new JMenuItem("Lista de Clientes");
		mntmListaDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClientes listcli = new ListClientes();
				listcli.setModal(true);
				listcli.setLocationRelativeTo(null);
				listcli.setVisible(true);

			}
		});
		mnNewMenu_1.add(mntmListaDeClientes);

		JMenu mnFactura = new JMenu("Factura");
		menuBar.add(mnFactura);

		JMenuItem mntmListFacturas = new JMenuItem("Lista de Facturas");
		mntmListFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFactura listfact = new ListFactura();
				listfact.setModal(true);
				listfact.setLocationRelativeTo(null);
				listfact.setVisible(true);
			}
		});
		mnFactura.add(mntmListFacturas);

		JMenu mnTest = new JMenu("Test");
		menuBar.add(mnTest);

		JMenuItem mntmTesting = new JMenuItem("Testing");

		mnTest.add(mntmTesting);

		JMenu menu = new JMenu("");
		menuBar.add(menu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.inactiveCaptionBorder);
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 390, 498);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(554, 11, 390, 498);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 88, 370, 128);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 31, 64, 14);
		panel_3.add(lblNombre);

		JLabel lblNewLabel = new JLabel("Tel\u00E9fono:");
		lblNewLabel.setBounds(10, 65, 64, 14);
		panel_3.add(lblNewLabel);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(191, 65, 64, 14);
		panel_3.add(lblDireccin);

		txtNombreCli = new JTextField();
		txtNombreCli.setEditable(false);
		txtNombreCli.setBounds(75, 28, 101, 20);
		panel_3.add(txtNombreCli);
		txtNombreCli.setColumns(10);

		txtTelef = new JFormattedTextField(TelFormat);
		txtTelef.setEditable(false);
		txtTelef.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTelef.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				//int tam = txtBuscar.getText().length();
				if (txtTelef.getText().equalsIgnoreCase("   -   -    ") && !check2) {
					txtTelef.setText("   -   -    ");
					check2=true;
				}

			}
		});
		txtTelef.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtTelef.getText().equalsIgnoreCase("   -   -    ") && !check2) {
					txtTelef.setText("   -   -    ");
					check2=true;
				}
			}
		});
		txtTelef.setBounds(75, 63, 101, 20);
		panel_3.add(txtTelef);
		txtTelef.setColumns(10);


		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(262, 62, 101, 20);
		panel_3.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblApelli = new JLabel("Apellido:");
		lblApelli.setHorizontalAlignment(SwingConstants.LEFT);
		lblApelli.setBounds(191, 31, 64, 14);
		panel_3.add(lblApelli);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(262, 28, 101, 20);
		panel_3.add(txtApellido);


		JLabel lblRellenarDatos = new JLabel("Al rellenar los datos del cliente continue con la orden y ser\u00E1 registrado.");
		lblRellenarDatos.setBounds(20, 94, 350, 22);
		panel_3.add(lblRellenarDatos);
		lblRellenarDatos.setForeground(SystemColor.activeCaption);
		lblRellenarDatos.setFont(new Font("Tahoma", Font.ITALIC, 10));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Orden - Productos Elaborados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 227, 370, 260);
		panel_1.add(panel_4);
		panel_4.setLayout(null);


		btnAnnadir = new JButton("");
		btnAnnadir.setIcon(new ImageIcon(Principal.class.getResource("/iconos/flecha.png")));
		btnAnnadir.setEnabled(false);
		btnAnnadir.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				pasarAFactura();
				if (quesoFactura.size()!=0) {
					btnFacturar_1.setEnabled(true);
				}
				float aux1=0;

				cargarTabla2();
				cargarTabla();
				DecimalFormat df = new DecimalFormat("#.00");
				//	txtCostoTotal.setText(df.format((double) sumaFactura));
				txtITBIS.setText(df.format((double) sumaFactura*0.18));
				txtLey.setText(df.format((double) sumaFactura*0.10));
				txtCostoTotal.setText(df.format((double) sumaFactura+Float.valueOf(txtITBIS.getText())+Float.valueOf(txtLey.getText()) ));
			}
		});
		btnAnnadir.setBounds(421, 211, 112, 63);
		panel.add(btnAnnadir);

		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Principal.class.getResource("/iconos/flechaatras.png")));
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				while(i<quesoFactura.size()) {
					if(quesoFactura.get(i).getCode().equalsIgnoreCase(holder1)) {
						Queso aux = quesoFactura.get(i);
						Empresa.getInstance().verifiCantQuesoVolver(aux);
						sumaFactura-=(aux.costo()*aux.getCantQueso());
						DecimalFormat df = new DecimalFormat("#0.00");
						txtCostoTotal.setText(df.format((double) sumaFactura));
						quesoFactura.remove(aux);
					}

					i++;
				}
				if (quesoFactura.size()==0) {
					btnFacturar_1.setEnabled(false);
				}
				cargarTabla2();
				cargarTabla();
				//eliminar producto
			}
		});
		btnEliminar.setBounds(421, 332, 112, 63);
		panel.add(btnEliminar);



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 350, 196);
		panel_4.add(scrollPane);

		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.inactiveCaption, SystemColor.activeCaption, SystemColor.textInactiveText, SystemColor.activeCaptionBorder));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>=0){
					int index = table.getSelectedRow();
					btnAnnadir.setEnabled(true);
					btnEliminar.setEnabled(false);
					holder = (String)table.getModel().getValueAt(index, 0);

				}
			}
		});
		tableModel = new DefaultTableModel();
		String[] columnNames = {"Código","Volumen", "Precio", "Tipo","Cant."};
		tableModel.setColumnIdentifiers(columnNames);
		cargarTabla();

		scrollPane.setViewportView(table);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnActualizar.setBounds(248, 228, 112, 23);
		panel_4.add(btnActualizar);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u00FAsqueda Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 11, 370, 66);
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Empresa.getInstance().findClient(txtCedula.getText())!=null) {
					Cliente aux = Empresa.getInstance().findClient(txtCedula.getText());
					txtNombreCli.setText(aux.getNombre());
					txtTelef.setText(aux.getTelefono());
					txtDireccion.setText(aux.getDireccion());
					txtApellido.setText(aux.getApellido());
					txtNombreCli.setEditable(false);
					txtDireccion.setEditable(false);
					txtTelef.setEditable(false);
					txtApellido.setEditable(false);
					haycli=true;

				}else if(txtCedula.getText().equalsIgnoreCase("   -       - ")) {
					JOptionPane.showMessageDialog(null, "Por favor Introducir la cedula", "Busqueda", JOptionPane.WARNING_MESSAGE);
				}else {
					int option =JOptionPane.showConfirmDialog(null, "Cliente no ha sido encontrado, ¿Desea registrar el cliente con la cédula: "+txtCedula.getText() +"?","Busqueda",JOptionPane.WARNING_MESSAGE);
					if(option == JOptionPane.OK_OPTION) {
						String textced = txtCedula.getText();
						lblRellenarDatos.setForeground(SystemColor.menuText);
						clean();
						txtCedula.setText(textced);
						txtNombreCli.setEditable(true);
						txtDireccion.setEditable(true);
						txtTelef.setEditable(true);}
						txtApellido.setEditable(true);
						haycli=false;

				}
			}
		});
		btnBuscarCliente.setEnabled(false);
		btnBuscarCliente.setBounds(277, 26, 83, 23);
		panel_5.add(btnBuscarCliente);

		txtCedula = new JFormattedTextField(CedulaFormat);
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (txtCedula.getText().equalsIgnoreCase("   -       - ") && !check) {
					txtCedula.setText("   -       - ");
					check=true;
				}
				if (!txtCedula.getText().equalsIgnoreCase("   -       - ")) {
					btnBuscarCliente.setEnabled(true);
				}else {
					btnBuscarCliente.setEnabled(false);
				}
			}
		});
		txtCedula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCedula.getText().equalsIgnoreCase("   -       - ") && !check) {
					txtCedula.setText("   -       - ");
					check=true;
				}
			}
		});
		txtCedula.setBounds(101, 27, 166, 20);
		panel_5.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblCdulaCliente = new JLabel("C\u00E9dula Cliente:");
		lblCdulaCliente.setBounds(10, 30, 350, 14);
		panel_5.add(lblCdulaCliente);



		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.activeCaption);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Facturaci\u00F3n - Productos Seleccionados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 11, 370, 476);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 27, 350, 298);
		panel_6.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setBackground(Color.WHITE);
		table_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.inactiveCaption, SystemColor.activeCaption, SystemColor.textInactiveText, SystemColor.activeCaptionBorder));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_1.getSelectedRow();
				btnAnnadir.setEnabled(false);
				btnEliminar.setEnabled(true);
				holder1 = (String)table_1.getModel().getValueAt(index, 0);
			}
		});
		tableModel_1=new DefaultTableModel();
		String[] columnNames1 = {"Código","Volumen", "Precio", "Tipo","Cant"};
		tableModel_1.setColumnIdentifiers(columnNames1);
		scrollPane_1.setViewportView(table_1);
		cargarTabla2();

		JLabel lblCdigoFactura = new JLabel("C\u00F3digo Factura:");
		lblCdigoFactura.setBounds(10, 340, 100, 14);
		panel_6.add(lblCdigoFactura);

		JLabel lblNewLabel_1 = new JLabel("Costo Total:");
		lblNewLabel_1.setBounds(185, 340, 78, 14);
		panel_6.add(lblNewLabel_1);

		txtCodeFactura = new JTextField();
		txtCodeFactura.setEditable(false);
		txtCodeFactura.setText("FA-"+(Empresa.getInstance().getMiFactura().size()+1));

		txtCodeFactura.setBounds(107, 337, 60, 20);
		panel_6.add(txtCodeFactura);
		txtCodeFactura.setColumns(10);

		txtCostoTotal = new JTextField();
		txtCostoTotal.setEditable(false);
		txtCostoTotal.setBounds(280, 337, 78, 20);
		panel_6.add(txtCostoTotal);
		txtCostoTotal.setColumns(10);

		btnFacturar_1 =  new JButton("Facturar");
		btnFacturar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!txtCedula.getText().equalsIgnoreCase("   -       - ") && !txtNombreCli.getText().equalsIgnoreCase("") && !txtApellido.getText().equalsIgnoreCase("") && !txtDireccion.getText().equalsIgnoreCase("") && !txtTelef.getText().equalsIgnoreCase("   -   -    ")) {    
					float pago = 0;
					float deuda = 0;
					System.out.println("entro if ced");
					deuda = Float.valueOf(txtCostoTotal.getText());

					pago = Float.valueOf(txtPagar.getText());

					if (pago < deuda) {

						txtPagar.setText("");

						JOptionPane.showMessageDialog(null, "El monto a pagar es superior a esta cifra!", "Advertencia!", JOptionPane.WARNING_MESSAGE);

					} else {
						System.out.println("entro al else");
						String cedula = txtCedula.getText();
						String nombre = txtNombreCli.getText();
						String apellido =txtApellido.getText();
						String direccion = txtDireccion.getText();
						String telefono = txtTelef.getText();
						if(haycli==false) {
							System.out.println("no hay cli registrado");
							Cliente aux = new Cliente(cedula, nombre, apellido, direccion, telefono);
							Empresa.getInstance().agregarCliente(aux);
							Factura mifactu = Empresa.getInstance().crearFactura(aux, quesoFactura);
							int codig= Empresa.getInstance().getMiFactura().size();

							Empresa.getInstance().agregarFactura(mifactu);
							JOptionPane.showMessageDialog(null, "Compra satisfactoria, la devuelta es: "+Math.round(pago-deuda)+" Pesos", "Facturacion", JOptionPane.INFORMATION_MESSAGE);
							quesoFactura.clear();
							txtCodeFactura.setText("FA-"+(codig+1));
							cargarTabla();
							cargarTabla2();
							txtCostoTotal.setText("0.00");
							txtPagar.setText("0.00");
							sumaFactura=0;
							lblRellenarDatos.setForeground(SystemColor.menu);
							clean();	
							
						}else {
							System.out.println("hay cli registrado");
							Cliente aux = Empresa.getInstance().findClient(cedula);
							Factura mifactu = Empresa.getInstance().crearFactura(aux, quesoFactura);
						
							int codig= Empresa.getInstance().getMiFactura().size();
	
							Empresa.getInstance().agregarFactura(mifactu);
							JOptionPane.showMessageDialog(null, "Compra satisfactoria, la devuelta es: "+Math.round(pago-deuda)+" Pesos", "Facturacion", JOptionPane.INFORMATION_MESSAGE);
							quesoFactura.clear();
							txtCodeFactura.setText("FA-"+(codig+1));
							cargarTabla();
							cargarTabla2();
							txtCostoTotal.setText("0.00");
							txtPagar.setText("0.00");
							sumaFactura=0;
							lblRellenarDatos.setForeground(SystemColor.menu);
							clean();	
						}
						
						

					}
				}
			}
		});
		btnFacturar_1.setBounds(107, 435, 141, 30);
		panel_6.add(btnFacturar_1);
		btnFacturar_1.setEnabled(false);

		JLabel lblLey = new JLabel("% Ley:");
		lblLey.setBounds(61, 366, 42, 14);
		panel_6.add(lblLey);

		JLabel lblItbis = new JLabel("ITBIS:");
		lblItbis.setBounds(61, 398, 49, 14);
		panel_6.add(lblItbis);

		txtLey = new JTextField();
		txtLey.setEditable(false);
		txtLey.setBounds(107, 366, 60, 20);
		panel_6.add(txtLey);
		txtLey.setColumns(10);

		txtITBIS = new JTextField();
		txtITBIS.setEditable(false);
		txtITBIS.setBounds(107, 395, 60, 20);
		panel_6.add(txtITBIS);
		txtITBIS.setColumns(10);

		JLabel lblCantidadPagada = new JLabel("Total Pagado:");
		lblCantidadPagada.setBounds(185, 368, 93, 14);
		panel_6.add(lblCantidadPagada);

		txtPagar = new JTextField();
		txtPagar.setBounds(280, 363, 78, 20);
		panel_6.add(txtPagar);
		txtPagar.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Principal.class.getResource("/iconos/cow (2).png")));
		lblNewLabel_2.setBounds(413, 11, 131, 134);
		panel.add(lblNewLabel_2);

		JPanel panel_7 = new JPanel();
		panel_7.setForeground(SystemColor.textHighlight);
		panel_7.setBackground(SystemColor.inactiveCaption);
		panel_7.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.activeCaption, SystemColor.inactiveCaption, SystemColor.scrollbar, SystemColor.menu));
		panel_7.setBounds(0, 520, 954, 28);
		panel.add(panel_7);
	}

	private void pasarAFactura() {
		int i=0;

		while(i<Empresa.getInstance().getMiQuesos().size()) {
			if(Empresa.getInstance().getMiQuesos().get(i).getCode().equalsIgnoreCase(holder)) {
				int cantQueso= Empresa.getInstance().getMiQuesos().get(i).getCantQueso();
				Queso aux = Empresa.getInstance().getMiQuesos().get(i);

				if(aux instanceof Esferico) {

					Esferico cheese = (Esferico) Empresa.getInstance().getMiQuesos().get(i);
					float preciuni = cheese.getCostoUnit();
					float precibase = cheese.getCostoBase();
					float radio= cheese.getRadioEsfera();
					String tipoqueso = cheese.getTipoqueso();

					Empresa.getInstance().getMiQuesos().get(i).setCantQueso(cantQueso);
					String codigo = cheese.getCode();
					Queso aa = new Esferico(precibase, preciuni, 1, codigo, tipoqueso, radio);
					if (Empresa.getInstance().verifiCantQueso(aa)) {
						sumaFactura+=(aa.costo()*aa.getCantQueso());
						quesoFactura.add(rett(aa));
					}else {
						JOptionPane.showMessageDialog(null, "No hay suficiente cantidad de este producto", "Cantidad", JOptionPane.WARNING_MESSAGE);
					}
				}else if(aux instanceof Cilindro) {

					Cilindro cheese = (Cilindro) Empresa.getInstance().getMiQuesos().get(i);
					float preciuni = cheese.getCostoUnit();
					float precibase = cheese.getCostoBase();
					float radio = cheese.getRadioExterior();
					float longitud = cheese.getAltura();
					String tipoqueso = cheese.getTipoqueso();
					Empresa.getInstance().getMiQuesos().get(i).setCantQueso(cantQueso);
					String codigo = cheese.getCode();
					Queso aa = new Cilindro(precibase, preciuni, 1, codigo, tipoqueso, radio, longitud);
					if (Empresa.getInstance().verifiCantQueso(aa)) {
						sumaFactura+=(aa.costo()*aa.getCantQueso());
						quesoFactura.add(rett(aa));
					}else {
						JOptionPane.showMessageDialog(null, "No hay suficiente cantidad de este producto", "Cantidad", JOptionPane.WARNING_MESSAGE);
					}

				}else if(aux instanceof Hueco) {

					Hueco cheese = (Hueco) Empresa.getInstance().getMiQuesos().get(i);
					float preciuni = cheese.getCostoUnit();
					float precibase = cheese.getCostoBase();
					float radio = cheese.getRadioExterior();
					float longitud = cheese.getAltura();
					float radioInterno = cheese.getRadioInterior();
					String tipoqueso = cheese.getTipoqueso();
					Empresa.getInstance().getMiQuesos().get(i).setCantQueso(cantQueso);
					String codigo = cheese.getCode();
					Queso aa = new Hueco(precibase, preciuni, 1, codigo, tipoqueso, radioInterno, radio,longitud);
					if (Empresa.getInstance().verifiCantQueso(aa)) {
						sumaFactura+=(aa.costo()*aa.getCantQueso());
						quesoFactura.add(rett(aa));
					}else {
						JOptionPane.showMessageDialog(null, "No hay suficiente cantidad de este producto", "Cantidad", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			i++;
		}

		while(i<quesoFactura.size()) {
			if(quesoFactura.get(i).getCode().equalsIgnoreCase(holder)) {
				int cantQueso = 1;

				quesoFactura.get(i).setCantQueso(cantQueso);
			}
			i++;
		}
	}


	private Queso rett(Queso aux) {
		return aux;
	}


	private void clean() {
		txtNombreCli.setText("");
		txtDireccion.setText("");
		txtITBIS.setText("");
		txtLey.setText("");
		txtPagar.setText("");
		txtTelef.setText(null);
		txtCedula.setText(null);
		txtNombreCli.setEditable(false);
		txtDireccion.setEditable(false);
		txtTelef.setEditable(false);
		btnAnnadir.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnFacturar_1.setEnabled(false);

		cargarTabla();
		cargarTabla2();

	}
	public static void cargarTabla() {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		DecimalFormat df = new DecimalFormat("#.00");
		for (Queso aux : Empresa.getInstance().getMiQuesos()) {
			if (aux.getCantQueso()!=0) {
				fila[0] = aux.getCode();
				fila[1] = df.format((double) aux.volumen());
				fila[2] = df.format((double) aux.costo());
				if(aux instanceof Esferico)
					fila[3] = "Esfera";
				if(aux instanceof Cilindro)
					fila[3] = "Cilindro";
				if(aux instanceof Hueco)
					fila[3] = "Hueco";
				fila[4] = aux.getCantQueso();
				tableModel.addRow(fila);

			}


		}


		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(70);
		columnModel.getColumn(1).setPreferredWidth(68);
		columnModel.getColumn(2).setPreferredWidth(82);
		columnModel.getColumn(3).setPreferredWidth(81);
		columnModel.getColumn(4).setPreferredWidth(46);
		//total 347


	}




	public static void cargarTabla2() {
		tableModel_1.setRowCount(0);
		fila_1 = new Object[tableModel_1.getColumnCount()];
		DecimalFormat df = new DecimalFormat("#.00");
		for (Queso aux : quesoFactura) {
			fila_1[0] = aux.getCode();
			fila_1[1] = df.format((double) aux.volumen());
			fila_1[2] = df.format((double) aux.costo());
			if(aux instanceof Esferico)
				fila_1[3] = "Esfera";
			if(aux instanceof Cilindro)
				fila_1[3] = "Cilindro";
			if(aux instanceof Hueco)
				fila_1[3] = "Hueco";
			fila_1[4] = aux.getCantQueso();
			tableModel_1.addRow(fila_1);

		}
		//total 347
		table_1.setModel(tableModel_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel1 = table_1.getColumnModel();
		columnModel1.getColumn(0).setPreferredWidth(65);
		columnModel1.getColumn(1).setPreferredWidth(68);
		columnModel1.getColumn(2).setPreferredWidth(82);
		columnModel1.getColumn(3).setPreferredWidth(86);
		columnModel1.getColumn(4).setPreferredWidth(46);


	}
}
