package Main.java;

import java.util.List;

public interface Tablero {
	
	
	public void agregarMinas(String posiciones);
	
	public void agregarParedes(String posiciones);
	
	public void agregarProvisiones(List<Provision> listaDeProvisiones ,
									List<Integer> ubicacionesDeLasProvisiones);
	
	public void mostrarTablero(Jugador jugadorActual);
	
	public int getColumnas();
	
	public int getFilas();

	public int getPosicionSalidaX();

	public int getPosicionSalidaY();
	
	public void afectarJugador(Jugador jugadorActual);
	
	public boolean hayPared(int posX, int posY); 
	}
