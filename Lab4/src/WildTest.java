
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class WildTest {

    @Test
    public void MaxTimeStepsTest() { // testing the number of simulation time steps
        final int MAXT = 3;
        int count = 0;
        for (int t = 0; t < MAXT; t++) {
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    public void SizeTest() { // Testing if thr size is correct
        final int MAXX = 2, MAXY = 4;
        int count = 0;
        Wild map[][] = new Wild[MAXX][MAXY];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                count++;
            }
        }
        assertEquals(8, count);
    }

    @Test
    public void PopulatedTest() { // Testing if every cell has a deer or a wolf in it
        final int MAXX = 2, MAXY = 4;
        Wild wild = new Wild();
        Wild map[][] = new Wild[MAXX][MAXY];
        wild.initallize(map);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                assertTrue(map[i][j] instanceof Deer || map[i][j] instanceof Wolf);
            }
        }

    }

    @Test
    public void EatTest() { // Testing if the eat method replaces the deer cell with a wolf and makes the
                            // cell the wolve came from null
        Wolf wolf = new Wolf();
        Wild map[][] = new Wild[1][2];

        map[0][0] = wolf;
        wolf.eat(0, 0, 0, 1, map);

        assertTrue(map[0][1] instanceof Wolf);
        assertTrue(map[0][0] == null);

    }

    @Test
    public void MoveTest() { // Testing if move method moves deer and wolf over and makes the cell they came
                             // from null
        Animal ani = new Animal();
        Deer deer = new Deer();
        Wolf wolf = new Wolf();
        Wild map[][] = new Wild[2][2];

        map[0][0] = deer;
        map[1][0] = wolf;

        ani.move(0, 0, 0, 1, map, deer);
        assertTrue(map[0][1] instanceof Deer);
        assertTrue(map[0][0] == null);
        ani.move(1, 0, 1, 1, map, wolf);
        assertTrue(map[1][1] instanceof Wolf);
        assertTrue(map[1][0] == null);

    }

    @Test
    public void MoveDeerTest() { // testing all directions of move deer
        Deer deer = new Deer();
        Wild map[][] = new Wild[2][1];

        // Test moving down
        deer.movedeer(0, 0, 0, map);
        assertTrue(map[0][0] == null);
        assertTrue(map[1][0] instanceof Deer);

        Wild map2[][] = new Wild[1][2];
        // Test moving right
        deer.movedeer(0, 0, 1, map2);
        assertTrue(map2[0][0] == null);
        assertTrue(map2[0][1] instanceof Deer);

        // Test moving up
        deer.movedeer(1, 0, 2, map);
        assertTrue(map[1][0] == null);
        assertTrue(map[0][0] instanceof Deer);

        // Test moving left
        deer.movedeer(0, 1, 3, map2);
        assertTrue(map2[0][1] == null);
        assertTrue(map2[0][0] instanceof Deer);

    }

    @Test
    public void MoveWolfTest() { // testing all directions of move wolf
        Wolf wolf = new Wolf();
        Wild map[][] = new Wild[2][1];

        // Test moving down
        wolf.movewolf(0, 0, 0, map);
        assertTrue(map[0][0] == null);
        assertTrue(map[1][0] instanceof Wolf);

        Wild map2[][] = new Wild[1][2];
        // Test moving right
        wolf.movewolf(0, 0, 1, map2);
        assertTrue(map2[0][0] == null);
        assertTrue(map2[0][1] instanceof Wolf);

        // Test moving up
        wolf.movewolf(1, 0, 2, map);
        assertTrue(map[1][0] == null);
        assertTrue(map[0][0] instanceof Wolf);

        // Test moving left
        wolf.movewolf(0, 1, 3, map2);
        assertTrue(map2[0][1] == null);
        assertTrue(map2[0][0] instanceof Wolf);

    }

    @Test
    public void DirectionTest() {
        Wild w = new Wild();
        Wild map[][] = new Wild[1][1];

        w.Sim(map);
        assertTrue(map[0][0] == null);

    }

    @Test
    public void DisplayTest() {
        Wild w = new Wild();
        Wild map[][] = new Wild[1][1];

        w.initallize(map);
        PrintStream oldOut = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        w.display(map);
        String output = new String(baos.toString());

        assertTrue(output.contains("W ") || output.contains("D "));

        System.setOut(oldOut);

    }
}