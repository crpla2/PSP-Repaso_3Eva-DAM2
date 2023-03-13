package UDPSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		
		//Para construir esta conexion necesitamos el puerto y el objeto que corresponde a nuestra direccion IP
		//no vasta solo con ponerlo como estring, por ello utilizamos el metodo estatico .getByName de InetAddress
		DatagramSocket dSock = new DatagramSocket(3001, InetAddress.getByName("localhost"));
		
		//El servidor envia informacion 
		//Clase datagram packet rellena con la info del cliente
		String mensaje = "Hola Mundo·Û";
		//Los datos de la conexion van referidos al que va ha recibir la informacion (En este caso coinciden
		//con los de server por que estamos en prueba local)
		DatagramPacket info = new DatagramPacket(mensaje.getBytes("UTF-8"),//Le podemos indicar la codificacion para que se envien correctamente las tildes, etc..
												 mensaje.getBytes().length, 
												 InetAddress.getByName("localhost"),
												 3000);
		
		dSock.send(info);
	}
}
