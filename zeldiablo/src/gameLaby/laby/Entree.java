package gameLaby.laby;

public class Entree extends Entite{
    /**
     * Constructeur de l'entrée
     *
     * @param dx Position selon x
     * @param dy Position selon y
     */
    public Entree(int dx, int dy) {
        super(dx, dy);
    }

    public String toString(){
        return("Entrée");
    }
}
