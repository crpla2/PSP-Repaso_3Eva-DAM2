package FTP_SPCKETS;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

import org.apache.commons.net.ftp.FTPClient;

public class HiloConexion extends Thread {

	private Socket sock;

	public HiloConexion(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {

		try {
			//Recibo el stream de socket
			DataInputStream entrada = new DataInputStream(sock.getInputStream());
			//Lo meto en un string
			String mensaje = entrada.readUTF();
			System.out.println("[Hilo]Recibido mensaje: "+mensaje);
			//Creo el archivo que guardare en memoria
			File archivo = new File("./mensaje.txt");
			//Guardo el archivo con el texto
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
			writer.write(mensaje);
			writer.close();
			System.out.println("[Hilo]Mensaje guardado");
	////SUBIDA AL SERVIDOR FTP////
			//Creamos el FTPCLIent
			FTPClient cliente= new FTPClient();
			//Nos conectamos
			cliente.connect("ftp.dlptest.com");
			//Nos logueamos
			boolean conectado=cliente.login("dlpuser","rNrKYTX9g7z3RgJRmxWuGHbeu");
			
			  cliente.enterLocalPassiveMode();
	          cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			if(conectado) {
				System.out.println("[Hilo]Login correcto");
				File subida= new File("./mensaje.txt");
				
				BufferedInputStream is= new BufferedInputStream(new FileInputStream(subida));
				
				boolean uploaded = cliente.storeFile("/input/mensaje.txt", is);
				is.close();
		         
		         if (uploaded) {
		             System.out.println("[Hilo]El archivo fue subido correctamente");
		          }
		         cliente.logout();
			} else {
				System.err.println("Error en el login.");
			}
			cliente.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
