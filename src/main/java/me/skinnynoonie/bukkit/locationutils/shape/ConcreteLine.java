package me.skinnynoonie.bukkit.locationutils.shape;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

public final class ConcreteLine implements ShapeTemplate {

    private final Location start;
    private final Location end;
    private final Vector incrementVector;

    private final int totalIterations;
    private int currentIteration;

    public ConcreteLine(@NotNull Location start, @NotNull Location end, double incrementLength) {
        this.start = start.clone();
        this.end = end.clone();

        this.incrementVector = start.clone().subtract(end).toVector();
        this.totalIterations = (int) Math.floor(this.incrementVector.length() / incrementLength) + 1; // +1 because of the end location.
        this.incrementVector.normalize().multiply(incrementLength);

        this.currentIteration = 0;
    }

    @Override
    public boolean hasNext() {
        return this.totalIterations > currentIteration;
    }

    @Override
    public void getNext(Location location) {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.totalIterations - 1 == this.currentIteration) {
            location.setX(this.end.getX());
            location.setY(this.end.getY());
            location.setZ(this.end.getZ());
        } else {
            location.setX(this.start.getX() + this.currentIteration * this.incrementVector.getX());
            location.setY(this.start.getY() + this.currentIteration * this.incrementVector.getY());
            location.setZ(this.start.getZ() + this.currentIteration * this.incrementVector.getZ());
        }
        this.currentIteration++;
    }

    @Override
    public void reset() {
        this.currentIteration = 0;
    }

}
