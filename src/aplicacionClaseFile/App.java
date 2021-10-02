package aplicacionClaseFile;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner( System.in );
		
		File fichero = new File( "/home/jordi/proyectosJavaEclipse/jordi_estelles_navarro_AE1_ADD/src" );
		
		GetInformation( fichero );
		
		System.out.print( "Introduce el nombre o PATH del directorio a crear : " );
		String nombreDirectorio = sc.next();
		
		CrearCarpeta( nombreDirectorio );
		
		

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
	
	
	static String CrearCarpeta( String nombreDirectorio ) {
		
		File directorio = new File( nombreDirectorio );
		
		boolean comodin = directorio.mkdir();
		
		if( comodin ) {
			System.out.println( "> Directorio creado correctamente\n" );
			
			return directorio.getAbsolutePath();
			
		}else {
			System.out.println( "> Error al crear el directorio\n");
			
			return "";

		}
		
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
		
		System.out.println( "\n---\n");
		
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
