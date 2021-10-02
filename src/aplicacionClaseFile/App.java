package aplicacionClaseFile;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner( System.in );
		String output = "";
		
		System.out.println( "\n\t*** BIENVENIDO! Que desea hacer : ***\n" );
		
		while( !output.equals( "exit" ) ) {
			System.out.println( "a. Mostrar informacion de un fichero\n" +
							    "b. Crear un directorio\n" +
                                "c. Crear un fichero\n" +
					            "d. Renombrar un fichero\n" +
                                "e. Eliminar un fichero\n" +
					
					            "\nIntroduzca \" exit \" para salir de la aplicacion.\n" );
			
			System.out.print( "> " );
			output = sc.next();
			
			switch( output ) {
				case "a":
					GetInformation( sc );
					break;
				case "b":
					File directorio = CrearCarpeta( sc );
					break;
				case "c":
					File ficheroCreado = CrearFichero( sc );
					break;
				case "d":
					Renombra( sc );
					break;
				case "e":
					Elimina( sc );
					break;
				case "exit":
					break;
				default:
					System.out.println( "\n> Nada seleccionado\n" );
					break;
			}
			
		}		
		
		System.out.print( "\n\nGRACIAS. Vuelva pronto!!!\n" );	
		
		sc.close();		

	}
	

	public static void GetInformation( Scanner sc ) {
		
		System.out.print( "\nIntroduce el nombre del fichero a seleccionar : " );		
		File fichero = new File( sc.next() );
		
		System.out.print( "\nNombre del " + FicheroODirectorio( fichero ) + " : " + fichero.getName() + "\n" +
			              "Ubicacion del " + FicheroODirectorio( fichero ) + " : " + fichero.getAbsolutePath() + "\n" +
			              "Fecha de " + FicheroODirectorio( fichero ) + " : " + milisegundosAFecha( fichero.lastModified() ) + "\n" );
		
		if( FicheroODirectorio( fichero ) == "fichero" ) {
			System.out.println( "Tamano del fichero : " + fichero.length() );
			
		}else if( FicheroODirectorio( fichero ) == "directorio" ){
			ListarFicherosEnDirectorio( fichero.listFiles(), fichero );
	
		}else {
			System.out.println( "\n> El fichero seleccionado no existe.\n" );
		}
		
	}
	
	
	static File CrearCarpeta( Scanner sc ) {
		
		System.out.print( "\nIntroduce el nombre o PATH del directorio a crear : " );		
		File directorio = new File( sc.next() );
		
		boolean comodin = directorio.mkdir();
		
		if( comodin ) {
			System.out.println( "\n> Directorio creado correctamente\n" );
			
		}else {
			System.out.println( "\n> Error al crear el directorio\n");

		}
		
		return directorio;
		
	}
	
	
	static File CrearFichero( Scanner sc ) {
		
		System.out.print( "\nIntroduce el nombre del fichero a crear (directorio actual) o la ruta donde crearlo : " );		
		File fichero = new File( sc.next() );
		
		boolean comodin = false;
		
		try {
			comodin = fichero.createNewFile();
			
		}catch( IOException e ){
			e.printStackTrace();
			
		}
		
		if( comodin ) {
			System.out.println( "\n> Fichero creado correctamente\n" );
			
		}else {
			System.out.println( "\nFichero no creado.\n");
		}
		
		return fichero;	
		
	}
	
	
	static void Elimina( Scanner sc ) {
		
		System.out.print( "\nIntroduce la ruta del fichero a eliminar : " );
		File ficheroAEliminar = new File( sc.next() );
		
		boolean comodin = ficheroAEliminar.delete();
		
		if( comodin ) System.out.println( "\n> Fichero eliminado correctamente.\n" );
		
	}
	
	
	static void Renombra( Scanner sc ) {
		
		System.out.print( "\nIntroduce la ruta del fichero a renombrar : " );		
		File fichero = new File( sc.next() );
		System.out.print( "\nIntroduce la ruta del fichero a RENOMBRADO : " );		
		File ficheroNuevoNombre = new File( sc.next() );
		
		boolean comodin = fichero.renameTo( ficheroNuevoNombre );
		
		if( comodin ) System.out.println( "\n> Fichero renombrado correctamente.\n" );
		
	}
	
	
	static String FicheroODirectorio( File fichero ) {
		
		if( fichero.isFile() == true ) {
			return "fichero";
			
		}else if( fichero.isDirectory() ) {
			return "directorio";
			
		}else {
			return "NULL";
		}
		
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
