package blazeDemo.db;

public class ItemModel {
    String name;
    String description;

    public ItemModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}