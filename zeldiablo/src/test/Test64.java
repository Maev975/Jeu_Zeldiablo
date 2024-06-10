package test;

import gameLaby.laby.Monstre;
import gameLaby.laby.Perso;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test64 {
    @Test
    public void test_avoirAmulette_vrai() {
        Perso p = new Perso(1, 2);

        p.recupererAmulette();
        boolean test = p.avoirAmulette();

        assertEquals(true, test);
    }

    @Test
    public void test_avoirAmulette_faux() {
        Perso p = new Perso(1, 2);

        boolean test = p.avoirAmulette();

        assertEquals(false, test);
    }

    @Test
    public void test_jeterAmulette_vrai() {
        Perso p = new Perso(1, 2);

        p.recupererAmulette();
        p.jeterAmulette();
        boolean test = p.avoirAmulette();

        assertEquals(false, test);
    }

    @Test
    public void test_jeterAmulette_faux() {
        Perso p = new Perso(1, 2);

        p.jeterAmulette();
        boolean test = p.avoirAmulette();

        assertEquals(false, test);
    }
}
