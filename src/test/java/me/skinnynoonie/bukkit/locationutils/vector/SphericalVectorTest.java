package me.skinnynoonie.bukkit.locationutils.vector;

import org.bukkit.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SphericalVectorTest {

    @Test
    void testConversionToBukkitVector() {
        Location location = new Location(null, 0, 0 ,0);

        for (int yaw = -94; yaw <= 689; yaw++) {
            for (int pitch = -684; pitch <= 904; pitch++) {
                location.setYaw(yaw);
                location.setPitch(pitch);
                SphericalVector sphericalVector = new SphericalVector(yaw, pitch);
                assertEquals(location.getDirection(), sphericalVector.toBukkitVector());
            }
        }
    }

}