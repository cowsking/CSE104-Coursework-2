/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse104ass2;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Hongyi.Wu
 */
public class Menu {
public static void main(String[] args){
    // TODO code application logic here
    Menu test = new Menu();
    //start the program
    while (true)
        test.start();
}
Scanner s = new Scanner(System.in);
Func main = new Func();
public void showMenu(){
    System.out.println();
    System.out.println(
        "Welcome to the students’ grades system, functions provided include the following:");
    System.out.println(
        "o Add    – to add a new student name together with course/grade info into the system");
    System.out.println(
        "o Update – to update the grade of an existing course of a student");
    System.out.println(
        "o Search – to enquire about the grades of a specific student in the system");
    System.out.println("o Delete – to deleteastudent’s info");
    System.out.println(
        "o List   – to list all grades of a specific course in descending order");
    System.out.println(
        "o Quit   – to exit from the current level of interactions");
    System.out.println();
}
public String ignoreUpperCase(String input){
    return input.toLowerCase();
}//ignore each input about the capitalization
public Course select(String coursename){
    if (coursename.equals("data structure"))
        return main.DataStructure;
    if (coursename.equals("database"))
        return main.Database;
    if (coursename.equals("mathematics"))
        return main.Mathematics;
    if (coursename.equals("operating system"))
        return main.OperatingSystem;
    if (coursename.equals("system design"))
        return main.SystemDesign;
    else
        return null;
}//match input with courses
public void start(){
    showMenu();
//firstly show the menu
    String in = ignoreUpperCase(s.nextLine());
//input ignores capitalization
    switch (in) {
        case "add":
            System.out.print("Enter student’s name: ");

            String addname = ignoreUpperCase(s.nextLine());
//judge if it is in the system
            for (Course course : main.courses) {
                if (course.contain(addname)) {
                    System.out.println(addname + " is already in the system.");
                    break;
                }
            }
            while (true) {
                Course addcourse = askcourse();

                if (addcourse == null) {
                    System.out.println("stop adding!");
                    break;
                }
                if (!addcourse.contain(addname)) {
                    int addgrade = askgrade();

                    addcourse.add(addname, addgrade);
                } else {
                    System.out.println(
                        "there is already the grade in this course");
                }
            }
            System.out.print("new entered: " + addname + "  ");
            for (Course course : main.courses) {
                if (course.contain(addname))
                    System.out.print(course.Coursename + "/" + course.get(
                                         addname) + " ");
            }
            break;

        case "update":
            System.out.print("Enter student’s name: ");

            String updatename = ignoreUpperCase(s.nextLine());
//input ignores capitalization
            if (!checkExist(updatename))
                break;
//if no this name in system, stop it
            Course updatecourse = askcourse();
            
            while (updatecourse == null)
                updatecourse = askcourse();
//if input a blank, continues to ask
            if (updatecourse.contain(updatename)) {
                int updaterecord = updatecourse.get(updatename);
                int updategrade = askgrade();
//then ask the grade
                updatecourse.add(updatename, updategrade);
                System.out.print("student" + updatename + ", " +
                                 updatecourse.Coursename + " grade updated from " +
                                 updaterecord + " to " + updategrade);
            } else {
                System.out.println("There is no grade to update");
            }
            break;

        case "search":
            System.out.print("Enter student’s name: ");

            String searchname = ignoreUpperCase(s.nextLine());
            Course searchcourse = askcourse();
//if blank, search all the courses
            if (searchcourse == null) {
                int sum = 0;
                int counter = 0;
                System.out.print("Student: " + searchname + " ");
                for (Course course : main.courses) {
                    if (course.contain(searchname)){
                        System.out.print(course.Coursename + "/" + course.get(
                                             searchname) + "; ");
                        sum += course.get(searchname); 
                        counter++;
                    }
                        
                }
                System.out.print("Average/"+(sum/counter)+'.'); 
            } else {
                System.out.print("Student: " + searchname + " ");
                if (searchcourse.contain(searchname)){
                    System.out.print(searchcourse.Coursename + "/" +
                                     searchcourse.get(searchname) + "; ");
                }   
            }
//else, search the focus course                 
            break;

        case "list":

            Course listcourse = askcourse();

            while (listcourse == null)
                listcourse = askcourse();
//if input a blank, continues to ask
            List sorted = listcourse.sort();

            if (sorted == null) {
                System.out.println("No grades in this course.");
                break;
            }//special situations
            System.out.print(listcourse.Coursename + ": ");

            int sum = 0;

            for (Object s : sorted) {
                Map.Entry temp = (Map.Entry)s;
                sum += (int)temp.getValue();
//display the result of the program
                System.out.print(temp.getKey() + "/" + temp.getValue() + "; ");
            }

            int avg = sum / (sorted.size());
//caculate the average score
            System.out.print("Average/" + avg + ".");
            break;

        case "delete":
            System.out.print("Enter student’s name: ");

            String deletename = ignoreUpperCase(s.nextLine());
            Course deletecourse = askcourse();

            if (!checkExist(deletename)) {
                break;
//no this name then stop
            } else if (deletecourse == null) {
                System.out.print("Course deleted: " + deletename + "; ");
                main.delete(deletename);
                //delete all the course of the student
            } else {
                if (deletecourse.contain(deletename)) {
                    System.out.print("Course deleted: " + deletename + "; " +
                                     deletecourse.Coursename + "/" + deletecourse.get(
                                         deletename) + "; ");
                    deletecourse.delete(deletename);
                } else {
                    System.out.print("No " + deletename + " found.");
                }
            }
//find  the course of the student and delete it
            break;

        case "quit":
            System.out.println("Bye-Bye");
            System.exit(0);
            break;

        default:
            System.out.println("Please enter the above commands.");
    }                                   
    s.nextLine();
}                                       
public Course askcourse(){
//ask the course including in demand, if it is not, ask again
    boolean flag = false;
    String course;

    do {
        flag = false;
        System.out.print("Enter course name(can be blank): ");
        course = ignoreUpperCase(s.nextLine());
        if (course.equals(""))
            return null;
        if (select(course) == null) {
            System.out.println("no this course, please input again");
            flag = true;
        }
    } while (flag);
    return select(course);
}
public int askgrade(){
//ask to input a valid grade
    boolean con = false;
    int number = 0;

    do {
        System.out.print("NewGrade: ");

        String in = s.nextLine();

        try {
            number = Integer.parseInt(in);
            con = false;
        } catch (NumberFormatException e) {
            con = true;
        }
        if (!((number >= 0) && (number <= 100)))        
// judge if it is from 0 to 100
            con = true;
    } while (con);                                      
// if not, input again
    return number;
}
public boolean checkExist(String studentname){
//check if the student exists in system
    boolean flag = false;

    for (Course course : main.courses) {
        if (course.contain(studentname))
            flag = true;
    }
    if (flag == false) {
        System.out.println("No " + studentname + " found");
        return false;
    }
    return true;
}
}
