import java.util.HashMap;
import java.util.Objects;

public class SubTask extends Task {
    HashMap<Integer, Epic> epics;

    public SubTask(String name, String description, Integer id, String status) {
        super(name, description, id, status);
        epics = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTask subtask = (SubTask) o;
        return Objects.equals(name, subtask.name) &&
                Objects.equals(description, subtask.description) &&
                Objects.equals(id, subtask.id) &&
                Objects.equals(status, subtask.status) &&
                Objects.equals(epics, subtask.epics);
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
        if (epics != null) {
            hash = hash + epics.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Subtask{" + "name='" + name + '\'' +
                ", description='" + description + '\'' + ", id='" + id + '\'' + ", status='" + status + '\''
                + '}';

    }

}