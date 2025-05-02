package model;

public enum TipoSistemaSom {
    NORMAL,
    DOLBY_ATMOS;

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


