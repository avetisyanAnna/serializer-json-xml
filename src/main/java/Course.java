class Course {

    //@ElementIgnore
    private int courseId;

    @Element()
    private String courseName;

    Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void foo(int x){

    }
}