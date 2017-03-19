import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException,
            IOException, NoSuchMethodException, InvocationTargetException {

        Course course = new Course(2, "Cryptography");
        Student student = new Student("John", "Smith", course);
        try {
            Serializer.serialize(student);
        } catch (AnnotationTypeException e) {
            e.printStackTrace();
        }
    }
}
