package com.capitalone.squadron.ayalytics.schedular;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;

import com.capitalone.squadron.ayalytics.constants.GetObjects;
import com.capitalone.squadron.ayalytics.constants.SQLConstants;

public class Report1Task extends TimerTask {

	

	@Override
	public void run() {

		try {
			System.out.println("Report1 generated");
			Map<Object, Object> segregatedTypes = GetObjects.suadronReportService
					.prepareReportData(SQLConstants.select_statement);

			for (Entry<Object, Object> entry : segregatedTypes.entrySet()) {

				GetObjects.suadronReportService.pushFetchedReportToReportTbl(
						entry.getKey(), (List<Object>) entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
