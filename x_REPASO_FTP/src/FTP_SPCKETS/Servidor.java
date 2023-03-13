package FTP_SPCKETS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//Servidor multicliente y gestiona hilos. Servidor recibe texto del cliente. con eso crea un archivo de texto y lo sube a un servidor ftp.
public class Servidor {

	public static void main(String[] args) throws IOException {
		ServerSocket servSock= new ServerSocket(4321);
		Socket sock=null;
		System.out.println("[Servidor]Conectado, esperando mensajes");
		while(true) {
			sock=servSock.accept();
			System.out.println("[Servidor]Mensaje recibido");
			new HiloConexion(sock).start();
			
		}

	}

}
