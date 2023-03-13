package clientes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PersonaObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket sock = new Socket("localhost", 3600);
		ObjectInputStream entradaObjeto = new ObjectInputStream(sock.getInputStream());
		
		model.Persona persona = (model.Persona) entradaObjeto.readObject();
				
		persona.toString();

	}

}
