package model;

/**
 * Created by brian on 13/03/17.
 */
public enum Surface implements ISurfaceType {

    CEMENT {
        @Override
        public double setDragFactor(double dragFactor) {
            return 0.75;
        }
    },
    ASPHALT {
        @Override
        public double setDragFactor(double dragFactor) {
            return 0.9;
        }

    },
    GRAVEL {
        @Override
        public double setDragFactor(double dragFactor) {
            return 0.8;

        }
    },
    SNOW {
        @Override
        public double setDragFactor(double dragFactor) {
            return 0.25;

        }
    },
    ICE {
        @Override
        public double setDragFactor(double dragFactor) {
            return 0.55;
        }
    }
}
