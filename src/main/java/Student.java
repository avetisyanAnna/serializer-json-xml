//import org.simpleframework.xml.Element;
@Serialize(type = "json")
class Student {

    @Element(name = "_firstName")
    private String firstName;

    private String lastName;

    @Element(name = "_course")
    private Course course;

    Student(String firstName, String lastName, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Course getCourse() {
        return course;
    }
}
