package Main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Casillero {

	private Estado estado;
	private List<EfectoAlJugador> efectosDentroDelCasillero;

	public Casillero (){
		this.estado = Estado.VACIO;
		efectosDentroDelCasillero = new ArrayList<EfectoAlJugador>();
	}

	public void add(EfectoAlJugador efecto){
		if(getEstado().equals(Estado.VACIO))
			efectosDentroDelCasillero.add(efecto);
	}

	public boolean hayEfectos(){
		return !efectosDentroDelCasillero.isEmpty();
	}

	public Estado getEstado() {
		return estado;
	}

	public void afectarJugador(Jugador jugadorActual){
		if(hayEfectos()){
			Iterator<EfectoAlJugador> itrEfect = efectosDentroDelCasillero.iterator();
			while(itrEfect.hasNext()){
				itrEfect.next().afectarAlJugador(jugadorActual);
			}
		}

	}

	public void addPared(){
		this.estado = Estado.PARED;
	}

	public void addSalida(){
		this.estado = Estado.SALIDA;
	}
	public String mostrarEstado(){
		if(getEstado().equals(Estado.PARED)){
			return "X ";
		}
		if(getEstado().equals(Estado.SALIDA)){
			return "S ";
		}
		if(getEstado().equals(Estado.VACIO) && !efectosDentroDelCasillero.isEmpty()){
			Iterator<EfectoAlJugador> itrAux= efectosDentroDelCasillero.iterator();
			boolean aux = false;
			while(itrAux.hasNext()&& aux==false){
				aux = itrAux.next().isUsado();
			}
			if(aux == false)
				return "* ";
		}

		return "- ";

	}
}
