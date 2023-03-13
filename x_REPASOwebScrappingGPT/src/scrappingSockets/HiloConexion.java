package scrappingSockets;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HiloConexion extends Thread {
	// Declaramos un socket
	private Socket sock;

	// Creamos el constructor que recibe el socket del servidor
	public HiloConexion(Socket sock) {
		this.sock = sock;
	}

	// Implementamos el metodo run que se ejecutara cuando el servidor lance el hilo
	// correspondiente
	@Override
	public void run() {
		// Creamos un stream para la salida de datos
		DataOutputStream salida = null;
	try {	
			//Lo asociamos al socket
			salida = new DataOutputStream(sock.getOutputStream());
			System.out.println("[Hilo] conectando al servicio web");
			// Generamos un retardo de 2 segundos
			Thread.sleep(2000);
			System.out.println("[Hilo] procesando datos");
			//Conectamos a la web meteoclimatic le hacemos get 
			Document doc = Jsoup.connect("https://www.meteoclimatic.net/perfil/ESARA2200000022002A").get();
			
			//Seleccionamos el elemento donde se publica la temperatura
			Elements elements = doc.select(
					"#content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(1)");
			
			//Lo pasamos a un String
			String temperatura = null;
			if (elements.size() > 0) {
				temperatura = elements.get(0).text();
			}
			//Lo pasamos al stream
			salida.writeUTF(temperatura);
			System.out.println("[Hilo] datos enviados");
			
			//Cerramos el flujo de salida
			salida.close();
			
			//Cerramos el socket
			sock.close();
			
		} catch (Exception e) {
		// No se controla ningun error para que el codigo quede mas claro habría que rodear con try/catch cada elemento que genere errores y tratar individualmente
		//	la excepcion para saber donde se generan lo fallos
	}
		

	}

}
