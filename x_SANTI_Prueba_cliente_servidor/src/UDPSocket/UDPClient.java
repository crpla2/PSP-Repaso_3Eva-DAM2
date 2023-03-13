package UDPSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	

	public static void main(String[] args) throws IOException {

		// Para construir esta conexion necesitamos el puerto y el objeto que corresponde a nuestra direccion IP
		// no vasta solo con ponerlo como estring, por ello utilizamos el metodo estatico .getByName de InetAddress
		DatagramSocket dSock = new DatagramSocket(3000, InetAddress.getByName("localhost"));
		
		//Hay que indicar una estimacion de lo que voy a recibir y la longitud, casi siemppre coincidira el tamaño.
		//(De alguna manera habrá que cuantificar esto en algun lado
		//para que no se escriba al tuntun, sino que tenga sentiido)
		DatagramPacket info = new DatagramPacket(new byte[1000], 1000);
		//Ahora marcaremos que recibimos algo, con la info del datagram
		dSock.receive(info);
		
		//El datagram se ampliara el info del cliente con informacion del server
		System.out.println("Recibido de: " +info.getAddress());
		System.out.println("Por el puerto: "+info.getPort());
		//asi es una manera de construir un string, a traves de bytes, para poder sacar la codificacion correcta hay que indicarle
		//tambien, como esta codificado el texto
		System.out.println("Datos recibidos: "+new String(info.getData(),"UTF-8")); 
		
		
	}
}
