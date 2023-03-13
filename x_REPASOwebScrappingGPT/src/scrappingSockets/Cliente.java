package scrappingSockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//Creo el socket pasandole la direccion del servidor y el puerto
		Socket sock = new Socket("localhost",4321);
		
		System.out.println("["+ sock.getLocalPort()+"]Conectando con el servidor "+ sock.getInetAddress());
		//Creo un stream para recibir los datos
		DataInputStream entrada= new DataInputStream(sock.getInputStream());
		
		System.out.println("Recibiendo datos");
		//Imprimo los datos recibidos del servidor
		System.out.println(entrada.readUTF());
		
		//Cierro el flujo
		entrada.close();
		
		//Cierro el socket
		sock.close();
	}

}
