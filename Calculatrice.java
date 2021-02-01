import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class principal
 * point d'entree du programme
 */
public class Calculatrice {
	public static void main(String[] arg){
		////Initialisation des variables
		int choix =1;
		//Initialise le scanner plus mon clavier personaliser avec des methodes pratique
		Scanner clavier = new Scanner(System.in).useLocale(Locale.US);
		LectureClavier clavierGigi = new LectureClavier(clavier);
		
		while (choix != 0){
			choix = menu(clavierGigi);//Affiche le menu et demande un nombres
			
			switch(choix) { 
				case 0: 
					System.out.println("Extinction de la calculatrice");//Fin du programme
					break; 
				case 1: 
					System.out.println("Calcultrice mode Clasique");
					clasic(clavierGigi);//Lance le mode clasique 
				break; 
				case 2: 
					System.out.println("Calcultrice mode Trigonometrie");
					trigo(clavierGigi);//Lance le mode trigo
					break; 
				case 3: 
					System.out.println("Calcultrice mode Ameliorer");
					clasicAmeliorer(clavierGigi);//Lance le mode ameliorer
					break; 
				default: System.out.println("Erreur : Choix inconnue"); //Normalement arrive jamais 
			}
		}
	}
	/**
	 * Affiche le menu. Et demande le l'action a réaliser
	 * @param clavierGigi L'entrer du clavier pour utiliser les methode personaliser
	 * @return retourne le choix qui est forcement dans le menu
	 */
	private static int menu(LectureClavier clavierGigi){
		//Initialisation des variables
		int mode =-1;
		//Affiche et demande l'action
		System.out.print("Menu Calculatrice\n0 : Quitter\n1 : Clasique\n2 : Trigonometrie\n3 : Clasique Ameliorer\n");
		mode = clavierGigi.demandeEntierInterval(0, 3);//demander un entier
		//retourne le choix
		return mode;
	}


	/**
	 * Fait un calcul en fontion de 3 variables tapez au clavier 2 nombres et 1 operateur
	 * @param clavierGigi L'entrer du clavier pour utiliser les methode personaliser
	 */
	private static void clasic(LectureClavier clavierGigi){
		//Initialisation des variables
		float nombres1=0;
		float nombres2=0;
		String operateur;
		float resultat =0;
		//Demande le premier nombres
		System.out.print("Donnez le premier nombres :");
		nombres1 = clavierGigi.demandeFloat();
		//Demande le l'operateur soit + - * /
		System.out.print("Donnez l'operateur ( + - * / ):");
		operateur = clavierGigi.demandeRegex("[/*\\-+]{1}", "+ - * /");
		////Demande le deuxieme nombres	
		System.out.print("Donnez le deuxieme nombres :");
		nombres2 = clavierGigi.demandeFloat();
		//Fait le calcul si possibles
		switch(operateur) { 
			case "+": 
				resultat = (nombres1+nombres2);
				break; 
			case "-": 
				resultat = (nombres1-nombres2);
			break; 
			case "*": 
				resultat = (nombres1*nombres2); 
				break; 
			case "/": 
				//Alternative pour la division par zero meme si ca marche avec Java c'est mieux
				if (nombres2 ==0){
					System.out.println("Erreur : Division par 0");
					resultat =0;
				}else{
					//mets ca dans un float sinon ca mets un doubles 
					resultat = (nombres1*1F/nombres2*1F);
				}	 
				break;
			default: System.out.println("Erreur : Operateur inconnu"); //Normalement arrive jamais  
		}
		//Affiche le resultat
		System.out.println(nombres1 + operateur + nombres2 + "=" + resultat);
	}


	/**
	 * Fait des calcul trigonimetrique en fontion de 3 variables tapez au clavier. 2 nombres et un operateur
	 * @param clavierGigi L'entrer du clavier pour utiliser les methode personaliser
	 */
	private static void trigo(LectureClavier clavierGigi){
		//Initialisation des variables
		String operateur;
		double resultat =0;
		float co=-1;
		float ca=-1;
		float ch=-1;
		//Affiche les option et demande l'operateur
		System.out.print("Donnez la fonction a utiliser (sin, cos,tan):");
		operateur =clavierGigi.demandeRegex("(sin|cos|tan)", "sin cos tan");
		//Fait le calcul de trigo
		switch(operateur) { 
			case "sin": 
				System.out.print("Donnez le cote oppose :");
				co =clavierGigi.demandeFloat();
				System.out.print("Donnez l'hypotenus' :");
				ch =clavierGigi.demandeFloat();
				//Ca calcule l'angle en randian en fonction du coter oposer et de l'hypotenus
				resultat = Math.asin(co/ch);
				break; 
			case "cos": 
				System.out.print("Donnez le cote adjacent :");
				ca =clavierGigi.demandeFloat();
				System.out.print("Donnez l'hypotenus' :");
				ch =clavierGigi.demandeFloat();
				//Ca calcule l'angle en randian en fonction du coter adjacant et de l'hypotenus
				resultat = Math.acos(ca/ch);
			break; 
			case "tan": 
				System.out.print("Donnez le cote oppose :");
				co =clavierGigi.demandeFloat();
				System.out.print("Donnez l'adjacent' :");
				ca =clavierGigi.demandeFloat();
				//Ca calcule l'angle en randian en fonction du coter adjacant et du coter oposer
				resultat = Math.atan(co/ca);
				break; 
			default: System.out.println("Erreur : Operateur inconnue");//Normalement arrive jamais 
		}
		//Affiche le resultat
		System.out.println("L'angles est de " + resultat + " radian");
	}

	
	/**
	 * Fait un calcul  en fontion de une seule variables tapez au clavier. Puis il fait le calcul
	 * @param clavierGigi L'entrer du clavier pour utiliser les methode personaliser
	 */
	private static void clasicAmeliorer(LectureClavier clavierGigi){
		//Initialisation des variables
		float nombres1=-1;
		float nombres2=0;
		String operateur;
		float resultat =0;
		//Demmande le calcule en entierement
		System.out.print("Donnez le calcul complet: ");
		operateur =clavierGigi.demandeRegex("[0-9\\.]+[/*\\-+]{1}[0-9\\.]+", "<nombres> <operateur> <nombres> (sans espace)");
		//Crée un  patern de 3 section avec comme element central + - * / Et decomposer la chaine en morceau
		Pattern separateur = Pattern.compile("(.+)([/*\\-+]{1})(.+)");
		Matcher morceau = separateur.matcher(operateur);
		//Convertit les nombres en Float et les dispatche dans leur variable 
		if(morceau.matches()) {
			nombres1 = Float.parseFloat(morceau.group(1));
			operateur = morceau.group(2);
			nombres2 = Float.parseFloat(morceau.group(3));
		}
		//Fait le calcul
		switch(operateur) { 
			case "+": 
				resultat = (nombres1+nombres2);
				break; 
			case "-": 
				resultat = (nombres1-nombres2);
			break; 
			case "*": 
				resultat = (nombres1*nombres2); 
				break; 
			case "/": 
				//Alternative pour la division par zero meme si ca marche avec Java c'est mieux
				if (nombres2 ==0){
					System.out.println("Erreur : Division par 0");
					resultat =0;
				}else{
					//mets ca dans un float sinon ca mets un doubles
					resultat = (nombres1*1F/nombres2*1F); 
				}	 
				break; 
			default: System.out.println("Erreur : Operateur inconnue"); //Normalement arrive jamais
		}
		//Affiche le resultat
		System.out.println(nombres1 + operateur + nombres2 + "=" + resultat);
	}
}