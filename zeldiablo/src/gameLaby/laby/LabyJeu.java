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
    public void update(double deltaTime, Clavier clavier) {
        if (clavier.haut) {
            this.labyrinthe.deplacement(Labyrinthe.HAUT);
        }
        if (clavier.bas) {
            this.labyrinthe.deplacement(Labyrinthe.BAS);
        }
        if (clavier.gauche) {
            this.labyrinthe.deplacement(Labyrinthe.GAUCHE);
        }
        if (clavier.droite) {
            this.labyrinthe.deplacement(Labyrinthe.DROITE);
        }
        if (clavier.espace) {
            this.labyrinthe.deplacement(Labyrinthe.ESPACE);
        }
    }

    @Override
    public void init() {
//pas besoin
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
