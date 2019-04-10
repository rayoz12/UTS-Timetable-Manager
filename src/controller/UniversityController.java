/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.util.Observer;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import model.Student;
import model.University;

/**
 *
 * @author Ryan
 */
public class UniversityController extends Controller<University>
{     
    @FXML ListView<Student> studentsListView;    
    @FXML Button removeStudent;
    @FXML Button login;
    
    University university;
    
    
    @FXML public void initialize()
    {
        university = getUniversity();
        studentsListView.setPlaceholder(new Label("No students"));
        Bindings.bindContentBidirectional(studentsListView.getItems(), university.getStudents());    
        
            studentsListView.getSelectionModel().selectedItemProperty().addListener((observer, oldVal, newVal) -> {
            removeStudent.setDisable(false);
            login.setDisable(false);
        });
    }
    
    @FXML private void removeStudent()
    {
        System.out.println("removing");
        Student studentToRemove = studentsListView.getSelectionModel().getSelectedItem();
        studentToRemove.unenrolFromAll();
        studentsListView.getItems().remove(studentToRemove);
        //check if there are no student left to select and disable buttons
        if (studentsListView.getItems().isEmpty())
        {
            removeStudent.setDisable(true);
            login.setDisable(true);
        }
    }
    
    @FXML private void login() throws Exception
    {
       int selectedStudent = studentsListView.getSelectionModel().getSelectedIndex();
       Student student = studentsListView.getItems().get(selectedStudent);
       //alt way
//       Object[] studentAndSubjects = new Object[2];       
//       studentAndSubjects[0] = student;
//       studentAndSubjects[1] = university.getSubjects();       
//       ViewLoader.showStage(studentAndSubjects, "/view/student.fxml", "Student", new Stage());

       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/student.fxml"));        
       Parent root = fxmlLoader.load();
       Stage stage = new Stage();
       stage.setTitle("Student");
       stage.setScene(new Scene(root));
       stage.sizeToScene();
       StudentController studentController = fxmlLoader.<StudentController>getController();
       studentController.SetSubjects(university.getSubjects());
       studentController.setStudent(student);        
       stage.show();
    }
    
    @FXML private void handleAddStudent (ActionEvent event) throws Exception 
    {        
        ViewLoader.showStage(university, "/view/add_student.fxml", "Add Student", new Stage());
    }

    public final University getUniversity() {
        return model;
    }
    
}
