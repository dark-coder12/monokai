import java.util.ArrayList;

public class Column {

    String name;
    ArrayList<Task> allTasks = new ArrayList<>();


    public void addTaskInColumn(String taskName, String taskPriority){

        Task myTask = new Task();
        myTask.addValues(taskName, taskPriority);

        allTasks.add(myTask);
    }

    public void print(){

        for(Task t: allTasks){

            System.out.println(t.name);
            System.out.println(t.priority);
        }
    }
}
