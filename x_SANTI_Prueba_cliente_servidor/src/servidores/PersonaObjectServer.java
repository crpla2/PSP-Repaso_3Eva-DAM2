package servidores;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class PersonaObjectServer {

	//Ejemplo de como enviar objetos de un cliente a un servidor(Recordar que el objeto tiene que ser serializable)
	public static void main(String[] args) throws IOException {
		ServerSocket servSock = new ServerSocket(3600);
		Socket sock = servSock.accept();
		
		ObjectOutputStream salidaObjeto = new ObjectOutputStream(sock.getOutputStream());
		salidaObjeto.writeObject(new model.Persona("Juan", 15)); //esto del model.Persona es por que intentamos que sea la misma persona para el cliente
																//y para el servidor, compartiendo la misma clase Persona del paquete model
		
		salidaObjeto.close();
		sock.close();
		servSock.close();

	}

}
