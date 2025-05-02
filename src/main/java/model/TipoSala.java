package model;

public enum TipoSala {
    DOIS_D,
    TRES_D,
    IMAX;

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
