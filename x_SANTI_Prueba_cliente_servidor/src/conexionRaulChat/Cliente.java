package conexionRaulChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try {
			//Creo socket especificando direccion a la que me quiero conectar y el puerto. Si no hay ningun servidor al que conectarse, saltara
			//un error al intentar crear el constructor
			//Socket socket = new Socket("192.168.101.5", 2700);
			Socket socket = new Socket("localhost", 2700);

			//Cuando el constructor vuelve es que se ha conectado y sigue leyendo este codigo
			System.out.println("[Cliente] �Conectado!");
			System.out.println("[Cliente]: Yo soy " + socket.getLocalAddress());
			System.out.println("[Cliente]: Yo estoy en el puerto " + socket.getLocalPort());
			System.out.println("[Cliente]: Yo estoy conectado con " + socket.getInetAddress());
			System.out.println("[Cliente]: Yo estoy conectado con el puerto " + socket.getPort());
			
			//Ahora lanzamos el hilo que esta a la escucha del teclado (Hilo tecladp)
			//Este hilo se encargara de que tanto en el cliente como en el servidor, se abrira un flujo de datos de salida
			//que enviara al socket que este en cada clase (cliente y servidor), todo lo que se escriba por consola.
			HiloTeclado ht = new HiloTeclado(socket);
			ht.start();
			
			//Antes de cerrar el cliente enviamos un mensaje al servidor, el servidor ya esta preparado para estar a la escucha de un
			//mensaje. Lo metemos en un while true para que sea como un chat
		//	Scanner s = new Scanner(System.in);
		/*	while(true) {
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(s.nextLine());
			}*/
			
			//Creamos esta parte de codigo para que funcione con (HiloTeclado)
			DataInputStream dis = new DataInputStream(socket.getInputStream()); //Miramos si el servidor nos ha enviado algo con 
			String mensaje = dis.readUTF();
			while(!mensaje.equals("Q")) {
				System.out.println("[Cliente] "+mensaje);
				try {
					System.out.println("a");
					mensaje = dis.readUTF();
				}catch (EOFException eofe) {
					System.out.println("El servidor ha cerrado sesion");
					break;
				}
			}
			
			socket.close(); //Lo comentamos por que eclipse detecta el while true y nunca llegara a esta parte de codigo
			dis.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("No he podido localizar esa direccion IP");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		

	}
	
	
	
