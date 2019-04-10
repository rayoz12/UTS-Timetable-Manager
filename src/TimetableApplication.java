import au.edu.uts.ap.javafx.*;
import model.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

public class TimetableApplication extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        University university = new University();
        ViewLoader.showStage(university, "/view/university.fxml", "Timetable System", stage);
//        Parent root = FXMLLoader.load(getClass().getResource("/view/university.fxml")); //USE THIS
//        //Parent root = FXMLLoader.load(getClass().getResource("/view/add_student.fxml"));
//        stage.setTitle("Timetable System");
//        stage.setScene(new Scene(root));
//        stage.sizeToScene();
//        stage.show();
    }
}
