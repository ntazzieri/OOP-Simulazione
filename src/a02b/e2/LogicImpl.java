package a02b.e2;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicImpl implements Logic {

    private static final long N_STARS = 3L;
    private final Set<Pair<Integer, Integer>> starPos;
    private final Set<Pair<Integer, Integer>> disabledPos;
    private final int size;

    public LogicImpl(final int size) {
        this.size = size;
        this.starPos = new HashSet<>();
        this.disabledPos = new HashSet<>();
    }

    @Override
    public boolean hit(final Pair<Integer, Integer> pair) {
        if(starPos.contains(pair)) {
            starPos.remove(pair);
            return false;
        } else {
            starPos.add(pair);
            return true;
        }
    }

    @Override
    public boolean checkDiagonal() {
        final var possiblePosition = starPos.stream()
            .collect(Collectors.groupingBy(e -> e.getX() - e.getY()))
            .entrySet().stream()
            .filter(e -> e.getValue().stream().count() == N_STARS)
            .map(e -> e.getKey())
            .findFirst();
        if(possiblePosition.isPresent()) {
            disableDiagonal(possiblePosition.get());
            return true;
        }
        return false;
    }

    private void disableDiagonal(final int diagonalPos) {
        int j = 0;
        for (int i = diagonalPos; i < size; i++) {
            if (i >= 0) {
                disabledPos.add(new Pair<Integer,Integer>(i, j));
            }
            j++;
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getDisabledPositions() {
        return Set.copyOf(disabledPos);
    }

    @Override
    public void reset() {
        disabledPos.clear();
        starPos.clear();
    }
}
