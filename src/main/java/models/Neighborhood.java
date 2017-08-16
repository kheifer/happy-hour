package models;

/**
 * Created by Guest on 8/16/17.
 */
public class Neighborhood {
    private String name;
    private String description;
    private int id;

    public Neighborhood(String name, String description){
        this.name = name;
        this.description = description;
    }
    //GETTERS
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(int id) {
        this.id = id;
    }

    //EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Neighborhood that = (Neighborhood) o;

        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
