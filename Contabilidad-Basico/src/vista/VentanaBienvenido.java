package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
//public class VentanaBienvenido extends JFrame implements ActionListener
public class VentanaBienvenido extends JFrame {
	private int anchoVentana = 400;
	private int largoVentana = 300;
	private JDesktopPane escritorio;
	private JPanel panelPrincipal;
	private JLabel tituloFecha, fechaActual, tituloHora, horaActual;
	private JLabel labelBienvenido;
	private JButton ingresar;
	private LocalDateTime tiempo = LocalDateTime.now();
	private int year = tiempo.getYear();
	private int mes = tiempo.getMonthValue();
	private int dia = tiempo.getDayOfMonth();

	public VentanaBienvenido() {
		this.setBounds(0, 0, anchoVentana, largoVentana);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Bienvenido");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		crearContenedores();
		
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		        while (true) {
		            try {
		                Thread.sleep(500);
		                horaActual.setText(formateador.format(LocalDateTime.now()));
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		};
		Thread hilo = new Thread(runnable);
		hilo.start();
	}

	private void crearContenedores() {
		escritorio = new JDesktopPane();
		escritorio.setBounds(0, 0, anchoVentana, largoVentana);
		escritorio.setVisible(true);
		escritorio.setLayout(null);
		this.add(escritorio);

		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, anchoVentana, largoVentana);
		panelPrincipal.setVisible(true);
		panelPrincipal.setLayout(null);
		escritorio.add(panelPrincipal);
		agregarCampos();
		agregarBotones();
	}
	
	private void agregarCampos() {
		labelBienvenido = new JLabel("BIENVENIDO");
		labelBienvenido.setBounds(155, 40, 100, 30);
		panelPrincipal.add(labelBienvenido);
		
		tituloFecha = new JLabel("Fecha:");
	    tituloFecha.setBounds(270, 0, 40, 30);
	    panelPrincipal.add(tituloFecha);
	    
	    String fechaString = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(year);
	    
	    fechaActual = new JLabel(fechaString);
	    fechaActual.setBounds(310, 0, 70, 30);
	    panelPrincipal.add(fechaActual);
	    
	    tituloHora = new JLabel("Hora:");
	    tituloHora.setBounds(270, 15, 40, 30);
	    panelPrincipal.add(tituloHora);
	    
	    horaActual = new JLabel();
	    horaActual.setBounds(310, 15, 70, 30);
	    panelPrincipal.add(horaActual);
	}
	
	private void agregarBotones() {
		ingresar = new JButton("INGRESAR");
		ingresar.setBounds(140, 170, 100, 30);
		ingresar.setFocusable(false);
		panelPrincipal.add(ingresar);
		//ingresar.addActionListener(this);
	}

	public JButton getIngresar() {
		return ingresar;
	}

	public void setIngresar(JButton ingresar) {
		this.ingresar = ingresar;
	}
	
	
	
	/*public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ingresar) {
			
		}
	}*/

	/*public static void main(String[] args) {
		VentanaBienvenido ventana = new VentanaBienvenido();
		ventana.setVisible(true);
	}*/
}
