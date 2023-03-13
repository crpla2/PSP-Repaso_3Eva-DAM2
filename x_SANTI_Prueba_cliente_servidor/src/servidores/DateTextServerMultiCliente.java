package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateTextServerMultiCliente {

	//Servidor que siempre esta a la espera de que un cliente le pida la hora, modificado para que siempre este a la escucha de todos los clientes
	//que quieran preguntar, por que esta en un while true y siempre vuelve a lanzar el servSock.accept()
	
		public static final int PORT = 3001;
		
		public static void main(String[] args) throws IOException, InterruptedException {
			ServerSocket servSock = new ServerSocket(PORT);
			
			Socket sock;
			
			while(true) {
				sock = servSock.accept();			
				DataOutputStream salida = new DataOutputStream(sock.getOutputStream());
				
				Date fecha = new Date();
				//Simulamos retardo en el calculo. (En el caso de que esto fuera real, tendriamos que usar hilos para que funcionara correctamente
				//de ahi el ejemplo que hemos hecho en el multiclienteThread, que en el caso de hacerlo con Thread, esperaran 5 segu y luego todos los clientes 
				//mostraran la fecha al mismo tiempo)
				Thread.sleep(5000);
				//
				salida.writeUTF(fecha.toString());
				
				salida.close();
				sock.close();
			}
			
		}//del main
	
}
