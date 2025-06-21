//Francisco Pedrosa - 2181248
package model;

import java.io.Serial;
import java.io.Serializable;

public enum TipoSistemaSom implements Serializable {
    NORMAL,
    DOLBY_ATMOS;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return switch (this) {
            case NORMAL -> "Normal";
            case DOLBY_ATMOS -> "Dolby Atmos";
        };
    }
}


