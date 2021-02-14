package Main.java;

public class Vitamina extends Provision {

	@Override
	public void afectarAlJugador(Jugador jugadorEnCurso) {
		if(!isUsado())
			jugadorEnCurso.modificarVida(1);
		seUso();
	}

}
