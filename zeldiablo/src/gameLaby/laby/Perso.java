package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite{

    private boolean amulette;
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        super(dx, dy);
        this.vie = 5;
        this.amulette = false;
    }

    /**
     * ToString qui retourne le nom de l'entite
     * @return String
     */
    public String toString(){
        return("Perso");
    }

    /**
     * * Méthode qui recupere l'amulette en le mettant à true
     *
     */
    public void recupererAmulette(){
        this.amulette = true;
    }

    /**
     * * Méthode qui permet de jeter l'amulette en le mettant sur false
     *
     */
    public void jeterAmulette(){
        this.amulette = false;
    }

    /**
     * Méthode que permet de verifier si on a l'amulette
     * @return boolean
     */
    public boolean avoirAmulette(){
        return(this.amulette);
    }


}
