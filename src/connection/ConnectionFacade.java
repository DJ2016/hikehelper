package connection;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entities.Things;
import javafx.collections.ObservableList;

public class ConnectionFacade implements Querable<Things>{

	private Statement stmnt;
	
	public void someMethod() throws ClassNotFoundException, SQLException{
		if (stmnt == null || stmnt.isClosed())
			stmnt = SQLConnection.connect();
	}

	@Override
	public List<Things> query(String arg) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableList<Things> sortedObservableQuery(String arg) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


}
