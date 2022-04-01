import java.util.ArrayList;
import java.util.List;

public class Momento {

    private List<Group> groups = new ArrayList<>();

    public Momento(List<Group> groups) {

        groups.stream().forEach(g ->{
            this.groups.add(new Group(g.getName(), (g.getStudents() != null) ? new ArrayList<>(g.getStudents()) : new ArrayList<>()));
        });
    }

    public List<Group> undo(){
        return groups;
    }
}
