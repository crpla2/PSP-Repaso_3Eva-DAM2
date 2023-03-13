package ej1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Hilo implements Runnable {

	private Socket socket;
	
	public Hilo(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		
		try {
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			System.out.println("El mensaje del cliente tiene el siguiente contenido: \n\t" + dis.readUTF());

			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Mensaje recibido correctamente!");
			
			dos.close();
			dis.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
