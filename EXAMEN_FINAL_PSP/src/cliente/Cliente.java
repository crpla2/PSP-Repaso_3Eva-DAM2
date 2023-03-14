package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


import modelo.Resultado;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		//Creo el socket
		Socket sock= new Socket("localhost", 3333);
		
		//Abro un flujo de teclado
		Scanner s= new Scanner(System.in);
		//Recojo la url
		System.out.println("Introduzca la URL:");
		String url= s.nextLine();
		//Recojo el selector
		System.out.println("Introduzca el selector:");
		String selector= s.nextLine();
		
		//Abro un flujo de salida y mando  la url
		DataOutputStream salida= new DataOutputStream(sock.getOutputStream());
		salida.writeUTF(url);
		//Abro otro flujo de salida y mando el selector
		salida= new DataOutputStream(sock.getOutputStream());
		salida.writeUTF(selector);
				
		//Abro un flujo de entrada
		ObjectInputStream entradaObject = 
				new ObjectInputStream(sock.getInputStream());
		//Creo un objeto resultado y lo imprimo
		Resultado resultado = (Resultado) entradaObject.readObject();
		System.out.println(resultado);
		//Cierro todo
		salida.close();
		entradaObject.close();
		sock.close();
	}
	
//url:https://www.meteoclimatic.net/perfil/ESARA2200000022002A
//selector:#content > table:nth-child(3) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) 
}
