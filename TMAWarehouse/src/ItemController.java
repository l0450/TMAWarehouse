import java.util.List;

public class ItemController {
    private ItemService itemService;

    // Constructor for initializing ItemService
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Method to handle HTTP GET request for fetching all items
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Method to handle HTTP POST request for adding a new item
    public void addItem(Item item) {
        itemService.addItem(item);
    }

    // Method to handle HTTP GET request for fetching an item by ID
    public Item getItemById(int id) {
        return itemService.getItemById(id);
    }

    // Method to handle HTTP PUT request for updating an existing item
    public void updateItem(Item item) {
        itemService.updateItem(item);
    }

    // Method to handle HTTP DELETE request for deleting an item by ID
    public void deleteItem(int id) {
        itemService.deleteItem(id);
    }
}

