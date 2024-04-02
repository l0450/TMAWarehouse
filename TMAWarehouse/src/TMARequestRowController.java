import java.util.List;

public class TMARequestRowController {
    private TMARequestRowService requestRowService;

    // The constructor for initializing TMARequestRowService
    public TMARequestRowController(TMARequestRowService requestRowService) {
        this.requestRowService = requestRowService;
    }

    // Method to handle fetching all TMA request rows
    public List<TMARequestRow> getAllRequestRows() {
        return requestRowService.getAllRequestRows();
    }

    // Method to handle adding a new TMA request row
    public void addRequestRow(TMARequestRow requestRow) {
        requestRowService.addRequestRow(requestRow);
    }

    // Method to handle fetching a TMA request row by ID
    public TMARequestRow getRequestRowById(int id) {
        return requestRowService.getRequestRowById(id);
    }

    // Method to handle updating an existing TMA request row
    public void updateRequestRow(TMARequestRow requestRow) {
        requestRowService.updateRequestRow(requestRow);
    }

    // Method to handle deleting a TMA request row by ID
    public void deleteRequestRow(int id) {
        requestRowService.deleteRequestRow(id);
    }
}
