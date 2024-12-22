package a02b.e2;

import java.util.Set;

public interface Logic {

    boolean hit(Pair<Integer, Integer> pair);

    boolean checkDiagonal();

    Set<Pair<Integer, Integer>> getDisabledPositions();

    void reset();

}
