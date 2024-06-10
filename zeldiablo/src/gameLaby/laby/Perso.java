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

    public String toString(){
        return("Perso");
    }

    public void recupererAmulette(){
        this.amulette = true;
    }

    public void jeterAmulette(){
        this.amulette = false;
    }

    public boolean avoirAmulette(){
        return(this.amulette);
    }


}
