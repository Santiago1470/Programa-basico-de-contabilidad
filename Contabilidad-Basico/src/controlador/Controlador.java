package controlador;

import java.awt.event.*;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;

import modelo.Guardar;
import modelo.CargarDatos;
import vista.*;

public class Controlador implements ActionListener {
	private VentanaBienvenido ventanaBienvenido;
	private VentanaPrincipal ventanaPrincipal;
	private JFileChooser seleccionarArchivo = new JFileChooser();
	private File archivo;
	private CargarDatos cargarDatos;
	private Guardar guardar;
	private int contador;
	
	public Controlador(VentanaBienvenido ventanaBienvenido, VentanaPrincipal ventanaPrincipal, CargarDatos cargarDatos, Guardar guardar) {
		this.ventanaBienvenido = ventanaBienvenido;
		this.ventanaPrincipal = ventanaPrincipal;
		this.cargarDatos = cargarDatos;
		this.guardar = guardar;
		ventanaBienvenido.getIngresar().addActionListener(this);
		ventanaPrincipal.cargarExcel.addActionListener(this);
		ventanaPrincipal.generarExcel.addActionListener(this);
		ventanaPrincipal.getMenuInicio().addActionListener(this);
		ventanaPrincipal.getMenuIngresos().addActionListener(this);
		ventanaPrincipal.getMenuEgresos().addActionListener(this);
		ventanaPrincipal.getMenuCompras().addActionListener(this);
		ventanaPrincipal.getAgregarDescripcionIngresos().addActionListener(this);
		ventanaPrincipal.getAgregarDescripcionEgresos().addActionListener(this);
		ventanaPrincipal.getAgregarDescripcionCompras().addActionListener(this);
		ventanaPrincipal.getjInternalFAgregarDescripcion().getAgregarDescripcionIngresos().addActionListener(this);
		ventanaPrincipal.getjInternalFAgregarDescripcion().getAgregarDescripcionEgresos().addActionListener(this);
		ventanaPrincipal.getjInternalFAgregarDescripcion().getAgregarDescripcionCompras().addActionListener(this);
		ventanaPrincipal.getEliminarDescripcionIngresos().addActionListener(this);
		ventanaPrincipal.getEliminarDescripcionEgresos().addActionListener(this);
		ventanaPrincipal.getEliminarDescripcionCompras().addActionListener(this);
		ventanaPrincipal.getjInternalFEliminarDescripcion().getEliminarDescripcionIngresos().addActionListener(this);
		ventanaPrincipal.getjInternalFEliminarDescripcion().getEliminarDescripcionEgresos().addActionListener(this);
		ventanaPrincipal.getjInternalFEliminarDescripcion().getEliminarDescripcionCompras().addActionListener(this);
		ventanaPrincipal.getAgregarVentaIngresos().addActionListener(this);
		ventanaPrincipal.getAgregarVentaEgresos().addActionListener(this);
		ventanaPrincipal.getAgregarVentaCompras().addActionListener(this);
		ventanaPrincipal.getjInternalFValores().getAgregarValor().addActionListener(this);
		ventanaPrincipal.getEliminarVentaIngresos().addActionListener(this);
		ventanaPrincipal.getEliminarVentaEgresos().addActionListener(this);
		ventanaPrincipal.getEliminarVentaCompras().addActionListener(this);
		ventanaPrincipal.getjInternalFEliminarValores().getEliminarValor().addActionListener(this);;
	}
	
	public void iniciar() {
		ventanaBienvenido.setVisible(true);
	}
	
	public void filtrarArchivos() {
		seleccionarArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
		seleccionarArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ventanaBienvenido.getIngresar()) {
			ventanaPrincipal.setVisible(true);
			ventanaBienvenido.setVisible(false);
		}
		
		if(e.getSource() == ventanaPrincipal.cargarExcel || e.getSource() == ventanaPrincipal.generarExcel) {
			contador++;
			if(contador == 1) {
				filtrarArchivos();
			}
		}
		
