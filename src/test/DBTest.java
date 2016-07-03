package test;

import java.sql.SQLException;
import java.util.List;

import connection.ConnectionFacade;
import entities.Params;
import entities.Things;

public class DBTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Params p = new Params().setRange("0-10").setTipeTp("Ћес").setCountD("10").setCountPR("3").setPrecipitation("ясно");
		List<Things> list =  ConnectionFacade.query(p);
		list.forEach((thing) -> System.out.println(thing));
	}

}
