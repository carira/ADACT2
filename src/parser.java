import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.util.Iterator;




import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class parser {
	
	private Document dom = null;
	private ArrayList<libro> Libros = null;

	public parser() {
		Libros = new ArrayList<libro>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("libro");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (libro)
				Element el = (Element) nl.item(i);

				// obtenemos una libro
				libro p = getlibro(el);

				// lo añadimos al array
				Libros.add(p);
			}
		}
	}
	
	private libro getlibro(Element libro){
		
		//para cada elemento libro, obtenemos sus cosas
		 String titulo= getTextValue(libro,"titulo");
		 String editor= getTextValue(libro,"editor");
		 int paginas= getIntValue(libro,"paginas");
		 int anyo= getAtribute(libro,titulo,"anyo");
		ArrayList <Autor> Autores= getAutor(libro);
		
		//String titulo = getTextValue(personaEle,"nombre");
		//int edad = getIntValue(personaEle,"edad");
		
		//Creamos una nueva persona con los elementos leídos del nodo
		libro e = new libro( titulo, editor, paginas, anyo, Autores);

		return e;		
		
	}
	
	private ArrayList<Autor> getAutor (Element libro){
		ArrayList <Autor> Autores= new ArrayList<Autor> ();
		
		// obtenemos el nodelist de elementos
		NodeList nl = libro.getElementsByTagName("autores");
		if (nl != null && nl.getLength() > 0) {
				// obtenemos un elemento de la lista (libro)
				Element el = (Element) nl.item(0);
				
				NodeList nl1 = libro.getElementsByTagName("autor");
				if (nl1 != null && nl1.getLength() > 0) {
					
					for (int i = 0; i < nl1.getLength(); i++)	{
						
						Element el1 = (Element) nl1.item(i);
						
						String nombre=getTextValue(el1,"nombre");;
						String apellido=getTextValue(el1,"apellido");
						
						Autor auxiliar= new Autor (nombre, apellido);
						Autores.add(auxiliar);
					}

				}
			
		}
		
		return Autores;
	}
	
	private int getAtribute(Element ele, String tagName, String atributo) {
		int atributaco = 0;
		NodeList nl = ele.getElementsByTagName(tagName);
		
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			atributaco = Integer.parseInt(el.getAttribute(atributo));
		}		
		return atributaco;
		
		
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	public void print(){
		

		Iterator it = Libros.iterator();
		System.out.println("longitud "+Libros.size());
		while(it.hasNext()) {
			
			libro p=(libro) it.next();
			System.out.println(p.toString());
		}
		
	}
	
	

}