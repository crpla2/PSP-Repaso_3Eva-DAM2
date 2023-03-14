package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		// Creacion del server socket
		ServerSocket servSock = new ServerSocket(3333);

		// Declaracion del socket
		Socket sock = null;
		// El servidor lanza un hilo cuando recibe una peticion, mientras se queda a la
		// espera
		while (true) {
			sock = servSock.accept();
			new HiloConexion(sock).start();
		}

	}

}
