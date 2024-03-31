package todo;

import java.util.HashMap;

public class Manager {
    HashMap<String, HashMap<Integer, Task>> tasks;
    private int id = 0;

    public Manager() {
        tasks = new HashMap<>();
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public int increaseID() {
        int newId = id + 1;
        this.id = newId;
        return newId;
    }

    public void PrintMenu() {
        System.out.println("MENU");
        System.out.println("1.Создать задачу");
        System.out.println("2.Получить список всех задач");
        System.out.println("3.Получить задачу по идентификатору");
        System.out.println("4.Удалить задачу");
        System.out.println("5.Обновить статус задачи");
        System.out.println("6.Получить все подзадачи Эпика");
        System.out.println("7.Удалить все задачи");
        System.out.println("8.Обновить задачу");
        System.out.println("9.Выход");
    }

    public void setTasks(Task task) {
        String type = task.getType();
        int id = task.getId();
        HashMap<String, HashMap<Integer, Task>> tasks = this.tasks;
        if (tasks == null) {
            tasks = new HashMap<>();
        }
        HashMap<Integer, Task> tasksManager = this.tasks.get(type);
        if (tasksManager == null) {
            tasksManager = new HashMap<>();
            tasks.put(type, tasksManager);
        }
        tasksManager.put(id, task);
    }

    public void infoAbotTasks() {
        HashMap<String, HashMap<Integer, Task>> tasks = this.tasks;
        if (tasks == null) {
            System.out.println("Нет задач");
        } else {
            for (String type :
                    tasks.keySet()) {
                HashMap<Integer, Task> mas = tasks.get(type);
                for (Integer id :
                        mas.keySet()) {
                    Task taskLocal = mas.get(id);
                    if (taskLocal != null) {
                        taskLocal.showInfo();
                    }
                }
            }
        }
    }

    public Task getTaskById(int id) {
        HashMap<String, HashMap<Integer, Task>> tasks = this.tasks;
        for (String type :
                tasks.keySet()) {
            HashMap<Integer, Task> mas = tasks.get(type);
            for (Integer idLocal :
                    mas.keySet()) {
                if (id != idLocal) {
                    continue;
                }
                Task taskLocal = mas.get(idLocal);
                if (taskLocal != null) {
                    return taskLocal;
                }
            }
        }
        return null;
    }

    public void PrintMenuChangeTask() {
        System.out.println("1. Изменить ID");
        System.out.println("2. Изменить наименование");
        System.out.println("3. Изменить описание");
        System.out.println("4. Закончить изменение задачи");
    }

    public void updateTasks(int oldId, Task task) {
        HashMap<String, HashMap<Integer, Task>> tasks = this.tasks;
        for (String type :
                tasks.keySet()) {
            HashMap<Integer, Task> mas = tasks.get(type);
            for (Integer idLocal :
                    mas.keySet()) {
                if (oldId != idLocal) {
                    continue;
                }
                Task taskLocal = mas.get(idLocal);
                if (taskLocal != null) {
                    mas.remove(oldId);
                    mas.put(task.getId(), task);
                }
            }
        }
    }

    public void deleteOneTask(int idForDelete){
        HashMap<String, HashMap<Integer,Task>> tasks = this.tasks;
        for (String type:
                tasks.keySet()) {
            HashMap<Integer, Task> mas = tasks.get(type);
            for (Integer id:
                    mas.keySet()) {
                if (idForDelete == id){
                    mas.remove(id);
                    System.out.println("Запись удалена");
                    break;
                }

            }

        }
    }
    public void deleteAllTasks(){

        HashMap<String, HashMap<Integer,Task>> tasks = this.tasks;
        for (String type:
             tasks.keySet()) {
            HashMap<Integer, Task> mas = tasks.get(type);
            for (Integer id:
                 mas.keySet()) {
                mas.remove(id);
            }
            tasks.remove(type);
        }
        System.out.println("Задачи удалены");
    }
}



