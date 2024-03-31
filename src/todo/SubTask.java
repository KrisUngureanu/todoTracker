package todo;

import java.util.HashMap;

public class SubTask extends Task{
    private Epic epic;
    public SubTask(String name, String description, Manager manager) {
        super(name, description, manager);
        this.setType("SUBTASK");
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
    public String showEpicInfo(){
        Epic epic = getEpic();
        return "EPIC ID: " + epic.getId() + " EPIC NAME " + epic.getName();
    }

    @Override
    public void update(int id, String name, String description) {
        int oldId = this.getId();
        super.update(id, name, description);
        //обновим в эпике список подзадач если сменился ID
        Epic epic = this.getEpic();
        if (epic != null && id != 0){
            HashMap<Integer, SubTask> subtasks = epic.getSubtasks();
            if (subtasks != null){
                for (Integer idLocal:
                     subtasks.keySet()) {
                    if (idLocal == oldId){
                        subtasks.remove(idLocal);
                        subtasks.put(id, this);
                    }
                }
            }
        }
    }

    @Override
    public void showInfo() {
        super.showInfo();
        Epic epic = this.getEpic();
        System.out.println("Epic: " + epic.getName());
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
        Epic epic = getEpic();
        //проверить статусы всех подзадач
        HashMap<Integer, SubTask> tasks = epic.getSubtasks();
        Boolean isFinished = true;
        if (tasks != null){
            for (Integer id:
                 tasks.keySet()) {
                SubTask task = tasks.get(id);
                String statusLocal = task.getStatus();
                if (!statusLocal.equals("DONE")){
                    isFinished = false;
                    break;
                }
            }
        }
        if (isFinished == true){
            epic.setStatus("DONE");
        } else {
            epic.setStatus("IN PROGRESS");
        }
    }
}
