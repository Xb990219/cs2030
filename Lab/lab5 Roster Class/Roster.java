public class Roster extends KeyableMap<Student> {
    
    public Roster(String name) {
        super(name);
    }

    public Roster(String name, ImmutableMap<String,Student> courseList) {
        super(name, courseList);
    }

    public Roster put(Student student) {
        ImmutableMap<String,Student> newMap = this.getMap().put(student.getKey(), student);
        return new Roster(this.getKey(), newMap);
    }

    /**
     * This is the method to find the grade of the assessment, if on return NOT FOUND.
     * @param studentID the student ID
     * @param courseCode the course ID
     * @param assesmentTitle the aassesment title
     * @return the grade of the assessment
     */
    public String getGrade(String studentID, String courseCode, String assesmentTitle) {
        String orElseString = "No such record: " + 
                              studentID + " " + 
                              courseCode + " " + 
                              assesmentTitle; 
        return this.getMap()
                   .get(studentID)
                   .flatMap(x -> x.get(courseCode))
                   .flatMap(x -> x.get(assesmentTitle))
                   .map(x -> x.getGrade())
                   .orElse(orElseString);
    }

    /**
     * This is the method to add new student, course, or assessment.
     * @param studentID student ID  
     * @param courseCode course code
     * @param assesmentTitle assessment title
     * @param grade grade
     * @return the new roster with new information
     */
    public Roster add(String studentID, 
                      String courseCode, 
                      String assesmentTitle, 
                      String grade) {
        Student newStudent = this.get(studentID).orElse(new Student(studentID));
        Course newCourse = newStudent.get(courseCode).orElse(new Course(courseCode));
        Assessment newAssessment = new Assessment(assesmentTitle, grade);
        return this.put(newStudent.put(newCourse.put(newAssessment)));
    }
}
