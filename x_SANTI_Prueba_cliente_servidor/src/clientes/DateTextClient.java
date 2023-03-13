package clientes;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import servidores.DateTextServer;

public class DateTextClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//Forma sin timeout
		//Socket sock = new Socket("localhost", DateTextServer.PORT);
		
		//De esta manera, declarando el socket por partes con socketAddress, conseguimos ponerle un timeout, que es simplemente ponerle una regla 
		//de que si cuando este cliente se quiera conectar, espera más de 6 seg una respuesta del servidor, lanzara un error 
		Socket sock = new Socket();
		SocketAddress sockAddr = new InetSocketAddress("192.168.101.100", servidores.DateTextServer.PORT);
		sock.connect(sockAddr, 6000);
		
		DataInputStream entrada = new DataInputStream(sock.getInputStream());
		
		String fecha = entrada.readUTF();
		
		System.out.println(fecha);
		entrada.close();
		sock.close();
		
	}//del main
	
}
