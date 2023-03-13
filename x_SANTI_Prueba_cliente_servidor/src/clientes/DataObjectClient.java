package clientes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class DataObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket sock = new Socket("localhost", 3600);
		ObjectInputStream entradaObjeto = new ObjectInputStream(sock.getInputStream());
		
		Date fecha = (Date) entradaObjeto.readObject();
		Date fechaActual = new Date();
		
		
		//Asi comprobamos en milisegundos, el tiempo que ha costado hacer todo este proceso, en milisegundos
		System.out.println("He recibido: "+fecha+"\nSon las: "+fechaActual+"\nDiferencia: "+ (fechaActual.getTime() - fecha.getTime())+"ms");

	}

}
