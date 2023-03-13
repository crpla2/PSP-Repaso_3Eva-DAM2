package ej1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 implements Runnable {

	private ServerSocket serverSocket;

	public Servidor2(int puerto) throws IOException {
		this.serverSocket = new ServerSocket(puerto);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Esperando un cliente...");
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado con ip " + socket.getInetAddress());
				new Thread(new Hilo2(socket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		Servidor2 servidor = new Servidor2(3000);
		new Thread(servidor).start();
	}

}
