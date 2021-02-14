package Main.java;

public class Mina implements EfectoAlJugador {

	
	private boolean usado= false;
	
	
	@Override
	public void afectarAlJugador(Jugador jugadorEnCurso) {
		if(!isUsado()){
			if(jugadorEnCurso.getEscudo()>0)
				jugadorEnCurso.modificarEscudo(-1);
			else
				jugadorEnCurso.modificarVida(-1);
		}
			
		seUso();
	}

	public void seUso(){
		this.usado = true;
	
	}

	public boolean isUsado() {
		return usado;
	}
}
