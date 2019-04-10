package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activity {
    private Subject subject;
    private StringProperty group = new SimpleStringProperty();
    private IntegerProperty number = new SimpleIntegerProperty();
    private StringProperty day = new SimpleStringProperty();
    private IntegerProperty start = new SimpleIntegerProperty();
    private IntegerProperty duration = new SimpleIntegerProperty();
    private StringProperty room = new SimpleStringProperty();
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private IntegerProperty enrolled = new SimpleIntegerProperty();

    public Activity(Subject subject, String group, int number, String day, int start, int duration, String room, int capacity) {
        this.subject = subject;
        this.group.set(group);
        this.number.set(number);
        this.day.set(day);
        this.start.set(start);
        this.duration.set(duration);
        this.room.set(room);
        this.capacity.set(capacity);
        enrolled.set(0);
    }

    public Subject getSubject() { return subject; }
    public IntegerProperty getSubjectNumber() { return subject.getNumber(); }
    public StringProperty getGroup() { return group; }
    public IntegerProperty getNumber() { return number; }
    public StringProperty getDay() { return day; }
    public IntegerProperty getStart() { return start; }
    public IntegerProperty getDuration() { return duration; }
    public StringProperty getRoom() { return room; }
    public IntegerProperty getCapacity() { return capacity; }
    public IntegerProperty getEnrolled() { return enrolled; }

    public boolean canEnrol() {
        return enrolled.get() < capacity.get();
    }

    public void enrol() {
        enrolled.set(enrolled.get() + 1);
    }

    public void withdraw() {
        enrolled.set(enrolled.get() - 1);
    }

    public boolean matches(int subjectNumber, String group) {
        return subject.matches(subjectNumber) && group.equals(this.group);
    }    

    @Override
    public String toString() {
        return String.format("%d %s %d %s %s %02d:00 %dhrs %d/%d", subject.getNumber(), group.get(), number.get(), day.get(), room.get(), start.get(), duration.get(), enrolled.get(), capacity.get());
    }

}
