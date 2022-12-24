package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {
	private int anchoVentana = 700;
	private int largoVentana = 550;
	private JDesktopPane escritorio;
	private JPanel panelPrincipal, panelIngresos, panelEgresos, panelCompras;
	private JMenuBar barraMenu;
	private JMenu opcionMovimientos;
	private JMenuItem menuInicio, menuIngresos, menuEgresos, menuCompras;
	private JLabel labelIngresos, labelEgresos, labelCompras;
	private JLabel tituloFecha, fechaActual, tituloHora, horaActual;
	private JLabel tituloFechaIngresos, fechaActualIngresos, tituloHoraIngresos, horaActualIngresos;
	private JLabel tituloFechaEgresos, fechaActualEgresos, tituloHoraEgresos, horaActualEgresos;
	private JLabel tituloFechaCompras, fechaActualCompras, tituloHoraCompras, horaActualCompras;
	private JLabel totalIngresos, totalEgresos, totalCompras;
	private JLabel tituloIngreso, totalMenuIngresos, valorTotalMenuIngresos;
	private JLabel tituloEgresos, totalMenuEgresos, valorTotalMenuEgresos;
	private JLabel tituloCompras, totalMenuCompras, valorTotalMenuCompras;
	private JScrollPane verTablaIngresos, verTablaEgresos, verTablaCompras;
	public JTable tablaIngresos, tablaEgresos, tablaCompras;
	public JButton generarExcel, cargarExcel;
	private JButton agregarDescripcionIngresos, eliminarDescripcionIngresos, agregarVentaIngresos, eliminarVentaIngresos;
	private JButton agregarDescripcionEgresos, eliminarDescripcionEgresos, agregarVentaEgresos, eliminarVentaEgresos;
	private JButton agregarDescripcionCompras, eliminarDescripcionCompras, agregarVentaCompras, eliminarVentaCompras;
	private JInternalFAgregarDescripcion jInternalFAgregarDescripcion;
	private JInternalFEliminarDescripcion jInternalFEliminarDescripcion;
	private JInternalFValores jInternalFValores;
	private JInternalFEliminarValores jInternalFEliminarValores;
	private LocalDateTime tiempo = LocalDateTime.now();
	private int year = tiempo.getYear();
	private int mes = tiempo.getMonthValue();
	private int dia = tiempo.getDayOfMonth();
	
	public VentanaPrincipal() {
		this.setBounds(0, 0, anchoVentana, largoVentana);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Contabilidad");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		crearContenedores();
	}
	
	private void crearContenedores() {
		barraMenu = new JMenuBar();
		barraMenu.setBounds(0, 0, anchoVentana, 20);
		barraMenu.setVisible(true);
		this.add(barraMenu);
		
		escritorio = new JDesktopPane();
		escritorio.setBounds(0, 0, anchoVentana, largoVentana);
		escritorio.setLayout(null);
		escritorio.setVisible(true);
		this.add(escritorio);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, anchoVentana, largoVentana);
		panelPrincipal.setLayout(null);
		panelPrincipal.setVisible(true);
		escritorio.add(panelPrincipal);
		
		panelIngresos = new JPanel();
		panelIngresos.setBounds(0, 0, anchoVentana, largoVentana);
		panelIngresos.setLayout(null);
		panelIngresos.setVisible(false);
		escritorio.add(panelIngresos);
		
		panelEgresos = new JPanel();
		panelEgresos.setBounds(0, 0, anchoVentana, largoVentana);
		panelEgresos.setLayout(null);
		panelEgresos.setVisible(false);
		escritorio.add(panelEgresos);
		
		panelCompras = new JPanel();
		panelCompras.setBounds(0, 0, anchoVentana, largoVentana);
		panelCompras.setLayout(null);
		panelCompras.setVisible(false);
		escritorio.add(panelCompras);
		
		jInternalFAgregarDescripcion = new JInternalFAgregarDescripcion();
		jInternalFAgregarDescripcion.setLocation(0, 20);
		jInternalFAgregarDescripcion.setVisible(false);
		escritorio.add(jInternalFAgregarDescripcion);
		
		jInternalFEliminarDescripcion = new JInternalFEliminarDescripcion();
		jInternalFEliminarDescripcion.setLocation(0, 20);
		jInternalFEliminarDescripcion.setVisible(false);
		escritorio.add(jInternalFEliminarDescripcion);
		
		jInternalFValores = new JInternalFValores();
		jInternalFValores.setLocation(0, 20);
		jInternalFValores.setVisible(false);
		escritorio.add(jInternalFValores);
		
		jInternalFEliminarValores = new JInternalFEliminarValores();
		jInternalFEliminarValores.setLocation(0, 20);
		jInternalFEliminarValores.setVisible(false);
		escritorio.add(jInternalFEliminarValores);
		
		agregarComponentesBarraMenu();
		agregarComponentesPanelPrincipal();
		agregarTablaIngresos();
		agregarTablaEgresos();
		agregarTablaCompras();
		agregarComponentesPanelIngresos();
		agregarComponentesPanelEgresos();
		agregarComponentesPanelCompras();
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		        while (true) {
		            try {
		                Thread.sleep(500);
		                horaActual.setText(formateador.format(LocalDateTime.now()));
		                horaActualIngresos.setText(formateador.format(LocalDateTime.now()));
		                horaActualEgresos.setText(formateador.format(LocalDateTime.now()));
		                horaActualCompras.setText(formateador.format(LocalDateTime.now()));
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		};
		Thread hilo = new Thread(runnable);
		hilo.start();
	}
	
	private void agregarComponentesBarraMenu() {
		opcionMovimientos = new  JMenu("Movimientos");
		opcionMovimientos.setVisible(true);
		barraMenu.add(opcionMovimientos);
		
		menuInicio = new JMenuItem("Inicio");
		menuInicio.setVisible(true);
		//menuInicio.setBounds(0, 0, 60, 20);
		opcionMovimientos.add(menuInicio);
		
		menuIngresos = new JMenuItem("Ingresos");
		menuIngresos.setVisible(true);
		//menuIngresos.setBounds(0, 0, 60, 20);
		opcionMovimientos.add(menuIngresos);
		
		menuEgresos = new JMenuItem("Egresos");
		menuEgresos.setVisible(true);
		//menuEgresos.setBounds(0, 0, 60, 20);
		opcionMovimientos.add(menuEgresos);
		
		menuCompras = new JMenuItem("Compras");
		menuCompras.setVisible(true);
		//menuCompras.setBounds(0, 0, 60, 20);
		opcionMovimientos.add(menuCompras);
	}
	
	private void agregarComponentesPanelPrincipal() {
		labelIngresos = new JLabel("Total Ingresos:");
		labelIngresos.setBounds(30, 30, 90, 30);
		panelPrincipal.add(labelIngresos);
		
		totalIngresos = new JLabel();
		totalIngresos.setBounds(120, 30, 130, 30);
		panelPrincipal.add(totalIngresos);
		
		
		labelEgresos = new JLabel("Total Egresos:");
		labelEgresos.setBounds(30, 60, 90, 30);
		panelPrincipal.add(labelEgresos);
		
		totalEgresos = new JLabel();
		totalEgresos.setBounds(120, 60, 130, 30);
		panelPrincipal.add(totalEgresos);
		
		
		labelCompras = new JLabel("Total Compras:");
		labelCompras.setBounds(30, 90, 90, 30);
		panelPrincipal.add(labelCompras);
		
		totalCompras = new JLabel();
		totalCompras.setBounds(120, 90, 130, 30);
		panelPrincipal.add(totalCompras);
		
		
		cargarExcel = new JButton("CARGAR EXCEL");
		cargarExcel.setBounds(30, 130, 130, 30);
		cargarExcel.setFocusable(false);
		panelPrincipal.add(cargarExcel);
		
		generarExcel = new JButton("GENERAR EXCEL");
		generarExcel.setBounds(180, 130, 130, 30);
		generarExcel.setFocusable(false);
		panelPrincipal.add(generarExcel);
		
	    
	    tituloFecha = new JLabel("Fecha:");
	    tituloFecha.setBounds(530, 10, 40, 30);
	    panelPrincipal.add(tituloFecha);
	    
	    String fechaString = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(year);
	    
	    fechaActual = new JLabel(fechaString);
	    fechaActual.setBounds(580, 10, 70, 30);
	    panelPrincipal.add(fechaActual);
	    
	    tituloHora = new JLabel("Hora:");
	    tituloHora.setBounds(530, 25, 40, 30);
	    panelPrincipal.add(tituloHora);
	    
	    horaActual = new JLabel();
	    horaActual.setBounds(580, 25, 70, 30);
	    panelPrincipal.add(horaActual);
	}
	
	private void agregarTablaIngresos() {
		/*DefaultTableModel modeloTablaIngresos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0 || column == 1) {
					return false;
				} else {
					return true;
				}
				//return super.isCellEditable(row, column);
			}
		};*/
		//modeloTablaIngresos.addColumn("DESCRIPCIÓN");
		//modeloTablaIngresos.addColumn("VALORES");
		/*String[] datos = {"Hola", "Chao"};
		modeloTablaIngresos.addRow(datos);*/
		tablaIngresos = new JTable();
		//tablaIngresos.setBounds(10, 30, 500, 350);
		//tablaIngresos.setModel(modeloTablaIngresos);
		//tablaIngresos.setVisible(true);
		tablaIngresos.getTableHeader().setReorderingAllowed(false);
		verTablaIngresos = new JScrollPane(tablaIngresos);
		verTablaIngresos.setVisible(true);
		verTablaIngresos.setBounds(10,50,520,400);
		//panelIngresos.add(verTablaIngresos);
		//panelIngresos.add(tablaIngresos);
		panelIngresos.add(verTablaIngresos, BorderLayout.CENTER);
	}
	
	private void agregarTablaEgresos() {
		/*DefaultTableModel modeloTablaEgresos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0 || column == 1) {
					return false;
				} else {
					return true;
				}
				//return super.isCellEditable(row, column);
			}
		};*/
		/*modeloTablaEgresos.addColumn("DESCRIPCIÓN");
		modeloTablaEgresos.addColumn("VALORES");*/
		/*String[] datos = {"Hola", "Chao"};
		modeloTablaEgresos.addRow(datos);*/
		tablaEgresos = new JTable();
		//tablaIngresos.setBounds(10, 30, 500, 350);
		//tablaEgresos.setModel(modeloTablaEgresos);
		//tablaEgresos.setVisible(true);
		tablaEgresos.getTableHeader().setReorderingAllowed(false);
		verTablaEgresos = new JScrollPane(tablaEgresos);
		verTablaEgresos.setVisible(true);
		verTablaEgresos.setBounds(10,50,520,400);
		panelEgresos.add(verTablaEgresos, BorderLayout.CENTER);
	}
	
	private void agregarTablaCompras() {
		/*DefaultTableModel modeloTablaCompras = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0 || column == 1) {
					return false;
				} else {
					return true;
				}
				//return super.isCellEditable(row, column);
			}
		};
		modeloTablaCompras.addColumn("DESCRIPCIÓN");
		modeloTablaCompras.addColumn("VALORES");*/
		/*String[] datos = {"Hola", "Chao"};
		modeloTablaCompras.addRow(datos);*/
		tablaCompras = new JTable();
		//tablaCompras.setBounds(10, 30, 500, 350);
		//tablaCompras.setModel(modeloTablaCompras);
		//tablaCompras.setVisible(true);
		tablaCompras.getTableHeader().setReorderingAllowed(false);
		verTablaCompras = new JScrollPane(tablaCompras);
		verTablaCompras.setVisible(true);
		verTablaCompras.setBounds(10,50,520,400);
		panelCompras.add(verTablaCompras, BorderLayout.CENTER);
	}
	
	private void agregarComponentesPanelIngresos() {
		// NOTA:
		// pasar escribir el metodo para asociar los componentes
		// de acuerdo al panel y asociar al correspondientes en los JInternalFrame
		
		tituloIngreso = new JLabel("INGRESOS:");
		tituloIngreso.setBounds(30, 20, 100, 30);
		panelIngresos.add(tituloIngreso);
		
		totalMenuIngresos = new JLabel("TOTAL VENTA:");
		totalMenuIngresos.setBounds(30, 450, 100, 30);
		panelIngresos.add(totalMenuIngresos);
		
		valorTotalMenuIngresos = new JLabel();
		valorTotalMenuIngresos.setBounds(150, 450, 130, 30);
		panelIngresos.add(valorTotalMenuIngresos);
		
		agregarDescripcionIngresos = new JButton("Agregar Descripción");
		agregarDescripcionIngresos.setBounds(30, 480, 155, 25);
		agregarDescripcionIngresos.setFocusable(false);
		panelIngresos.add(agregarDescripcionIngresos);
		
		eliminarDescripcionIngresos = new JButton("Eliminar Descripción");
		eliminarDescripcionIngresos.setBounds(185, 480, 155, 25);
		eliminarDescripcionIngresos.setFocusable(false);
		panelIngresos.add(eliminarDescripcionIngresos);
		
		tituloFechaIngresos = new JLabel("Fecha:");
		tituloFechaIngresos.setBounds(530, 10, 40, 30);
		panelIngresos.add(tituloFechaIngresos);
	    
	    String fechaString = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(year);
	    
	    fechaActualIngresos = new JLabel(fechaString);
	    fechaActualIngresos.setBounds(580, 10, 70, 30);
	    panelIngresos.add(fechaActualIngresos);
	    
	    tituloHoraIngresos = new JLabel("Hora:");
	    tituloHoraIngresos.setBounds(530, 25, 40, 30);
	    panelIngresos.add(tituloHoraIngresos);
	    
	    horaActualIngresos = new JLabel();
	    horaActualIngresos.setBounds(580, 25, 70, 30);
	    panelIngresos.add(horaActualIngresos);
		
		agregarVentaIngresos = new JButton("Agregar Venta");
		agregarVentaIngresos.setBounds(530, 50, 120, 25);
		agregarVentaIngresos.setFocusable(false);
		panelIngresos.add(agregarVentaIngresos);
		
		eliminarVentaIngresos = new JButton("Eliminar Venta");
		eliminarVentaIngresos.setBounds(530, 80, 120, 25);
		eliminarVentaIngresos.setFocusable(false);
		panelIngresos.add(eliminarVentaIngresos);
	}
	
	private void agregarComponentesPanelEgresos() {
		tituloEgresos = new JLabel("EGRESOS:");
		tituloEgresos.setBounds(30, 20, 100, 30);
		panelEgresos.add(tituloEgresos);
		
		totalMenuEgresos = new JLabel("TOTAL EGRESOS:");
		totalMenuEgresos.setBounds(30, 450, 100, 30);
		panelEgresos.add(totalMenuEgresos);
		
		valorTotalMenuEgresos = new JLabel();
		valorTotalMenuEgresos.setBounds(150, 450, 130, 30);
		panelEgresos.add(valorTotalMenuEgresos);
		
		agregarDescripcionEgresos = new JButton("Agregar Descripción");
		agregarDescripcionEgresos.setBounds(30, 480, 155, 25);
		agregarDescripcionEgresos.setFocusable(false);
		panelEgresos.add(agregarDescripcionEgresos);
		
		eliminarDescripcionEgresos = new JButton("Eliminar Descripción");
		eliminarDescripcionEgresos.setBounds(185, 480, 155, 25);
		eliminarDescripcionEgresos.setFocusable(false);
		panelEgresos.add(eliminarDescripcionEgresos);
		
		
		tituloFechaEgresos = new JLabel("Fecha:");
		tituloFechaEgresos.setBounds(530, 10, 40, 30);
		panelEgresos.add(tituloFechaEgresos);
	    
	    String fechaString = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(year);
	    
	    fechaActualEgresos = new JLabel(fechaString);
	    fechaActualEgresos.setBounds(580, 10, 70, 30);
	    panelEgresos.add(fechaActualEgresos);
	    
	    tituloHoraEgresos = new JLabel("Hora:");
	    tituloHoraEgresos.setBounds(530, 25, 40, 30);
	    panelEgresos.add(tituloHoraEgresos);
	    
	    horaActualEgresos = new JLabel();
	    horaActualEgresos.setBounds(580, 25, 70, 30);
	    panelEgresos.add(horaActualEgresos);
		
		
		agregarVentaEgresos = new JButton("Agregar Egreso");
		agregarVentaEgresos.setBounds(530, 50, 125, 25);
		agregarVentaEgresos.setFocusable(false);
		panelEgresos.add(agregarVentaEgresos);
		
		eliminarVentaEgresos = new JButton("Eliminar Egreso");
		eliminarVentaEgresos.setBounds(530, 80, 125, 25);
		eliminarVentaEgresos.setFocusable(false);
		panelEgresos.add(eliminarVentaEgresos);
	}
	
	private void agregarComponentesPanelCompras() {
		tituloCompras = new JLabel("COMPRAS:");
		tituloCompras.setBounds(30, 20, 100, 30);
		panelCompras.add(tituloCompras);
		
		totalMenuCompras = new JLabel("TOTAL COMPRAS:");
		totalMenuCompras.setBounds(30, 450, 110, 30);
		panelCompras.add(totalMenuCompras);
		
		valorTotalMenuCompras = new JLabel();
		valorTotalMenuCompras.setBounds(150, 450, 130, 30);
		panelCompras.add(valorTotalMenuCompras);
		
		agregarDescripcionCompras = new JButton("Agregar Descripción");
		agregarDescripcionCompras.setBounds(30, 480, 155, 25);
		agregarDescripcionCompras.setFocusable(false);
		panelCompras.add(agregarDescripcionCompras);
		
		eliminarDescripcionCompras = new JButton("Eliminar Descripción");
		eliminarDescripcionCompras.setBounds(185, 480, 155, 25);
		eliminarDescripcionCompras.setFocusable(false);
		panelCompras.add(eliminarDescripcionCompras);
		
		
		tituloFechaCompras = new JLabel("Fecha:");
		tituloFechaCompras.setBounds(530, 10, 40, 30);
		panelCompras.add(tituloFechaCompras);
	    
	    String fechaString = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(year);
	    
	    fechaActualCompras = new JLabel(fechaString);
	    fechaActualCompras.setBounds(580, 10, 70, 30);
	    panelCompras.add(fechaActualCompras);
	    
	    tituloHoraCompras = new JLabel("Hora:");
	    tituloHoraCompras.setBounds(530, 25, 40, 30);
	    panelCompras.add(tituloHoraCompras);
	    
	    horaActualCompras = new JLabel();
	    horaActualCompras.setBounds(580, 25, 70, 30);
	    panelCompras.add(horaActualCompras);
		
		
		agregarVentaCompras = new JButton("Agregar Compra");
		agregarVentaCompras.setBounds(530, 50, 130, 25);
		agregarVentaCompras.setFocusable(false);
		panelCompras.add(agregarVentaCompras);
		
		eliminarVentaCompras = new JButton("Eliminar Compra");
		eliminarVentaCompras.setBounds(530, 80, 130, 25);
		eliminarVentaCompras.setFocusable(false);
		panelCompras.add(eliminarVentaCompras);
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
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

	public JMenu getOpcionMovimientos() {
		return opcionMovimientos;
	}

	public void setOpcionMovimientos(JMenu opcionMovimientos) {
		this.opcionMovimientos = opcionMovimientos;
	}

	public JMenuItem getMenuInicio() {
		return menuInicio;
	}

	public void setMenuInicio(JMenuItem menuInicio) {
		this.menuInicio = menuInicio;
	}

	public JMenuItem getMenuIngresos() {
		return menuIngresos;
	}

	public void setMenuIngresos(JMenuItem menuIngresos) {
		this.menuIngresos = menuIngresos;
	}

	public JMenuItem getMenuEgresos() {
		return menuEgresos;
	}

	public void setMenuEgresos(JMenuItem menuEgresos) {
		this.menuEgresos = menuEgresos;
	}

	public JMenuItem getMenuCompras() {
		return menuCompras;
	}

	public void setMenuCompras(JMenuItem menuCompras) {
		this.menuCompras = menuCompras;
	}

	public JLabel getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(JLabel totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public JLabel getTotalEgresos() {
		return totalEgresos;
	}

	public void setTotalEgresos(JLabel totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

	public JLabel getTotalCompras() {
		return totalCompras;
	}

	public void setTotalCompras(JLabel totalCompras) {
		this.totalCompras = totalCompras;
	}

	public JButton getAgregarDescripcionIngresos() {
		return agregarDescripcionIngresos;
	}

	public void setAgregarDescripcionIngresos(JButton agregarDescripcionIngresos) {
		this.agregarDescripcionIngresos = agregarDescripcionIngresos;
	}

	public JButton getEliminarDescripcionIngresos() {
		return eliminarDescripcionIngresos;
	}

	public void setEliminarDescripcionIngresos(JButton eliminarDescripcionIngresos) {
		this.eliminarDescripcionIngresos = eliminarDescripcionIngresos;
	}

	public JButton getAgregarVentaIngresos() {
		return agregarVentaIngresos;
	}

	public void setAgregarVentaIngresos(JButton agregarVentaIngresos) {
		this.agregarVentaIngresos = agregarVentaIngresos;
	}

	public JButton getEliminarVentaIngresos() {
		return eliminarVentaIngresos;
	}

	public void setEliminarVentaIngresos(JButton eliminarVentaIngresos) {
		this.eliminarVentaIngresos = eliminarVentaIngresos;
	}

	public JButton getAgregarDescripcionEgresos() {
		return agregarDescripcionEgresos;
	}

	public void setAgregarDescripcionEgresos(JButton agregarDescripcionEgresos) {
		this.agregarDescripcionEgresos = agregarDescripcionEgresos;
	}

	public JButton getEliminarDescripcionEgresos() {
		return eliminarDescripcionEgresos;
	}

	public void setEliminarDescripcionEgresos(JButton eliminarDescripcionEgresos) {
		this.eliminarDescripcionEgresos = eliminarDescripcionEgresos;
	}

	public JButton getAgregarVentaEgresos() {
		return agregarVentaEgresos;
	}

	public void setAgregarVentaEgresos(JButton agregarVentaEgresos) {
		this.agregarVentaEgresos = agregarVentaEgresos;
	}

	public JButton getEliminarVentaEgresos() {
		return eliminarVentaEgresos;
	}

	public void setEliminarVentaEgresos(JButton eliminarVentaEgresos) {
		this.eliminarVentaEgresos = eliminarVentaEgresos;
	}

	public JButton getAgregarDescripcionCompras() {
		return agregarDescripcionCompras;
	}

	public void setAgregarDescripcionCompras(JButton agregarDescripcionCompras) {
		this.agregarDescripcionCompras = agregarDescripcionCompras;
	}

	public JButton getEliminarDescripcionCompras() {
		return eliminarDescripcionCompras;
	}

	public void setEliminarDescripcionCompras(JButton eliminarDescripcionCompras) {
		this.eliminarDescripcionCompras = eliminarDescripcionCompras;
	}

	public JButton getAgregarVentaCompras() {
		return agregarVentaCompras;
	}

	public void setAgregarVentaCompras(JButton agregarVentaCompras) {
		this.agregarVentaCompras = agregarVentaCompras;
	}

	public JButton getEliminarVentaCompras() {
		return eliminarVentaCompras;
	}

	public void setEliminarVentaCompras(JButton eliminarVentaCompras) {
		this.eliminarVentaCompras = eliminarVentaCompras;
	}

	public JInternalFAgregarDescripcion getjInternalFAgregarDescripcion() {
		return jInternalFAgregarDescripcion;
	}
	
	public JLabel getValorTotalMenuIngresos() {
		return valorTotalMenuIngresos;
	}

	public void setValorTotalMenuIngresos(JLabel valorTotalMenuIngresos) {
		this.valorTotalMenuIngresos = valorTotalMenuIngresos;
	}

	public JLabel getValorTotalMenuEgresos() {
		return valorTotalMenuEgresos;
	}

	public void setValorTotalMenuEgresos(JLabel valorTotalMenuEgresos) {
		this.valorTotalMenuEgresos = valorTotalMenuEgresos;
	}

	public JLabel getValorTotalMenuCompras() {
		return valorTotalMenuCompras;
	}

	public void setValorTotalMenuCompras(JLabel valorTotalMenuCompras) {
		this.valorTotalMenuCompras = valorTotalMenuCompras;
	}

	public void setjInternalFAgregarDescripcion(JInternalFAgregarDescripcion jInternalFAgregarDescripcion) {
		this.jInternalFAgregarDescripcion = jInternalFAgregarDescripcion;
	}

	public JInternalFEliminarDescripcion getjInternalFEliminarDescripcion() {
		return jInternalFEliminarDescripcion;
	}

	public void setjInternalFEliminarDescripcion(JInternalFEliminarDescripcion jInternalFEliminarDescripcion) {
		this.jInternalFEliminarDescripcion = jInternalFEliminarDescripcion;
	}

	public JInternalFValores getjInternalFValores() {
		return jInternalFValores;
	}

	public void setjInternalFValores(JInternalFValores jInternalValores) {
		this.jInternalFValores = jInternalValores;
	}

	public JInternalFEliminarValores getjInternalFEliminarValores() {
		return jInternalFEliminarValores;
	}

	public void setjInternalFEliminarValores(JInternalFEliminarValores jInternalFEliminarValores) {
		this.jInternalFEliminarValores = jInternalFEliminarValores;
	}
	
}
