import service.ChiffrementService;

public class Main {
	
	public static void main(String[] args) {
		ChiffrementService service = new ChiffrementService();
		System.out.println("Le texte chiffr� est : " + service.algoChiffrementTexte("open campus"));
		System.out.println("Le texte chiffr� est : " + service.algoDechiffrementTexte("ayoh4jtdyuc"));
	}
	
}
