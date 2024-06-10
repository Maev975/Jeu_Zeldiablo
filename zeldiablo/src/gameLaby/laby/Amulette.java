package gameLaby.laby;

/**
 * Classe representant l'amulette dans le labyrinthe
 */
public class Amulette extends Entite {

    /**
     * Constructeur de l'amulette
     *
     * @param dx Position selon x
     * @param dy Position selon y
     */
    public Amulette(int dx, int dy) {
        super(dx, dy);
    }

    public String toString(){
        return("Amulette");
    }
}
