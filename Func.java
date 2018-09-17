/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse104ass2;

/**
 *
 * @author Hongyi.Wu
 */
public class Func {
Course Database = new Course("Database");
Course DataStructure = new Course("Data Structure");
Course OperatingSystem = new Course("Operating System");
Course Mathematics = new Course("Mathematics");
Course SystemDesign = new Course("System Design");
Course[] courses = { Database, DataStructure, OperatingSystem, Mathematics, SystemDesign };
//create five courses and put in an array
public void add(Course course, String student, int grade){
    course.add(student, grade);
}//store the student and the grade in a coruse

public void delete(String student){
    for (Course course : courses) {
        if (course.contain(student)) {
            System.out.print(course.Coursename + "/" + course.get(student) + "; ");
            course.delete(student);
        }
    }
}////delete the student and the grade in a coruse

public void delete(String student, Course course){
    if (course.contain(student))
        course.delete(student);
//delte a student in one course
}
}
