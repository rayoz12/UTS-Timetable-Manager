/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.University;

import au.edu.uts.ap.javafx.Controller;

/**
 *
 * @author Ryan
 */
public class Add_studentController extends Controller<University> {
    
    boolean AttendenceSelected;
    University university;
        
    @FXML TextField numberTf;
    @FXML TextField nameTf;
    @FXML ToggleGroup attandanceGroup;
    @FXML CheckBox scholarshipCb;
    @FXML Button cancel;
    @FXML Button addStudent;
    @FXML Label errorMessage;
    
    @FXML public void initialize()
    {
        university = getUniversity();
    }
    
    public final University getUniversity()
    {
        return model;
    }
    
    @FXML private void cancel (ActionEvent event) throws Exception 
    {
        stage.close();
    }
    
    @FXML private void FormChanged (Event event)
    {
        InputValidate();
    }
    
    private void InputValidate ()
    {
        //System.out.println("Validating");
        boolean number = !numberTf.getText().equals("");
        boolean name = !nameTf.getText().equals("");
        if (number && name && AttendenceSelected)
        {
            addStudent.setDisable(false);
        }
        else
        {
            addStudent.setDisable(true);
        }
    }
    
    @FXML private void registerRadioButtonClick (ActionEvent event)
    {
        AttendenceSelected = true;
        InputValidate();
    }
        
    
    @FXML private void addStudent (ActionEvent event)
    {
        //System.out.println("adding!");
        RadioButton selectedToggle = (RadioButton) attandanceGroup.getSelectedToggle();
        String radioText = (String) selectedToggle.getUserData();
        try 
        {
            university.addStudent(numberTf.getText(), nameTf.getText(), radioText, scholarshipCb.selectedProperty().get());
            stage.close();
        }
        catch (Exception e)
        {
            errorMessage.setText(e.getMessage());
        }        
    }
}
