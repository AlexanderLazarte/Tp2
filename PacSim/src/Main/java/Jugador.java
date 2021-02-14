package Main.java;

public class Jugador {
	
	private int escudo;
	private int vida;
	private int posicionX;
	private int posicionY;
	private boolean estaVivo;
	
	
	public Jugador(int posicionX , int posicionY){
		this.escudo = 0;
		this.vida = 3;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.estaVivo = true;
	}
	
	public void mostrarEstado(){
		System.out.println("ESTADO DEL JUGADOR: \n Vida: "+ getVida()+ " Escudo: "+ getEscudo());
	}

	public void moverIzquierda(){
		this.posicionY -=1 ;
	}
	
	public void moverDerecha(){
		this.posicionY +=1;
	}
	
	public void moverArriba(){
		this.posicionX -=1;
	}
	
	public void moverAbajo(){
		this.posicionX +=1;
	}
	
	public int getVida(){
		return this.vida;
	}
	
	public int getEscudo(){
		return this.escudo;
	}
	
	public void modificarVida(int valor){
		this.vida += valor;
		if(this.vida == 0)
			this.estaVivo=false;
	}
	
	public void modificarEscudo(int valor){
		this.escudo += valor;
	}
	
	public boolean isEstaVivo() {
		return estaVivo;
	}
	
	public int getPosicionX(){
		return this.posicionX;
	}
	public int getPosicionY(){
		return this.posicionY;
	}
}
