public class Student extends KeyableMap<Course> {

    public Student(String name) {
        super(name);
    }

    public Student(String name, ImmutableMap<String,Course> courseList) {
        super(name, courseList);
    }

    public Student put(Course course) {
        ImmutableMap<String,Course> newMap = this.getMap().put(course.getKey(), course);
        return new Student(this.getKey(), newMap);
    }
}
