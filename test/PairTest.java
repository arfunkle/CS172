import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PairTest {

    @Test
    void constructorWorks(){
        Pair a = new Pair(1,1);
        assertEquals(a, a);
    }

    @Test
    void equalsWorks(){
        Pair a = new Pair(2,3);
        Pair b = new Pair(2,3);
        assertEquals(true, a.equals(b));
    }


    @Test
    void toStringWorks() {
        Pair x = new Pair(1,2);
        assertEquals("<2, 1>", x.toString());
    }

    @Test
    void addWorks(){
        Pair a = new Pair(1,1);
        Pair b = new Pair(2,2);
        a.addPair(b);
        assertEquals("<3, 3>",  a.addPair(b).toString());
    }

    @Test
    void flipDirectionWorks(){
        Pair a = new Pair(-1,1);
        assertEquals("<-1, 1>", a.flipDirection().toString());
    }
}
