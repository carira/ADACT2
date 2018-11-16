
public class mainhub {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		parser parser= new parser();
		parser.parseFicheroXml("biblio.xml");
		parser.parseDocument();
		parser.print();
		
		
		
		
		
		
		
	}
	

}
