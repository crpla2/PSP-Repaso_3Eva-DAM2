package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MulticastSender {

	public static void main(String[] args) throws IOException {
		//DatagramSocket dSock = new DatagramSocket(3001, InetAddress.getByName("localhost"));
		// tambien funciona asi, sin tener que especificar el puerto y la direccion, se asignara todo automaticamente
		DatagramSocket socket = new DatagramSocket(); 
		
		InetAddress ipGrupo = InetAddress.getByName("224.0.0.1");
		String mensaje = "Hola grupo multicast";
		DatagramPacket info = new DatagramPacket(mensaje.getBytes(), 
												 mensaje.getBytes().length,
												 ipGrupo,
												 3000);
		
		socket.send(info);
		socket.close();
		

	}

}
