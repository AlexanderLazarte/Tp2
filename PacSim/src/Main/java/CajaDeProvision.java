package Main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CajaDeProvision extends Provision{
	
	
	private List<Provision> provisiones;
	
	public CajaDeProvision(){
		provisiones = new ArrayList<Provision>();
	}

	@Override
	public void afectarAlJugador(Jugador jugadorEnCurso) {
		Iterator<Provision> provisionesItr = provisiones.iterator();
		while(provisionesItr.hasNext()){
			Provision provisionActual = provisionesItr.next();
			provisionActual.afectarAlJugador(jugadorEnCurso);
			
		}
		
	}
	
	public void add(Provision provision){
		provisiones.add(provision);
	}
	
	
}


