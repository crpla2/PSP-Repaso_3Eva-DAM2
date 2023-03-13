package scrappingSockets;
/*Cliente se conecta a un servidor para saber la temperatura en huesca desde una web, el servidor recoge los datos y se los devuelve al cliente.*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException{
		//Creamos el serversocket pasandole el puerto en el que va a estar a la escucha
		ServerSocket servSock = new ServerSocket(4321);
		//Declaramos el socket
		Socket sock;
		//Hacemos un bucle que ira lanzando hilos cuando reciba un cocket de un cliente
		while (true) {
			sock= servSock.accept();
			new HiloConexion(sock).start();
			System.out.println("[Servidor] Peticion recibida de "+sock.getInetAddress()+"/"+sock.getPort());
		}
		
		
	}
}
