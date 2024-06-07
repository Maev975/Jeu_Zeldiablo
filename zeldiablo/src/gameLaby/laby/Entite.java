package gameLaby.laby;

/**
 *Classe abstraite qui représente les entités qui sont dans le labyrinthe
 */

public abstract class Entite {

    /**
     * Position de l'entité
     */
    protected int x, y;
    protected int vie;

    protected int freeze;
    /**
     * Constructeur de l'entité
     *
     * @param dx Position selon x
     * @param dy Position selon y
     */
    public Entite(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.vie = 0;
        this.freeze = 0;
    }

    /**
     * Vérifie si l'entité est présente à une certaine position
     *
     * @param dx Position x testée
     * @param dy Position y testée
     * @return vrai si l'entité est en (dx, dy)
     */
    public boolean etrePresent(int dx, int dy){
        return (this.x == dx && this.y == dy);
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
     * méthode etreMort
     *
     * renvoie true si le personnage a moins de 5 points de vie
     *
     * @return
     */
    public boolean etreMort(){
        return(this.vie <= 0);
    }

    public boolean etreFreeze(){
        return(this.freeze > 0);
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

    public void attaquer(int d, int f, Entite e){
        if(!this.etreFreeze()) {
            e.subirAttaque(d);
            e.setFreeze(f);
        }
    }

    public void subirFreeze(int f){
        if (this.freeze < f)
            this.freeze = f;
    }

    // ############################################
    // GETTER / SETTER
    // ############################################

    /**
     * @return Position x de l'entité
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return Position y de l'entité
     */
    public int getY() {
        return this.y;
    }

    public void setX(int xn){
        this.x = xn;
    }

    public void setY(int yn){
        this.y = yn;
    }

    public int getVie(){
        return(this.vie);
    }

    public int getFreeze(){
        return(this.freeze);
    }

    public void setFreeze(int f){
        this.freeze = f;
    }

}
