package test;

import gameLaby.laby.Monstre;
import gameLaby.laby.Perso;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test61 {
    @Test
    public void test_etreACote_vrai() {
        Perso p = new Perso(1, 2);
        Monstre m = new Monstre(2,2);

        boolean test = p.etreACote(m);

        assertEquals(true, test);
    }

    @Test
    public void test_etreACote_faux() {
        Perso p = new Perso(4, 2);
        Monstre m = new Monstre(2,2);

        boolean test = p.etreACote(m);

        assertEquals(false, test);
    }

    @Test
    public void test_subirDegat() {
        Perso p = new Perso(0, 0);

        p.subirAttaque(1);

        int test = p.getVie();

        assertEquals(4, test);
    }

    @Test
    public void test_etreMort_vrai() {
        Perso p = new Perso(0, 0);

        p.subirAttaque(5);

        boolean test = p.etreMort();

        assertEquals(true, test);
    }

    @Test
    public void test_etreMort_faux() {
        Perso p = new Perso(0, 0);


        boolean test = p.etreMort();

        assertEquals(false, test);
    }
}
