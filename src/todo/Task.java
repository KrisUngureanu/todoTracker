package todo;

public class Task {
    private String name;
    private String description;
    private int id;
    private String status;
    private String type;

    public Task(String name, String description, Manager manager) {
        int id = manager.increaseID();
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = "NEW";
        this.type = "TASK";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void showInfo() {
        System.out.println("Тип задачи: " + this.getType() +
                " наименование: " + this.getName() + " ID: "
                + this.getId() + " статус: "
                + this.getStatus()
                + " описание:  "
                + this.getDescription());
    }

    public void update(int id, String name, String description){
        if (id != 0){
            //обновление ID
            this.setId(id);
        }
        if (!name.equals("")){
            //обновление имени
            this.setName(name);
        }
        if (!description.equals("")){
            //обновление описания
            this.setDescription(description);
        }
    }
}
