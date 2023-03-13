package FTP_SPCKETS;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//Creo el socket pasandole ip y puerto del server
		Socket sock= new Socket("localhost",4321);
		//Escribo el texto por teclado
		System.out.println("Escriba el mensaje:");
		Scanner s= new Scanner(System.in);
		//Guardo el texto en un string
		String  mensaje= s.nextLine();
		//lo meto en el stream
		DataOutputStream salida= new DataOutputStream(sock.getOutputStream());
		salida.writeUTF(mensaje);
		//Cierro el flujo
		salida.close();
		//Cierro el csocket
		sock.close();
	}

}
