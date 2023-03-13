package servidores;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateObjectServer {

	//Ejemplo de como enviar objetos de un cliente a un servidor(Recordar que el objeto tiene que ser serializable)
	public static void main(String[] args) throws IOException {
		ServerSocket servSock = new ServerSocket(3600);
		Socket sock = servSock.accept();
		
		ObjectOutputStream salidaObjeto = new ObjectOutputStream(sock.getOutputStream());
		salidaObjeto.writeObject(new Date());
		
		
		salidaObjeto.close();
		sock.close();
		servSock.close();

	}

}
