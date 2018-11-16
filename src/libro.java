import java.util.ArrayList;

public class libro {

	private String titulo;
	private String editor;
	private int paginas;
	private int anyo;
	private ArrayList <Autor> Autores;
	public libro(String titulo, String editor, int paginas, int anyo, ArrayList<Autor> autores) {
		super();
		this.titulo = titulo;
		this.editor = editor;
		this.paginas = paginas;
		this.anyo = anyo;
		Autores = autores;
	}
	@Override
	public String toString() {
		
		
			
		
		String aux= "libro titulo=" + titulo + "\n editor=" + editor + "\n paginas=" + paginas + "\n anyo=" + anyo
				+ ",\n Autores=";
		
		for (int i=0; i<Autores.size();i++) {
			Autor autor=Autores.get(i);
			
			aux= aux +autor.toString();
			
			
		}
		return aux;	
				
		
	}
	
	
	
	
}
