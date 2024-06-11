package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

/**
 * Classe qui permet de faire fonctionner le jeu dans le labyrinthe
 */
public class LabyJeu implements Jeu {
    private final Labyrinthe labyrinthe;

    /**
     * Constructeur de la classe LabyJeu.
     *
     * @param nom Le nom du fichier contenant la configuration du labyrinthe.
     * @throws IOException Si une erreur de lecture du fichier se produit.
     */
    public LabyJeu(String nom) throws IOException {
        this.labyrinthe = new Labyrinthe(nom);
    }

    @Override
    public void update(double deltaTime, Clavier clavier) throws IOException {
        if (clavier.haut) {
            this.labyrinthe.realiserEtape(Labyrinthe.HAUT);
            clavier.haut = false; // Réinitialise l'indicateur après l'action
        }
        if (clavier.bas) {
            this.labyrinthe.realiserEtape(Labyrinthe.BAS);
            clavier.bas = false; // Réinitialise l'indicateur après l'action
        }
        if (clavier.gauche) {
            this.labyrinthe.realiserEtape(Labyrinthe.GAUCHE);
            clavier.gauche = false; // Réinitialise l'indicateur après l'action
        }
        if (clavier.droite) {
            this.labyrinthe.realiserEtape(Labyrinthe.DROITE);
            clavier.droite = false; // Réinitialise l'indicateur après l'action
        }
        if (clavier.espace) {
            this.labyrinthe.realiserEtape(Labyrinthe.ESPACE);
            clavier.espace = false; // Réinitialise l'indicateur après l'action
        }
    }

    @Override
    public void init() {
        // pas besoin
    }

    @Override
    public boolean etreFini() {
        return this.labyrinthe.etreFini();
    }

    /**
     * @return Retourne le labyrinthe
     */
    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }
}
