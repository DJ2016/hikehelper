package connection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Params;
import entities.Things;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConnectionFacade{
	private ConnectionFacade(){}
	
	protected static Connection connection;
	protected static Statement statement;
	protected static ResultSet set;


	private static final String dbname = "BDSS.db";
	protected static void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + dbname);
		statement = connection.createStatement();
	}


	
	
	public static boolean requireNullOrClosed (Object arg) {
		try {
			return arg == null || (Boolean) arg.getClass().getMethod("isClosed").invoke(arg);
		} catch (IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException 
				| NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace(System.out);
		} return false;
		
	}
	
	
	protected static void close() throws SQLException  {
		if (!requireNullOrClosed(statement))
			statement.close(); 
		if (!requireNullOrClosed(connection))
			connection.close();
		if (!requireNullOrClosed(set))
			set.close();
	}
	public static int sumMass=0;
	protected static final String tableName = "Things";
	protected static final String columnMass = "Mass";
	protected static final String columnName = "nameThing";
	protected static final String columnValue = "value";
	
	private static void query(List<Things> list, String tableName, Params params) throws ClassNotFoundException, SQLException{
		connect();
		sumMass = 0;
		set = statement.executeQuery(
				"SELECT DISTINCT " + columnName + ", "+ columnValue + ", " + columnMass +" FROM " + tableName + " "
					+ "left join Weather on Things.idThing = Weather.idThing "
					+ "left join Topography on Things.idThing = Topography.idThing "
					+ "left join CountDay on Things.idThing = CountDay.idThing "
						+ "where (range = '" + params.getRange() + "'" 
							+ "and precipitation='" + params.getPrecipitation() + "')" 
							+ "or tipeTP='" + params.getTipeTp() + "'"
							+ "or countD='" + params.getCountD() + "'" 
							+ "or Things.tipe = 1"
		);
		
		while (set.next()){
			list.add(new Things()
					.setThingName(set.getString(columnName))
					.setValue(set.getString(columnValue)));
		sumMass+=set.getInt(columnMass);
		
		}
		
		close();
	}
	

	



	public static List<Things> query(Params arg) throws ClassNotFoundException, SQLException  {
		List<Things> result = new ArrayList<Things>();
		query(result, tableName, arg);
		return result;
	}


	
	public static ObservableList<Things> observableQuery(Params arg) throws ClassNotFoundException, SQLException {
		ObservableList<Things> result = FXCollections.observableArrayList();
		query(result, tableName, arg);
		return result;
	}

}
