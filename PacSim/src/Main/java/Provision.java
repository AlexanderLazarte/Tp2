package Main.java;

public abstract class Provision implements EfectoAlJugador{

	private boolean usado= false;


	public void seUso(){
		this.usado = true;
	}

	public boolean isUsado(){
		return usado;
	}

}
