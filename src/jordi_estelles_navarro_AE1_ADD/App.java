package jordi_estelles_navarro_AE1_ADD;

import java.io.*;

public class App {

	public static void main(String[] args) {
		
		File fichero = new File( "README.txt" );

	}

	public static void GetInformacion( File fichero) {
		
		File[] ficherosEnDirectorio = new File[]; //Seria un arraylist??
		
		System.out.println( "Nombre del " + FicheroODirectorio( fichero ) + " : \n" + fichero.getName() +
			                "Ubicacion del " + FicheroODirectorio( fichero ) + " : \n" + fichero.getAbsolutePath() +
			                "Fecha de " + FicheroODirectorio( fichero ) + " : \n" + fichero.lastModified() );
		
		if( FicheroODirectorio( fichero ) == "fichero" ) {
			System.out.println( "Tamano del fichero : " + fichero.getTotalSpace() );
			
		}else {
			ListarFicherosEnDirectorio( ficherosEnDirectorio, fichero )
	
		}
		
	}
	
	public static void CrearCarpeta() {
		
	}
	
	public static void CrearFichero() {
		
	}
	
	public static void Elimina() {
		
	}
	
	public static void Renombra() {
		
	}
	
	public static String FicheroODirectorio( File fichero ) {
		
		if( fichero.isFile() == true ) return "directorio"; else return "fichero";
		
	}
	
	public static void ListarFicherosEnDirectorio( File[] ficheros, File fichero ) {
		
		System.out.println( "\n---\nCantidad de ficheros en directorio : \n---\n" + ficheros.length );
		
		for ( File ficheroHijo: ficheros) {
			System.out.println( "Espacio libre en el directorio : " + fichero.getFreeSpace() + "\n" +
		                        "Espacio disponible en el directorio : " + fichero.getTotalSpace() + "\n" +
		                        "Espacio total del directorio : " + fichero.getTotalSpace() + "\n" );
	}
		
		System.out.println( "\n---\n" + ficheros.length );
		
}
