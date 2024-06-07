package gameLaby.laby;

/**
 * Classe representant le monstre dans le labyrinthe
 */
public class Monstre extends Entite {

    /**
     * Constructeur du monstre
     *
     * @param dx Position selon x
     * @param dy Position selon y
     */
    public Monstre(int dx, int dy) {
        super(dx, dy);
        this.vie = 100;
    }

    public String toString(){
        return("Monstre");
    }
}
