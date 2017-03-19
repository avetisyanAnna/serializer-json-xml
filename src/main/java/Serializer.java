import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class Serializer {
    private static File file;

    static void serialize(Object object) throws IOException, IllegalAccessException, AnnotationTypeException {
        StringBuilder builder = new StringBuilder();
        Serialize serialize = object.getClass().getAnnotation(Serialize.class);
        String type = serialize.type();
        if("xml".equals(type)) {
            getXml(builder, object);
            file = new File("C:\\Users\\User\\IdeaProjects\\SerializerJsonXml\\Student.xml");
        }else if("json".equals(type)){
            builder.append("{\n");
            getJson(builder,object);
            builder.append("\n}\n");
            file = new File("C:\\Users\\User\\IdeaProjects\\SerializerJsonXml\\Student.json");
        }else{
            throw new AnnotationTypeException(type);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(builder.toString());
        writer.flush();
    }

    private static void getXml(StringBuilder builder, Object object) throws IllegalAccessException {

        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        builder.append("<").append(clazz.getSimpleName()).append(">").append("\n");
        for (Field field : fields) {
            Annotation elementIgnore = field.getAnnotation(ElementIgnore.class);
            if (elementIgnore != null)
                continue;

            Element element = field.getAnnotation(Element.class);
            String fieldName = element != null && !element.name().equals("") ?
                    element.name() : field.getName();

            field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (isPrimitive(fieldValue)) {
                builder.append("<").append(fieldName).append(">");
                builder.append(fieldValue);
                builder.append("</").append(fieldName).append(">").append("\n");
            } else {
                getXml(builder, fieldValue);
            }
        }
        builder.append("</").append(clazz.getSimpleName()).append(">").append("\n");
    }

    private static void getJson(StringBuilder builder, Object object) throws IllegalAccessException {

        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        builder.append("\"").append(clazz.getSimpleName()).append("\": {").append("\n");
        String prefix = "";
        for (Field field : fields) {

            builder.append(prefix);
            prefix = ",\n";
            Annotation elementIgnore = field.getAnnotation(ElementIgnore.class);
            if (elementIgnore != null) {
                prefix = "";
                continue;
            }

            Element element = field.getAnnotation(Element.class);
            String fieldName = element != null && !element.name().equals("") ?
                    element.name() : field.getName();

            field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (isPrimitive(fieldValue)) {
                builder.append("\"").append(fieldName).append("\": ").append("\"").append(fieldValue).append("\"");
            } else {
                getJson(builder, fieldValue);
            }
        }
        builder.append("\n}");
    }

    private static boolean isPrimitive(Object var0) {
        return var0 instanceof Boolean
                || var0 instanceof Byte
                || var0 instanceof Character
                || var0 instanceof Short
                || var0 instanceof Integer
                || var0 instanceof Long
                || var0 instanceof Float
                || var0 instanceof Double
                || var0 instanceof String;
    }

}
class AnnotationTypeException extends Exception{

    public AnnotationTypeException(String type){
        System.out.println(type + " - Incorrect type");
    }
}