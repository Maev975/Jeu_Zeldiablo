package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite{

    /**
     * position du personnage
     */

    int vie;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        super(dx, dy);
        this.vie = 5;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    /**
     * méthode etreMort
     *
     * renvoie true si le personnage a moins de 5 points de vie
     *
     * @return
     */
    public boolean etreMort(){
        return(this.vie <= 0);
    }

    /**
     * méthode etreACote
     *
     * renvoie true si l'entite en parametre (e) est dans une des 9 cases autour de l'entite (this)
     *
     * @param e, Entite en parametre
     * @return boolean
     */
    public boolean etreACote(Entite e){
        if(this.x - 1 == e.getX() && ( this.y - 1 == e.getY() || this.y + 1 == e.getY() || this.y == e.getY() ))
            return(true);
        if(this.x == e.getX() && ( this.y - 1 == e.getY() || this.y + 1 == e.getY() || this.y == e.getY() ))
            return(true);
        if(this.x + 1 == e.getX() && ( this.y - 1 == e.getY() || this.y + 1 == e.getY() || this.y == e.getY() ))
            return(true);
        return(false);
    }

    /**
     * méthode subirAttaque
     *
     * si le perso n'est pas mort, on perd degat de point de vie
     * @param degat
     */
    public void subirAttaque(int degat){
        if(!this.etreMort())
            this.vie -= degat;
        if(this.vie < 0)
            this.vie = 0;
    }


    // ############################################
    // GETTER
    // ############################################

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    public int getVie(){
        return(this.vie);
    }
}
