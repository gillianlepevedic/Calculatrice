import java.util.Scanner;

public class LectureClavier {
    //Initialise un scanner
    private Scanner clavier;
    /**
     * Initialise le Scanner des qu'on fait appelle a la fonction
     * @param entrer le STDIN qu'on veut ecouter
     */
    public LectureClavier(Scanner entrer) {
        clavier = entrer;
    }
    /**
     * Demmande un entier et continue tant qu'il a pas un entier
     * @return retourne forcement un entier
     */
    public int demandeEntier(){
        //Initialise les variable utile
        boolean ok=false;
        int entier=0;
        //boucle pour avoir un entier
        while (! ok){
            try {
                entier = clavier.nextInt();
                ok = true;
            } 
            catch (java.util.InputMismatchException e) {
                System.out.print("Erreur : Chiffre incorrect \nDonnez un nombre entier :") ;
                clavier.nextLine();
            }  
        }
        //retourne un entier
        return entier;
    }
    /**
     * Demmande un entier et continue tant qu'il a pas un entier positif
     * @return retourne forcement un entier positif
     */
    public int demandeEntierPositif(){
        //Initialise les variable utile
        boolean ok=false;
        int entierP=0;
        //Boucle tant que l'entier n'est pas positif
        while (! ok){
            entierP= demandeEntier();

            if (entierP >=0){
                ok = true;
            }else{
                System.out.print("Erreur : Chiffre incorrect \nDonnez un nombre entier positif :") ;
            }
        }
        //Retourne l'entier taper au clavier
        return entierP;
    }
    /**
     * Damande un entier entres un intervalle passer en paramettres. Les bornes sont compris dedans
     * @param min Ca serat le plus petit entier accepter
     * @param max Ca serat le plus grand entier accepter
     * @return  retourne forcement un entier comprit dans l'interval
     */
    public int demandeEntierInterval(int min, int max){
        //Retourne l'entier taper au clavier
        boolean ok=false;
        int entierInter=0;
        //Boucle tant que un entier n'est pas comprit dans l'interval
        while (! ok){
            entierInter= demandeEntier();

            if (entierInter >=min && entierInter <= max){
                ok = true;
            }else{
                System.out.print("Erreur : Chiffre incorrect \nDonnez un nombres plus petit ou egal a " + max + " et plus grand ou egal a " + min + " :") ;
            }
        }
        //Retourne l'entier comprit dans l'interval
        return entierInter;
    }
    /**
     * Demmande un float et continue tant qu'il a pas un float avec un point si on utilise "Local.US"
     * @return retourne forcement un float
     */
    public float demandeFloat(){
        //Initialise les variable utile
        boolean ok=false;
        float reel=0;
        //Boucle tant qu'il y a pas un float
        while (! ok){
            try { 
                reel = clavier.nextFloat();
                ok = true;
            } 
            catch (java.util.InputMismatchException e) {
                System.out.print("Erreur : Chiffre incorrect \nDonnez un nombre reel :") ;
                clavier.nextLine();
            }  
        }
        //retourne un float
        return reel;
    }
    /**
     * Demande un mots avec maj ou/et min ou/et underscore
     * @return retourne forcement un mots
     */
    public String demandeMot(){
        //Initialise les variable utile
        String mot = "";
        //demande un mots corespondant au Regex
        mot= demandeRegex("[A-Za-z_]+", "lettres min ,maj et underscore");
        //Retourne un string avec des lettres et des underscore
        return mot;
    }
    /**
     * Demande une chaine qui coresponde au Regex envoyer en paramtres
     * @param autoriserRegex Regex qui autorisera ou nom la chaine
     * @param autoriserFR Personalisation du message d'erreur
     * @return Retourne forcement un String corespondant au Regex
     */
	public String demandeRegex(String autoriserRegex, String autoriserFR){
        //Initialise les variable utile
        boolean ok=false;
        String chaine = "";
        //Boucle tant que la chaine ne corespond pas au Regex
        while (! ok){
            try { 
                chaine = clavier.next(autoriserRegex);
                ok = true;
            } 
            catch (java.util.InputMismatchException e) {
				System.out.print("Erreur : Operateur incorrect \nVous devez utiliser seulement ( " + autoriserFR + " ) :") ;
                clavier.nextLine();
            }  
        }
        //Retourne un string qui match avec le Regex
        return chaine;
    }


}