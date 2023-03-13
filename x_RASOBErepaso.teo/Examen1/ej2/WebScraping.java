package ej2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraping {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create("https://kingsleague.pro/")).GET().build();

		HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
		
//		HttpResponse<Path> resPath = client.send(req, HttpResponse.BodyHandlers.ofFile(Paths.get("./Archivos/WebScraping/descargado.html")));
//		System.out.println("Body descargado correctamente!");
		
		Document doc = Jsoup.parse(res.body());
		
		Scanner teclado = new Scanner(System.in);
		Integer jornada = 0;
		while (jornada < 1 || jornada > 11) {
			System.out.print("Selecciona la jornada: ");
			jornada = Integer.valueOf(teclado.nextLine());
		}
		Elements resultadosJugados = doc.select("#page\\#" + (13 + jornada) + " > div > table > tbody tr");
		for (Element element : resultadosJugados) {
			System.out.println(element.text());
		}
		
	}

}
