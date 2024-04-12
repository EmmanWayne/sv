package ventanas;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import clases.votantes;
import conexion.conexion;
import consultas.consultas_votantes;

public class ventana_menu_administracion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public String nombreVotante;
	public String dniVotante;
	public String identidad;
	
	public JLabel lblNombreAdmin;
	public JLabel lblDniAdmin;

	public ventana_menu_administracion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventana_menu_administracion.class.getResource("/recursos/logo_ipm.png")));
		setTitle("Sistema de votación - IPM 2024.");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 11, 550, 739);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("SISTEMA DE VOTACIÓN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 530, 38);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		panel.add(lblNewLabel);

		JLabel lblIpm = new JLabel("IPM 2024");
		lblIpm.setHorizontalAlignment(SwingConstants.CENTER);
		lblIpm.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblIpm.setBounds(10, 49, 530, 38);
		panel.add(lblIpm);
		
		JButton btnCandidatos = new JButton("CANDIDATOS");
		btnCandidatos.setForeground(Color.WHITE);
		btnCandidatos.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnCandidatos.setBackground(new Color(0, 139, 139));
		btnCandidatos.setBounds(10, 323, 530, 74);
		panel.add(btnCandidatos);
		
		JButton btnVotantes = new JButton("VOTANTES");
		btnVotantes.setForeground(Color.WHITE);
		btnVotantes.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnVotantes.setBackground(new Color(0, 139, 139));
		btnVotantes.setBounds(10, 441, 530, 74);
		panel.add(btnVotantes);
		
		JButton btnAdministradores = new JButton("ADMINISTRADORES");
		btnAdministradores.setForeground(Color.WHITE);
		btnAdministradores.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnAdministradores.setBackground(new Color(0, 139, 139));
		btnAdministradores.setBounds(10, 561, 530, 74);
		panel.add(btnAdministradores);
		
		JLabel lblMenDeAdministracin = new JLabel("Menú de administración");
		lblMenDeAdministracin.setForeground(new Color(0, 128, 0));
		lblMenDeAdministracin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenDeAdministracin.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblMenDeAdministracin.setBounds(10, 85, 530, 51);
		panel.add(lblMenDeAdministracin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 147, 530, 130);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAdministrador = new JLabel("Administrador:");
		lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrador.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAdministrador.setBounds(10, 11, 510, 30);
		panel_1.add(lblAdministrador);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNombre.setBounds(79, 43, 142, 38);
		panel_1.add(lblNombre);
		
		JLabel lblAdministrador_1_1 = new JLabel("DNI:");
		lblAdministrador_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdministrador_1_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblAdministrador_1_1.setBounds(79, 76, 142, 38);
		panel_1.add(lblAdministrador_1_1);
		
		 lblNombreAdmin = new JLabel("Nombre");
		lblNombreAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreAdmin.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNombreAdmin.setBounds(185, 43, 335, 38);
		panel_1.add(lblNombreAdmin);
		
		 lblDniAdmin = new JLabel("DNI");
		lblDniAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDniAdmin.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDniAdmin.setBounds(185, 76, 335, 38);
		panel_1.add(lblDniAdmin);
		
		JLabel lblParaAdministrarLos = new JLabel("Administrar los candidatos del sistema de votaciones.");
		lblParaAdministrarLos.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaAdministrarLos.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblParaAdministrarLos.setBounds(10, 288, 530, 38);
		panel.add(lblParaAdministrarLos);
		
		JLabel lblAdministrarLosVotantes = new JLabel("Administrar los votantes del sistema de votaciones.");
		lblAdministrarLosVotantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarLosVotantes.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblAdministrarLosVotantes.setBounds(10, 405, 530, 38);
		panel.add(lblAdministrarLosVotantes);
		
		JLabel lblParaAdministrarLos_2_1 = new JLabel("Administrar los usuarios administrativos del sistema de votaciones.");
		lblParaAdministrarLos_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaAdministrarLos_2_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblParaAdministrarLos_2_1.setBounds(10, 526, 530, 38);
		panel.add(lblParaAdministrarLos_2_1);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_login login = new ventana_login();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				Timer time = new Timer();
				time.schedule(login.tarea, 0, 1000);
				dispose();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnSalir.setBackground(new Color(210, 105, 30));
		btnSalir.setBounds(10, 646, 530, 74);
		panel.add(btnSalir);
	}


	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
