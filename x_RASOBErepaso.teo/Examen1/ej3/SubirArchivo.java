package ej3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubirArchivo {

	public static void main(String[] args) {

		String servidorFTP = "localhost";
		int puertoFTP = 21;
		String usuarioFTP = "user";
		String passwordFTP = "user";

		FTPClient ftpClient = new FTPClient();
		FileInputStream inputStream = null;

		try {

			// Conectarse al servidor FTP
			ftpClient.connect(servidorFTP, puertoFTP);
			System.out.println(ftpClient.login(usuarioFTP, passwordFTP));

			// Activar modo binario
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// Cambiar el directorio remoto
			ftpClient.changeWorkingDirectory("/");

			// Crear un objeto File del archivo a subir
			File archivoLocal = new File("./Archivos/FTP/Profile-Picture.jpg");
//			File archivoLocal = new File("./Archivos/FTP/pruebaSubida.txt");
//			File archivoLocal = new File("./Archivos/FTP/socketsEnJava.pdf");

			// Crear un objeto FileInputStream del archivo local
			inputStream = new FileInputStream(archivoLocal);

			// Subir el archivo al servidor FTP
			boolean subido = ftpClient.storeFile("pruebaDeQueSeHaSubido.jpg", inputStream);
//			boolean subido = ftpClient.storeFile("pruebaDeQueSeHaSubido.txt", inputStream);
//			boolean subido = ftpClient.storeFile("pruebaDeQueSeHaSubido.pdf", inputStream);

			if (subido) {
				System.out.println("Archivo subido con éxito");
			} else {
				System.out.println("No se pudo subir el archivo");
			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		} finally {
			try {
				// Cerrar el input stream y la conexión FTP
				if (inputStream != null) {
					inputStream.close();
				}
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
	}

}
