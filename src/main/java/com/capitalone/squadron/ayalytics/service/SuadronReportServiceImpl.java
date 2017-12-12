package com.capitalone.squadron.ayalytics.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.capitalone.squadron.ayalytics.beans.Report1FetchedPOJO;
import com.capitalone.squadron.ayalytics.beans.Table1POJO;
import com.capitalone.squadron.ayalytics.constants.GetObjects;
import com.capitalone.squadron.ayalytics.constants.SQLConstants;
import com.capitalone.squadron.ayalytics.dao.SquadronAnalyticsDAO;
import com.capitalone.squadron.ayalytics.dao.SquadronAnalyticsDAOImpl;
import com.capitalone.squadron.ayalytics.util.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SuadronReportServiceImpl implements SuadronReportService{

	/*
	 * This method will fetch report info from sqadron DB and insert into report
	 * table
	 */
	//SquadronAnalyticsDAO squadronAnalyticsDAO = new SquadronAnalyticsDAOImpl();

	JSONUtil jSONUtil = new JSONUtil();
	int counter = 0;
	int size = 1;
	int pojoCount = 0;

	public Map<Object, Object> prepareReportData(String query)
			throws ClassNotFoundException, SQLException, IOException {
		/* fetching records from squadron table */
		List<Report1FetchedPOJO> allTypes = GetObjects.squadronAnalyticsDAO
				.fetchRecordsFromDB(query);
		Map<Object, Object> segregratedProcess = new HashMap<Object, Object>();
		Set uniqueType = new HashSet();
		for (Report1FetchedPOJO O : allTypes) {
			uniqueType.add(O.getType());
		}
		ArrayList a[] = new ArrayList[Integer.valueOf(uniqueType.size())];

		for (Object S : uniqueType) {

			a[counter] = new ArrayList();
			for (Report1FetchedPOJO l : allTypes) {
				if (l.getType() == S) {
					a[counter].add(l);
				}
			}
			segregratedProcess.put(S, a[counter]);
			counter++;
		}
		
		counter = 0;
		return segregratedProcess;
	}

	public String pushFetchedReportToReportTbl(Object key, List<Object> pojoList)
			throws ClassNotFoundException, SQLException, IOException {

		String JSONdata = jSONUtil.convertListToJSON(pojoList);
		List<Object> dataTobeSendtoReportTable = new ArrayList<Object>();
		dataTobeSendtoReportTable.add(key);
		dataTobeSendtoReportTable.add(JSONdata);
		return GetObjects.squadronAnalyticsDAO.InsertRecordsToDB(
				SQLConstants.insert_statement, dataTobeSendtoReportTable);

	}
	
	public List<Table1POJO> getReportMetadaInfo(String query) throws ClassNotFoundException, SQLException, IOException
	{
		/*Map<Object,List<Object>> metainfo = new HashMap<Object,List<Object>>();
		List<Object> metaInfo = new ArrayList<Object>();*/
		List<Table1POJO> metadataList = GetObjects.squadronAnalyticsDAO.fetchReportMetadata(query);
		/*for(Table1POJO O : metadataList)
		{
			metainfo.put(O.getReportID(),O.getFrequency());
		}
		*/
		return metadataList;
	}

}
