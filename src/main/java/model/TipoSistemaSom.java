package model;

import java.io.Serializable;

public enum TipoSistemaSom implements Serializable {
    NORMAL,
    DOLBY_ATMOS;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        switch (this) {
            case NORMAL:
                return "Normal";
            case DOLBY_ATMOS:
                return "Dolby Atmos";
            default:
                return super.toString();
        }
    }
}


