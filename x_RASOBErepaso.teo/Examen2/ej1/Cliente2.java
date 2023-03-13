package ej1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente2 {

	private final static int PUERTO = 3000;
	private final static String SERVIDOR = "localhost";
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket(SERVIDOR, PUERTO);

		// Mandamos la jornada que quiere el usuario
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		int jornada = 0;
		while (jornada < 1 || jornada > 11) {
			System.out.print("Indica la jornada que quieras visualizar de la KingsLeague: ");
			jornada = s.nextInt();
		}
		dos.writeInt(jornada);
		System.out.println("Jornada enviada...");

		// Leemos la jornada recibida
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		System.out.println("\n" + dis.readUTF());

	}

}
