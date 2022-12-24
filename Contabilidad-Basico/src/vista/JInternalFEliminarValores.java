package vista;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class JInternalFEliminarValores extends JInternalFrame {
	private int anchoInternal = 400;
	private int largoInternal = 300;
	private JPanel panelIngresos, panelEgresos, panelCompras;
	private JLabel tituloEliminarValor, tituloNombre;
	private JTextField quitarValor;
	private JButton eliminarValor;
	public boolean ingresos = false;
	public boolean egresos = false;
	public boolean compras = false;
	
	public JInternalFEliminarValores() {
		this.setBounds(0, 0, anchoInternal, largoInternal);
		this.setLayout(null);
		this.setTitle("Eliminar Valor");
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
		tituloEliminarValor = new JLabel("Cantidad del valor a eliminar:");
		tituloEliminarValor.setBounds(20, 10, 175, 30);
		
		tituloNombre = new JLabel("Valor númerico:");
		tituloNombre.setBounds(20, 50, 90, 30);
		
		quitarValor = new JTextField();
		quitarValor.setBounds(120, 50, 170, 30);
		
		eliminarValor = new JButton("ELIMINAR");
		eliminarValor.setBounds(20, 100, 100, 30);
		eliminarValor.setFocusable(false);
	}
	
	public void agregarComponentesPanelIngresos(boolean asociar) {
		if(asociar) {
			panelIngresos.add(tituloEliminarValor);
			
			panelIngresos.add(tituloNombre);
			
			panelIngresos.add(quitarValor);
			
			panelIngresos.add(eliminarValor);
			
			ingresos = true;
		} else {
			panelIngresos.remove(tituloEliminarValor);
			
			panelIngresos.remove(tituloNombre);
			
			panelIngresos.remove(quitarValor);
			
			panelIngresos.remove(eliminarValor);
			
			ingresos = false;
		}
		
	}
	
	public void agregarComponentesPanelEgresos(boolean asociar) {
		if(asociar) {
			panelEgresos.add(tituloEliminarValor);
			
			panelEgresos.add(tituloNombre);
			
			panelEgresos.add(quitarValor);
			
			panelEgresos.add(eliminarValor);
			
			egresos = true;
		} else {
			panelEgresos.remove(tituloEliminarValor);
			
			panelEgresos.remove(tituloNombre);
			
			panelEgresos.remove(quitarValor);
			
			panelEgresos.remove(eliminarValor);
			
			egresos = false;
		}
		
	}
	
	public void agregarComponentesPanelCompras(boolean asociar) {
		if(asociar) {
			panelCompras.add(tituloEliminarValor);
			
			panelCompras.add(tituloNombre);
			
			panelCompras.add(quitarValor);
			
			panelCompras.add(eliminarValor);
			
			compras = true;
		} else {
			panelCompras.remove(tituloEliminarValor);
			
			panelCompras.remove(tituloNombre);
			
			panelCompras.remove(quitarValor);
			
			panelCompras.remove(eliminarValor);
			
			compras = false;
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

	public JTextField getQuitarValor() {
		return quitarValor;
	}

	public void setQuitarValor(JTextField quitarValor) {
		this.quitarValor = quitarValor;
	}

	public JButton getEliminarValor() {
		return eliminarValor;
	}

	public void setEliminarValor(JButton eliminarValor) {
		this.eliminarValor = eliminarValor;
	}
	
	
}
