package smo.lot;

import java.util.Random;

public class PlaneUtils {
    private static int id = 0;

    public static int generatePrority() {
        double abs = Math.abs(new Random().nextGaussian() * 100);
        if (abs < 5) {
            return 0;
        } else if (abs >= 10 && abs < 50) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int generateId() {
        PlaneUtils.id++;
        return PlaneUtils.id;
    }

    public static double generateTime() {
        return Math.round(Math.abs(new Random().nextGaussian() % 10))+1;
    }
}
