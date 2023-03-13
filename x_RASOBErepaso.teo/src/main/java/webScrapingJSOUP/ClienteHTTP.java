package webScrapingJSOUP;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ClienteHTTP {
	// Programa para hacer peticiones a una web
	// Tipos de peticiones a una web (get, post, delete, put, ...)

	public static void main(String[] args) throws IOException, InterruptedException {

		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create("https://www.meteoclimatic.net/perfil/ESARA2200000022002A?screen_width=1440")).GET()
				.build();

		HttpResponse<String> res = cliente.send(req, HttpResponse.BodyHandlers.ofString());

		// Tambien podemos guardarlo en un fichero de salida para tenerlo ya en un
		// fichero
//		HttpResponse<Path> resPath = cliente.send(req, HttpResponse.BodyHandlers.ofFile(Paths.get("descargado.html")));

		// Tambien podemos guardarlo mediante un stream, y ahora esto podemos enviarlo
		// por un socket por ejemplo
//		HttpResponse<InputStream> resInputStream = cliente.send(req, HttpResponse.BodyHandlers.ofInputStream());

//		System.out.println(res); // con esto nos devuelve un mensaje con el codigo de respuesta, 200, 405, 300...
		// este body y el bodyHandelrs se refiere al body de la respuesta, no
		// confundirlo con el body del html. por que como vemos, nos descarga toda la
		// web no solo lo que esta dentro del body
//		System.out.println(res.body()); // con esto nos descargamos toda la web

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++

		// AHORA VAMOS A RECUPERAR FILTRANDO, USANDO LAS LIBRERIAS QUE HEMOS IMPORTADO
		Document doc = Jsoup.parse(res.body());

		// una forma de sacar datos concretos es copiando el selector css haciendo clic
		// derecho, inspeccioanr y despues me posiciono en el dato que quiero, boton
		// derecho, copy, selector css. y eso lo podemos pegar en el doc.select

		// Podemos seleccionar tantos como queramos, separando por comas

		// Elements elementos = doc.select(".titolseccio");
		Elements elementos = doc.select(
				"#content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(1), #content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(3)");
		for (Element element : elementos) {
			System.out.println(element.text());
		}

		// +++++++++++++++++++++++++++++++++++++++++++++++
		// Esto de antes era simple para paginas antiguas, pero el problema de las
		// paginas actules esque
		// usan AJAX, (que son los javascript, estos scripts son codigo que sirven para
		// modificar en tiempo real el html o css). Lo malo de esto es que por ejemplo
		// hay paginas que de html solo cargan la estructura
		// todo lo demas lo cargan a traves de peticiones al servidor mediante scripts.
		// (ahora los script exportan la infromacion en json, despues se interpretan y
		// se pasan a html y se ponen en la pagina a cargar)

		// Un ejemplo es con w3schol, vemos como podemos recupear el codigo que intenta
		// inyectar el javascript al presionar el boton
		// https://www.w3schools.com/js/js_ajax_intro.asp -- Esta es la web que vamos a
		// utilizar para sacar el javascript
		
		// Vamos al inspector nos posicionamos en network y seleccionamos la etiqueta de
		// XHR. Despues volvemos a recargar la web y abnalizara todos los javascript de
		// la wwb, le damos al boton del ejemplo "change content" y se anyadira la
		// ultima peticion javascript al final del todo.

		// Y copiamos el enlace de esa peticion javascript para recuperar todo lo que
		// envia esa peticion javascript

		HttpClient cliente2 = HttpClient.newHttpClient();
		HttpRequest req2 = HttpRequest.newBuilder().uri(URI.create("https://www.w3schools.com/js/ajax_info.txt")).GET()
				.build();
		HttpResponse<String> res2 = cliente.send(req2, HttpResponse.BodyHandlers.ofString());

		/*
		 * Esto de abajo no hace falta en este caso por que no vamos a filtrar Document
		 * doc2 = Jsoup.parse(res2.body()); Elements elementos2 =
		 * doc2.select("#demo > button"); for (Element element2 : elementos2) {
		 * System.out.println(element2.text()); }
		 */

		// System.out.println(res);
		System.out.println(res2.body());

	}
}
