package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 * <ul> un monstre (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char MONSTRE = 'M';
    public static final char VIDE = '.';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";
    public static final String ESPACE = "Espace";

    // Ajoutez les constantes pour les directions possibles
    public static final String[] ACTIONS = {HAUT, BAS, GAUCHE, DROITE, ESPACE};

    /**
     * attributs du personnage et du monstre
     */
    public Perso pj;
    public Monstre monstre;
    public List<Entite> lst_entite;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    private Random random;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on diminue colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.monstre = null;
        this.random = new Random();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(colonne, numeroLigne);
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute Monstre
                        this.monstre = new Monstre(colonne, numeroLigne);
                        break;
                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();

        // Initialise lst_entite après avoir initialisé pj et monstre
        this.lst_entite = new ArrayList<>();
        if (this.pj != null) {
            this.lst_entite.add(this.pj);
        }
        if (this.monstre != null) {
            this.lst_entite.add(this.monstre);
        }
    }

    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs et le monstre
     *
     * @param action une des actions possibles
     */
    public void realiserEtape(String action) {
        //si notre action est un deplacement, on deplace tout le monde
        ArrayList<String> mouv = new ArrayList<>();
        mouv.add("Haut");
        mouv.add("Bas");
        mouv.add("Gauche");
        mouv.add("Droite");
        if(mouv.contains(action)) {
            for (Entite e : lst_entite) {
                if (e instanceof Perso)
                    deplacerEntite(action, e);
                else
                    deplacerEntite("", e);
            }
        } else {
            //si notre action est une attaque, on ne deplace personne !
            //si c'est espace, donc une attaque. On attaque tout les monstres
            if(action.equals("Espace")){
                boolean attaque_vide = true;
                for (Entite e : lst_entite) {
                    if (e instanceof Monstre) {
                        if(this.pj.etreACote(e)) {
                            this.pj.attaquer(1, 1, e);
                            attaque_vide = false;
                        }
                    }
                }
                //si on a attaque personne on se freeze
                if(attaque_vide == true)
                    this.pj.setFreeze(1);
            }
        }



        //on fait les attaques des/du monstre(s)
        for(Entite e : lst_entite) {
            if(e instanceof Monstre)
                if (e.etreACote(this.pj)){
                    e.attaquer(1, 0, this.pj);
                }
        }
    }

    public void deplacerEntite(String action, Entite e) {
        if(e.etreFreeze()){
            System.out.println(e + " est freeze pdt: " + e.getFreeze());
            e.setFreeze(e.getFreeze()-1);
            return;
        }
        if(action.equals("")){
            ArrayList<String> mouv = new ArrayList<>();
            mouv.add("Haut");
            mouv.add("Bas");
            mouv.add("Gauche");
            mouv.add("Droite");
            action = mouv.get(random.nextInt(mouv.size()));
        }
        int[] courante = {e.getX(), e.getY()};
        int[] suivante = getSuivant(courante[0], courante[1], action);

        boolean possible = true;
        if (this.murs[suivante[0]][suivante[1]])
            possible = false;
        for (Entite e2 : lst_entite) {
            if (e2 != e) {
                if (e2.getX() == suivante[0] && e2.getY() == suivante[1])
                    possible = false;
            }
        }
        if (possible) {
            e.setX(suivante[0]);
            e.setY(suivante[1]);
        }
    }

    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return (this.pj.etreMort());
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        return this.murs[x][y];
    }
}
