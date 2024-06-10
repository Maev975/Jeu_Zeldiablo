package moteurJeu;

import javafx.scene.input.KeyEvent;

public class Clavier {

    /**
     * controle appuyes
     */
    public boolean haut, bas, gauche, droite, espace;

    /**
     * Indicateurs pour vérifier si une action a déjà été effectuée
     */
    private boolean hautAppuye, basAppuye, gaucheAppuye, droiteAppuye, espaceAppuye;

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void appuyerTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S:
                if (!basAppuye) {
                    this.bas = true;
                    basAppuye = true;
                }
                break;

            // si touche haut
            case Z:
                if (!hautAppuye) {
                    this.haut = true;
                    hautAppuye = true;
                }
                break;

            // si touche gauche
            case Q:
                if (!gaucheAppuye) {
                    this.gauche = true;
                    gaucheAppuye = true;
                }
                break;

            // si touche droite
            case D:
                if (!droiteAppuye) {
                    this.droite = true;
                    droiteAppuye = true;
                }
                break;

            // si espace
            case SPACE:
                if (!espaceAppuye) {
                    this.espace = true;
                    espaceAppuye = true;
                }
                break;
        }

    }

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void relacherTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S:
                this.bas = false;
                basAppuye = false;
                break;

            // si touche haut
            case Z:
                this.haut = false;
                hautAppuye = false;
                break;

            // si touche gauche
            case Q:
                this.gauche = false;
                gaucheAppuye = false;
                break;

            // si touche droite
            case D:
                this.droite = false;
                droiteAppuye = false;
                break;

            // si espace
            case SPACE:
                this.espace = false;
                espaceAppuye = false;
                break;
        }
    }
}
