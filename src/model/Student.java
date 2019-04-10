package model;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student {
    private University university;
    private final String number;
    private final String name;
    private final String attendance;
    private final boolean scholarship;
    private ObservableList<Activity> activities = FXCollections.observableArrayList();
    //private LinkedList<Activity> activities = new LinkedList<Activity>();

    public Student(University university, String number, String name, String attendance, boolean scholarship) {
        this.university = university;
        this.number = number;
        this.name = name;
        this.attendance = attendance;
        this.scholarship = scholarship;
    }

    public University getUniversity() { return university; }
    public final String getNumber() { return number; }
    public final String getName() { return name; }
    public final String getAttendance() { return attendance; }
    public boolean getScholarship() { return scholarship; }
    public ObservableList<Activity> getActivities() { return activities; }

    public boolean isEnrolledIn(Activity activity) {
        return activities.contains(activity);
    }

    public boolean matches(String number) {
        return this.number.equals(number);
    }

    public void enrol(Activity activity) {
        // You should first check if the student is already enrolled
        // in an existing activity with the same subject and group.
        // If so, the student should be withdrawn from that activity first.
        // See Lecture 5 for hints.
        if (activity.canEnrol())
        {
                Activity prevActivity = checkIfEnrolledInGroup(activity);//check if already enrolled in same activity type
                if (prevActivity != null)
                {
                        prevActivity.withdraw();
                        activities.remove(prevActivity);
                }
                activity.enrol();
                activities.add(activity);
        }
    }
    
    public void unenrolFromAll() {
        for (int i=activities.size()-1;i>=0;i--)
        {
            withdraw(activities.get(i));            
        }
    }

    public void withdraw(Activity activity) {
        activities.remove(activity);
        activity.withdraw();
    }
    
    private Activity checkIfEnrolledInGroup(Activity activityToCheck)
    {
        for (Activity activity : activities)
        {
            if (activityToCheck.getSubject() == activity.getSubject() && activityToCheck.getGroup().get().equals(activity.getGroup().get()))
            {
                return activity;
            }
        }
        return null;
    }

    // This lookup function should be useful to check if a student is
    // already enrolled in an activity.
    private Activity activity(int subjectNumber, String group) {
        for (Activity activity : activities)
            if (activity.matches(subjectNumber, group))
                return activity;
        return null;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }


}
