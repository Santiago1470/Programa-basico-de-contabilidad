package vista;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JInternalFEliminarDescripcion extends JInternalFrame {
	private int anchoInternal = 400;
	private int largoInternal = 300;
	private JPanel panelIngresos, panelEgresos, panelCompras;
	private JLabel tituloEliminarDescripcion;
	private JScrollPane verListaIngresos, verListaEgresos, verListaCompras;
	private JList<String> listaDescripcionIngresos, listaDescripcionEgresos, listaDescripcionCompras;
	private JButton eliminarDescripcionIngresos, eliminarDescripcionEgresos, eliminarDescripcionCompras;
	
	public JInternalFEliminarDescripcion() {
		this.setBounds(0, 0, anchoInternal, largoInternal);
		this.setLayout(null);
		this.setTitle("Eliminar Descripcion");
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
		tituloEliminarDescripcion = new JLabel("Seleccione la descripción que desea eliminar:");
		tituloEliminarDescripcion.setBounds(20, 10, 260, 30);
		panelIngresos.add(tituloEliminarDescripcion);
		
		listaDescripcionIngresos = new JList<String>();
		verListaIngresos = new JScrollPane(listaDescripcionIngresos);
		verListaIngresos.setVisible(true);
		verListaIngresos.setBounds(10, 50, 370, 170);
		panelIngresos.add(verListaIngresos);
		
		eliminarDescripcionIngresos = new JButton("ELIMINAR");
		eliminarDescripcionIngresos.setBounds(20, 230, 100, 30);
		eliminarDescripcionIngresos.setFocusable(false);
		panelIngresos.add(eliminarDescripcionIngresos);
	}
	
	public void agregarComponentesPanelEgresos() {
		tituloEliminarDescripcion = new JLabel("Seleccione la descripción que desea eliminar:");
		tituloEliminarDescripcion.setBounds(20, 10, 260, 30);
		panelEgresos.add(tituloEliminarDescripcion);
		
		listaDescripcionEgresos = new JList<String>();
		verListaEgresos = new JScrollPane(listaDescripcionEgresos);
		verListaEgresos.setVisible(true);
		verListaEgresos.setBounds(10, 50, 370, 170);
		panelEgresos.add(verListaEgresos);
		
		eliminarDescripcionEgresos = new JButton("ELIMINAR");
		eliminarDescripcionEgresos.setBounds(20, 230, 100, 30);
		eliminarDescripcionEgresos.setFocusable(false);
		panelEgresos.add(eliminarDescripcionEgresos);
	}
	
	public void agregarComponentesPanelCompras() {
		tituloEliminarDescripcion = new JLabel("Seleccione la descripción que desea eliminar:");
		tituloEliminarDescripcion.setBounds(20, 10, 260, 30);
		panelCompras.add(tituloEliminarDescripcion);
		
		listaDescripcionCompras = new JList<String>();
		verListaCompras = new JScrollPane(listaDescripcionCompras);
		verListaCompras.setVisible(true);
		verListaCompras.setBounds(10, 50, 370, 170);
		panelCompras.add(verListaCompras);
		
		eliminarDescripcionCompras = new JButton("ELIMINAR");
		eliminarDescripcionCompras.setBounds(20, 230, 100, 30);
		eliminarDescripcionCompras.setFocusable(false);
		panelCompras.add(eliminarDescripcionCompras);
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

	public JList<String> getListaDescripcionIngresos() {
		return listaDescripcionIngresos;
	}

	public void setListaDescripcionIngresos(JList<String> listaDescripcionIngresos) {
		this.listaDescripcionIngresos = listaDescripcionIngresos;
	}

	public JList<String> getListaDescripcionEgresos() {
		return listaDescripcionEgresos;
	}

	public void setListaDescripcionEgresos(JList<String> listaDescripcionEgresos) {
		this.listaDescripcionEgresos = listaDescripcionEgresos;
	}

	public JList<String> getListaDescripcionCompras() {
		return listaDescripcionCompras;
	}

	public void setListaDescripcionCompras(JList<String> listaDescripcionCompras) {
		this.listaDescripcionCompras = listaDescripcionCompras;
	}

	public JButton getEliminarDescripcionIngresos() {
		return eliminarDescripcionIngresos;
	}

	public void setEliminarDescripcionIngresos(JButton eliminarDescripcionIngresos) {
		this.eliminarDescripcionIngresos = eliminarDescripcionIngresos;
	}

	public JButton getEliminarDescripcionEgresos() {
		return eliminarDescripcionEgresos;
	}

	public void setEliminarDescripcionEgresos(JButton eliminarDescripcionEgresos) {
		this.eliminarDescripcionEgresos = eliminarDescripcionEgresos;
	}

	public JButton getEliminarDescripcionCompras() {
		return eliminarDescripcionCompras;
	}

	public void setEliminarDescripcionCompras(JButton eliminarDescripcionCompras) {
		this.eliminarDescripcionCompras = eliminarDescripcionCompras;
	}
	
	
	
}
