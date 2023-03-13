package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import model.Pregunta;

public class HiloFTP extends Thread {
	private List<Pregunta> preguntas;

	public HiloFTP(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	@Override
	public void run() {
		try {
			//cada 120 segundos ehjecuta lo que hay debajo del SLEEP
			while (true) {
				Thread.sleep(120000);
				//Sacar la hora de un (NEW Date()) 
				Date fecha = new Date();
				SimpleDateFormat formatoHora = new SimpleDateFormat("HH-mm-ss");
				String hora = formatoHora.format(fecha);
				//Recorro la lista y meto todo lo que hay en un string para pasarlo a un txt
				String lista = "";
				for (Pregunta pregunta2 : preguntas) {
					lista += pregunta2.getPregunta() + "\n" + pregunta2.getRespuesta() + "\n------------------------\n";
				}
				//((CrEACION DEL TXT))
				// Creo el archivo que guardare en memoria
				File archivo = new File("./listapreguntas_" + hora + ".txt");
				// Guardo el archivo con el texto
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
				writer.write(lista);
				writer.close();
				System.out.println("[Hilo]txt guardado");

				//// SUBIDA AL SERVIDOR FTP////
				// Creamos el FTPCLIent
				FTPClient cliente = new FTPClient();
				// Nos conectamos
				cliente.connect("ftp.dlptest.com");
				// Nos logueamos
				boolean conectado = cliente.login("dlpuser", "rNrKYTX9g7z3RgJRmxWuGHbeu");
				//lo configuramos
				cliente.enterLocalPassiveMode();
				cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
				//Si se ha realizado la conexion
				if (conectado) {
					System.out.println("[Hilo]Login correcto");
					//Creo un file para subir a l servidor(Podria utilizar el que he creado antes...)
					File subida = archivo;
					//Lo meto en un buffer
					BufferedInputStream is = new BufferedInputStream(new FileInputStream(subida));
					//lo subo al FTP  y guardo el resultado que es booleano
					boolean uploaded = cliente.storeFile("/input/" + archivo.getName(), is);
					//cierro el buffer
					is.close();

					if (uploaded) {
						System.out.println("[HiloFTP]El archivo fue subido correctamente");
					}
					cliente.logout();
				} else {
					System.err.println("[HiloFTP]Error en el login.");
				}
				cliente.disconnect();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
