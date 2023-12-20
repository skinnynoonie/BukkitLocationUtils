package me.skinnynoonie.bukkit.locationutils.shape;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface ShapeTemplate {
    @NotNull Iterator<@NotNull Vector> getPositionIterator();

    default @NotNull List<@NotNull Vector> getPositionList() {
        List<Vector> positions = new ArrayList<>();
        Iterator<Vector> iterator = this.getPositionIterator();
        while (iterator.hasNext()) {
            positions.add(iterator.next());
        }
        return positions;
    }
}
