package me.skinnynoonie.bukkit.locationutils.vector;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class SphericalVector implements CustomVector {
    private double radius;
    private float yaw;
    private float pitch;

    /**
     * Creates a spherical vector with radius 1, yaw 0 degrees, and pitch 0 degrees.
     */
    public SphericalVector() {
        this(1, 0, 0);
    }

    /**
     * Creates a spherical vector with radius 1.
     */
    public SphericalVector(float yaw, float pitch) {
        this(1, yaw, pitch);
    }

    public SphericalVector(double radius, float yaw, float pitch) {
        this.radius = radius;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public @NotNull Vector toBukkitVector() {
        double conventionalYaw = this.yaw + 90;
        double conventionalPitch = this.pitch * -1;

        double radiansYaw = toRadians(conventionalYaw);
        double radiansPitch = toRadians(conventionalPitch);

        double vectorProjectionLength = cos(radiansPitch) * this.radius;
        double x = cos(radiansYaw) * vectorProjectionLength;
        double y = sin(radiansPitch) * this.radius;
        double z = sin(radiansYaw) * vectorProjectionLength;

        return new Vector(x, y, z);
    }

    public double getRadius() {
        return this.radius;
    }

    public @NotNull SphericalVector setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public @NotNull SphericalVector addRadius(double radius) {
        return setRadius(this.radius + radius);
    }

    public float getYaw() {
        return this.yaw;
    }

    public @NotNull SphericalVector setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }

    public @NotNull SphericalVector addYaw(float yaw) {
        return setYaw(this.yaw + yaw);
    }

    public float getPitch() {
        return this.pitch;
    }

    public @NotNull SphericalVector setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public @NotNull SphericalVector addPitch(float pitch) {
        return setPitch(this.pitch + pitch);
    }
}
