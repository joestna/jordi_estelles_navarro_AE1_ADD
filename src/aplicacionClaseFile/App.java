package aplicacionClaseFile;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

	public static void main(String[] args) {
		
		File fichero = new File( "/home/jordi/proyectosJavaEclipse/jordi_estelles_navarro_AE1_ADD/src" );
		
		GetInformation( fichero );

	}

	public static void GetInformation( File fichero) {
		
		System.out.print( "\nNombre del " + FicheroODirectorio( fichero ) + " : " + fichero.getName() + "\n" +
			              "Ubicacion del " + FicheroODirectorio( fichero ) + " : " + fichero.getAbsolutePath() + "\n" +
			              "Fecha de " + FicheroODirectorio( fichero ) + " : " + milisegundosAFecha( fichero.lastModified() ) + "\n" );
		
		if( FicheroODirectorio( fichero ) == "fichero" ) {
			System.out.println( "Tamano del fichero : " + fichero.length() );
			
		}else {
			ListarFicherosEnDirectorio( fichero.listFiles(), fichero );
	
		}
		
	}
	
	
	static void CrearCarpeta() {
		
	}
	
	
	static void CrearFichero() {
		
	}
	
	
	static void Elimina() {
		
	}
	
	
	static void Renombra() {
		
	}
	
	
	static String FicheroODirectorio( File fichero ) {
		
		if( fichero.isFile() == true ) return "fichero"; else return "directorio";
		
	}

	
	static void ListarFicherosEnDirectorio( File[] ficheros, File fichero ) {
		
		System.out.println( "\n---\nCantidad de elementos en directorio : " + ficheros.length + "\n---\n" );
		
		for ( File ficheroHijo: ficheros) {
			
			System.out.println( "Espacio libre en el directorio : " + ficheroHijo.getFreeSpace() + "\n" +
		                        "Espacio ocupado en el directorio : " + EspacioTotalMenosLibre( fichero.getTotalSpace(), fichero.getFreeSpace() ) + "\n" +
		                        "Espacio total del directorio : " + ficheroHijo.getTotalSpace() + "\n" );
		}
		
		System.out.println( "\n---\n" + ficheros.length );
		
	}
	
	
	static Date milisegundosAFecha( Long milisegundos ) {
		
		Date fecha = new Date( milisegundos );
		Calendar calendario = new GregorianCalendar();
		calendario.setTime( fecha );
		
		return calendario.getTime();
		
	}
	
	
	static int EspacioTotalMenosLibre( Long espacioTotal, Long espacioLibre ) {
		
		return espacioTotal.intValue() - espacioLibre.intValue();
		
	}
	
}