		if(e.getSource() == ventanaPrincipal.cargarExcel) {
			if(seleccionarArchivo.showDialog(null, "Seleccionar Archivo") == JFileChooser.APPROVE_OPTION) {
				archivo = seleccionarArchivo.getSelectedFile();
				
				if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
					JOptionPane.showMessageDialog(null, cargarDatos.cargarExcel(archivo));
					cargarDatos.cargarExcelIngresos(ventanaPrincipal.tablaIngresos);
					cargarDatos.cargarExcelEgresos(ventanaPrincipal.tablaEgresos);
					cargarDatos.cargarExcelCompras(ventanaPrincipal.tablaCompras);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccionar formato válido");
				}
				calcularValoresTotales();
			}
			
		}
		
		if(e.getSource() == ventanaPrincipal.generarExcel) {
			if(seleccionarArchivo.showDialog(null, "Crear Archivo") == JFileChooser.APPROVE_OPTION) {
				archivo = seleccionarArchivo.getSelectedFile();
				
				if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
					JOptionPane.showMessageDialog(null, cargarDatos.generarExcel(archivo, ventanaPrincipal.tablaIngresos, ventanaPrincipal.tablaEgresos, ventanaPrincipal.tablaCompras));
				} else {
					JOptionPane.showMessageDialog(null, "Seleccionar formato válido");
				}
			}
		}
		
		// Activar panel:
		// 1 - De menu inicio
		// 2 - De menu ingresos
		// 3 - De menu egresos
		// 4 - De menu compras
		if(e.getSource() == ventanaPrincipal.getMenuInicio()) {
			activarPanelesMenu(1);
		} else if(e.getSource() == ventanaPrincipal.getMenuIngresos()) {
			activarPanelesMenu(2);
		} else if(e.getSource() == ventanaPrincipal.getMenuEgresos()) {
			activarPanelesMenu(3);
		} else if(e.getSource() == ventanaPrincipal.getMenuCompras()) {
			activarPanelesMenu(4);
		}
		
		// Activar ventana agregar descripción:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getAgregarDescripcionIngresos()) {
			activarVentanaAgregarDescripcion(1);
		} else if (e.getSource() == ventanaPrincipal.getAgregarDescripcionEgresos()) {
			activarVentanaAgregarDescripcion(2);
		} else if (e.getSource() == ventanaPrincipal.getAgregarDescripcionCompras()) {
			activarVentanaAgregarDescripcion(3);
		}
		
		// Agregar Descripción:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getjInternalFAgregarDescripcion().getAgregarDescripcionIngresos()) {
			agregarDescripciones(1);
		} else if(e.getSource() == ventanaPrincipal.getjInternalFAgregarDescripcion().getAgregarDescripcionEgresos()) {
			agregarDescripciones(2);
		} else if(e.getSource() == ventanaPrincipal.getjInternalFAgregarDescripcion().getAgregarDescripcionCompras()) {
			agregarDescripciones(3);
		}
		
		// Activar ventana eliminar descripcion:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getEliminarDescripcionIngresos()) {
			activarVentanaEliminarDescripcion(1);
		} else if(e.getSource() == ventanaPrincipal.getEliminarDescripcionEgresos()) {
			activarVentanaEliminarDescripcion(2);
		} else if(e.getSource() == ventanaPrincipal.getEliminarDescripcionCompras()) {
			activarVentanaEliminarDescripcion(3);
		}
		
		// Eliminar Descripción:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getjInternalFEliminarDescripcion().getEliminarDescripcionIngresos()) {
			eliminarDescripciones(1);
		}	else if(e.getSource() == ventanaPrincipal.getjInternalFEliminarDescripcion().getEliminarDescripcionEgresos()) {
			eliminarDescripciones(2);
		} else if(e.getSource() == ventanaPrincipal.getjInternalFEliminarDescripcion().getEliminarDescripcionCompras()) {
			eliminarDescripciones(3);
		}
		
		// Activar ventana agregar valores:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getAgregarVentaIngresos()) {
			activarVentanaAgregarVenta(1);
		} else if(e.getSource() == ventanaPrincipal.getAgregarVentaEgresos()) {
			activarVentanaAgregarVenta(2);
		} else if(e.getSource() == ventanaPrincipal.getAgregarVentaCompras()) {
			activarVentanaAgregarVenta(3);
		}
		
		// Agregar valor:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getjInternalFValores().getAgregarValor()) {
			agregarValores();
		}
		
		// Activar ventana eliminar valores:
		// 1 - Ingresos
		// 2 - Egresos
		// 3 - Compras
		if(e.getSource() == ventanaPrincipal.getEliminarVentaIngresos()) {
			activarVentanaEliminarValores(1);
		} else if(e.getSource() == ventanaPrincipal.getEliminarVentaEgresos()) {
			activarVentanaEliminarValores(2);
		} else if(e.getSource() == ventanaPrincipal.getEliminarVentaCompras()) {
			activarVentanaEliminarValores(3);
		}
		
		// Eliminar valor:
		if(e.getSource() == ventanaPrincipal.getjInternalFEliminarValores().getEliminarValor()) {
			eliminarValores();
		}
	}
	
	private void activarPanelesMenu(int opcion) {
		switch (opcion) {
		case 1:
			// Sección Principal
			
			ventanaPrincipal.getPanelPrincipal().setVisible(true);
			ventanaPrincipal.getPanelIngresos().setVisible(false);
			ventanaPrincipal.getPanelEgresos().setVisible(false);
			ventanaPrincipal.getPanelCompras().setVisible(false);
			
			break;
			
		case 2:
			// Sección Ingresos
			
			ventanaPrincipal.getPanelPrincipal().setVisible(false);
			ventanaPrincipal.getPanelIngresos().setVisible(true);
			ventanaPrincipal.getPanelEgresos().setVisible(false);
			ventanaPrincipal.getPanelCompras().setVisible(false);
			
			// ---------------------------------------------------------------
			// Paneles de los JInternalFrame para agregrar o eliminar valores
						
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelIngresos(true);
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelEgresos(false);
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelCompras(false);
						
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelIngresos(true);
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelEgresos(false);
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelCompras(false);
			
			break;
			
		case 3:
			// Sección Egresos
			
			ventanaPrincipal.getPanelPrincipal().setVisible(false);
			ventanaPrincipal.getPanelIngresos().setVisible(false);
			ventanaPrincipal.getPanelEgresos().setVisible(true);
			ventanaPrincipal.getPanelCompras().setVisible(false);
			
			// ---------------------------------------------------------------
			// Paneles de los JInternalFrame para agregrar o eliminar valores
						
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelIngresos(false);
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelEgresos(true);
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelCompras(false);
						
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelIngresos(false);
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelEgresos(true);
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelCompras(false);
			
			break;
			
		case 4:
			// Sección Compras
			
			ventanaPrincipal.getPanelPrincipal().setVisible(false);
			ventanaPrincipal.getPanelIngresos().setVisible(false);
			ventanaPrincipal.getPanelEgresos().setVisible(false);
			ventanaPrincipal.getPanelCompras().setVisible(true);
			
			// ---------------------------------------------------------------
			// Paneles de los JInternalFrame para agregrar o eliminar valores
			
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelIngresos(false);
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelEgresos(false);
			ventanaPrincipal.getjInternalFValores().agregarComponentesPanelCompras(true);
			
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelIngresos(false);
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelEgresos(false);
			ventanaPrincipal.getjInternalFEliminarValores().agregarComponentesPanelCompras(true);
			
			break;
		}
		calcularValoresTotales();
	}
	
	private void activarVentanaAgregarDescripcion(int opcion) {
		ventanaPrincipal.getjInternalFAgregarDescripcion().setVisible(true);
		switch (opcion) {
			case 1:
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelIngresos().setVisible(true);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelCompras().setVisible(false);
				
				break;
	
			case 2:
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelEgresos().setVisible(true);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelCompras().setVisible(false);
				
				break;
				
			case 3:
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getPanelCompras().setVisible(true);
				
				break;
		}
	}
	
	private void agregarDescripciones(int opcion) {
		String descripcion = "";
		switch (opcion) {
			case 1:
				descripcion = ventanaPrincipal.getjInternalFAgregarDescripcion().getNombreDescripcionIngresos().getText();
				guardar.guardarDatos(descripcion, ventanaPrincipal.tablaIngresos);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getNombreDescripcionIngresos().setText("");
				
				break;
				
			case 2:
				descripcion = ventanaPrincipal.getjInternalFAgregarDescripcion().getNombreDescripcionEgresos().getText();
				guardar.guardarDatos(descripcion, ventanaPrincipal.tablaEgresos);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getNombreDescripcionEgresos().setText("");
				
				break;
				
			case 3:
				descripcion = ventanaPrincipal.getjInternalFAgregarDescripcion().getNombreDescripcionCompras().getText();
				guardar.guardarDatos(descripcion, ventanaPrincipal.tablaCompras);
				ventanaPrincipal.getjInternalFAgregarDescripcion().getNombreDescripcionCompras().setText("");
				
				break;
		}
		ventanaPrincipal.getjInternalFAgregarDescripcion().setVisible(false);
		calcularValoresTotales();
	}
	
	private void activarVentanaEliminarDescripcion(int opcion) {
		ventanaPrincipal.getjInternalFEliminarDescripcion().setVisible(true);
		switch (opcion) {
			case 1:
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelIngresos().setVisible(true);
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelCompras().setVisible(false);
				guardar.guardarDatosLista(ventanaPrincipal.tablaIngresos, ventanaPrincipal.getjInternalFEliminarDescripcion().getListaDescripcionIngresos());
				
				break;
	
			case 2:
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelEgresos().setVisible(true);
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelCompras().setVisible(false);
				guardar.guardarDatosLista(ventanaPrincipal.tablaEgresos, ventanaPrincipal.getjInternalFEliminarDescripcion().getListaDescripcionEgresos());
				
				break;
				
			case 3:
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarDescripcion().getPanelCompras().setVisible(true);
				guardar.guardarDatosLista(ventanaPrincipal.tablaCompras, ventanaPrincipal.getjInternalFEliminarDescripcion().getListaDescripcionCompras());
				
				break;
		}
	}
	
	private void eliminarDescripciones(int opcion) {
		switch (opcion) {
		case 1:
			guardar.eliminarDescripcion(ventanaPrincipal.tablaIngresos, ventanaPrincipal.getjInternalFEliminarDescripcion().getListaDescripcionIngresos());
			
			break;
		
		case 2:
			guardar.eliminarDescripcion(ventanaPrincipal.tablaEgresos, ventanaPrincipal.getjInternalFEliminarDescripcion().getListaDescripcionEgresos());
					
			break;
					
		case 3:
			guardar.eliminarDescripcion(ventanaPrincipal.tablaCompras, ventanaPrincipal.getjInternalFEliminarDescripcion().getListaDescripcionCompras());
			
			break;
		
		}
		ventanaPrincipal.getjInternalFEliminarDescripcion().setVisible(false);
		calcularValoresTotales();
	}
	
	private void activarVentanaAgregarVenta(int opcion) {
		ventanaPrincipal.getjInternalFValores().setVisible(true);
		switch (opcion) {
			case 1:
				ventanaPrincipal.getjInternalFValores().getPanelIngresos().setVisible(true);
				ventanaPrincipal.getjInternalFValores().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFValores().getPanelCompras().setVisible(false);
				
				break;
	
			case 2:
				ventanaPrincipal.getjInternalFValores().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFValores().getPanelEgresos().setVisible(true);
				ventanaPrincipal.getjInternalFValores().getPanelCompras().setVisible(false);
				
				break;
				
			case 3:
				ventanaPrincipal.getjInternalFValores().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFValores().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFValores().getPanelCompras().setVisible(true);
				
				break;
		}
	}
	
	private void agregarValores() {
		if(ventanaPrincipal.getjInternalFValores().ingresos) {
			guardar.agregarValoresTabla(ventanaPrincipal.tablaIngresos, ventanaPrincipal.getjInternalFValores().getNuevoValor().getText());
			
		} else if(ventanaPrincipal.getjInternalFValores().egresos) {
			guardar.agregarValoresTabla(ventanaPrincipal.tablaEgresos, ventanaPrincipal.getjInternalFValores().getNuevoValor().getText());
			
		} else if(ventanaPrincipal.getjInternalFValores().compras) {
			guardar.agregarValoresTabla(ventanaPrincipal.tablaCompras, ventanaPrincipal.getjInternalFValores().getNuevoValor().getText());
			
		}
		ventanaPrincipal.getjInternalFValores().getNuevoValor().setText("");
		ventanaPrincipal.getjInternalFValores().setVisible(false);
		calcularValoresTotales();
	}
	
	private void activarVentanaEliminarValores(int opcion) {
		ventanaPrincipal.getjInternalFEliminarValores().setVisible(true);
		switch (opcion) {
			case 1:
				ventanaPrincipal.getjInternalFEliminarValores().getPanelIngresos().setVisible(true);
				ventanaPrincipal.getjInternalFEliminarValores().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarValores().getPanelCompras().setVisible(false);
				
				
				break;
	
			case 2:
				ventanaPrincipal.getjInternalFEliminarValores().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarValores().getPanelEgresos().setVisible(true);
				ventanaPrincipal.getjInternalFEliminarValores().getPanelCompras().setVisible(false);
				
				
				break;
				
			case 3:
				ventanaPrincipal.getjInternalFEliminarValores().getPanelIngresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarValores().getPanelEgresos().setVisible(false);
				ventanaPrincipal.getjInternalFEliminarValores().getPanelCompras().setVisible(true);
				
				
				break;
		}
	}
	
	private void eliminarValores() {
		if(ventanaPrincipal.getjInternalFEliminarValores().ingresos) {
			guardar.eliminarValoresTabla(ventanaPrincipal.tablaIngresos, ventanaPrincipal.getjInternalFEliminarValores().getQuitarValor().getText());
			
		} else if(ventanaPrincipal.getjInternalFEliminarValores().egresos) {
			guardar.eliminarValoresTabla(ventanaPrincipal.tablaEgresos, ventanaPrincipal.getjInternalFEliminarValores().getQuitarValor().getText());
			
		} else if(ventanaPrincipal.getjInternalFEliminarValores().compras) {
			guardar.eliminarValoresTabla(ventanaPrincipal.tablaCompras, ventanaPrincipal.getjInternalFEliminarValores().getQuitarValor().getText());
		}
		ventanaPrincipal.getjInternalFEliminarValores().getQuitarValor().setText("");
		ventanaPrincipal.getjInternalFEliminarValores().setVisible(false);
		calcularValoresTotales();
	}
	
	private void calcularValoresTotales() {
		if(ventanaPrincipal.getjInternalFValores().ingresos) {
			ventanaPrincipal.getValorTotalMenuIngresos().setText(String.valueOf(guardar.calcularTotales(ventanaPrincipal.tablaIngresos)));
			cargarDatos.setFilaFinal1(ventanaPrincipal.getValorTotalMenuIngresos().getText());
		} else if(ventanaPrincipal.getjInternalFValores().egresos) {
			ventanaPrincipal.getValorTotalMenuEgresos().setText(String.valueOf(guardar.calcularTotales(ventanaPrincipal.tablaEgresos)));
			cargarDatos.setFilaFinal2(ventanaPrincipal.getValorTotalMenuEgresos().getText());
		} else if(ventanaPrincipal.getjInternalFValores().compras) {
			ventanaPrincipal.getValorTotalMenuCompras().setText(String.valueOf(guardar.calcularTotales(ventanaPrincipal.tablaCompras)));
			cargarDatos.setFilaFinal3(ventanaPrincipal.getValorTotalMenuCompras().getText());
		}
		
		ventanaPrincipal.getTotalIngresos().setText(String.valueOf(guardar.calcularTotales(ventanaPrincipal.tablaIngresos)));
		ventanaPrincipal.getTotalEgresos().setText(String.valueOf(guardar.calcularTotales(ventanaPrincipal.tablaEgresos)));
		ventanaPrincipal.getTotalCompras().setText(String.valueOf(guardar.calcularTotales(ventanaPrincipal.tablaCompras)));
	}
}
