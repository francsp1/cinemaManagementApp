package model;

import java.io.Serializable;

public enum TipoSala implements Serializable {
    DOIS_D,
    TRES_D,
    IMAX;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        switch (this) {
            case DOIS_D:
                return "2D";
            case TRES_D:
                return "3D";
            case IMAX:
                return "IMAX";
            default:
                return super.toString();
        }
    }
}
