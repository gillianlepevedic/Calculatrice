import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculatrice {
	private static Scanner clavier = new Scanner(System.in).useLocale(Locale.US);//Ouvre STDIN et pour les nombres réel il faut utiliser un point

	public static void main(String[] arg){//Fonction principal
		int choix =1;
		while (choix != 0){
			choix = menu();//Affiche le menu et demande un nombres
			
			switch(choix) { 
				case 0: 
					System.out.println("Extinction de la calculatrice");//Fin du programme
					break; 
				case 1: 
					System.out.println("Calcultrice mode Clasique");
					clasic();//Lance le mode clasique 
				break; 
				case 2: 
					System.out.println("Calcultrice mode Trigonometrie");
					trigo();//Lance le mode trigo
					break; 
				case 3: 
					System.out.println("Calcultrice mode Ameliorer");
					clasicAmeliorer();//Lance le mode ameliorer
					break; 
				default: System.out.println("Erreur : Choix inconnue"); //Normalement arrive jamais 
			}
		}
	}

	private static int menu(){
		int mode;
		System.out.print("Menu Calculatrice\n0 : Quitter\n1 : Clasique\n2 : Trigonometrie\n3 : Clasique Ameliorer\n");
		mode = demandeentier();//demander un entier

		while (mode < 0 || mode > 3){//Verifie si il est bien dans le menu
			System.out.print("Donnez sois 0, 1, 2, 3:");
			mode = demandeentier();
		}
		return mode;//retourne le choix
	}

	private static void clasic(){
		float nombres1=0;
		float nombres2=0;
		String operateur;
		float resultat =0;

		System.out.print("Donnez le premier nombres :");
		nombres1 = demandefloat();//Demande une nombres réel

		System.out.print("Donnez l'operateur ( + - * / ):");
		operateur = demandeoperateur("[/*\\-+]{1}", "+ - * /");//Demande un oprerateur soit  + - * / et si il y a une erreur il affiche le deuxieme en message d'erreur
			
		System.out.print("Donnez le deuxieme nombres :");
		nombres2 = demandefloat();//Demande une nombres réel

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

	private static void trigo(){
		String operateur;
		double resultat =0;
		float co=-1;
		float ca=-1;
		float ch=-1;

		System.out.print("Donnez la fonction a utiliser (sin, cos,tan):");
		operateur = demandeoperateur("(sin|cos|tan)", "sin cos tan");

		switch(operateur) { 
			case "sin": 
				System.out.print("Donnez le cote oppose :");
				co = demandefloat();//Demande une nombres réel
				System.out.print("Donnez l'hypotenus' :");
				ch = demandefloat();//Demande une nombres réel

				resultat = Math.asin(co/ch);//Ca calcule l'angle en randian en fonction du coter oposer et de l'hypotenus
				break; 
			case "cos": 
				System.out.print("Donnez le cote adjacent :");
				ca = demandefloat();
				System.out.print("Donnez l'hypotenus' :");
				ch = demandefloat();

				resultat = Math.acos(ca/ch);//Ca calcule l'angle en randian en fonction du coter adjacant et de l'hypotenus
			break; 
			case "tan": 
				System.out.print("Donnez le cote oppose :");
				co = demandefloat();
				System.out.print("Donnez l'adjacent' :");
				ca = demandefloat();

				resultat = Math.atan(co/ca);//Ca calcule l'angle en randian en fonction du coter adjacant et du coter oposer
				break; 
			default: System.out.println("Erreur : Operateur inconnue");//Normalement arrive jamais 
		}
		System.out.println("L'angles est de " + resultat + " radian");//Affiche le resultat
	}

	private static void clasicAmeliorer(){
		float nombres1=-1;
		float nombres2=0;
		String operateur;
		float resultat =0;


		System.out.print("Donnez le calcul en entier: ");
		operateur = demandeoperateur("[0-9\\.]+[/*\\-+]{1}[0-9\\.]+", "<nombres> <operateur> <nombres> (sans espace)");	//Demande un nombres avec ou sans virgule 
																														//suivie d'un operateur + - * / et finir 
																														//avec un nombres avec ou sans virgule

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


	private static int demandeentier(){
        boolean ok=false;
        int entier=0;

        while (! ok){//Tant qu'il a pa lu un entier il sort pas
            try {//Si la commande nextInt ne marche pas Alors il fait se qu'il y a dans catch 
                entier = clavier.nextInt();//Si c'est un entier tout va bien sinon une erreur
                ok = true;//Il a reussie a lire un entier
            } 
            catch (java.util.InputMismatchException e) {//Si l'entrer n'est pas un entier alors il fait ca
                System.out.print("Erreur : Chiffre incorrect \nDonnez un nombre entier :") ;
                clavier.nextLine();//Vide le clavier ca evite les erreur si on a tapez n'importe quoi
            }  
        }

        return entier;//Retourne l'entier taper au clavier
    }

	private static float demandefloat(){
        boolean ok=false;
        float reel=0;

        while (! ok){//Tant qu'il a pa lu un réel il sort pas
            try {//Si la commande nextFloat ne marche pas Alors il fait se qu'il y a dans catch 
                reel = clavier.nextFloat();//Si c'est un réel tout va bien sinon une erreur
                ok = true;//Il a reussie a lire un réel
            } 
            catch (java.util.InputMismatchException e) {//Si l'entrer n'est pas un réel alors il fait ca
                System.out.print("Erreur : Chiffre incorrect \nDonnez un nombre reel :") ;
                clavier.nextLine();//Vide le clavier ca evite les erreur si on a tapez n'importe quoi
            }  
        }

        return reel;//Retourne le réel taper au clavier
	}

	private static String demandeoperateur(String autoriserRegex, String autoriserFR){
        boolean ok=false;
        String operateur = "";

        while (! ok){//Tant qu'il a pa lu un quelque chose qui match avec le Regex il sort pas
            try {//Si la commande next ne marche pas Alors il fait se qu'il y a dans catch 
                operateur = clavier.next(autoriserRegex);//Si la lecture au clavier est match avec un regex alors pas de probleme sinon erreur
                ok = true;
            } 
            catch (java.util.InputMismatchException e) {//Si ca match pas
				System.out.print("Erreur : Operateur incorrect \nVous devez utiliser seulement ( " + autoriserFR + " ) :") ;//Affiche un message d'erreur personaliser 
																															//en fonction de se qu'on a mits en paramtres
                clavier.nextLine();//Vide le clavier ca evite les erreur si on a tapez n'importe quoi
            }  
        }

        return operateur;//Renvoie une chaine corespondant au Regex envoyer
	}
}