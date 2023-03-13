package ej1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	final static int PUERTO = 2000;
	final static String IP = "localhost";
	
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket(IP, PUERTO);
		
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		System.out.println("Escribe el mensaje que quieras mandar al servidor:");
		dos.writeUTF(s.nextLine());
		
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		System.out.println(dis.readUTF());
		
		dis.close();
		dos.close();
		
	}
	
}
