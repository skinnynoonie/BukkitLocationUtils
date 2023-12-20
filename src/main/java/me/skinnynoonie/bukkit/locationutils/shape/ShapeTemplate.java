package me.skinnynoonie.bukkit.locationutils.shape;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface ShapeTemplate {
    @NotNull Iterator<@NotNull Vector> getPositionIterator();
}
