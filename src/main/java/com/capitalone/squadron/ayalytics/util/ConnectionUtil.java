package com.capitalone.squadron.ayalytics.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws IOException, SQLException,
			ClassNotFoundException {
		String DRIVER = MiscUtil.miscUtil.getProperties("DB_DRIVER");
		String DB_URL = MiscUtil.miscUtil.getProperties("DB_URL");
		String DB_USER = MiscUtil.miscUtil.getProperties("DB_USER");
		String DB_PWD = MiscUtil.miscUtil.getProperties("DB_PASSWORD");
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		return con;
	}
}
