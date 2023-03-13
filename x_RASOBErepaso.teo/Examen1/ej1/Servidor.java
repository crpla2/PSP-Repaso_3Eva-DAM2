package ej1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {

	private ServerSocket serverSocket;

	public Servidor(int puerto) throws IOException {
		this.serverSocket = new ServerSocket(puerto);
	}

	public void run() {
		Socket socket = null;
		while (true) {
			try {
				System.out.println("Esperando a un cliente...");
				socket = serverSocket.accept();
				System.out.println("Cliente conectado con ip " + socket.getInetAddress());
				new Thread(new Hilo(socket)).start();
//				hilo.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Servidor2 serverSocket = new Servidor2(2000);
		new Thread(serverSocket).start(); // Esto lo hacemos para crear un nuevo Hilo, en vez de utilizar el mismo
//		serverSocket.run(); 	Esto no lo utilizamos ya que estamos utilizando el mismo hilo en vez de estar usando uno nuevo
	}

}
