import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;

public class TMARequestRowDAO implements DAO<TMARequestRow> {
    private Connection connection;

    // The constructor for initializing the connection
    public TMARequestRowDAO(Connection connection) {
        this.connection = connection;
    }

    // Getting the TMARequestRow by ID
    public TMARequestRow getById(int id) {
        String query = "SELECT * FROM TMARequestRows WHERE RequestRowID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractTMARequestRowFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Getting all TMARequestRows
    public List<TMARequestRow> getAll() {
        List<TMARequestRow> requestRows = new ArrayList<>();
        String query = "SELECT * FROM TMARequestRows";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                TMARequestRow requestRow = extractTMARequestRowFromResultSet(resultSet);
                requestRows.add(requestRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestRows;
    }

    // Adding a new TMARequestRow
    public void add(TMARequestRow requestRow) {
        String query = "INSERT INTO TMARequestRows (RequestID, ItemID, UnitOfMeasurement, " +
                "Quantity, PriceWithoutVAT, Comment) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, requestRow.getRequestId());
            statement.setInt(2, requestRow.getItemId());
            statement.setString(3, requestRow.getUnitOfMeasurement());
            statement.setDouble(4, requestRow.getQuantity());
            statement.setDouble(5, requestRow.getPriceWithoutVAT());
            statement.setString(6, requestRow.getComment());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updating an existing TMARequestRow
    public void update(TMARequestRow requestRow) {
        String query = "UPDATE TMARequestRows SET RequestID = ?, ItemID = ?, UnitOfMeasurement = ?, " +
                "Quantity = ?, PriceWithoutVAT = ?, Comment = ? WHERE RequestRowID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, requestRow.getRequestId());
            statement.setInt(2, requestRow.getItemId());
            statement.setString(3, requestRow.getUnitOfMeasurement());
            statement.setDouble(4, requestRow.getQuantity());
            statement.setDouble(5, requestRow.getPriceWithoutVAT());
            statement.setString(6, requestRow.getComment());
            statement.setInt(7, requestRow.getRequestRowId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deleting TMARequestRow by ID
    public void delete(int id) {
        String query = "DELETE FROM TMARequestRows WHERE RequestRowID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // The helper method for extracting TMARequestRow from result set
    private TMARequestRow extractTMARequestRowFromResultSet(ResultSet resultSet) throws SQLException {
        int requestRowID = resultSet.getInt("RequestRowID");
        int requestID = resultSet.getInt("RequestID");
        int itemID = resultSet.getInt("ItemID");
        String unitOfMeasurement = resultSet.getString("UnitOfMeasurement");
        double quantity = resultSet.getDouble("Quantity");
        double priceWithoutVAT = resultSet.getDouble("PriceWithoutVAT");
        String comment = resultSet.getString("Comment");
        return new TMARequestRow(requestRowID, requestID, itemID, unitOfMeasurement, quantity,
                priceWithoutVAT, comment);
    }
}
