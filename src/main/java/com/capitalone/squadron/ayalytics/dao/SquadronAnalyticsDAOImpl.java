package com.capitalone.squadron.ayalytics.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capitalone.squadron.ayalytics.beans.Report1FetchedPOJO;
import com.capitalone.squadron.ayalytics.beans.Table1POJO;
import com.capitalone.squadron.ayalytics.util.ConnectionUtil;

public class SquadronAnalyticsDAOImpl implements SquadronAnalyticsDAO {

	public int Report1FetchedPOJOSize = 1;
	public int ReportMetadataPOJOSize = 1;
	public int pojoCount = 0;
	public int metaPojoCount = 0;

	public List<Report1FetchedPOJO> fetchRecordsFromDB(String query)
			throws ClassNotFoundException, SQLException, IOException {

		Statement stmt = ConnectionUtil.getConnection().createStatement();
		Statement stmtCount = ConnectionUtil.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSet rsCount = stmtCount.executeQuery(query);
		List<Report1FetchedPOJO> alltypes = new ArrayList<Report1FetchedPOJO>();
		while (rsCount.next()) {
			Report1FetchedPOJOSize++;
		}
		Report1FetchedPOJO[] report1FetchedPOJO = new Report1FetchedPOJO[Report1FetchedPOJOSize];
		while (rs.next()) {
			/* Set squadron table values to Report1FetchedPOJO */

			report1FetchedPOJO[pojoCount] = new Report1FetchedPOJO();
			report1FetchedPOJO[pojoCount].setId(rs.getInt(1));
			report1FetchedPOJO[pojoCount].setName(rs.getString(2));
			report1FetchedPOJO[pojoCount].setPhone(rs.getString(3));
			report1FetchedPOJO[pojoCount].setType(rs.getInt(4));
			alltypes.add(report1FetchedPOJO[pojoCount]);
			pojoCount++;
		}

		stmt.close();
		stmtCount.close();
		return alltypes;

	}

	public String InsertRecordsToDB(String insertSql,
			List<Object> dataTobeInserted) throws ClassNotFoundException,
			SQLException, IOException {

		PreparedStatement preparedStmt = ConnectionUtil.getConnection()
				.prepareStatement(insertSql);
		for (int i = 1; i <= dataTobeInserted.size(); i++) {
			preparedStmt.setObject(i, dataTobeInserted.get(i - 1));
		}
		preparedStmt.execute();
		preparedStmt.close();

		return "INSERTED";
	}

	public List<Table1POJO> fetchReportMetadata(String query)
			throws ClassNotFoundException, SQLException, IOException {
		Statement stmt = ConnectionUtil.getConnection().createStatement();
		Statement stmtCount = ConnectionUtil.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSet rsCount = stmtCount.executeQuery(query);
		List<Table1POJO> alltypes = new ArrayList<Table1POJO>();
		while (rsCount.next()) {
			ReportMetadataPOJOSize++;
		}

		Table1POJO[] reportMetadataFetchedPOJO = new Table1POJO[ReportMetadataPOJOSize];

		while (rs.next()) {
			/* Set squadron table values to Report1FetchedPOJO */

			reportMetadataFetchedPOJO[pojoCount] = new Table1POJO();
			reportMetadataFetchedPOJO[pojoCount].setReportID(rs.getInt(1));
			reportMetadataFetchedPOJO[pojoCount].setFrequency(rs.getInt(2));
			reportMetadataFetchedPOJO[pojoCount].setStopFlag(rs.getString(3));
			alltypes.add(reportMetadataFetchedPOJO[pojoCount]);
			pojoCount++;
		}

		stmt.close();
		stmtCount.close();
		return alltypes;
	}
}
