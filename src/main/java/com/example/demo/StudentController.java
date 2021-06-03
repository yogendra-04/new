package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import java.util.List;

@RestController
public class StudentController {

    private static  List<Students> stArray = new ArrayList<Students>();


    @PostConstruct
    public void init(){
        Students s1 = new Students(101,"name1","grade1");
        stArray.add(s1);
        Students s2 = new Students(102,"name2","grade2");
        stArray.add(s2);
        System.out.println(stArray.toString());
    }
     @GetMapping("/")
    public ResponseEntity<?> getStudents(){
        System.out.println("getmapping");
         System.out.println(stArray.toString());

        return ResponseEntity.ok(stArray);
    }
    //getstudentbyid createstudent update, delete,

    @PostMapping("/student")
    public Students addStudents(@RequestBody Students student) {
        stArray.add(student);
        System.out.println("adding");

        return  student;
    }
    @GetMapping("/student/{id}")
    public ResponseEntity getStudentsById(@PathVariable(value = "id") int id)
    {
        Students temp = null;
        for(int i = 0; i< stArray.size(); i++){
            if(stArray.get(i).getId() == id){
                temp = stArray.get(i);
                break;
            }
        }

        if(temp == null) {
            return new ResponseEntity<>("no student with given id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(temp, HttpStatus.OK);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity updateStudent(@RequestBody Students student, @PathVariable(value = "id") int id)
    {
        Students temp = null;
        for(int i = 0; i< stArray.size(); i++){
            if(stArray.get(i).getId() == id){

                temp = stArray.get(i);
                stArray.get(i).setName(student.getName());
                stArray.get(i).setGrade(student.getGrade());
                break;
            }
        }


        System.out.println("updating");
        if(temp == null) {
            return new ResponseEntity<>("no student with given id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("updated successfully", HttpStatus.OK);


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable(value="id") int id){
        Students temp = null;
        for(int i = 0; i< stArray.size(); i++){
            if(stArray.get(i).getId() == id){
                temp = stArray.get(i);
                break;
            }
        }

        if(temp == null) {
            return new ResponseEntity("no such id is present",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(stArray.remove(temp),HttpStatus.OK);
    }


}
