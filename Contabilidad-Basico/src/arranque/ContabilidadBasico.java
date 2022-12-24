package arranque;

import controlador.*;
import modelo.CargarDatos;
import modelo.Guardar;
import vista.*;

public class ContabilidadBasico {
	
	public static void main(String[] args) {
		VentanaBienvenido ventanaBienvenido = new VentanaBienvenido();
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		CargarDatos cargarDatos = new CargarDatos();
		Guardar guardar = new Guardar();
		Controlador controlador = new Controlador(ventanaBienvenido, ventanaPrincipal, cargarDatos, guardar);
		controlador.iniciar();
	}
}
