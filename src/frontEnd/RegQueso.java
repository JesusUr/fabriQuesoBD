package frontEnd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import logic.Cilindro;
import logic.Empresa;
import logic.Esferico;
import logic.Hueco;
import logic.Queso;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.awt.SystemColor;


public class RegQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtFprecioUni;
	private JTextField txtBase;
	private JTextField txtRadio;
	private JTextField txtFlongitud;
	private JTextField txtRadioInterno;
	private JLabel lblLongitud;
	private JLabel lblRadioInterno;
	private JRadioButton rdbtnQuesoEsferico;
	private JRadioButton rdbtnQuesoCilindro;
	private JRadioButton rdbtnQuesoCilindroHueco;
	private JPanel panel_queso ;
	private boolean noponer=true;
	private int ubica=1;
	private JPanel panel_1;
	private JPanel panel_2;

	 
	public RegQueso() {
		
		setTitle("Registrar Producto");
		setBounds(100, 100, 524, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 485, 84);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 463, 62);
			panel_1.add(panel);
			panel.setBackground(SystemColor.activeCaption);
			panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			panel.setBorder(new TitledBorder(null, "Tipo de Queso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setLayout(null);
			
			rdbtnQuesoEsferico = new JRadioButton("Esf\u00E9rico");
			rdbtnQuesoEsferico.setBackground(SystemColor.activeCaption);
			rdbtnQuesoEsferico.setSelected(true);
			rdbtnQuesoEsferico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel_queso.setBorder(new TitledBorder(null, "Informaci\u00F3n del Queso Esférico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					rdbtnQuesoEsferico.setSelected(true);
					rdbtnQuesoCilindro.setSelected(false);
					rdbtnQuesoCilindroHueco.setSelected(false);
					lblLongitud.setVisible(false);
					txtFlongitud.setVisible(false);
					lblRadioInterno.setVisible(false);
					txtRadioInterno.setVisible(false);
					txtFlongitud.setText("");
					txtRadioInterno.setText("");
					txtCodigo.setText("QESF-"+(Empresa.getInstance().getMiQuesos().size()+1));
					
				}
			});
			rdbtnQuesoEsferico.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnQuesoEsferico.setBounds(24, 19, 107, 23);
			panel.add(rdbtnQuesoEsferico);
			
			rdbtnQuesoCilindro = new JRadioButton("Cil\u00EDndrico");
			rdbtnQuesoCilindro.setBackground(SystemColor.activeCaption);
			rdbtnQuesoCilindro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel_queso.setBorder(new TitledBorder(null, "Informaci\u00F3n del Queso Cilíndrico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					rdbtnQuesoEsferico.setSelected(false);
					rdbtnQuesoCilindro.setSelected(true);
					rdbtnQuesoCilindroHueco.setSelected(false);
					lblLongitud.setVisible(true);
					txtFlongitud.setVisible(true);
					lblRadioInterno.setVisible(false);
					txtRadioInterno.setVisible(false);
					txtFlongitud.setText("");
					txtRadioInterno.setText("");
					txtCodigo.setText("QCIL-"+(Empresa.getInstance().getMiQuesos().size()+1));
					//Cilindro
					
				}
			});
			rdbtnQuesoCilindro.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnQuesoCilindro.setBounds(173, 19, 122, 23);
			panel.add(rdbtnQuesoCilindro);
			
			rdbtnQuesoCilindroHueco = new JRadioButton("Cilindrico Hueco");
			rdbtnQuesoCilindroHueco.setBackground(SystemColor.activeCaption);
			rdbtnQuesoCilindroHueco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel_queso.setBorder(new TitledBorder(null, "Informaci\u00F3n del Queso Cilíndrico Hueco", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					rdbtnQuesoEsferico.setSelected(false);
					rdbtnQuesoCilindro.setSelected(false);
					rdbtnQuesoCilindroHueco.setSelected(true);
					lblLongitud.setVisible(true);
					txtFlongitud.setVisible(true);
					txtFlongitud.setText("");
					txtRadioInterno.setText("");
					lblRadioInterno.setVisible(true);
					txtRadioInterno.setVisible(true);
					txtCodigo.setText("QHUE-"+(Empresa.getInstance().getMiQuesos().size()+1));
					//hueco
				}
			});
			rdbtnQuesoCilindroHueco.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnQuesoCilindroHueco.setBounds(326, 19, 122, 23);
			panel.add(rdbtnQuesoCilindroHueco);
		}
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(10, 114, 485, 154);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		panel_queso = new JPanel();
		panel_queso.setBounds(12, 11, 463, 132);
		panel_2.add(panel_queso);
		panel_queso.setBackground(SystemColor.activeCaption);
		panel_queso.setBorder(new TitledBorder(null, "Informaci\u00F3n del Queso Esférico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_queso.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(10, 21, 55, 20);
		panel_queso.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setText("QESF-"+(Empresa.getInstance().getMiQuesos().size()+1));
		
		
		
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodigo.setBounds(75, 21, 117, 20);
		panel_queso.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblPrecioUnitario = new JLabel("Precio Unitario:");
		lblPrecioUnitario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioUnitario.setBounds(10, 59, 86, 20);
		panel_queso.add(lblPrecioUnitario);
		
		txtFprecioUni = new JTextField();
		txtFprecioUni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (ubica!=1) {
					ubica=1;
					noponer=true;
				}
				if (!Character.isDigit(c) && (!(c==46) || noponer!=true)) {
					e.consume();
				}
				if ((c==46)) {
					noponer=false;
				}
			}
		});
		txtFprecioUni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFprecioUni.setBounds(106, 57, 86, 20);
		panel_queso.add(txtFprecioUni);
		txtFprecioUni.setColumns(10);
		
		JLabel lblPrecioBase = new JLabel("Precio Base: ");
		lblPrecioBase.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioBase.setBounds(10, 96, 77, 20);
		panel_queso.add(lblPrecioBase);
		
		txtBase = new JTextField();
		txtBase.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (ubica!=2) {
					ubica=2;
					noponer=true;
				}
				if (!Character.isDigit(c) && (!(c==46) || noponer!=true)) {
					e.consume();
				}
				if ((c==46)) {
					noponer=false;
				}
			}
		});
		txtBase.setBounds(87, 96, 105, 20);
		panel_queso.add(txtBase);
		txtBase.setColumns(10);
		
		JLabel lblRadio = new JLabel("Radio: ");
		lblRadio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRadio.setBounds(247, 21, 46, 20);
		panel_queso.add(lblRadio);
		
				txtRadio = new JTextField();
				txtRadio.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar();
						if (ubica!=3) {
							ubica=3;
							noponer=true;
						}
						if (!Character.isDigit(c) && (!(c==46) || noponer!=true)) {
							e.consume();
						}
						if ((c==46)) {
							noponer=false;
						}
					}
				});
				txtRadio.setBounds(303, 22, 140, 20);
				panel_queso.add(txtRadio);
				txtRadio.setColumns(10);
				
						lblLongitud = new JLabel("Longitud: ");
						lblLongitud.setVisible(false);
						lblLongitud.setFont(new Font("Tahoma", Font.PLAIN, 12));
						lblLongitud.setBounds(247, 59, 65, 20);
						panel_queso.add(lblLongitud);
						
						txtFlongitud = new JTextField();
						txtFlongitud.addKeyListener(new KeyAdapter() {
							@Override
							public void keyTyped(KeyEvent e) {
								if (ubica!=4) {
									ubica=4;
									noponer=true;
								}
								char c = e.getKeyChar();

								if (!Character.isDigit(c) && (!(c==46) || noponer!=true)) {
									e.consume();
								}
								if ((c==46)) {
									noponer=false;
								}
							}
						});
						txtFlongitud.setVisible(false);
						txtFlongitud.setColumns(10);
						txtFlongitud.setBounds(303, 60, 140, 20);
						panel_queso.add(txtFlongitud);
						
						lblRadioInterno = new JLabel("Radio Interno:");
						lblRadioInterno.setVisible(false);
						lblRadioInterno.setFont(new Font("Tahoma", Font.PLAIN, 12));
						lblRadioInterno.setBounds(247, 96, 86, 20);
						panel_queso.add(lblRadioInterno);
						
						txtRadioInterno = new JTextField();
						txtRadioInterno.addKeyListener(new KeyAdapter() {
							@Override
							public void keyTyped(KeyEvent e) {
								if (ubica!=5) {
									ubica=5;
									noponer=true;
								}
								char c = e.getKeyChar();

								if (!Character.isDigit(c) && (!(c==46) || noponer!=true)) {
									e.consume();
								}
								if ((c==46)) {
									noponer=false;
								}
							}
						});
						txtRadioInterno.setVisible(false);
						txtRadioInterno.setColumns(10);
						txtRadioInterno.setBounds(338, 97, 105, 20);
						panel_queso.add(txtRadioInterno);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtBase.getText().equalsIgnoreCase("") && (!txtFlongitud.getText().equalsIgnoreCase("") || !rdbtnQuesoCilindro.isSelected())&& (!txtFlongitud.getText().equalsIgnoreCase("") || !rdbtnQuesoCilindroHueco.isSelected()) && !txtFprecioUni.getText().equalsIgnoreCase("") && !txtRadio.getText().equalsIgnoreCase("") && (!txtRadioInterno.getText().equalsIgnoreCase("")  || !rdbtnQuesoCilindroHueco.isSelected())) {
							float preciuni = (float) Double.parseDouble(txtFprecioUni.getText());
							float precibase = (float) Double.parseDouble(txtBase.getText());
							float radio= (float) Double.parseDouble(txtRadio.getText());
							boolean mayor = false;
							
							if(rdbtnQuesoEsferico.isSelected()) {
								Queso aux = new Esferico(precibase, preciuni, 1, txtCodigo.getText(), "Esfera", radio);
										
								if (Empresa.getInstance().AgregarQueso(aux)) {
									System.out.println("agregado con exito");
								}else {
									System.out.println("sin exito");
								}
								
							}else if(rdbtnQuesoCilindro.isSelected()) {
								float longitud = (float) Double.parseDouble(txtFlongitud.getText());
								
								Queso aux = new Cilindro(precibase, preciuni, 1, txtCodigo.getText(),"Cilindro", radio, longitud);
								if (Empresa.getInstance().AgregarQueso(aux)) {
									System.out.println("agregado cilindrico con exito");
									
								}else {
									System.out.println("problema con cilindrico");
								}
							}else if(rdbtnQuesoCilindroHueco.isSelected()) {
								float longitud = (float) Double.parseDouble(txtFlongitud.getText());
								float radioInterno = (float) Double.parseDouble(txtRadioInterno.getText());
								if (radioInterno<radio) {
									Queso aux = new Hueco(precibase, preciuni, 1, txtCodigo.getText(),"Hueco",radioInterno,radio, longitud );
									if (Empresa.getInstance().AgregarQueso(aux)) {
										System.out.println("agregado hueco con exito");
									}else {
										System.out.println("problema con hueco");
									}
								}else {
									mayor=true;
								}
							}
							if (mayor==false) {
								txtFprecioUni.setText("");
								txtBase.setText("");
								txtFlongitud.setText("");
								txtRadio.setText("");
								txtRadioInterno.setText("");
								txtCodigo.setText("QESF-"+(Empresa.getInstance().getMiQuesos().size()+1));
								rdbtnQuesoEsferico.setSelected(true);
								rdbtnQuesoCilindro.setSelected(false);
								rdbtnQuesoCilindroHueco.setSelected(false);
								lblLongitud.setVisible(false);
								txtFlongitud.setVisible(false);
								lblRadioInterno.setVisible(false);
								txtRadioInterno.setVisible(false);
								txtFlongitud.setText("");
								txtRadioInterno.setText("");
								noponer=true;
								
								JOptionPane.showMessageDialog(null, "Producto guardado", "Validación", JOptionPane.INFORMATION_MESSAGE);
							
							}else {
								JOptionPane.showMessageDialog(null, "El radio interno es mayor que externo, favor de cambiarlo", "Validación", JOptionPane.WARNING_MESSAGE);
								
							}
							}else {
							JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
						}
						//Guardar
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Volver al Inicio");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}