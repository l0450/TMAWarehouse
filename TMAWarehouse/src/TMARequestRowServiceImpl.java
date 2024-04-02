import java.util.List;

public class TMARequestRowServiceImpl implements TMARequestRowService {
    private TMARequestRowDAO requestRowDAO;
    
    public TMARequestRowServiceImpl(TMARequestRowDAO requestRowDAO) {
        this.requestRowDAO = requestRowDAO;
    }
    
    // Getting a request row by ID
    @Override
    public TMARequestRow getRequestRowById(int id) {
        return requestRowDAO.getById(id);
    }

    // Getting all request rows
    @Override
    public List<TMARequestRow> getAllRequestRows() {
        return requestRowDAO.getAll();
    }

    // Adding a new request row
    @Override
    public void addRequestRow(TMARequestRow requestRow) {
        requestRowDAO.add(requestRow);
    }

    // Updating existing request row
    @Override
    public void updateRequestRow(TMARequestRow requestRow) {
        requestRowDAO.update(requestRow);
    }

    // Deleting request row by ID
    @Override
    public void deleteRequestRow(int id) {
        requestRowDAO.delete(id);
    }

}
