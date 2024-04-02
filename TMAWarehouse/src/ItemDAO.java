import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;

public class ItemDAO implements DAO<Item> {
    private Connection connection;

    // Constructor for initializing connection
    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    // Getting an item by ID
    public Item getById(int id) {
        String query = "SELECT * FROM Items WHERE ItemID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Getting all items
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM Items";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Item item = extractItemFromResultSet(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Adding an new item
    public void add(Item item) {
        String query = "INSERT INTO Items (ItemGroup, UnitOfMeasurement, Quantity, PriceWithoutVAT, " +
                "Status, StorageLocation, ContactPerson, Photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getItemGroup());
            statement.setString(2, item.getUnitOfMeasurement());
            statement.setDouble(3, item.getQuantity());
            statement.setDouble(4, item.getPriceWithoutVAT());
            statement.setString(5, item.getStatus());
            statement.setString(6, item.getStorageLocation());
            statement.setString(7, item.getContactPerson());
            statement.setBytes(8, item.getPhoto());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updating an  existing item
    public void update(Item item) {
        String query = "UPDATE Items SET ItemGroup = ?, UnitOfMeasurement = ?, Quantity = ?, " +
                "PriceWithoutVAT = ?, Status = ?, StorageLocation = ?, ContactPerson = ?, Photo = ? " +
                "WHERE ItemID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getItemGroup());
            statement.setString(2, item.getUnitOfMeasurement());
            statement.setDouble(3, item.getQuantity());
            statement.setDouble(4, item.getPriceWithoutVAT());
            statement.setString(5, item.getStatus());
            statement.setString(6, item.getStorageLocation());
            statement.setString(7, item.getContactPerson());
            statement.setBytes(8, item.getPhoto());
            statement.setInt(9, item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deleting an item by ID  
    public void delete(int id) {
        String query = "DELETE FROM Items WHERE ItemID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // The helper method extracting an item from result set
    private Item extractItemFromResultSet(ResultSet resultSet) throws SQLException {
        int itemID = resultSet.getInt("ItemID");
        String itemGroup = resultSet.getString("ItemGroup");
        String unitOfMeasurement = resultSet.getString("UnitOfMeasurement");
        double quantity = resultSet.getDouble("Quantity");
        double priceWithoutVAT = resultSet.getDouble("PriceWithoutVAT");
        String status = resultSet.getString("Status");
        String storageLocation = resultSet.getString("StorageLocation");
        String contactPerson = resultSet.getString("ContactPerson");
        byte[] photo = resultSet.getBytes("Photo");
        return new Item(itemID, itemGroup, unitOfMeasurement, quantity, priceWithoutVAT, status,
                storageLocation, contactPerson);
    }
}
