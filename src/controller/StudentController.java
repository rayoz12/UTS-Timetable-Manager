/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Activity;
import model.Student;
import model.Subject;

/**
 *
 * @author Ryan
 */
public class StudentController{
    Student student;
    ObservableList<Subject> subjects; 
    
    @FXML Text welcomeMessage;
    @FXML Text infoNumber;
    @FXML Text infoAttendance;
    @FXML Text infoScholarship;
    
    @FXML ComboBox<Subject> SubjectsDropDown;
    @FXML Button withdrawButton;
    @FXML Button enrolButton;
    @FXML Button logout;
    
    @FXML TableView<Activity> currentActivityTable;
    @FXML TableColumn<Activity, Integer> activitySubject;
    @FXML TableColumn<Activity, String> activityGroup;
    @FXML TableColumn<Activity, Integer> activityActivity;
    @FXML TableColumn<Activity, String> activityDay;
    @FXML TableColumn<Activity, Integer> activityStart;
    @FXML TableColumn<Activity, Integer> activityDuration;
    @FXML TableColumn<Activity, String> activityRoom;
    @FXML TableColumn<Activity, Integer> activityCapacity;
    @FXML TableColumn<Activity, Integer> activityEnrolled;
    
    @FXML TableView<Activity> enrolTable;
    @FXML TableColumn<Activity, Integer> enrolSubject;
    @FXML TableColumn<Activity, String> enrolGroup;
    @FXML TableColumn<Activity, Integer> enrolActivity;
    @FXML TableColumn<Activity, String> enrolDay;
    @FXML TableColumn<Activity, Integer> enrolStart;
    @FXML TableColumn<Activity, Integer> enrolDuration;
    @FXML TableColumn<Activity, String> enrolRoom;
    @FXML TableColumn<Activity, Integer> enrolCapacity;
    @FXML TableColumn<Activity, Integer> enrolEnrolled;
    
    
    
    
    @FXML public void initialize()
    {
        //populate student info
        
        //Bind Tables (sorry)        
        
        activitySubject.setCellValueFactory(cellData -> cellData.getValue().getSubjectNumber().asObject());
        activityGroup.setCellValueFactory(cellData -> cellData.getValue().getGroup());
        activityActivity.setCellValueFactory(cellData -> cellData.getValue().getNumber().asObject());
        activityDay.setCellValueFactory(cellData -> cellData.getValue().getDay());
        activityStart.setCellValueFactory(cellData -> cellData.getValue().getStart().asObject());
        activityDuration.setCellValueFactory(cellData -> cellData.getValue().getDuration().asObject());
        activityRoom.setCellValueFactory(cellData -> cellData.getValue().getRoom());
        activityCapacity.setCellValueFactory(cellData -> cellData.getValue().getCapacity().asObject());
        activityEnrolled.setCellValueFactory(cellData -> cellData.getValue().getEnrolled().asObject());
        
        enrolSubject.setCellValueFactory(cellData -> cellData.getValue().getSubjectNumber().asObject());
        enrolGroup.setCellValueFactory(cellData -> cellData.getValue().getGroup());
        enrolActivity.setCellValueFactory(cellData -> cellData.getValue().getNumber().asObject());
        enrolDay.setCellValueFactory(cellData -> cellData.getValue().getDay());
        enrolStart.setCellValueFactory(cellData -> cellData.getValue().getStart().asObject());
        enrolDuration.setCellValueFactory(cellData -> cellData.getValue().getDuration().asObject());
        enrolRoom.setCellValueFactory(cellData -> cellData.getValue().getRoom());
        enrolCapacity.setCellValueFactory(cellData -> cellData.getValue().getCapacity().asObject());
        enrolEnrolled.setCellValueFactory(cellData -> cellData.getValue().getEnrolled().asObject());
        
        //bind student activities
        
        //Enroled table selection change listener
        currentActivityTable.getSelectionModel().selectedItemProperty().addListener((observer, oldVal, newVal) -> {
            withdrawButton.setDisable(false);
        });
        
        //Enrol table selection change listener
        enrolTable.getSelectionModel().selectedItemProperty().addListener((observer, oldVal, newVal) -> {
            enrolButton.setDisable(true); //reset state after selecting a new item
            if (enrolTable.getSelectionModel().getSelectedItem() != null)
            {
                boolean canEnrol = enrolTable.getSelectionModel().getSelectedItem().canEnrol();
                if (canEnrol)
                {
                    enrolButton.setDisable(false);    
                }       
            }
                         
        });
    }
    
    @FXML private void comboBoxAction(ActionEvent event)
    {
        System.out.println(SubjectsDropDown.getValue().toString());
        Subject selectedSubject = SubjectsDropDown.getValue();
        for (Subject subject: subjects )
        {
            if (selectedSubject == subject)
            {
                ObservableList<Activity> subjectActivities = subject.getActivities();
                enrolTable.setItems(subjectActivities);
            }
        }
    }
    
    @FXML private void withdraw()
    {
       Activity activityToRemove = currentActivityTable.getSelectionModel().getSelectedItem();
       student.withdraw(activityToRemove);
       if (currentActivityTable.getItems().isEmpty())
       {
            withdrawButton.setDisable(true);           
       }
    }
    
    @FXML private void enrol()
    {
       Activity activityToAdd = enrolTable.getSelectionModel().getSelectedItem();
       student.enrol(activityToAdd);
       enrolButton.setDisable(true);
    }    
    
    @FXML private void logout()
    {
        getStage().close();
    }
    
    public void setStudent(Student student)
    {
        this.student = student;
        //set UI elements, would use FXML binding but i'm not sure how to get them working with an object that is defined at runtime.
        welcomeMessage.setText("Logged in as " + student.getName());
        infoNumber.setText("" + student.getNumber());
        infoAttendance.setText((student.getAttendance().equals("ft")) ? "Full Time":"Part Time");
        infoScholarship.setText(student.getScholarship() ? "Yes":"No");
        
        ObservableList<Activity> studentActivities = student.getActivities();
        Bindings.bindContentBidirectional(currentActivityTable.getItems(), studentActivities);
    }
    
    public final Student getStudent()
    {
        return student;
    }
    
    public void SetSubjects(ObservableList<Subject> subjects)
    {
        this.subjects = subjects;
        Bindings.bindContentBidirectional(SubjectsDropDown.getItems(), subjects);
    }
    
    private Stage getStage()
    {
        Stage stage = (Stage) logout.getScene().getWindow();
        return stage;
    }
    

}
