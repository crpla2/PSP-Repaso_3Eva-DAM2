package webGPT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class temperatura {
	
	    public static void main(String[] args) {
	        try {
	        	//Recuperar la informacion de la página
	            Document doc = Jsoup.connect("https://www.meteoclimatic.net/perfil/ESARA2200000022002A").get();
	            
	////////////Descarga el contenido HTML en un archivo
	            File file = new File("archivo.html");
	            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	            writer.write(doc.html());
	            writer.close();
	            
	/////////////AQUI HAGO BUSQUEDAS Y LAS IMPRIMO POR PANTALLA/////////////            
	            //ELEMENTS(buscar por texto)
	            Elements elements = doc.getElementsContainingOwnText(" ºC");
	            
	            //ELEMENTAS(buscar por regex)
	            Elements elementas = doc.getElementsMatchingOwnText("( ºC| mm| %| km/h| hPa)");
	          /*
	           * 	Abre el documento HTML en tu navegador web preferido.
					Haz clic derecho en el elemento de la temperatura y selecciona "Inspeccionar" (o la opción equivalente dependiendo del navegador).
					Se abrirá la herramienta de desarrollo en el navegador, mostrando el elemento de la temperatura resaltado en el código fuente HTML.
					Haz clic derecho en el elemento y selecciona "Copy selector/selector CSS" 
	           * */  
	            Elements elementis=doc.select("#content > table:nth-child(3) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) > td.titolsensor");
	          
	            //ELEMENTOS(buscar por ruta css)
	    		Elements elementos= doc.select("#content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(1),"
	    				+ "					    #content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(3),"
	    				+ "						#content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(5),"
	    				+ "						#content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(7),"
	    				+ "						#content > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(9)");
	    		//ELEMENTES(buscar por clase)
	    		Elements elementes=doc.getElementsByClass("dadesactuals");
	    		
	    		System.out.println("ELEMENTS:");
	    		if (elements.size() > 0) {
	    			Element element = elements.get(0);
	    			System.out.println("Temperatura en Huesca: " + element.text());
	    		} else {
	    			System.out.println("No se pudo obtener la temperatura."); 
	    		}
	    		
	    		System.out.println();
	    		System.out.println("ELEMENTAS:");
	    		for(Element e:elementas) {
	    			System.out.println(e.text());
	    		}
	    		
	    		System.out.println();
	    		System.out.println("5 PRIMEROS ELEMENTAS:");
	    		for (int i = 0; i < 5; i++) {
	    			System.out.println(elementas.get(i).ownText());
				}
//	    		
	            
	            System.out.println();
	    		System.out.println("ELEMENTOS:");
	    		for (Element e : elementos) {
					System.out.println(e.text());
				}
	    		
	    		System.out.println();
	    		System.out.println("ELEMENTES:");
	    		for (Element e : elementes) {
					System.out.println(e.text());
				}
	    		System.out.println();
	    		System.out.println("ELEMENTiS:");
	    		for (Element e : elementis) {
					System.out.println(e.text());
				}
	    		
	    		System.out.println();
	    	//PARA IMPRIMIR TODO EL HTML
	    	//	System.out.println(doc);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

