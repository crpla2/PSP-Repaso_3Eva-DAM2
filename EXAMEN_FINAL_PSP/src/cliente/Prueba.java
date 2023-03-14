package cliente;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Prueba {
public static void main(String[] args) throws IOException {
	String url ="https://www.meteoclimatic.net/perfil/ESARA2200000022002A" ;
	
	String selector ="#content > table:nth-child(3) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) > td.titolsensor ";
	
	
	Document doc = Jsoup.connect(url).get();
	// ELEMENTOS(buscar por ruta css)
	//Elements elementos = doc.select(selector);
	Elements elementos=doc.select("#content > table:nth-child(3) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) > td");
	
	System.out.println(elementos);
	
//	for ( Element e: elementos) {
//		System.out.println(e.text());
//	}
//	System.out.println(doc.body());
	// Imprimir el primer elemento
	//Element element =elementos.get(0);
	
	//System.out.println(element.text());
}
}
