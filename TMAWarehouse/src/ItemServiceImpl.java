import java.util.List;

public class ItemServiceImpl implements ItemService {
	
	private ItemDAO itemDAO;

    // Constructor for initializing ItemDAO
    public ItemServiceImpl(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    // Getting an item by ID
    @Override
    public Item getItemById(int id) {
        return itemDAO.getById(id);
    }

    // Getting all items
    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAll();
    }

    // Adding an new item
    @Override
    public void addItem(Item item) {
        itemDAO.add(item);
    }

    // Updating an existing item
    @Override
    public void updateItem(Item item) {
        itemDAO.update(item);
    }

    // Deleting an item by ID
    @Override
    public void deleteItem(int id) {
        itemDAO.delete(id);
    }
	

}
