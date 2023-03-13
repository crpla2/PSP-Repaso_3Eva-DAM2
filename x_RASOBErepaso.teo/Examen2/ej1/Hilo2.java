package ej1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Hilo2 implements Runnable {

	private Socket socket;

	private String servidorFTP = "localhost";
	private int puertoFTP = 21;
	private String usuarioFTP = "user";
	private String passwordFTP = "user";

	public Hilo2(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {

			// Recogemos la jornada del cliente
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			int jornada = dis.readInt();

			// Empezamos WebScrapping
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(URI.create("https://kingsleague.pro/")).GET().build();

			HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

			Document doc = Jsoup.parse(res.body());

			Elements resultadosJornadas = doc.select("#page\\#" + (jornada + 13) + " > div > table > tbody tr");

			// Creamos y escribimos el archivo de la jornada especificada

			String nombreArchivo = "jornada" + jornada + "KL.txt";
			String rutaArchivo = "./Archivos/FTP/" + nombreArchivo;

			File archivo = new File(rutaArchivo);
			if (!archivo.exists()) {
				FileWriter writer = new FileWriter(archivo);
				String texto = "Resultados jornada " + jornada + " KL:\n--------------------------------\n";
				for (Element element : resultadosJornadas) {
					texto += element.text() + "\n";
				}
				writer.write(texto);
				writer.close();
				System.out.println("Archivo creado correctamente!");
			} else {
				System.out.println("El archivo ya existe");
			}

			// Subimos el archivo al servidor FTP
			subirArchivoFTP(rutaArchivo, nombreArchivo);
			System.out.println("Archivo enviado correctamente!");

			// Descargamos el archivo desde el servidor FTP
			bajarArchivoFTP(nombreArchivo);
			System.out.println("Archivo descargado correctamente!");

			// Recogemos el contenido del archivo y lo almacenamos en un String
			BufferedReader br = new BufferedReader(
					new FileReader(new File("./Archivos/FTP/descarga-" + nombreArchivo)));

			String linea;
			String textoArchivo = "";
			while ((linea = br.readLine()) != null) {
				textoArchivo += linea + "\n";
			}

			// Mandamos el resultado al cliente para que pueda visualizarlo
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(textoArchivo);

		} catch (IOException e) {
			System.err.println("Error salida o entreada " + e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("Error en el WebScarping " + e.getMessage());
		}

	}

	public void subirArchivoFTP(String rutaArchivo, String nombreArchivoConExtension) {

		FTPClient ftpClient = new FTPClient();
		FileInputStream inputStream = null;

		try {

			ftpClient.connect(servidorFTP, puertoFTP);
			
			ftpClient.login(usuarioFTP, passwordFTP);

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			ftpClient.changeWorkingDirectory("/");

			File archivoLocal = new File(rutaArchivo);

			inputStream = new FileInputStream(archivoLocal);

			boolean subido = ftpClient.storeFile(nombreArchivoConExtension, inputStream);

			if (subido) {
				System.out.println("Archivo subido con éxito");
			} else {
				System.out.println("No se pudo subir el archivo");
			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		} finally {
			try {
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

	public void bajarArchivoFTP(String nombreArchivoExtension) {
		String remoteFilePath = "/" + nombreArchivoExtension;
		String localFilePath = "./Archivos/FTP/descarga-" + nombreArchivoExtension;

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(servidorFTP, puertoFTP);
			ftpClient.login(usuarioFTP, passwordFTP);

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
