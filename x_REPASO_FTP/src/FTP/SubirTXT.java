package FTP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class SubirTXT {

	public static void main(String[] args) throws SocketException, IOException {
		//Creamos el FTPCLIent
		FTPClient cliente= new FTPClient();
		//Nos conectamos
		cliente.connect("ftp.dlptest.com");
		//Nos logueamos
		boolean conectado=cliente.login("dlpuser","rNrKYTX9g7z3RgJRmxWuGHbeu");
		
		  cliente.enterLocalPassiveMode();
          cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
		
		if(conectado) {
			System.out.println("Login correcto");
			File subida= new File("./prueba.txt");
			
			BufferedInputStream is= new BufferedInputStream(new FileInputStream(subida));
			
			boolean uploaded = cliente.storeFile("/input/prueba.txt", is);
			is.close();
	         
	         if (uploaded) {
	             System.out.println("El archivo fue subido correctamente");
	          }
	         cliente.logout();
		} else {
			System.err.println("Error en el login.");
		}
		cliente.disconnect();
		
	}

}
