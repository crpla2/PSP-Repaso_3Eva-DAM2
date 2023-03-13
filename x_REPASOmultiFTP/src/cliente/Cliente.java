package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//Creo el socket
		Socket sock= new Socket("localhost", 3333);
		//Abro un flujo de teclado
		Scanner s= new Scanner(System.in);
		//Recojo la pregunta
		System.out.println("Introduzca su Pregunta:");
		String pregunta= s.nextLine();
		//Recojo la respuesta
		System.out.println("Introduzca su Respuesta:");
		String respuesta= s.nextLine();
		//Abro un flujo de salida y mando  la pregunta
		DataOutputStream salida= new DataOutputStream(sock.getOutputStream());
		salida.writeUTF(pregunta);
		//Abro otro flujo de salida y mando la respuesta
		salida= new DataOutputStream(sock.getOutputStream());
		salida.writeUTF(respuesta);
		//Abro un flujo de entrada y recibo la lista y la imprimo por pantalla
		DataInputStream entrada= new DataInputStream(sock.getInputStream());
		System.out.println(entrada.readUTF());
		//Cierro todo
		salida.close();
		entrada.close();
		sock.close();
	}

}
