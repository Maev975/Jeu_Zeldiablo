package test;

import gameLaby.laby.Monstre;
import gameLaby.laby.Perso;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Test52 {

    @Test
    public void test_attaquer_ok() {
        Perso p = new Perso(1, 2);
        Monstre m = new Monstre(2,2);

        p.attaquer(1,1,m);

        assertEquals(99, m.getVie());
        assertEquals(1, m.getFreeze());
    }

    @Test
    public void test_attaquer_freeze() {
        Perso p = new Perso(1, 2);
        Monstre m = new Monstre(2,2);

        p.subirFreeze(1);

        p.attaquer(1,1,m);

        assertEquals(100, m.getVie());
        assertEquals(0, m.getFreeze());
    }

    @Test
    public void test_subirFreeze_plusbas() {
        Perso p = new Perso(1, 2);
        Monstre m = new Monstre(2,2);

        m.setFreeze(5);

        p.attaquer(0,1,m);

        assertEquals(5, m.getFreeze());
    }

    @Test
    public void test_subirFreeze_plushaut() {
        Perso p = new Perso(1, 2);
        Monstre m = new Monstre(2,2);

        m.setFreeze(1);

        p.attaquer(0,3,m);

        assertEquals(3, m.getFreeze());
    }
}
