import java.util.List;

public class TMARequestServiceImpl implements TMARequestService {
	
	private TMARequestDAO requestDAO;
	
	public TMARequestServiceImpl(TMARequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }
	
	// Getting request by ID
    @Override
    public TMARequest getRequestById(int id) {
        return requestDAO.getById(id);
    }

    // Getting all requests
    @Override
    public List<TMARequest> getAllRequests() {
        return requestDAO.getAll();
    }

    // Adding new request
    @Override
    public void addRequest(TMARequest request) {
        requestDAO.add(request);
    }

    // Updating existing request
    @Override
    public void updateRequest(TMARequest request) {
        requestDAO.update(request);
    }

    // Deleting request by ID
    @Override
    public void deleteRequest(int id) {
        requestDAO.delete(id);
    }  

}
