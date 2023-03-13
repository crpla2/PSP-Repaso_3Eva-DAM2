package ej3;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;

public class BajarArchivo {

	public static void main(String[] args) {
		String server = "localhost";
		int port = 21;
		String user = "user";
		String pass = "user";
		String remoteFilePath = "/pruebaDeQueSeHaSubido.jpg";
		String localFilePath = "./Archivos/FTP/pruebaBajada.jpg";

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);

			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFilePath));
			boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
			outputStream.close();

			if (success) {
				System.out.println("El archivo ha sido descargado correctamente.");
			}
		} catch (IOException ex) {
			System.out.println("Error al descargar el archivo: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
