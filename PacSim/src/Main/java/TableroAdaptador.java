package Main.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TableroAdaptador implements Tablero {

	private TableroBidimensional adaptado;
	
	public TableroAdaptador(int columnas, int filas, int posicionSalida){
		int posicionSalidaX = (posicionSalida/columnas);
		int posicionSalidaY =  (posicionSalida%columnas);
		adaptado = new TableroBidimensional( columnas,  filas,  posicionSalidaX,  posicionSalidaY);
	}

	@Override
	public void agregarMinas(String posiciones) {
		String[] posicionesAux = posiciones.split(",");
		
		List<Integer> posicionesX = new LinkedList<Integer>() ;
		List<Integer>posicionesY = new LinkedList<Integer>() ;
		
		for(int i= 0; i<posicionesAux.length; i++){
			int posicion = Integer.parseInt(posicionesAux[i]);
			posicionesX.add(posicion/this.adaptado.getColumnas());
			posicionesY.add(posicion%this.adaptado.getColumnas());
		}
		adaptado.agregarMinas(posicionesX, posicionesY);
	}

	@Override
	public void agregarParedes(String posiciones) {
		char[] posicionesAux = posiciones.toCharArray();
		adaptado.agregarParedes(posicionesAux);
	}

	@Override
	public void agregarProvisiones(List<Provision> listaDeProvisiones,
									List<Integer> ubicacionesDeLasProvisiones) {
		
		Iterator<Integer> itrUbic = ubicacionesDeLasProvisiones.iterator();
		List<Integer> posicionesX = new LinkedList<Integer>() ;
		List<Integer> posicionesY = new LinkedList<Integer>() ;
		
		while(itrUbic.hasNext()){
			int posicion = itrUbic.next();
			posicionesX.add(posicion/this.adaptado.getColumnas());
			posicionesY.add(posicion%this.adaptado.getColumnas());
		}
		adaptado.agregarProvisiones(listaDeProvisiones, posicionesX, posicionesY);
	}

	@Override
	public void mostrarTablero(Jugador jugadorActual) {
		adaptado.mostrarTablero(jugadorActual);
		
	}

	@Override
	public int getColumnas() {
		return adaptado.getColumnas();
	}

	@Override
	public int getFilas() {
		return adaptado.getFilas();
	}

	@Override
	public int getPosicionSalidaX() {
		return adaptado.getPosicionSalidaX() ;
	}
	
	@Override
	public int getPosicionSalidaY() {
		return adaptado.getPosicionSalidaY() ;
	}

	@Override
	public void afectarJugador(Jugador jugadorActual) {
		adaptado.afectarJugador(jugadorActual);
		
	}

	@Override
	public boolean hayPared(int posX, int posY) {
		return adaptado.hayPared(posX, posY);
	}
	

	
}
