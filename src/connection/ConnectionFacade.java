package connection;

import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFacade {

	private Statement stmnt;
	
	public void someMethod() throws ClassNotFoundException, SQLException{
		if (stmnt == null || stmnt.isClosed())
			stmnt = SQLConnection.connect();
	}
}
