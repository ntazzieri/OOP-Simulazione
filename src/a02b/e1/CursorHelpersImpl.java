package a02b.e1;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CursorHelpersImpl implements CursorHelpers {

    static private class CursorImpl<X> implements Cursor<X> {
        private final Iterator<X> iterator;
        private X lastElem; 

        public CursorImpl(final Iterator<X> iterator) {
            this.iterator = iterator;
            lastElem = this.iterator.next();
        }

        @Override
        public X getElement() {
            return lastElem;
        }

        @Override
        public boolean advance() {
            if (iterator.hasNext()) {
                lastElem = iterator.next();
                return true;
            }
            return false;
        }

    }

    @Override
    public <X> Cursor<X> fromNonEmptyList(final List<X> list) {
        return new CursorImpl<>(list.iterator());
    }

    @Override
    public Cursor<Integer> naturals() {
        return new CursorImpl<>(Stream.iterate(0, i -> i + 1).iterator());
    }

    @Override
    public <X> Cursor<X> take(final Cursor<X> input, final int max) {
        final List<X> tmp = new ArrayList<>();
        tmp.add(input.getElement());
        for (int i = 0; i < max - 1 && input.advance(); i++) {
            tmp.add(input.getElement());
        }
        return new CursorImpl<>(tmp.iterator());
        // return new CursorImpl<>(Stream.iterate(input.getElement(), e -> input.advance(), e -> input.getElement()).limit(max).iterator());
    }

    @Override
    public <X> void forEach(final Cursor<X> input, final Consumer<X> consumer) {
        do  {
            consumer.accept(input.getElement());
        } while(input.advance());
        //Stream.iterate(input.getElement(), e -> input.advance(), e -> input.getElement()).forEach(consumer);
    }

    @Override
    public <X> List<X> toList(final Cursor<X> input, final int max) {
        final List<X> ret = new ArrayList<>();
        ret.add(input.getElement());
        for (int i = 0; i < max - 1 && input.advance(); i++) {
            ret.add(input.getElement());
        }
        return ret;
        //return Stream.iterate(input.getElement(), e -> input.advance(), e -> input.getElement()).limit(max).toList();
    }    
}
