package modelo;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Guardar {
	private long valores = 0;

	public Guardar() {

	}

	public void guardarDatos(String descripcion, JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

		Object[] texto = { descripcion, "" };
		modelo.addRow(texto);
	}

	public void guardarDatosLista(JTable tabla, JList<String> lista) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		lista.setModel(modelo);
		int filas = tabla.getRowCount();

		for (int i = 0; i < filas; i++) {
			modelo.addElement(String.valueOf(tabla.getValueAt(i, 0)));
		}
	}

	public void eliminarDescripcion(JTable tabla, JList<String> lista) {
		DefaultListModel<String> modelo = (DefaultListModel<String>) lista.getModel();
		DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
		int filas = tabla.getRowCount();

		for (int i = 0; i < filas; i++) {
			if (modelo.get(lista.getSelectedIndex()).equals(String.valueOf(tabla.getValueAt(i, 0)))) {
				modeloTabla.removeRow(i);
				break;
			}
		}

		modelo.remove(lista.getSelectedIndex());
	}

	public void agregarValoresTabla(JTable tabla, String textoNuevoValor) {
		long valor = 0;
		long anteriorValor = 0;
		long suma = 0;

		int fila = tabla.getSelectedRow();
		valor = Long.parseLong(textoNuevoValor);
		if (valor < 0) {
			valor = 0;
		}
		if (String.valueOf(tabla.getValueAt(fila, 1)).equals("null")
				|| String.valueOf(tabla.getValueAt(fila, 1)).equals("")) {
			anteriorValor = 0;
		} else {
			anteriorValor = Long.parseLong(String.valueOf(tabla.getValueAt(fila, 1)));
		}

		suma = valor + anteriorValor;
		tabla.setValueAt(suma, fila, 1);
	}

	public void eliminarValoresTabla(JTable tabla, String textoEliminarValor) {
		long valor = 0;
		long anteriorValor = 0;
		long resta = 0;
		int fila = tabla.getSelectedRow();
		valor = Long.parseLong(textoEliminarValor);
		if (valor < 0) {
			valor = 0;
		}
		if (String.valueOf(tabla.getValueAt(fila, 1)).equals("null")
				|| String.valueOf(tabla.getValueAt(fila, 1)).equals("")) {
			anteriorValor = 0;
		} else {
			anteriorValor = Long.parseLong(String.valueOf(tabla.getValueAt(fila, 1)));
			resta = anteriorValor - valor;
		}
		if(resta < 0) {
			resta = 0;
		}

		tabla.setValueAt(resta, fila, 1);

	}

	public long calcularTotales(JTable tabla) {
		long total = 0;
		int fila = tabla.getRowCount();

		for (int i = 0; i < fila; i++) {
			long valor = 0;
			if (!String.valueOf(tabla.getValueAt(i, 1)).equals("null")
					&& !String.valueOf(tabla.getValueAt(i, 1)).equals("")) {
				valor = Long.parseLong(String.valueOf(tabla.getValueAt(i, 1)));
			}

			total += valor;
		}

		return total;
	}

	public void darFormatoNumeros(DefaultTableModel modelo) {
		String valorCelda = "";
		boolean quitar = false;
		String valorCeldaFinal = "";
		int fila = modelo.getRowCount();

		for (int i = 0; i < fila; i++) {
			valorCelda = String.valueOf(modelo.getValueAt(i, 1));
			for (Character c : valorCelda.toCharArray()) {
				if (c.equals('.')) {
					quitar = true;
				}
				if (!quitar) {
					valorCeldaFinal += c;
				}
			}
			if (String.valueOf(modelo.getValueAt(i, 1)).equals("null")) {
				valorCeldaFinal = "";
			}
			modelo.setValueAt(valorCeldaFinal, i, 1);
			valorCeldaFinal = "";
			quitar = false;
		}
	}

	public long getValores() {
		return valores;
	}

	public void setValores(long valores) {
		this.valores = valores;
	}
}
