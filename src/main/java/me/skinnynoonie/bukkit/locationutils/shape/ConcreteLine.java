package me.skinnynoonie.bukkit.locationutils.shape;

import me.skinnynoonie.bukkit.locationutils.exception.IllegalShapePropertiesException;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record ConcreteLine(Vector displacement, double incrementLength) implements ShapeTemplate {

    public ConcreteLine(@NotNull Vector displacement, double incrementLength) {
        Objects.requireNonNull(displacement, "Parameter displacement is null.");
        if (incrementLength <= 0) {
            throw new IllegalShapePropertiesException("Property incrementLength must be greater than 0.");
        }
        this.displacement = displacement.clone();
        this.incrementLength = incrementLength;
    }

    @Override
    public @NotNull List<@NotNull Vector> calculatePositions() {
        List<Vector> vectors = new ArrayList<>();

        vectors.add(new Vector(0, 0, 0));
        Vector displacementNormalized = this.displacement.clone().normalize();
        int iterationsTillDisplacement = (int) Math.floor(displacementNormalized.length() / this.incrementLength);
        for (int i = 0; i < iterationsTillDisplacement; i++) {
            vectors.add(displacementNormalized.clone().multiply(i * this.incrementLength));
        }
        vectors.add(this.displacement.clone());

        return vectors;
    }

    @Override
    public @NotNull Vector displacement() {
        return this.displacement.clone();
    }
}
