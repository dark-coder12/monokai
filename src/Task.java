enum Priority{
    LOW, MEDIUM, HIGH
};
public class Task {

    String name;
    Priority priority;

    public void addValues(String name, String priorityStr){

        this.name = name;

        if(priorityStr.equals("Low")){
            priority = Priority.LOW;
        }
        if(priorityStr.equals("Medium")){
            priority = Priority.MEDIUM;
        }
        if(priorityStr.equals("High")){
            priority = Priority.HIGH;
        }
    }
}
