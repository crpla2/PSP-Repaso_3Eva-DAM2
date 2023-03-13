package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.Pregunta;

public class Servidor {

	//Lista que comparten todos los hilos que se creen. Se pasa como parametro a los hilos al crearse para que todos tengan la misma
	private static List<Pregunta> preguntas = new ArrayList<>();
	public static void main(String[] args) throws IOException, InterruptedException {
		//Creacion del server socket
		ServerSocket servSock= new ServerSocket(3333);
		//Creacion del hilo que va a subir los archivos al FTP(hay que ponerlo aqui porque si lo ponemos despues del while (true) nunca se ejecutará
		new HiloFTP(preguntas).start();
		//Declaracion del socket
		Socket sock=null;
		//El servidor lanza un hilo cuando recibe una peticion, mientras se queda a la espera
		while (true) {
			sock= servSock.accept();
			new HiloPregunton(sock, preguntas).start();
		}
		
	
	}

}
