package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateTextServer {

	//Servidor que siempre esta a la espera de que un cliente le pida la hora luego se desconecta
	
		public static final int PORT = 3001;
		
		public static void main(String[] args) throws IOException {
			ServerSocket servSock = new ServerSocket(PORT);
			
			Socket sock = servSock.accept();
			
			DataOutputStream salida = new DataOutputStream(sock.getOutputStream());
			
			Date fecha = new Date();
			salida.writeUTF(fecha.toString());
			
			salida.close();
			sock.close();
			servSock.close();
			
			
		}//del main
	
}
