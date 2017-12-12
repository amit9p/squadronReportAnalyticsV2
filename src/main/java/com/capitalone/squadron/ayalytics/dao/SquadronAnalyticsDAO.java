package com.capitalone.squadron.ayalytics.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.capitalone.squadron.ayalytics.beans.Report1FetchedPOJO;
import com.capitalone.squadron.ayalytics.beans.Table1POJO;

public interface SquadronAnalyticsDAO {

	public List<Report1FetchedPOJO> fetchRecordsFromDB(String query)
			throws ClassNotFoundException, SQLException, IOException;

	public String InsertRecordsToDB(String insertSql,
			List<Object> dataTobeInserted) throws ClassNotFoundException,
			SQLException, IOException;
	
	public List<Table1POJO> fetchReportMetadata(String query)
			throws ClassNotFoundException, SQLException, IOException;
}
