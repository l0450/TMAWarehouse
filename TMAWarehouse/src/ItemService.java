import java.util.List;

public interface ItemService {
    Item getItemById(int id);
    List<Item> getAllItems();
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(int id);
}

