import java.util.HashMap;
import java.util.Objects;

public class Task {
    public String name;
    public String description;
    public Integer  id;
    public String status;

    public Task (String name, String description, Integer  id, String status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(id, task.id) &&
                Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if (name != null) {
            hash = hash + name.hashCode();
        }
        hash = hash * 31;

        if (description != null) {
            hash = hash + description.hashCode();
        }
        if (id != null) {
            hash = hash + id.hashCode();
        }
        if (status != null) {
            hash = hash + status.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Task{" + "name='" + name + '\'' +
                ", description='" + description + '\'' + ", id='" + id + '\'' + ", status='" + status + '\''
                + '}';

    }


}
