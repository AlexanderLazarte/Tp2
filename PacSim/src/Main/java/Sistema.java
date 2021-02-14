package Main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class Sistema {


	private Lector lector;
	private Tablero tablero;
	private Jugador jugador;

	
	public Sistema(){
		this.lector = new Lector();
		List<Integer> configInicial = lector.leerConfiguracionInicial();
		this.tablero = crearTableroInicial(configInicial);
		this.jugador = crearJugador(configInicial);
		this.tablero.agregarParedes(lector.leerPosicionDeParedes());
		this.tablero.agregarMinas(lector.leerSeccionMinas());
		this.tablero.agregarProvisiones(lector.leerSeccionProvision(), lector.leerSeccionUbicacionDeLasProvisiones());
	}
	
	public void iniciarJuego(){
		System.out.println("*****************************************************");
		System.out.println("Inicia el juego");
		while(this.jugador.isEstaVivo() && !(this.jugador.getPosicionX()==this.tablero.getPosicionSalidaX() && this.jugador.getPosicionY()==this.tablero.getPosicionSalidaY())){
			this.jugador.mostrarEstado();
			this.tablero.mostrarTablero(this.jugador);
			System.out.println("¿hacia donde se quiere mover? Ingrese: izq, der, arrib, abaj");
			String respuesta = recibirRespuesta();
			mover(respuesta);
			tablero.afectarJugador(this.jugador);
			System.out.println("---------------------------------------------------------------------------------");
		}
		if(this.jugador.isEstaVivo()== false){
			this.jugador.mostrarEstado();
			this.tablero.mostrarTablero(this.jugador);
			System.out.println("Ah muerto!\n Game over.\n Su puntuacion es de :" + this.jugador.getVida());
		}
		if((this.jugador.getPosicionX()==this.tablero.getPosicionSalidaX() && this.jugador.getPosicionY()==this.tablero.getPosicionSalidaY())){
			this.jugador.mostrarEstado();
			this.tablero.mostrarTablero(this.jugador);
			System.out.println("Ah llegado a la salida! Felicidades! \n Game over.\n Su puntuacion es de :" + this.jugador.getVida());
		}
		
		System.out.println("¿Desea reiniciar el juego?");
		String respuesta = recibirRespuestaDeReinicio();
		if(respuesta.equals("Si") ||respuesta.equals("SI") ||respuesta.equals("si")){
			Sistema sistema = new Sistema();
			sistema.iniciarJuego();
		}
		System.out.println("Muchas gracias por jugar!");
	}

	private String recibirRespuestaDeReinicio() {
		String respuesta = "";
		BufferedReader ingresoDelUsuario = new BufferedReader(new InputStreamReader (System.in));
		try {
			respuesta = ingresoDelUsuario.readLine();
			if(respuesta.equals("Si") ||respuesta.equals("SI") ||respuesta.equals("si") || respuesta.equals("No")|| respuesta.equals("NO") || respuesta.equals("no")  )
				return respuesta;
			else{
				System.out.println(" ingreso invalido, por favor ingrese Si para aceptar o No para rechazar");
				recibirRespuesta();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "No se ingreso nada";
	}

	private void mover(String respuesta) {
		if(respuesta.equals("izq")|| respuesta.equals("IZQ")|| respuesta.equals("Izq"))
			if((this.jugador.getPosicionY()-1)<0 || this.tablero.hayPared(this.jugador.getPosicionX(), this.jugador.getPosicionY()-1)){
				System.out.println("Movimiento invalido, ingrese otro movimiento:");
				mover(recibirRespuesta());
			}
			else
				this.jugador.moverIzquierda();
		else if ( respuesta.equals("der")|| respuesta.equals("Der")|| respuesta.equals("DER"))
			if((this.jugador.getPosicionY()+1 > this.tablero.getColumnas()-1 )|| this.tablero.hayPared(this.jugador.getPosicionX(), this.jugador.getPosicionY()+1)){
				System.out.println("Movimiento invalido, ingrese otro movimiento:");
				mover(recibirRespuesta());
			}
			else
				this.jugador.moverDerecha();
		else if (respuesta.equals("arrib")||respuesta.equals("ARRIB")|| respuesta.equals("Arrib"))
			if((this.jugador.getPosicionX()-1 < 0) || this.tablero.hayPared(this.jugador.getPosicionX()-1, this.jugador.getPosicionY())){
				System.out.println("Movimiento invalido, ingrese otro movimiento:");
				mover(recibirRespuesta());
			}
			else
				this.jugador.moverArriba();
		else if(respuesta.equals("abaj")|| respuesta.equals("Abaj")|| respuesta.equals("ABAJ"))
			if((this.jugador.getPosicionX()+1 > this.tablero.getFilas()-1) || this.tablero.hayPared(this.jugador.getPosicionX()+1, this.jugador.getPosicionY())){
				System.out.println("Movimiento invalido, ingrese otro movimiento:");
				mover(recibirRespuesta());
			}
			else
				this.jugador.moverAbajo();
		
	}

	private String recibirRespuesta() {
		String respuesta = "";
		BufferedReader ingresoDelUsuario = new BufferedReader(new InputStreamReader (System.in));
		try {
			respuesta = ingresoDelUsuario.readLine();
			if(respuesta.equals("izq") || respuesta.equals("der")|| respuesta.equals("arrib")|| respuesta.equals("abaj")|| respuesta.equals("Abaj")|| respuesta.equals("ABAJ")
					|| respuesta.equals("ARRIB")|| respuesta.equals("Arrib")|| respuesta.equals("Der")|| respuesta.equals("DER")|| respuesta.equals("IZQ")|| respuesta.equals("Izq"))
				return respuesta;
			else{
				System.out.println(" ingreso invalido, por favor ingrese izq,der,arrib,abaj");
				recibirRespuesta();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "No se ingreso nada";
	}

	private Jugador crearJugador(List<Integer> configInicial) {
		Iterator<Integer> ItrConfigInicial = configInicial.iterator();
		int dato= 0;
		for(int i = 0; i<4 ; i++)
			dato = ItrConfigInicial.next();

		int posicionEntradaX = (dato/this.tablero.getColumnas());
		int posicionEntradaY = (dato%this.tablero.getColumnas());

		Jugador jugador = new Jugador(posicionEntradaX, posicionEntradaY);
		return jugador;
	}

	private Tablero crearTableroInicial(List<Integer> configInicial) {
		Iterator<Integer> ItrConfigInicial = configInicial.iterator();
		int columna = ItrConfigInicial.next();
		int fila = ItrConfigInicial.next();
		int salida = ItrConfigInicial.next();
		Tablero tablero= new TableroAdaptador(columna, fila, salida);
		return tablero;
	}
}
