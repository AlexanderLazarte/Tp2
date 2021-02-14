package Main.java;

public class Escudo extends Provision{

	@Override
	public void afectarAlJugador(Jugador jugadorEnCurso) {
		if(!isUsado())
			jugadorEnCurso.modificarEscudo(1);
		seUso();
	}
	
}
