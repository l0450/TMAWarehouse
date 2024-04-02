import java.util.List;

public interface TMARequestRowService {
    TMARequestRow getRequestRowById(int id);
    List<TMARequestRow> getAllRequestRows();
    void addRequestRow(TMARequestRow requestRow);
    void updateRequestRow(TMARequestRow requestRow);
    void deleteRequestRow(int id);
}