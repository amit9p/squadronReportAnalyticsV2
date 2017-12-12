package com.capitalone.squadron.ayalytics.schedular;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import com.capitalone.squadron.ayalytics.beans.Table1POJO;
import com.capitalone.squadron.ayalytics.constants.GetObjects;
import com.capitalone.squadron.ayalytics.constants.SQLConstants;
import com.capitalone.squadron.ayalytics.constants.SchedularConstants;

public class DemonTaskDriver extends TimerTask {

	public static List<Table1POJO> initialMetadataMapReport1;
	public List<Table1POJO> updatedMetadataMapReport1;

	public Object[] task = null;
	int counter = 0;
	int updatedCount = 0;

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			if (!SchedularConstants.schedularack.equals("STARTED")) {
				/* Initialize initial metadata map */
				initialMetadataMapReport1 = (List<Table1POJO>) GetObjects.suadronReportService
						.getReportMetadaInfo(SQLConstants.select_report_metadata);

				task = new Report1Task[initialMetadataMapReport1.size()];
			}

			else {
				/* Initialize periodic updated metadata map */
				updatedMetadataMapReport1 = (List<Table1POJO>) GetObjects.suadronReportService
						.prepareReportData(SQLConstants.select_report_metadata);
			}

			if (SchedularConstants.schedularack.equals("STARTED")) {

				for(int i=0; i<=updatedMetadataMapReport1.size(); i++)
				{
					if(initialMetadataMapReport1.get(i).getFrequency() != updatedMetadataMapReport1.get(i).getFrequency())
					{
						
					}
					else if(updatedMetadataMapReport1.get(i).getStopFlag().equals("TRUE"))
					{
						TimerTask t = (TimerTask) task[i];
						t.cancel();
					}
				}
				initialMetadataMapReport1 = updatedMetadataMapReport1;
				/*
				 * compare initial map and periodic map for any change in
				 * frequency
				 */
				/*
				 * if change in frequency found then cancel the running task and
				 * re launch with new frequency* else do nothing
				 */

			} else {

				/* change the schedularack to STARTED */
				SchedularConstants.schedularack = "STARTED";
				/* launch report tasks with specified frequency */
				for (Table1POJO O : initialMetadataMapReport1) {
					task[counter] = (Object) Class.forName(
							GetObjects.miscUtil.getProperties(String.valueOf(O
									.getReportID()))).newInstance();

					if (!O.getStopFlag().equals("TRUE")) {
						GetObjects.timer.schedule((TimerTask) task[counter],
								100, (Integer) O.getFrequency());

					} else {
						System.out.println("Report task not started");
						
					}
					counter++;
				}

			}

		}

		catch (Exception e) {
			System.out.println(e.getCause());
		}

	}

}
