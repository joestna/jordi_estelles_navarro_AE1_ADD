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
					CrearCarpeta( sc );
					break;
					
				case "c":
					CrearFichero( sc );
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
	
	
	// Metodo: GetInformation
	// Parametros de entrada: scanner
	// Funcionalidad: Pide que se seleccione un fichero y muestra su informacion
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
	
	
	// Metodo: CrearCarpeta
	// Parametros de entrada: scanner
	// Funcionalidad: Pide que se introduzca un nombre que sera el nombre del directorio a crear
	static void CrearCarpeta( Scanner sc ) {
		
		System.out.print( "\nIntroduce el nombre o PATH del directorio a crear : " );		
		File directorio = new File( sc.next() );
		
		boolean comodin = directorio.mkdir();
		
		if( comodin ) {
			System.out.println( "\n> Directorio creado correctamente\n" );
			
		}else {
			System.out.println( "\n> Error al crear el directorio\n");

		}
		
	}
	
	
	// Metodo: CrearFichero
	// Parametros de entrada: scanner
	// Funcionalidad: Pide que se introduzca un nombre que sera el nombre del fichero a crear
	static void CrearFichero( Scanner sc ) {
		
		System.out.print( "\nIntroduce el nombre del fichero a crear (directorio actual) o la ruta donde crearlo : " );		
		File fichero = new File( sc.next() );
		
		boolean comodin = false;
		
		try {
			comodin = fichero.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			if( comodin ) {
				System.out.println( "\nFichero creado correctamente.\n" );
				
			}else {
				System.out.println( "\nFichero no creado\n" );
				
			}			
		}		
		
	}
	
	
	// Metodo: Elimina
	// Parametros de entrada: scanner
	// Funcionalidad: Pide que se introduzca un nombre que sera el nombre del fichero a eliminar
	static void Elimina( Scanner sc ) {
		
		System.out.print( "\nIntroduce la ruta del fichero a eliminar : " );
		File ficheroAEliminar = new File( sc.next() );
		
		boolean comodin = ficheroAEliminar.delete();
		
		if( comodin ) System.out.println( "\n> Fichero eliminado correctamente.\n" );
		
	}
	
	
	// Metodo: Renombra
	// Parametros de entrada: scanner
	// Funcionalidad: Pide que se introduzca un nombre que sera el nombre del fichero a renombrar
	static void Renombra( Scanner sc ) {
		
		System.out.print( "\nIntroduce el nombre del fichero a renombrar (directorio actual) o la ruta del fichero : " );		
		File fichero = new File( sc.next() );
		System.out.print( "\nIntroduce el nombre del fichero a RENOMBRADO (directorio actual) o la ruta del fichero : " );		
		File ficheroNuevoNombre = new File( sc.next() );
		
		boolean comodin = fichero.renameTo( ficheroNuevoNombre );
		
		if( comodin ) System.out.println( "\n> Fichero renombrado correctamente.\n" );
		
	}
	
	
	// Metodo: FicheroODirectorio
	// Parametros de entrada: File
	// Funcionalidad: Indica si el fichero pasado por parametro es un fichero o un directorio
	static String FicheroODirectorio( File fichero ) {
		
		if( fichero.isFile() == true ) {
			return "fichero";
			
		}else if( fichero.isDirectory() ) {
			return "directorio";
			
		}else {
			return "NULL";
		}
		
	}

	
	// Metodo: ListarFicherosEnDirectorio
	// Parametros de entrada: File[], File
	// Funcionalidad: Lista los ficheros que contiene el directorio y muestra su informacion
	static void ListarFicherosEnDirectorio( File[] ficheros, File fichero ) {
		
		System.out.println( "\n---\nCantidad de elementos en directorio : " + ficheros.length + "\n---\n" );
		
		for ( File ficheroHijo: ficheros) {
			
			System.out.println( "Espacio libre en el directorio : " + ficheroHijo.getFreeSpace() + "\n" +
		                        "Espacio ocupado en el directorio : " + EspacioTotalMenosLibre( fichero.getTotalSpace(), fichero.getFreeSpace() ) + "\n" +
		                        "Espacio total del directorio : " + ficheroHijo.getTotalSpace() + "\n" );
		}
		
		System.out.println( "\n---\n");
		
	}
	
	
	// Metodo: milisegundosAFecha
	// Parametros de entrada: long
	// Funcionalidad: Transforma el parametro pasado en formato fecha del calendario Gregoriano
	static Date milisegundosAFecha( Long milisegundos ) {
		
		Date fecha = new Date( milisegundos );
		Calendar calendario = new GregorianCalendar();
		calendario.setTime( fecha );
		
		return calendario.getTime();
		
	}
	
	
	// Metodo: EspacioTotalMenosLibre
	// Parametros de entrada: long, long
	// Funcionalidad: Muestra la informacion disponible en un directorio
	static int EspacioTotalMenosLibre( Long espacioTotal, Long espacioLibre ) {
		
		return espacioTotal.intValue() - espacioLibre.intValue();
		
	}
	
}
