import java.util.List;

public class TMARequestController {
    private TMARequestService requestService;

    // Constructor for initializing TMARequestService
    public TMARequestController(TMARequestService requestService) {
        this.requestService = requestService;
    }

    // Method to handle fetching all TMA requests
    public List<TMARequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    // Method to handle adding a new TMA request
    public void addRequest(TMARequest request) {
        requestService.addRequest(request);
    }

    // Method to handle fetching a TMA request by ID
    public TMARequest getRequestById(int id) {
        return requestService.getRequestById(id);
    }

    // Method to handle updating an existing TMA request
    public void updateRequest(TMARequest request) {
        requestService.updateRequest(request);
    }

    // Method to handle deleting a TMA request by ID
    public void deleteRequest(int id) {
        requestService.deleteRequest(id);
    }
}
