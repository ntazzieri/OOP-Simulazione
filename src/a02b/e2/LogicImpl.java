package a02b.e2;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicImpl implements Logic {

    private final Set<Pair<Integer, Integer>> starPos;
    private final Set<Pair<Integer, Integer>> disabledPos;
    private final int size;

    public LogicImpl(int size) {
        this.size = size;
        this.starPos = new HashSet<>();
        this.disabledPos = new HashSet<>();
    }


    @Override
    public boolean hit(Pair<Integer, Integer> pair) {
        if(starPos.contains(pair)) {
            starPos.remove(pair);
            return false;
        } else {
            starPos.add(pair);
            return true;
        }
    }


    @Override
    public boolean check() {
        int count = 0;
        /* for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (starPos.contains(new Pair<Integer,Integer>(j, j))) {
                    count++;
                }
            }
        } */
        /*IntStream.range(0, size)
            .forEach(i -> IntStream.range(0, size)
                .forEach(j -> starPos.stream()
                    .collect(Collectors.groupingBy(p -> Math.abs(p.getX() - p.getY() == 1, )))));*/

        /*starPos.stream()
            .collect(Collectors.groupingBy(e -> e.getX() - e.getY()))
            .entrySet()
            .stream()
            .filter(e -> e.getKey() == 1)
            .collect(Collectors.groupingBy()); */

        throw new UnsupportedOperationException("Unimplemented method 'check'");
    }


    @Override
    public Set<Pair<Integer, Integer>> getDisabledPositions() {
        return Set.copyOf(disabledPos);
    }


    @Override
    public void reset() {
        disabledPos.clear();
    }
}
