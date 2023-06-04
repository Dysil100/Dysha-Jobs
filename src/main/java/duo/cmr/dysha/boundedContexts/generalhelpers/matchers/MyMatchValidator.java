package duo.cmr.dysha.boundedContexts.generalhelpers.matchers;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class MyMatchValidator<T> {
    private final String defautlRegex = "^(?!.*\\s{3})[a-zA-Z0-9@#$%^&+= ]+$";
    public boolean generalMatches(T object, String regex) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value != null && !Pattern.matches(regex, value.toString())) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean matches(T object) {
        return  true;
        //return generalMatches(object, defautlRegex);
    }

    public boolean matches(T object, String regex) {
        if (regex.isBlank()) {
            return generalMatches(object, defautlRegex);
        } else return matches(object);
    }
}