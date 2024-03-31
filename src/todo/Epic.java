package todo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;

public class Epic extends Task{
    private HashMap<Integer, SubTask> subtasks;
    public Epic(String name, String description, Manager manager) {
        super(name, description, manager);
        this.setType("EPIC");
        subtasks = new HashMap<>();
    }

    public HashMap<Integer, SubTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(SubTask subtask) {
        subtasks = this.getSubtasks();
        subtasks.put(subtask.getId(), subtask);
    }



    @Override
    public void setStatus(String status) {
        HashMap<Integer, SubTask> subtasks = this.getSubtasks();
        Boolean isFinished = true;
        if (subtasks != null){
            for (Integer id:
                 subtasks.keySet()) {
                SubTask subtask = subtasks.get(id);
                String statusLocal = subtask.getStatus();
                if (!statusLocal.equals("DONE")){
                    isFinished = false;
                }
            }
        }
        if (isFinished == true) {
            super.setStatus(status);
        }

    }
}
