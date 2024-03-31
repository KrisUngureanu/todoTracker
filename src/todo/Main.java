package todo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();


        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        while (userInput != 9) {
            manager.PrintMenu();
            userInput = scanner.nextInt();
            if (userInput == 9) {
                break;
            } else if (userInput == 1) {
//                создать задачу
                System.out.println("Выберите тип задачи 1-TASK, 2-EPIC, 3-Subtask");
                int typeLocal = scanner.nextInt();
                System.out.println("Введите наименование задачи");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("Введите описание задачи");

                String desc = scanner.nextLine();
                switch (typeLocal) {
                    case 1:
                        Task task = new Task(name, desc, manager);
                        manager.setTasks(task);
                        break;
                    case 2:
                        Epic epic = new Epic(name, desc, manager);
                        manager.setTasks(epic);
                        break;
                    case 3:
                        SubTask subtask = new SubTask(name, desc, manager);
                        manager.setTasks(subtask);
                        System.out.println("Введите айди Эпика");
                        int idEpic = scanner.nextInt();
                        Epic epic1 = (Epic) manager.getTaskById(idEpic);
                        if (epic1 != null) {
                            subtask.setEpic(epic1);
                        } else {
                            System.out.println("Эпик не найжен. Подзадача будет удалена");
                            manager.deleteOneTask(subtask.getId());
                        }

                        break;
                    default:
                        System.out.println("Неверный тип задачи");
                        break;
                }
            } else if (userInput == 2) {
//                получить список всех задач
                manager.infoAbotTasks();
            } else if (userInput == 3) {
//                получить задачу по идентификатору
                System.out.println("Введите идентификатор");
                int findedId = scanner.nextInt();
                Task taskFinded = manager.getTaskById(findedId);
                taskFinded.showInfo();
            } else if (userInput == 4) {
//                удалить задачу
                System.out.println("Введите id задачи для удаления");
                int idForDelete = scanner.nextInt();
                manager.deleteOneTask(idForDelete);
            } else if (userInput == 5) {
//                обновить статус задачи
                System.out.println("Введите ID задачи");
                int idForShangeStatus = scanner.nextInt();
                Task taskForChangeStatus = manager.getTaskById(idForShangeStatus);
                if (taskForChangeStatus == null) {
                    System.out.println("Задача не найдена");
                } else {
                    System.out.println("Введите статус 1 - IN PROGRESS 2 - DONE 3 - NEW");
                    int statusInt = scanner.nextInt();
                    String statusString = "";
                    switch (statusInt) {
                        case 1:
                            statusString = "IN PROGRESS";
                            break;
                        case 2:
                            statusString = "DONE";
                            break;
                        case 3:
                            statusString = "NEW";
                            break;
                        default:
                            System.out.println("Неверный статус");
                            break;
                    }
                    if (statusString.length() > 0) {
                        taskForChangeStatus.setStatus(statusString);
                        System.out.println("Статус изменен");
                    }

                }
            } else if (userInput == 6) {
//                получить все подзадачи эпика
                System.out.println("Введите ID эпика");
                int epicId = scanner.nextInt();
                Task task = manager.getTaskById(epicId);
                if (task == null) {
                    System.out.println("Не найдена задача по заданному идентификатору");
                } else {
                    String type = task.getType();
                    if (!type.equals("EPIC")) {
                        System.out.println("Задача не является Эпиком");
                    } else {
                        Epic epic = (Epic) task;
                        epic.showInfo();
                    }
                }
            } else if (userInput == 7) {
//                удалить все задачи
                System.out.println("Вы уверены, что хотите удалить все задачи? 1- Да. 2- Нет");
                userInput = scanner.nextInt();
                if (userInput == 1){
                    manager.deleteAllTasks();
                }

            } else if (userInput == 8) {
//                обновить задачу
                System.out.println("Введите ID задачи для изменения");
                int idForChange = scanner.nextInt();
                Task taskForChange = manager.getTaskById(idForChange);
                if (taskForChange == null) {
                    System.out.println("Задача не найдена");
                } else {
                    System.out.println("Введите данные для изменения");
                    while (userInput != 4) {
                        manager.PrintMenuChangeTask();
                        userInput = scanner.nextInt();
                        if (userInput == 4) {
                            break;
                        } else if (userInput == 1) {
                            //смена ID
                            System.out.println("Введите новый ID");
                            userInput = scanner.nextInt();
                            taskForChange.update(userInput, "", "");
                            manager.updateTasks(idForChange, taskForChange);
                        } else if (userInput == 2) {
                            //смена наименования
                            System.out.println("Введите новое наименование");
                            scanner.nextLine();
                            String newName = scanner.nextLine();
                            taskForChange.update(0, newName, "");
                        } else if (userInput == 3) {
                            //смена описания
                            System.out.println("Введите новое описание");
                            scanner.nextLine();
                            String newDesc = scanner.nextLine();
                            taskForChange.update(0, "", newDesc);
                        }
                    }
                }
            }

        }

    }
}
