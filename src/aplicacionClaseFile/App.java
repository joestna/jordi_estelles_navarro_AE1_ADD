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
		
		System.out.print( "Introduce el nombre del fichero a crear dentor del directorio anterior : " );
		String nombreFichero = sc.next();
		
		CrearFichero( CrearCarpeta( nombreDirectorio ), nombreFichero );
		
		sc.close();		

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
			System.out.println( "\n> Directorio creado correctamente" );
			
			return directorio.getAbsolutePath();
			
		}else {
			System.out.println( "\n> Error al crear el directorio");
			
			return "";

		}
		
	}
	
	
	static void CrearFichero( String path, String nombreFichero ) {
		
		File fichero = new File( path + "/" + nombreFichero );
		
		boolean comodin = false;
		
		try {
			comodin = fichero.createNewFile();
			
		}catch( IOException e ){
			e.printStackTrace();
		}
		
		if( comodin ) System.out.println( "\n> Fichero creado correctamente\n" );
		
	}
	
	
	static void Elimina( File ficheroSeleccionado ) {
		
		ficheroSeleccionado.delete();
		
	}
	
	
	static void Renombra( File ficheroSeleccionado, File nombreNuevo ) {
		
		ficheroSeleccionado.renameTo( nombreNuevo );
		
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
