import java.util.HashMap;
import java.util.Objects;

public class Epic extends Task {
    HashMap<Integer, SubTask> subTasks;

    public Epic(String name, String description, Integer id, String status) {
        super(name, description, id, status);
        subTasks = new HashMap<>();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epic epic = (Epic) o;
        return Objects.equals(name, epic.name) &&
                Objects.equals(description, epic.description) &&
                Objects.equals(id, epic.id) &&
                Objects.equals(status, epic.status) &&
                Objects.equals(subTasks, epic.subTasks);
    }

    @Override
    public int hashCode() {
        int hash = 18;
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
        if (subTasks != null) {
            hash = hash + subTasks.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Epic{" + "name='" + name + '\'' +
                ", description='" + description + '\'' + ", id='" + id + '\'' + ", status='" + status + '\''
                + '}';

    }

}
