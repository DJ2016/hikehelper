package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entities.Params;
import entities.Things;
import javafx.collections.ObservableList;

public class ConnectionFacade implements Querable<Things>{

	protected static Connection connection;
	protected static Statement statement;
	protected static ResultSet set;

	@Deprecated
	protected static final class Tables {
		public static final String weather = "Weather";
		public static final String user = "User";
		public static final String checkList = "CheckList";
		public static final String topography = "Topography";
		public static final String countPersons = "CountPersons";
		public static final String countDay = "CountDay";
	}

	private static final String dbname = "BDSS.db";
	protected static Statement connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:resources/" + dbname);
		statement = connection.createStatement();
		return statement;
		
	}


	protected static void delete(String tableName) throws ClassNotFoundException, SQLException {
		statement.execute("DROP TABLE '" + tableName + "';");
	}

	protected static void close() throws SQLException {
		if (!statement.isClosed())
			statement.close();
		if (!connection.isClosed())
			connection.close();
		if (set != null && !set.isClosed())
			set.close();
	}
	
	
	public static void someMethod(List<Things> list, String tableName, Params params) throws ClassNotFoundException, SQLException{
		if (statement == null || statement.isClosed())
			statement = connect();
		
		set = statement.executeQuery(
				"SELECT nameThing, priority, value FROM " + tableName + " "
					+ "left join Weather on Things.idThing=Weather.idThing "
					+ "left join Topography on Things.idThing=topography.idThing "
					+ "left join CountDay on Things.idThing=CountDay.idThing "
						+ "where range='" + params.getRange() + "'" 
							+ "or precipitation='" + params.getPrecipitation() + "'" 
							+ "or tipeTP='" + params.getTipeTp() + "'"
							+ "or countD='" + params.getCountD() + "'" 
							+ "or Things.tipe=1"
		);
		
		while (set.next()){
			list.add(new Things()
					.setThingName(set.getString("nameThing"))
					.setPriority(set.getString("priority"))
					.setValue(set.getString("value")));
		}
		
		statement.close();
		set.close();
	}
	
	
	@Override
	public List<Things> query(String arg) throws IOException {
		List<Things> result = null;
		return result;
	}

	@Override
	public ObservableList<Things> sortedObservableQuery(String arg) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


}
