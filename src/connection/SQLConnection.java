package connection;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
	protected static Connection connection;
	protected static Statement statement;
	protected static ResultSet set;

	protected static final class Tables {
		public static final String weather = "Weather";
		public static final String user = "User";
		public static final String checkList = "CheckList";
		public static final String topography = "Topography";
		public static final String countPersons = "CountPersons";
		public static final String countDay = "CountDay";
		//TODO: ADD OTHER TABLES HERE
	}
	public static final class Param {	
		public static final String nameThing = "nameThing";
		public static final String range = "range";
		public static final String precipitation = "precipitation";
		public static final String tipeTp = "tipeTP";
		public static final String countPR = "countPR";
		public static final String countD = "countD";
	}


	protected static Statement connect() throws ClassNotFoundException, SQLException {
		//TODO: ADD THIS 
		String dbname = "DBSS";
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:resources/" + dbname);
		statement = connection.createStatement();
		return statement;
	}

	protected static void create(String tableName) throws ClassNotFoundException, SQLException {
		statement.execute("CREATE TABLE if not exists " + tableName +
				//TODO: SET ADD THIS
				" id INTEGER PRIMARY KEY AUTOINCREMENT, ;");
	}
	
	protected static void insert(String tableName, String values) throws SQLException, ClassNotFoundException {
		//TODO: COMPLETE THIS
		statement.execute("INSERT INTO '" + tableName + "' () VALUES " + values + ";");
	}
	protected static void read(PrintStream ps, String tableName) throws ClassNotFoundException, SQLException {
		set = statement.executeQuery("SELECT * FROM " + tableName);
		while (set.next()) {
			ps.print("ID = " + set.getInt("id"));
			//TODO: PUT CODE HERE
			ps.println();
		}
	}

/*
 set=statement.executeQuery("SELECT nameThing, priority, value FROM"+ tableName +"left join Weather on Things.idThing=Weather.idThing "
 		+ "left join Topography on Things.idThing=topography.idThing "
 		+ "left join CountDay on Things.idThing=CountDay.idThing where range='"+params.range+"'" +"or precipitation='"+params.precipitation+"'" +"or tipeTP='"+params.tipeTp+"'"+"or countD='"+params.countD+"'"+"or Things.tipe=1");
 */
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
}
