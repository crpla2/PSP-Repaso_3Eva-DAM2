package repasoAntesDeExamen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	
	public static void main(String[] args) throws IOException {
		
		ServerSocket servsock = new ServerSocket(4321);
		Socket sock = null;
		
		while(true) {
			 sock = servsock.accept();
			 //Lo lanzamos con Thread por que estamos usando runnable
			 new Thread( new HiloConexion(sock)).start();
		}
		
		
		
		
		
	}
	
}
