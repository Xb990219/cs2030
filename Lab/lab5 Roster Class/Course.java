public class Course extends KeyableMap<Assessment> {
    
    public Course(String name) {
        super(name);
    }

    public Course(String name, ImmutableMap<String,Assessment> assesmentList) {
        super(name,assesmentList);
    }

    /**
     * Put a new assessment into the course assessmentmap.
     * @param assessment assessment
     * @return the new course
     */
    public Course put(Assessment assessment) {
        ImmutableMap<String, Assessment> newMap = this.getMap()
                                                      .put(assessment.getKey(), 
                                                           assessment);
        return new Course(this.getKey(), newMap);
    }
}
