package Main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Lector {
	
	private String directorio;
	
	public Lector (){
		File file = new File("src/Main/resources", "Config.txt" );
		directorio = file.getAbsolutePath();
	}

	public List<Integer> leerConfiguracionInicial(){
		LinkedList<Integer> configInicial = new LinkedList<Integer>();
		try {
			FileReader file = new FileReader(directorio);
			BufferedReader lector = new BufferedReader(file);
			String lineaActual = lector.readLine();

			if(lineaActual.equals("[TABLERO]")){
				for(int i = 0 ; i < 4; i++){
					String lineaAux = lector.readLine();
					String[] separador = lineaAux.split("=");
					int dato = Integer.parseInt(separador[1]);
					configInicial.add(dato);
				}
			}
			lector.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


		return configInicial;
	}

	public String leerPosicionDeParedes(){
		String posicionDePared = null;

		try {
			FileReader file = new FileReader(directorio);
			BufferedReader lector = new BufferedReader(file);
			String lineaActual = lector.readLine();
			while(!lineaActual.startsWith("L")){
				lineaActual = lector.readLine();
			}
			String[] separador = lineaActual.split("=");
			posicionDePared = separador[1];
			
			lector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return posicionDePared;

	}
	public String leerSeccionMinas(){
		String posicionDeMinas= null;
		try {
			FileReader file = new FileReader(directorio);
			BufferedReader lector = new BufferedReader(file);
			String lineaActual = lector.readLine();
			
			while(!lineaActual.equals("[MINAS]")){
				lineaActual = lector.readLine();
			}
			lineaActual = lector.readLine();
			String[] separador = lineaActual.split("=");
			posicionDeMinas = separador[1];
			
			lector.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return posicionDeMinas;
	}
	
	public List<Provision> leerSeccionProvision(){

		List<Provision> provisiones = new LinkedList<Provision>();
		try {
			FileReader file = new FileReader(directorio);
			BufferedReader lector = new BufferedReader(file);
			String lineaActual = lector.readLine();

			while(!lineaActual.equals("[PROVISIONES]")){
				lineaActual = lector.readLine();
			}
			lineaActual = lector.readLine();
			while(!lineaActual.equals("[UBICACION_PROVISIONES]")){
				String lineaAux = lineaActual;
				String [] separadorAux = lineaAux.split("=");
				String lineaAux2 = separadorAux[1];
				String[] separadorAux2 = lineaAux2.split(",");
				if(separadorAux2.length>1){
					CajaDeProvision caja = new CajaDeProvision();
					for(int i=0 ;  i<=separadorAux2.length-1; i++){
						if(separadorAux2[i].equals("E"))
							caja.add(new Escudo());
						if(separadorAux2[i].equals("V"))
							caja.add(new Vitamina());
					}
					provisiones.add(caja);

				}else if(separadorAux2[0].equals("E")){
					provisiones.add(new Escudo());
				}else if(separadorAux2[0].equals("V")){
					provisiones.add(new Vitamina());
				}

				lineaActual = lector.readLine();
			}
			lector.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return provisiones;

	}
	
	public List<Integer> leerSeccionUbicacionDeLasProvisiones(){
		
		List<Integer> ubicacionesDeLasProvisiones = new LinkedList<Integer>();
		
		FileReader file;
		try {
			file = new FileReader(directorio);
			BufferedReader lector = new BufferedReader(file);
			String lineaActual = lector.readLine();
			
			while(!lineaActual.equals("[UBICACION_PROVISIONES]")){
				lineaActual = lector.readLine();
			}
			lineaActual = lector.readLine();
			while(lineaActual != null){
				String [] separadorAux = lineaActual.split("=");
				int dato = Integer.parseInt(separadorAux[1]);
				ubicacionesDeLasProvisiones.add(dato);
				lineaActual = lector.readLine();
			}
			lector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ubicacionesDeLasProvisiones;
		
		
	}
}
