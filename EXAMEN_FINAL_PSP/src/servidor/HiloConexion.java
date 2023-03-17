package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import modelo.Resultado;

public class HiloConexion extends Thread {

	Socket sock;

	public HiloConexion(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		
			// Abrir dos flujos de entrada y almacenarlos en dos strings
			DataInputStream entrada = null;
			String url = null,selector = null;
			try {
				entrada = new DataInputStream(sock.getInputStream());
				url = entrada.readUTF();
			//MAL	//entrada = new DataInputStream(sock.getInputStream());
				selector = entrada.readUTF();
			} catch (IOException e) {
				System.err.println("Error en el flujo de entrada");
			}

			/// WEB SCRAPPING///
			// Recuperar la informacion de la página
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException|IllegalArgumentException e) {
				System.err.println("Error en la url");
			}
			// ELEMENTOS(buscar por ruta css)
			Elements elementos = null;
			String elementString = null;
			try {
				elementos = doc.select(selector);
				elementString=elementos.get(0).text();
			} catch (NullPointerException|IndexOutOfBoundsException e1) {
				System.err.println("Sin resultados en la busqueda");
			}
				
			String elementString2;
			// Creacion del objeto resultado
			if(elementString==null) {
				elementString2="Sin resultados";
			}else {
				elementString2=elementString;
			}
			Resultado resultado = new Resultado(url, selector, new Date(),elementString2 );
			
			// Creacion del flujo de salida y  Envio el objeto

			ObjectOutputStream salidaObjeto = null;
			try {
				salidaObjeto = new ObjectOutputStream(sock.getOutputStream());
				salidaObjeto.writeObject(resultado);
			} catch (IOException e) {
				System.err.println("Error en el flujo de salida");
			}
			
			// Imprimir el primer elemento
			try {
				Element element = null;
				if (elementos.size() > 0) {
					element = elementos.get(0);
				}
				System.out.println(element.text());
			} catch (Exception e1) {
				System.err.println("Nada que imprimir");
			}
			
			try {
			// cierro todo
			entrada.close();
			salidaObjeto.close();
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
