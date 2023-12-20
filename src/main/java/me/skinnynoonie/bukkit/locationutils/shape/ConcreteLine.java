package me.skinnynoonie.bukkit.locationutils.shape;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConcreteLine implements ShapeTemplate {
    private final Vector displacement;
    private final double incrementLength;

    public ConcreteLine(@NotNull Location start, @NotNull Location end, double incrementLength) {
        Preconditions.checkNotNull(start, "Parameter start is null.");
        Preconditions.checkNotNull(end, "Parameter end is null.");
        Preconditions.checkState(incrementLength > 0, "Parameter incrementLength must be greater than 0.");
        this.displacement = end.clone().subtract(start).toVector();
        this.incrementLength = incrementLength;
    }

    @Override
    public @NotNull Iterator<@NotNull Vector> getPositionIterator() {
        return new ConcreteLinePositionIterator(this.displacement, this.incrementLength);
    }

    private static class ConcreteLinePositionIterator implements Iterator<Vector> {
        private final int totalIterations;
        private int currentIteration;

        private final double incrementX;
        private final double incrementY;
        private final double incrementZ;

        private ConcreteLinePositionIterator(Vector end, double incrementLength) {
            double displacementLength = end.length();
            this.totalIterations = (int) Math.floor(displacementLength / incrementLength);
            this.incrementX = end.getX() / displacementLength * incrementLength;
            this.incrementY = end.getY() / displacementLength * incrementLength;
            this.incrementZ = end.getZ() / displacementLength * incrementLength;
            this.currentIteration = 0;
        }

        @Override
        public boolean hasNext() {
            return this.currentIteration < totalIterations;
        }

        @Override
        public Vector next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            Vector nextPosition = new Vector();
            nextPosition.setX(this.incrementX * this.currentIteration);
            nextPosition.setY(this.incrementY * this.currentIteration);
            nextPosition.setZ(this.incrementZ * this.currentIteration);

            this.currentIteration++;
            return nextPosition;
        }
    }
}
