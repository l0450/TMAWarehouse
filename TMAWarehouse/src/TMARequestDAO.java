import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;

public class TMARequestDAO implements DAO<TMARequest> {
    private Connection connection;

 // The constructor for initializing the connection
    public TMARequestDAO(Connection connection) {
        this.connection = connection;
    }

    // Getting a TMARequest by ID
    public TMARequest getById(int id) {
        String query = "SELECT * FROM TMARequests WHERE RequestID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractTMARequestFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Getting all TMARequests
    public List<TMARequest> getAll() {
        List<TMARequest> requests = new ArrayList<>();
        String query = "SELECT * FROM TMARequests";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                TMARequest request = extractTMARequestFromResultSet(resultSet);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    // Adding a new TMARequest
    public void add(TMARequest request) {
        String query = "INSERT INTO TMARequests (EmployeeName, ItemID, UnitOfMeasurement, Quantity, " +
                "PriceWithoutVAT, Comment, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, request.getEmployeeName());
            statement.setInt(2, request.getItemId());
            statement.setString(3, request.getUnitOfMeasurement());
            statement.setDouble(4, request.getQuantity());
            statement.setDouble(5, request.getPriceWithoutVAT());
            statement.setString(6, request.getComment());
            statement.setString(7, request.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updating a existing TMARequest
    public void update(TMARequest request) {
        String query = "UPDATE TMARequests SET EmployeeName = ?, ItemID = ?, UnitOfMeasurement = ?, " +
                "Quantity = ?, PriceWithoutVAT = ?, Comment = ?, Status = ? WHERE RequestID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, request.getEmployeeName());
            statement.setInt(2, request.getItemId());
            statement.setString(3, request.getUnitOfMeasurement());
            statement.setDouble(4, request.getQuantity());
            statement.setDouble(5, request.getPriceWithoutVAT());
            statement.setString(6, request.getComment());
            statement.setString(7, request.getStatus());
            statement.setInt(8, request.getRequestId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deleting a TMARequest by ID
    public void delete(int id) {
        String query = "DELETE FROM TMARequests WHERE RequestID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // The helper method extracting TMARequest from result set
    private TMARequest extractTMARequestFromResultSet(ResultSet resultSet) throws SQLException {
        int requestID = resultSet.getInt("RequestID");
        String employeeName = resultSet.getString("EmployeeName");
        int itemID = resultSet.getInt("ItemID");
        String unitOfMeasurement = resultSet.getString("UnitOfMeasurement");
        double quantity = resultSet.getDouble("Quantity");
        double priceWithoutVAT = resultSet.getDouble("PriceWithoutVAT");
        String comment = resultSet.getString("Comment");
        String status = resultSet.getString("Status");
        return new TMARequest(requestID, employeeName, itemID, unitOfMeasurement, quantity,
                priceWithoutVAT, comment, status);
    }

    // Creating a request as specified as user wanted to
    public void createRequest(int itemId, int quantity, double priceWithoutVAT, String comment) {
        String query = "INSERT INTO TMARequests (ItemID, Quantity, PriceWithoutVAT, Comment) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, itemId);
            statement.setInt(2, quantity);
            statement.setDouble(3, priceWithoutVAT);
            statement.setString(4, comment);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
