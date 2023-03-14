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

	            Elements elementis=doc.select("#content > table:nth-child(3) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) > td.titolsensor");
	          
	    	
	            
	          
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

