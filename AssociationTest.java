import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTest {
    Association prueba= new Association("dog","perro");

    @Test
    void getEnglish() {
        assertEquals("dog", prueba.getEnglish());
    }

    @Test
    void getSpanish() {
        assertEquals("perro", prueba.getSpanish());
    }
}