package me.skinnynoonie.bukkit.locationutils.shape;

import org.bukkit.Location;

public interface ShapeTemplate {

    boolean hasNext();

    void getNext(Location location);

    void reset();

}
