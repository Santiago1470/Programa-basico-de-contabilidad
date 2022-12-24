package vista;

import javax.swing.JInternalFrame;
import javax.swing.*;

public class JInternalFAgregarDescripcion extends JInternalFrame {
	private int anchoInternal = 400;
	private int largoInternal = 300;
	private JPanel panelIngresos, panelEgresos, panelCompras;
	private JLabel tituloAgregarDescripcion, tituloNombre;
	private JTextField nombreDescripcionIngresos, nombreDescripcionEgresos, nombreDescripcionCompras;
	private JButton agregarDescripcionIngresos, agregarDescripcionEgresos, agregarDescripcionCompras;
	
	public JInternalFAgregarDescripcion() {
		this.setBounds(0, 0, anchoInternal, largoInternal);
		this.setLayout(null);
		this.setTitle("Agregar Descripcion");
		agregarContenedor();
	}
	
	private void agregarContenedor() {
		panelIngresos = new JPanel();
		panelIngresos.setVisible(false);
		panelIngresos.setBounds(0, 0, anchoInternal, largoInternal);
		panelIngresos.setLayout(null);
		this.add(panelIngresos);
		
		panelEgresos = new JPanel();
		panelEgresos.setVisible(false);
		panelEgresos.setBounds(0, 0, anchoInternal, largoInternal);
		panelEgresos.setLayout(null);
		this.add(panelEgresos);
		
		panelCompras = new JPanel();
		panelCompras.setVisible(false);
		panelCompras.setBounds(0, 0, anchoInternal, largoInternal);
		panelCompras.setLayout(null);
		this.add(panelCompras);
		
		agregarComponentesPanelIngresos();
		agregarComponentesPanelEgresos();
		agregarComponentesPanelCompras();
	}
	
	public void agregarComponentesPanelIngresos() {
		tituloAgregarDescripcion = new JLabel("Descripcion de nuevo valor:");
		tituloAgregarDescripcion.setBounds(20, 10, 175, 30);
		panelIngresos.add(tituloAgregarDescripcion);
		
		tituloNombre = new JLabel("Nombre:");
		tituloNombre.setBounds(20, 50, 50, 30);
		panelIngresos.add(tituloNombre);
		
		nombreDescripcionIngresos = new JTextField();
		nombreDescripcionIngresos.setBounds(80, 50, 170, 30);
		panelIngresos.add(nombreDescripcionIngresos);
		
		agregarDescripcionIngresos = new JButton("AGREGAR");
		agregarDescripcionIngresos.setBounds(20, 100, 100, 30);
		agregarDescripcionIngresos.setFocusable(false);
		panelIngresos.add(agregarDescripcionIngresos);
	}
	
	public void agregarComponentesPanelEgresos() {
		tituloAgregarDescripcion = new JLabel("Descripcion de nuevo valor:");
		tituloAgregarDescripcion.setBounds(20, 10, 175, 30);
		panelEgresos.add(tituloAgregarDescripcion);
		
		tituloNombre = new JLabel("Nombre:");
		tituloNombre.setBounds(20, 50, 50, 30);
		panelEgresos.add(tituloNombre);
		
		nombreDescripcionEgresos = new JTextField();
		nombreDescripcionEgresos.setBounds(80, 50, 170, 30);
		panelEgresos.add(nombreDescripcionEgresos);
		
		agregarDescripcionEgresos = new JButton("AGREGAR");
		agregarDescripcionEgresos.setBounds(20, 100, 100, 30);
		agregarDescripcionEgresos.setFocusable(false);
		panelEgresos.add(agregarDescripcionEgresos);
	}
	
	public void agregarComponentesPanelCompras() {
		tituloAgregarDescripcion = new JLabel("Descripcion de nuevo valor:");
		tituloAgregarDescripcion.setBounds(20, 10, 175, 30);
		panelCompras.add(tituloAgregarDescripcion);
		
		tituloNombre = new JLabel("Nombre:");
		tituloNombre.setBounds(20, 50, 50, 30);
		panelCompras.add(tituloNombre);
		
		nombreDescripcionCompras = new JTextField();
		nombreDescripcionCompras.setBounds(80, 50, 170, 30);
		panelCompras.add(nombreDescripcionCompras);
		
		agregarDescripcionCompras = new JButton("AGREGAR");
		agregarDescripcionCompras.setBounds(20, 100, 100, 30);
		agregarDescripcionCompras.setFocusable(false);
		panelCompras.add(agregarDescripcionCompras);
	}

	public JPanel getPanelIngresos() {
		return panelIngresos;
	}

	public void setPanelIngresos(JPanel panelIngresos) {
		this.panelIngresos = panelIngresos;
	}

	public JPanel getPanelEgresos() {
		return panelEgresos;
	}

	public void setPanelEgresos(JPanel panelEgresos) {
		this.panelEgresos = panelEgresos;
	}

	public JPanel getPanelCompras() {
		return panelCompras;
	}

	public void setPanelCompras(JPanel panelCompras) {
		this.panelCompras = panelCompras;
	}

	public JTextField getNombreDescripcionIngresos() {
		return nombreDescripcionIngresos;
	}

	public void setNombreDescripcionIngresos(JTextField nombreDescripcionIngresos) {
		this.nombreDescripcionIngresos = nombreDescripcionIngresos;
	}

	public JTextField getNombreDescripcionEgresos() {
		return nombreDescripcionEgresos;
	}

	public void setNombreDescripcionEgresos(JTextField nombreDescripcionEgresos) {
		this.nombreDescripcionEgresos = nombreDescripcionEgresos;
	}

	public JTextField getNombreDescripcionCompras() {
		return nombreDescripcionCompras;
	}

	public void setNombreDescripcionCompras(JTextField nombreDescripcionCompras) {
		this.nombreDescripcionCompras = nombreDescripcionCompras;
	}

	public JButton getAgregarDescripcionIngresos() {
		return agregarDescripcionIngresos;
	}

	public void setAgregarDescripcionIngresos(JButton agregarDescripcionIngresos) {
		this.agregarDescripcionIngresos = agregarDescripcionIngresos;
	}

	public JButton getAgregarDescripcionEgresos() {
		return agregarDescripcionEgresos;
	}

	public void setAgregarDescripcionEgresos(JButton agregarDescripcionEgresos) {
		this.agregarDescripcionEgresos = agregarDescripcionEgresos;
	}

	public JButton getAgregarDescripcionCompras() {
		return agregarDescripcionCompras;
	}

	public void setAgregarDescripcionCompras(JButton agregarDescripcionCompras) {
		this.agregarDescripcionCompras = agregarDescripcionCompras;
	}
	
	
}
