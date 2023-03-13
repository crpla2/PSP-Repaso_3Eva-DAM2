package FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class bajarPDF {
	public static void main(String[] args) throws SocketException, IOException {

		// Creamos el FTPCLIent
		FTPClient cliente = new FTPClient();
		// Nos conectamos
		cliente.connect("ftp.dlptest.com");
		// Nos logueamos
		boolean conectado = cliente.login("dlpuser", "rNrKYTX9g7z3RgJRmxWuGHbeu");
		//configuramos el FTP
		cliente.enterLocalPassiveMode();
		cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
		
		if (conectado) {
			System.out.println("¡Login correcto!");
			//Archivo local
			File bajada = new File("./socketsEnJava2.pdf");

			FileOutputStream is = new FileOutputStream(bajada);
			//Archivo en servidor
			if(cliente.retrieveFile("/input/socketsEnJava.pdf", is)) {
				System.out.println("¡Fichero descargado!");
			}else {
				System.out.println("¡Error en la descarga!");
			}
			is.close();
		} else {
			System.err.println("Error en el login.");
		}
		cliente.disconnect();
	}
}
