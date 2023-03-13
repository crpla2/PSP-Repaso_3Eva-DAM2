package conexionRaulChat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket servSock = new ServerSocket(2700);
		
		System.out.println("[Server] Esperando conexion...");
		Socket socket = servSock.accept(); //Se quedara a la espera hasta que un cliente se conecte y entonces seguira con el codigo de abajo
		System.out.println("[Server] �Conectado!");
		//Ahora lanzamos el hilo que esta a la escucha del teclado
		HiloTeclado ht = new HiloTeclado(socket);
		ht.start();

		System.out.println("[Server]: Yo soy " + socket.getLocalAddress());
		System.out.println("[Server]: Yo estoy en el puerto " + socket.getLocalPort());
		System.out.println("[Server]: Yo estoy conectado con " + socket.getInetAddress());
		System.out.println("[Server]: Yo estoy conectado con el puerto " + socket.getPort());

		//Antes de cerrar el socket vamos a leer cualquier mnensaje que el cliente nos haya enviado, guardandolo en un flujo de datos
		//DataInputStream
		DataInputStream dis = new DataInputStream(socket.getInputStream());//Le indicamos que recoja en un DataInputStream, todo lo que el socket Cliente pueda estar enviando mediante el metodo getInputStream
		String mensaje = dis.readUTF();
		while(!mensaje.equals("Q")) {
			System.out.println("[Server] "+mensaje);
			try {
				System.out.println("c");
				mensaje = dis.readUTF();
			}catch (EOFException eofe) {
				System.out.println("El cliente ha cerrado sesion");
				break;
			}
		}
		
		System.out.println("[Server] Sesion finalizada");
		
		dis.close();
		socket.close();
		servSock.close();
		ht.join();

	}

}
