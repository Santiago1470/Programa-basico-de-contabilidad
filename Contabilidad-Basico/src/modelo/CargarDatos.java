package modelo;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CargarDatos {
	private Workbook libroExcelLectura;
	private Guardar guardar;
	private DefaultTableModel modeloIngresos;
	private DefaultTableModel modeloEgresos;
	private DefaultTableModel modeloCompras;
	private String filaFinal1 = "", filaFinal2 = "", filaFinal3 = "";
	//private FileInputStream archivo;

	public CargarDatos() {
		guardar = new Guardar();
	}
	/*
	public String cargarExcel(File archivoRecibido, JTable tabla) {
		String mensajeEstado = "No se pudo cargar correctamente el archivo Excel";
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);

		try {
			// archivo = new FileInputStream(archivoRecibido);
			libroExcelLectura = WorkbookFactory.create(new FileInputStream(archivoRecibido));
			Sheet hoja = libroExcelLectura.getSheetAt(0);
			Iterator FilaIterator = hoja.rowIterator();
			int numFila = -1;

			while (FilaIterator.hasNext()) {
				numFila++;
				Row fila = (Row) FilaIterator.next();
				Iterator ColumnaIterator = fila.cellIterator();
				Object[] listaColumna = new Object[9999];
				int numColumna = -1;

				while (ColumnaIterator.hasNext()) {
					numColumna++;
					Cell celda = (Cell) ColumnaIterator.next();

					if (numFila == 0) {
						modelo.addColumn(celda.getStringCellValue());
					} else {
						if (celda != null) {
							switch (celda.getCellTypeEnum().toString()) {
								case "NUMERIC":
									listaColumna[numColumna] = celda.getNumericCellValue();
	
									break;
								case "STRING":
									listaColumna[numColumna] = celda.getStringCellValue();
	
									break;
								case "FORMULA":
									listaColumna[numColumna] = celda.getCellFormula();
	
									break;
								case "BOOLEAN":
									listaColumna[numColumna] = celda.getBooleanCellValue();
	
									break;
							}
						}
					}
				}
				if(numFila != 0) {
					modelo.addRow(listaColumna);
				}
			}
			mensajeEstado = "Archivo Excel cargado exitosamente";
		} catch (Exception ex) {
			System.err.println("Error: " + ex);
		}
		return mensajeEstado;
	}*/

	public String cargarExcel(File archivoRecibido) {
		String mensajeEstado = "No se pudo cargar correctamente el archivo Excel";

		try {
			libroExcelLectura = WorkbookFactory.create(new FileInputStream(archivoRecibido));
			
			mensajeEstado = "Archivo Excel cargado exitosamente";
		} catch (Exception ex) {
			System.err.println("Error: " + ex);
		}
		return mensajeEstado;
	}
	
	public void cargarExcelIngresos(JTable tabla) {
		String mensajeEstado = "No se pudo cargar correctamente el archivo Excel";
		modeloIngresos = new DefaultTableModel() {
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
		
		tabla.setModel(modeloIngresos);
		int columna = 0;

		try {
			Sheet hoja = libroExcelLectura.getSheetAt(0);
			int filas = hoja.getLastRowNum();
			
			for(int i = 0; i < filas; i++) {
				Row fila = hoja.getRow(i);
				Object[] listaColumna = new Object[9999];

				for(int j = 0; j < 2; j++) {
					Cell celda = fila.getCell(j);
					if (i == 0) {
						modeloIngresos.addColumn(celda.getStringCellValue());
					} else {
						if (celda != null) {
							switch (celda.getCellTypeEnum().toString()) {
								case "NUMERIC":
									listaColumna[columna] = celda.getNumericCellValue();
	
									break;
								case "STRING":
									listaColumna[columna] = celda.getStringCellValue();
	
									break;
								case "FORMULA":
									listaColumna[columna] = celda.getCellFormula();
	
									break;
								case "BOOLEAN":
									listaColumna[columna] = celda.getBooleanCellValue();
	
									break;
							}
						}
						columna += 1;
						if(columna >= 2) {
							columna = 0;
						}
					}
				}
				if(i != 0 && listaColumna[0] != null && !listaColumna[0].equals("")) {
					modeloIngresos.addRow(listaColumna);
				}
			}
			mensajeEstado = "Archivo Excel cargado exitosamente";
		} catch (Exception ex) {
			System.err.println("Error: " + ex);
		}
		if(mensajeEstado.equals("No se pudo cargar correctamente el archivo Excel")) {
			JOptionPane.showMessageDialog(null, mensajeEstado);
		}
		//return mensajeEstado;
		guardar.darFormatoNumeros(modeloIngresos);
	}
	
	public void cargarExcelEgresos(JTable tabla) {
		String mensajeEstado = "No se pudo cargar correctamente el archivo Excel";
		modeloEgresos = new DefaultTableModel() {
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
		tabla.setModel(modeloEgresos);
		int columna = 0;

		try {
			Sheet hoja = libroExcelLectura.getSheetAt(0);
			int filas = hoja.getLastRowNum();
			
			for(int i = 0; i < filas; i++) {
				Row fila = hoja.getRow(i);
				Object[] listaColumna = new Object[9999];

				for(int j = 3; j < 5; j++) {
					Cell celda = fila.getCell(j);
					if (i == 0) {
						modeloEgresos.addColumn(celda.getStringCellValue());
					} else {
						if (celda != null) {
							switch (celda.getCellTypeEnum().toString()) {
								case "NUMERIC":
									listaColumna[columna] = celda.getNumericCellValue();
	
									break;
								case "STRING":
									listaColumna[columna] = celda.getStringCellValue();
	
									break;
								case "FORMULA":
									listaColumna[columna] = celda.getCellFormula();
	
									break;
								case "BOOLEAN":
									listaColumna[columna] = celda.getBooleanCellValue();
	
									break;
							}
						}
						columna += 1;
						if(columna >= 2) {
							columna = 0;
						}
					}
				}
				if(i != 0 && listaColumna[0] != null && !listaColumna[0].equals("")) {
					modeloEgresos.addRow(listaColumna);
				}
			}
			mensajeEstado = "Archivo Excel cargado exitosamente";
		} catch (Exception ex) {
			System.err.println("Error: " + ex);
		}
		if(mensajeEstado.equals("No se pudo cargar correctamente el archivo Excel")) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar correctamente el archivo Excel");
		}
		//return mensajeEstado;
		guardar.darFormatoNumeros(modeloEgresos);
	}
	
	public void cargarExcelCompras(/*File archivoRecibido,*/ JTable tabla) {
		String mensajeEstado = "No se pudo cargar correctamente el archivo Excel";
		modeloCompras = new DefaultTableModel() {
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
		tabla.setModel(modeloCompras);
		int columna = 0;

		try {
			//libroExcelLectura = WorkbookFactory.create(new FileInputStream(archivoRecibido));
			Sheet hoja = libroExcelLectura.getSheetAt(0);
			int filas = hoja.getLastRowNum();
			
			for(int i = 0; i < filas; i++) {
				Row fila = hoja.getRow(i);
				Object[] listaColumna = new Object[9999];

				for(int j = 6; j < 8; j++) {
					Cell celda = fila.getCell(j);
					if (i == 0) {
						modeloCompras.addColumn(celda.getStringCellValue());
					} else {
						if (celda != null) {
							switch (celda.getCellTypeEnum().toString()) {
								case "NUMERIC":
									listaColumna[columna] = celda.getNumericCellValue();
	
									break;
								case "STRING":
									listaColumna[columna] = celda.getStringCellValue();
	
									break;
								case "FORMULA":
									listaColumna[columna] = celda.getCellFormula();
	
									break;
								case "BOOLEAN":
									listaColumna[columna] = celda.getBooleanCellValue();
	
									break;
							}
						}
					}
					columna += 1;
					if(columna >= 2) {
						columna = 0;
					}
				}
				if(i != 0 && listaColumna[0] != null && !listaColumna[0].equals("")) {
					modeloCompras.addRow(listaColumna);
				}
			}
			mensajeEstado = "Archivo Excel cargado exitosamente";
		} catch (Exception ex) {
			System.err.println("Error: " + ex);
		}
		if(mensajeEstado.equals("No se pudo cargar correctamente el archivo Excel")) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar correctamente el archivo Excel");
		}
		//return mensajeEstado;
		guardar.darFormatoNumeros(modeloCompras);
	}
	
	public String generarExcel(File archivo, JTable tabla1, JTable tabla2, JTable tabla3) {
		String mensajeEstado = "No se pudo generar el archivo Excel";
		int numFila1 = tabla1.getRowCount();
		int numFila2 = tabla2.getRowCount();
		int numFila3 = tabla3.getRowCount();
		int numFila = 0;
		
		if(numFila1 > numFila2 && numFila1 > numFila3) {
			numFila = numFila1; 
		} else if(numFila2 > numFila1 && numFila2 > numFila3) {
			numFila = numFila2;
		} else {
			numFila = numFila3;
		}
		
		int numColumna = tabla1.getColumnCount() + tabla2.getColumnCount() + tabla3.getColumnCount() + 2;
		int contador = 0;
		
		if(archivo.getName().endsWith("xls")) {
			libroExcelLectura = new HSSFWorkbook();
		} else {
			libroExcelLectura = new XSSFWorkbook();
		}
		//Color color = new Color(255, 217, 102);
		CellStyle style1 = libroExcelLectura.createCellStyle();
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style1.setFillForegroundColor(HSSFColor.GOLD.index);
		style1.setBorderBottom(BorderStyle.MEDIUM);
		style1.setBorderRight(BorderStyle.MEDIUM);
		style1.setBorderLeft(BorderStyle.MEDIUM);
		style1.setBorderTop(BorderStyle.MEDIUM);
		
		CellStyle style2 = libroExcelLectura.createCellStyle();
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.MEDIUM);
		style2.setBorderTop(BorderStyle.THIN);		
		
		CellStyle style3 = libroExcelLectura.createCellStyle();
		style3.setBorderBottom(BorderStyle.THIN);
		style3.setBorderRight(BorderStyle.MEDIUM);
		style3.setBorderLeft(BorderStyle.THIN);
		style3.setBorderTop(BorderStyle.THIN);
		
		Sheet hoja = libroExcelLectura.createSheet("Hoja1");
		
		try {
			boolean bucle = true;
			for(int i = -1; i < numFila; i++) {
				if(i == (numFila - 1) && bucle) {
					numFila += 1;
					bucle = false;
				}
				Row fila = hoja.createRow(i + 1);
				boolean reiniciarContador = false;
				
				for(int j = 0; j < numColumna; j++) {
					Cell celda = fila.createCell(j);
					celda.setCellType(CellType.STRING);
					
					if(j == 2 || j == 5) {
						reiniciarContador = true;
					} else {
						reiniciarContador = false;
					}
					if(i == -1) {
						if(j == 0 || j == 1) {
							celda.setCellValue(String.valueOf(tabla1.getColumnName(contador)));
							celda.setCellStyle(style1);
						} else if(j == 3 || j == 4) {
							celda.setCellValue(String.valueOf(tabla2.getColumnName(contador)));
							celda.setCellStyle(style1);
						} else if(j == 6 || j == 7) {
							celda.setCellValue(String.valueOf(tabla3.getColumnName(contador)));
							celda.setCellStyle(style1);
						}
						
					} else {
						if(j == 0 || j == 1) {
							if(j == 0) {
								celda.setCellStyle(style2);
							} else {
								celda.setCellStyle(style3);
							}
							if(i < numFila1) {
								celda.setCellType(CellType.NUMERIC);
								if(tabla1.getValueAt(i, contador) != null && !String.valueOf(tabla1.getValueAt(i, contador)).equals("")) {
									if(j == 1) {
										
										celda.setCellValue(Long.parseLong(String.valueOf(tabla1.getValueAt(i, contador))));
									} else {
										celda.setCellValue(String.valueOf(tabla1.getValueAt(i, contador)));
									}
								} else {
									celda.setCellValue(0);
								}
							}
						} else if(j == 3 || j == 4) {
							if(j == 3) {
								celda.setCellStyle(style2);
							} else {
								celda.setCellStyle(style3);
							}
							if(i < numFila2) {
								celda.setCellType(CellType.NUMERIC);
								if(tabla2.getValueAt(i, contador) != null && !String.valueOf(tabla2.getValueAt(i, contador)).equals("")) {
									if(j == 4) {
										
										celda.setCellValue(Long.parseLong(String.valueOf(tabla2.getValueAt(i, contador))));
									} else {
										celda.setCellValue(String.valueOf(tabla2.getValueAt(i, contador)));
									}
								} else {
									celda.setCellValue(0);
								}
							}
							
						} else if(j == 6 || j == 7) {
							if(j == 6) {
								celda.setCellStyle(style2);
							} else {
								celda.setCellStyle(style3);
							}
							if(i < numFila3) {
								celda.setCellType(CellType.NUMERIC);
								if(tabla3.getValueAt(i, contador) != null && !String.valueOf(tabla3.getValueAt(i, contador)).equals("")) {
									
									if(j == 7) {
										celda.setCellValue(Long.parseLong(String.valueOf(tabla3.getValueAt(i, contador))));
									} else {
										celda.setCellValue(String.valueOf(tabla3.getValueAt(i, contador)));
									}
								} else {
									if(j == 1 || j == 4 || j == 7) {
										celda.setCellValue(0);
									} else {
										celda.setCellValue("");
									}
								}
							}
						}
						
					}
					if(i == (numFila - 1) && contador == 0 && j != 2 && j != 5) {
						celda.setCellValue("TOTAL");
						celda.setCellStyle(style1);
					} else if(i == (numFila - 1) && contador == 1 && j != 2 && j != 5) {
						if(j == 1) {
							if(filaFinal1.equals("")) {
								filaFinal1 = "0";
							}
							celda.setCellType(CellType.FORMULA);
							celda.setCellFormula(String.format("SUMA(B%d:B%d)", 2, (numFila1 + 1)));
							celda.setCellStyle(style1);
						} else if(j == 4) {
							if(filaFinal2.equals("")) {
								filaFinal2 = "0";
							}
							celda.setCellType(CellType.FORMULA);
							celda.setCellFormula(String.format("SUMA(E%d:E%d)\n", 2, (numFila2 + 1)));
							celda.setCellStyle(style1);
						} else if(j == 7) {
							if(filaFinal3.equals("")) {
								filaFinal3 = "0";
							}
							celda.setCellType(CellType.FORMULA);
							celda.setCellFormula(String.format("SUMA(H%d:H%d)", 2, (numFila3 + 1)));
							celda.setCellStyle(style1);
						}
					} 
					
					contador += 1;
					if(contador >= 2) {
						contador = 0;
					}
					if(reiniciarContador) {
						contador = 0;
					}
					
					libroExcelLectura.write(new FileOutputStream(archivo));
					
				}
				
				
			}
			
			mensajeEstado = "Archivo Excel generado exitosamente";
		} catch(Exception ex) {
			System.err.println("Error: " + ex);
		}
		
		return mensajeEstado;
	}
	
	
	/*public String generarExcel(File archivo, JTable tabla) {
		String mensajeEstado = "No se pudo generar el archivo Excel";
		int numFila = tabla.getRowCount();
		int numColumna = tabla.getColumnCount();
		
		if(archivo.getName().endsWith("xls")) {
			libroExcelLectura = new HSSFWorkbook();
		} else {
			libroExcelLectura = new XSSFWorkbook();
		}
		
		Sheet hoja = libroExcelLectura.createSheet("Hoja1");
		
		try {
			for(int i = -1; i < numFila; i++) {
				Row fila = hoja.createRow(i + 1);
				
				for(int j = 0; j < numColumna; j++) {
					Cell celda = fila.createCell(j);
					
					if(i == -1) {
						celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
					} else {
						if(tabla.getValueAt(i, j) != null) {
							celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
						} else {
							celda.setCellValue(" ");
						}
						
					}
					libroExcelLectura.write(new FileOutputStream(archivo));
				}
			}
			mensajeEstado = "Archivo Excel generado exitosamente";
		} catch(Exception ex) {
			System.err.println("Error: " + ex);
		}
		
		return mensajeEstado;
	}*/
	
	public Guardar getGuardar() {
		return guardar;
	}

	public void setGuardar(Guardar guardar) {
		this.guardar = guardar;
	}

	public String getFilaFinal1() {
		return filaFinal1;
	}

	public void setFilaFinal1(String filaFinal1) {
		this.filaFinal1 = filaFinal1;
	}

	public String getFilaFinal2() {
		return filaFinal2;
	}

	public void setFilaFinal2(String filaFinal2) {
		this.filaFinal2 = filaFinal2;
	}

	public String getFilaFinal3() {
		return filaFinal3;
	}

	public void setFilaFinal3(String filaFinal3) {
		this.filaFinal3 = filaFinal3;
	}

}
