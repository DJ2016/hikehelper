package entities;

import java.awt.List;
import java.io.PrintStream;
import java.sql.SQLException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import connection.SQLConnection;
import connection.SQLConnection.Param;

public class Things {

	private String thingName;
	private int quantity;

	public Things() {
		this.thingName = "";
		this.quantity = 0;
	}

	public Things(String thingname, int quantity) {
		this.thingName = thingname;
		this.quantity = quantity;
	}

	public String getThingName() {
		return thingName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setThing(String thing, int quantity) {
		this.thingName = thing;
		this.quantity = quantity;
	}
	public Things safeSet(String thing, String quantity){
		quantity = Optional
				.ofNullable(quantity)
				.get()
				.replaceAll("\\D+", "");
		
		int value;
		if (quantity.isEmpty())
			value = 0;
		else value = Integer.parseInt(quantity);
		setThing(thing, value);
		return this;
		
	}
	/*
	protected static List <Things> seach (String tableName, Param params) throws ClassNotFoundException, SQLException {
		//List <Thing> list = new List();
		//CONECT JDBS?
		set = statement.executeQuery("SELECT nameThing FROM " + tableName + "where Things.tipe=1");
		set = statement.executeQuery("SELECT nameThing FROM " + tableName + "inner join Weather on Things.idThing=Weather.idThinginner join Topography on Things.idThing=Topography.idThing inner join CountPersons "
				+ "on Things.idThing=CountPersons.idThing inner join CountDay "
				+ "on Things.idThing=CountDay.idThing where range=" +params.range +"and precipitation="+params.precipitation +"and tipeTP="+params.tipeTp+"and countD="+params.countD);
		while (set.next()) {
		list+=set.getString();
		}
	}*/

}
