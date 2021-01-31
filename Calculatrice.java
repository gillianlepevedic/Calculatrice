import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class principal
 * point d'entree du programme
 */
public class Calculatrice {
	public static void main(String[] arg){//Fonction principal
		int choix =1;
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

	private static int menu(LectureClavier clavierGigi){
		int mode;
		System.out.print("Menu Calculatrice\n0 : Quitter\n1 : Clasique\n2 : Trigonometrie\n3 : Clasique Ameliorer\n");
		mode = clavierGigi.demandeEntierInterval(0, 3);//demander un entier
		return mode;//retourne le choix
	}

	private static void clasic(LectureClavier clavierGigi){
		float nombres1=0;
		float nombres2=0;
		String operateur;
		float resultat =0;

		System.out.print("Donnez le premier nombres :");
		nombres1 = clavierGigi.demandeFloat();//Demande une nombres réel

		System.out.print("Donnez l'operateur ( + - * / ):");
		operateur = clavierGigi.demandeRegex("[/*\\-+]{1}", "+ - * /");
			
		System.out.print("Donnez le deuxieme nombres :");
		nombres2 = clavierGigi.demandeFloat();//Demande une nombres réel

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
				if (nombres2 ==0){//Alternative pour la division par zero meme si ca marche avec Java c'est mieux
					System.out.println("Erreur : Division par 0");
					resultat =0;
				}else{
					resultat = (nombres1*1F/nombres2*1F);//mets ca dans un float sinon ca mets un doubles 
				}	 
				break; 
			default: System.out.println("Erreur : Operateur inconnu"); //Normalement arrive jamais 
		}
		System.out.println(nombres1 + operateur + nombres2 + "=" + resultat);//Affiche le resultat
	}

	private static void trigo(LectureClavier clavierGigi){
		String operateur;
		double resultat =0;
		float co=-1;
		float ca=-1;
		float ch=-1;

		System.out.print("Donnez la fonction a utiliser (sin, cos,tan):");
		operateur =clavierGigi.demandeRegex("(sin|cos|tan)", "sin cos tan");

		switch(operateur) { 
			case "sin": 
				System.out.print("Donnez le cote oppose :");
				co =clavierGigi.demandeFloat();//Demande une nombres réel
				System.out.print("Donnez l'hypotenus' :");
				ch =clavierGigi.demandeFloat();//Demande une nombres réel

				resultat = Math.asin(co/ch);//Ca calcule l'angle en randian en fonction du coter oposer et de l'hypotenus
				break; 
			case "cos": 
				System.out.print("Donnez le cote adjacent :");
				ca =clavierGigi.demandeFloat();
				System.out.print("Donnez l'hypotenus' :");
				ch =clavierGigi.demandeFloat();

				resultat = Math.acos(ca/ch);//Ca calcule l'angle en randian en fonction du coter adjacant et de l'hypotenus
			break; 
			case "tan": 
				System.out.print("Donnez le cote oppose :");
				co =clavierGigi.demandeFloat();
				System.out.print("Donnez l'adjacent' :");
				ca =clavierGigi.demandeFloat();

				resultat = Math.atan(co/ca);//Ca calcule l'angle en randian en fonction du coter adjacant et du coter oposer
				break; 
			default: System.out.println("Erreur : Operateur inconnue");//Normalement arrive jamais 
		}
		System.out.println("L'angles est de " + resultat + " radian");//Affiche le resultat
	}

	private static void clasicAmeliorer(LectureClavier clavierGigi){
		float nombres1=-1;
		float nombres2=0;
		String operateur;
		float resultat =0;


		System.out.print("Donnez le calcul en entier: ");
		operateur =clavierGigi.demandeRegex("[0-9\\.]+[/*\\-+]{1}[0-9\\.]+", "<nombres> <operateur> <nombres> (sans espace)");

		Pattern separateur = Pattern.compile("(.+)([/*\\-+]{1})(.+)");//Crée un  patern de 3 section avec comme element central + - * /
		Matcher morceau = separateur.matcher(operateur);//Decomposer la chaine en trois section les 2 nombres et l'operateur

		if(morceau.matches()) {//Comvertit les nombres en Float et les dispatche dans leur variable 
			nombres1 = Float.parseFloat(morceau.group(1));//Convertit String en Float et copie dans nombres2
			operateur = morceau.group(2);
			nombres2 = Float.parseFloat(morceau.group(3)); //Convertit String en Float et copie dans nombres2
		}
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
				if (nombres2 ==0){//Alternative pour la division par zero meme si ca marche avec Java c'est mieux
					System.out.println("Erreur : Division par 0");
					resultat =0;
				}else{
					resultat = (nombres1*1F/nombres2*1F);//mets ca dans un float sinon ca mets un doubles 
				}	 
				break; 
			default: System.out.println("Erreur : Operateur inconnue"); //Normalement arrive jamais
		}
		System.out.println(nombres1 + operateur + nombres2 + "=" + resultat);//Affiche le resultat
	}
}