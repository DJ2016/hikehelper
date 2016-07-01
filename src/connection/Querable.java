package connection;

import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;

public interface Querable<T> {

	public List<T> query(String arg) throws IOException;
	
	public ObservableList<T> sortedObservableQuery(String arg) throws IOException;
	
}
