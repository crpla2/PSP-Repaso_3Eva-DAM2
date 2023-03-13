package FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubirPDF {
	public static void main(String[] args) throws SocketException, IOException {
		//Creamos el FTPCLIent
				FTPClient cliente= new FTPClient();
				//Nos conectamos
				cliente.connect("ftp.dlptest.com");
				//Nos logueamos
				boolean conectado=cliente.login("dlpuser","rNrKYTX9g7z3RgJRmxWuGHbeu");
				
				  cliente.enterLocalPassiveMode();
		          cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
		if (conectado) {
			System.out.println("¡Login correcto!");
			File archivoSubida = new File("./socketsEnJava.pdf");
			
			FileInputStream is = new FileInputStream(archivoSubida);
			OutputStream os = cliente.storeFileStream("/input/socketsEnJava.pdf");
			//preparar el buffer
			byte[] buffer = new byte[4096];
			int bytesLeidos = 0;
			//hacer un bucle para enviar los bytes del archivo
			while ( (bytesLeidos = is.read(buffer)) != -1 ) {
				os.write(buffer, 0, bytesLeidos);
			}
			is.close();
			os.close();
			if(cliente.completePendingCommand()) {
				System.out.println("Archivo Subido correctamente.");
			}else {
				System.err.println("Error!");
			}
		} else {
			System.err.println("Error en el login.");
		}
		cliente.disconnect();
		
	}
}
