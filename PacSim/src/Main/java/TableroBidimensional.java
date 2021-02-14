package Main.java;

import java.util.Iterator;
import java.util.List;

public class TableroBidimensional {

	private int columnas;
	private int filas;
	private int posicionSalidaX;
	private int posicionSalidaY;
	private Casillero [][] casilleros;
	
	public TableroBidimensional(int columnas, int filas, int posicionSalidaX, int posicionSalidaY){
		this.columnas = columnas;
		this.filas = filas;
		this.posicionSalidaX = posicionSalidaX;
		this.posicionSalidaY = posicionSalidaY;
		this.casilleros = new Casillero[filas][columnas];
		for(int i= 0 ; i< casilleros.length;i++){
			for(int j= 0 ; j< casilleros[i].length; j++){
				casilleros[i][j] = new Casillero();
			}
		}
		casilleros[posicionSalidaX][posicionSalidaY].addSalida();
	}

	public void agregarMinas(List<Integer> posicionesX,List<Integer> posicionesY){
		
		Iterator<Integer> ItrX = posicionesX.iterator();
		Iterator<Integer> ItrY = posicionesY.iterator();
		
		while(ItrX.hasNext() && ItrY.hasNext()){
			
			Mina mina = new Mina();
			int posX = ItrX.next();
			int posY = ItrY.next();
			this.casilleros[posX][posY].add(mina);
	
		}
		
		
	}
	
	public void agregarParedes(char[] posiciones){

		int p = 0;
		
		for(int i= 0 ; i< casilleros.length && p < posiciones.length;i++){
			for(int j= 0 ; j< casilleros[i].length && p < posiciones.length; j++){
				String posicion = String.valueOf(posiciones[p]);
				if(Integer.parseInt(posicion) == 1 ){
					this.casilleros[i][j].addPared();
				}
				p++;
			}
		}
	}

	public void agregarProvisiones(List<Provision> listaDeProvisiones ,
									List<Integer> ubicacionesDeLasProvisionesX,
									List<Integer> ubicacionesDeLasProvisionesY){

		Iterator<Provision> ItrListProv = listaDeProvisiones.iterator();
		Iterator<Integer> ubiDeLasProvX = ubicacionesDeLasProvisionesX.iterator();
		Iterator<Integer> ubiDeLasProvY = ubicacionesDeLasProvisionesY.iterator();
		Provision provisionActual = null;
		Integer ubiDeLaProvX = 0;
		Integer ubiDeLaProvY = 0;
		while(ItrListProv.hasNext() && ubiDeLasProvX.hasNext() && ubiDeLasProvY.hasNext()){

			provisionActual = ItrListProv.next();
			ubiDeLaProvX = ubiDeLasProvX.next();
			ubiDeLaProvY = ubiDeLasProvY.next();
			if(casilleros[ubiDeLaProvX][ubiDeLaProvY].getEstado() == Estado.VACIO)
				casilleros[ubiDeLaProvX][ubiDeLaProvY].add(provisionActual);

		}
	}

	
	public void mostrarTablero(Jugador jugadorActual){
		
		String tablero = "TABLERO ACTUAL : \n";
		
		int posicionJugadorX = jugadorActual.getPosicionX();
		int posicionJugadorY = jugadorActual.getPosicionY();
		
		System.out.println("REFERENCIAS DEL TABLERO: \n Jugador = J \n Casillero Vacio =  -  \n Pared = X   \n Salida = S  \n Recolectable = * ");
		
		for(int i = 0; i < casilleros.length; i++){
			for(int j = 0; j < casilleros[i].length ; j++){
				if(i== posicionJugadorX && j == posicionJugadorY)
					tablero += "J "; 
				else 
					tablero += casilleros[i][j].mostrarEstado();
			}
			tablero += "\n";
		}
		System.out.println(tablero);
		
	}

	public int getColumnas() {
		return columnas;
	}

	public int getFilas() {
		return filas;
	}

	public int getPosicionSalidaX() {
		return posicionSalidaX;
	}

	public int getPosicionSalidaY() {
		return posicionSalidaY;
	}
	
	public void afectarJugador(Jugador jugadorActual){
		casilleros[jugadorActual.getPosicionX()][jugadorActual.getPosicionY()].afectarJugador(jugadorActual);
		}

	public boolean hayPared(int posX, int posY) {
		Estado estadoDelCasillero= this.casilleros[posX][posY].getEstado();
		if(estadoDelCasillero.equals(Estado.PARED))
			return true;
		return false;
	}
}
