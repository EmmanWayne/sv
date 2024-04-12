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

import clases.administradores;
import clases.votantes;
import conexion.conexion;
import consultas.consultas_administradores;
import consultas.consultas_votantes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventana_login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdentidad;
	private JLabel lblMensaje;
	private JButton btnIngresar;
	public JLabel lblFecha;
	public static JLabel lblHora;

	public String nombreVotante;
	public String dniVotante;
	public int estadoVotante;
	public String gradoVotante;
	public String modalidadVotante;
	
	
	public String identidad;
	public String identidad2;
	public String nombreAdmin;
	public String dniAdmin;

	public ventana_login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventana_login.class.getResource("/recursos/logo_ipm.png")));
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
		lblNewLabel.setBounds(10, 109, 530, 38);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		panel.add(lblNewLabel);

		JLabel lblIpm = new JLabel("IPM 2024");
		lblIpm.setHorizontalAlignment(SwingConstants.CENTER);
		lblIpm.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblIpm.setBounds(10, 147, 530, 38);
		panel.add(lblIpm);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(140, 183, 276, 340);
		panel.add(lblLogo);
		final ImageIcon logo1 = new ImageIcon(getClass().getResource("/recursos/logo_ipm.png"));
		final ImageIcon icono1 = new ImageIcon(
				logo1.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono1);

		JLabel lblPorFavorIntroduzca = new JLabel("Por favor introduzca su DNI sin guiones para ingresar al sistema.");
		lblPorFavorIntroduzca.setForeground(Color.BLACK);
		lblPorFavorIntroduzca.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorFavorIntroduzca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPorFavorIntroduzca.setBounds(10, 554, 530, 27);
		panel.add(lblPorFavorIntroduzca);

		txtIdentidad = new JTextField();
		txtIdentidad.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setBounds(45, 592, 458, 38);
		panel.add(txtIdentidad);
		txtIdentidad.setColumns(10);
		InputMap map5 = txtIdentidad.getInputMap(JComponent.WHEN_FOCUSED);
		map5.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;

				if (!numeros) {
					e.consume();
				}

				if (txtIdentidad.getText().trim().length() == 13) {
					e.consume();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		lblMensaje = new JLabel("¡Bienvenido!");
		lblMensaje.setForeground(new Color(0, 128, 0));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblMensaje.setBounds(10, 690, 530, 38);
		panel.add(lblMensaje);

		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setBackground(new Color(0, 128, 0));
		btnIngresar.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setBounds(45, 641, 458, 38);
		panel.add(btnIngresar);
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});

		lblFecha = new JLabel("Fecha");
		lblFecha.setForeground(new Color(0, 128, 0));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblFecha.setBounds(14, 60, 523, 38);
		panel.add(lblFecha);
		lblFecha.setText(getFecha());

		lblHora = new JLabel("Hora");
		lblHora.setForeground(new Color(0, 128, 0));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblHora.setBounds(14, 11, 523, 38);
		panel.add(lblHora);

		lblInstrucciones = new JLabel("Instrucciones:");
		lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrucciones.setForeground(Color.BLACK);
		lblInstrucciones.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblInstrucciones.setBounds(10, 525, 527, 27);
		panel.add(lblInstrucciones);

		lblLimpiar = new JLabel("");
		lblLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIdentidad.setText("");
				lblMensaje.setText("¡Bienvenido!");
				lblMensaje.setForeground(new Color(0, 128, 0));
			}
		});
		lblLimpiar.setBounds(505, 592, 34, 38);
		panel.add(lblLimpiar);
		final ImageIcon logo2 = new ImageIcon(getClass().getResource("/recursos/limpiar.png"));
		final ImageIcon icono2 = new ImageIcon(
				logo2.getImage().getScaledInstance(lblLimpiar.getWidth(), lblLimpiar.getHeight(), Image.SCALE_DEFAULT));
		lblLimpiar.setIcon(icono2);
	}

	Timer time = new Timer();
	public TimerTask tarea = new TimerTask() {
		@Override
		public void run() {
			Calendar calendario = new GregorianCalendar();
			Date fechaHoraActual = new Date();
			calendario.setTime(fechaHoraActual);
			String horas;
			String minutos;
			String segundos;
			String ampm;
			Thread hilo = null;
			Thread hilo2;
			hilo2 = Thread.currentThread();
			hilo = new Thread();
			hilo.start();
			ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			if (ampm.equals("PM")) {
				int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
				horas = h > 9 ? "" + h : "0" + h;
			} else {
				horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
						: "0" + calendario.get(Calendar.HOUR_OF_DAY);
			}
			minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
					: "0" + calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
					: "0" + calendario.get(Calendar.SECOND);

			lblHora.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};
	private JLabel lblInstrucciones;
	private JLabel lblLimpiar;

	public static String getFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'del' yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	public static void getHora() {
		Calendar cal = Calendar.getInstance();
		Date fecha = cal.getTime();
		DateFormat formatter = DateFormat.getTimeInstance();
		lblHora.setText(formatter.format(fecha));

	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void limpiar() {
		txtIdentidad.setText("");
	}

	public void consultarDatosVotante() {
		conexion conex = new conexion();
		try {
			identidad = String.valueOf(txtIdentidad.getText().toString());
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM votantes WHERE dni_votante='" + identidad + "'");
			if (rs.next()) {
				nombreVotante = (rs.getString("nombre_votante"));
				dniVotante = (rs.getString("dni_votante"));
				estadoVotante = (rs.getInt("estado_votante"));
				gradoVotante = (rs.getString("grado_votante"));
				modalidadVotante = (rs.getString("modalidad_votante"));
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void consultarDatosAdministrador() {
		conexion conex = new conexion();
		try {
			identidad2 = String.valueOf(txtIdentidad.getText().toString());
			Statement estatuto = conex.conectar().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT * FROM administradores WHERE dni_administrador='" + identidad2 + "'");
			if (rs.next()) {
				nombreAdmin = (rs.getString("nombre_administrador"));
				dniAdmin = (rs.getString("dni_administrador"));
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	@SuppressWarnings("unlikely-arg-type")
	public void iniciarSesion() {
		ventana_votacion ventana_votantes = new ventana_votacion();
		ventana_menu_administracion ventana_menu = new ventana_menu_administracion();
		String user = String.valueOf(txtIdentidad.getText().toString());
		if (user.equals("")) {
			lblMensaje.setText("El DNI esta vacío, ingréselo para votar.");
			lblMensaje.setForeground(Color.RED);
		} else {
			consultas_votantes consulta = new consultas_votantes();
			votantes clase = new votantes();
			clase.setDni_votante(txtIdentidad.getText().toString());
			if (consulta.buscarVotante(clase)) {
				consultarDatosVotante();
				
				if (estadoVotante==0) {
					ventana_votantes.setLocationRelativeTo(null);
					ventana_votantes.setVisible(true);
					ventana_votantes.lblNombre.setText(nombreVotante);
					ventana_votantes.lblDni.setText(dniVotante);
					ventana_votantes.lblGrado.setText(gradoVotante);
					ventana_votantes.lblModalidad.setText(modalidadVotante);
					ventana_votantes.consultarCandidato1();
					ventana_votantes.consultarCandidato2();
					ventana_votantes.consultarCandidato3();
					ventana_votantes.consultarCandidato4();
					ventana_votantes.consultarCandidato5();
					ventana_votantes.consultarCandidato6();
					ventana_votantes.consultarCandidato7();
					ventana_votantes.establecerCandidatos();
					dispose();
				}else {
					lblMensaje.setText("¡¡Según el DNI ingresado, El votante ya realizo su voto!!");
					lblMensaje.setForeground(Color.BLUE);
				}
				
			} else {
				consultas_administradores consulta2 = new consultas_administradores();
				administradores clase2 = new administradores();
				clase2.setDni_administrador(txtIdentidad.getText().toString());
				if (consulta2.buscarAdministrador(clase2)) {
					consultarDatosAdministrador();
					ventana_menu.setLocationRelativeTo(null);
					ventana_menu.setVisible(true);
					ventana_menu.lblNombreAdmin.setText(nombreAdmin);
					ventana_menu.lblDniAdmin.setText(dniAdmin);
					dispose();
				} else {
					lblMensaje.setText("¡El DNI Ingresado es incorrecto o no existe!");
					lblMensaje.setForeground(Color.RED);
				}

			}
		}

	}
}
