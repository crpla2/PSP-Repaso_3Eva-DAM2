package conexionRaulChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HiloTeclado extends Thread{
	//Hilo que esta pendiente del teclado para enviar el mensaje, con esto conseguiremos un chat en linea de verdad, antes con el escaner
	//como lo teniamos implementado, siempre tenia que escribir uno sea cliente o servidor y cuando lo haga uno, podria escribir el otro, ya que
	//habia que esperar el nextLine del escaner y tanto el cliente como el servidor tenian que esperar ese mensaje para poder escribir ellos otro.
	//Creando un hilo que cada clase implementara, ahora ya pueden escribir independientemente el uno del otro.
	
	//En resumen, este hilo crea un flujo de salida de datos para cada socket, tanto del cliente como del servidor. Y se queda a la espera con el while 
	//de que escribas la siguiente linea, asi luego el cliente y el servidor solo se preocupan de ver si hay algun dato que se les ha enviado (socket.getInputStream)
	
	Socket socket;
	
	public HiloTeclado(Socket socket) {
		super();
		this.socket = socket;
	}


	@Override
	public void run() {
		//Creo el teclado
		Scanner scanner = new Scanner(System.in);
		DataOutputStream dos = null;
		try {
			//configuro la salida del socket
			 dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {System.out.println("Error capturando la salida del socket");}
		//leo el primer mensaje desde el teclado
		String mensaje =  scanner.nextLine();
		//bucle principal: Envio por el socket y me quedo leyendo de teclado
		while(!mensaje.equals("Q")) {
			try {
				System.out.println("b");
				dos.writeUTF(mensaje);
			} catch (IOException e) {System.out.println("Error enviando el mensaje");}
			mensaje =  scanner.nextLine();
		}
		System.out.println("Recibido mensaje de salida");
		try {
			socket.close();
		} catch (IOException e) {System.out.println("Error cerrando el socket");}
		try {
			dos.close();
		} catch (IOException e) {System.out.println("Error cerrando el dos");}
	
	}
}
