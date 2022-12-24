package vista;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class JInternalFValores extends JInternalFrame {
	private int anchoInternal = 400;
	private int largoInternal = 300;
	private JPanel panelIngresos, panelEgresos, panelCompras;
	private JLabel tituloAgregarValor, tituloNombre;
	private JTextField nuevoValor/*, nombreDescripcionEgresos, nombreDescripcionCompras*/;
	private JButton agregarValor/*, agregarDescripcionEgresos, agregarDescripcionCompras*/;
	public boolean ingresos = false;
	public boolean egresos = false;
	public boolean compras = false;
	
	public JInternalFValores() {
		this.setBounds(0, 0, anchoInternal, largoInternal);
		this.setLayout(null);
		this.setTitle("Agregar Valor");
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
		
		crearComponentes();
	}
	
	public void crearComponentes() {
		tituloAgregarValor = new JLabel("Cantidad del nuevo valor:");
		tituloAgregarValor.setBounds(20, 10, 175, 30);
		
		tituloNombre = new JLabel("Valor númerico:");
		tituloNombre.setBounds(20, 50, 90, 30);
		
		nuevoValor = new JTextField();
		nuevoValor.setBounds(120, 50, 170, 30);
		
		agregarValor = new JButton("AGREGAR");
		agregarValor.setBounds(20, 100, 100, 30);
		agregarValor.setFocusable(false);
	}
	
	public void agregarComponentesPanelIngresos(boolean asociar) {
		if(asociar) {
			panelIngresos.add(tituloAgregarValor);
			
			panelIngresos.add(tituloNombre);
			
			panelIngresos.add(nuevoValor);
			
			panelIngresos.add(agregarValor);
			
			ingresos = true;
		} else {
			panelIngresos.remove(tituloAgregarValor);
			
			panelIngresos.remove(tituloNombre);
			
			panelIngresos.remove(nuevoValor);
			
			panelIngresos.remove(agregarValor);
			
			ingresos = false;
		}
		
	}
	
	public void agregarComponentesPanelEgresos(boolean asociar) {
		if(asociar) {
			panelEgresos.add(tituloAgregarValor);
			
			panelEgresos.add(tituloNombre);
			
			panelEgresos.add(nuevoValor);
			
			panelEgresos.add(agregarValor);
			
			egresos = true;
		} else {
			panelEgresos.remove(tituloAgregarValor);
			
			panelEgresos.remove(tituloNombre);
			
			panelEgresos.remove(nuevoValor);
			
			panelEgresos.remove(agregarValor);
			
			egresos = false;
		}
		
	}
	
	public void agregarComponentesPanelCompras(boolean asociar) {
		if(asociar) {
			panelCompras.add(tituloAgregarValor);
			
			panelCompras.add(tituloNombre);
			
			panelCompras.add(nuevoValor);
			
			panelCompras.add(agregarValor);
			
			compras = true;
		} else {
			panelCompras.remove(tituloAgregarValor);
			
			panelCompras.remove(tituloNombre);
			
			panelCompras.remove(nuevoValor);
			
			panelCompras.remove(agregarValor);
			
			compras = true;
		}
		
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

	public JTextField getNuevoValor() {
		return nuevoValor;
	}

	public void setNuevoValor(JTextField nuevoValor) {
		this.nuevoValor = nuevoValor;
	}

	public JButton getAgregarValor() {
		return agregarValor;
	}

	public void setAgregarValor(JButton agregarValor) {
		this.agregarValor = agregarValor;
	}
	
	/*public void agregarComponentesPanelEgresos() {
		tituloAgregarValor = new JLabel("Descripcion de nuevo valor:");
		tituloAgregarValor.setBounds(20, 10, 175, 30);
		panelEgresos.add(tituloAgregarValor);
		
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
		tituloAgregarValor = new JLabel("Descripcion de nuevo valor:");
		tituloAgregarValor.setBounds(20, 10, 175, 30);
		panelCompras.add(tituloAgregarValor);
		
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
	}*/
	
	
}
