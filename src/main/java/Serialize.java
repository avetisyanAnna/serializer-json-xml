import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by User on 19.03.2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Serialize {
    String type();
}
