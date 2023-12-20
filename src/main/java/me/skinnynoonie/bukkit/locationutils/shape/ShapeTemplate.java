package me.skinnynoonie.bukkit.locationutils.shape;

import org.bukkit.util.Vector;

import java.util.Iterator;

public interface ShapeTemplate {
    Iterator<Vector> getPositionIterator();
}
