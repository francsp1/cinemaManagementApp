package model;

import java.io.Serial;
import java.io.Serializable;

public enum TipoSala implements Serializable {
    DOIS_D,
    TRES_D,
    IMAX;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return switch (this) {
            case DOIS_D -> "2D";
            case TRES_D -> "3D";
            case IMAX -> "IMAX";
        };
    }
}
