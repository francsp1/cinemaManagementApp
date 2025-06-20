package view;

import java.lang.reflect.Field;

public class TestUtils {

    public static Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao aceder ao campo " + fieldName, e);
        }
    }
}
