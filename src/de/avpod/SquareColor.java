package de.avpod;

/**
 * Created by apodznoev
 * date 09.11.2017.
 */
public enum SquareColor {
    BLACK {
        @Override
        public SquareColor invert() {
            return WHITE;
        }
    },
    WHITE {
        @Override
        public SquareColor invert() {
            return BLACK;
        }
    };

    public abstract SquareColor invert();
}
