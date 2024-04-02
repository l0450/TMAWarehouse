import java.util.List;

public interface TMARequestService {
    TMARequest getRequestById(int id);
    List<TMARequest> getAllRequests();
    void addRequest(TMARequest request);
    void updateRequest(TMARequest request);
    void deleteRequest(int id);
}
