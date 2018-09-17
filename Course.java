/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse104ass2;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hongyi.Wu
 */
public class Course {
String Coursename;
private Map<String, Integer> student = new HashMap<String, Integer>();
// add the map to store students' name and grades
public Course(String name){
    Coursename = name;
}

public void add(String name, int grade){
    student.put(name, grade);
}    // encapsulation

public void delete(String name){
    student.remove(name);
}    // encapsulation

public int get(String name){
    return student.get(name);
}    // encapsulation

public boolean contain(String name){
    return student.containsKey(name);
}    // encapsulation

public List sort(){    // the method to list grades in a course
    if (student.isEmpty()) {
        return null;
    }    // if there is no students, return null
    List list = new LinkedList(student.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer> >(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o1.getKey().compareTo(o2.getKey());
//if students have the same grades, sort them in alphabetical order
                } else {
                    return -(o1.getValue().compareTo(o2.getValue()));
//else sort them in grade descending order                    
                }
            }
        });
    return list;
}
}
