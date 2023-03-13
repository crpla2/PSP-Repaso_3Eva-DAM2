package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateTextServerMultiClienteThread {

	//Servidor que siempre esta a la espera de que un cliente le pida la hora, modificado para que cree la conexion  y lanze hilosConexion
		public static final int PORT = 3001;
		
		public static void main(String[] args) throws IOException {
			ServerSocket servSock = new ServerSocket(PORT);
			
			Socket sock;
			
			while(true) {
				sock = servSock.accept();			
				new HiloConexion(sock).start();
			}
			
		}//del main
	
}
