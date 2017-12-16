package com.capitalone.squadron.ayalytics.schedular;

import java.util.List;
import java.util.TimerTask;

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
	boolean flag = false;

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
				System.out.println("Main schedular called");
				/* Initialize periodic updated metadata map */
				updatedMetadataMapReport1 = (List<Table1POJO>) GetObjects.suadronReportService
						.getReportMetadaInfo(SQLConstants.select_report_metadata);
			}

			if (SchedularConstants.schedularack.equals("STARTED")) {

				for (int i = 0; i < updatedMetadataMapReport1.size(); i++) {
					System.out.println(initialMetadataMapReport1.get(i)
							.getFrequency());
					System.out.println(updatedMetadataMapReport1.get(i)
							.getFrequency());

					if (!initialMetadataMapReport1
							.get(i)
							.getFrequency()
							.equals(updatedMetadataMapReport1.get(i)
									.getFrequency())) {
						System.out.println("Cancelling the task");
						System.out.println("launching with new frequency");
						TimerTask t = (TimerTask) task[i];
						t.cancel();

						if (updatedMetadataMapReport1.get(i).getStopFlag()
								.equals("FALSE")) {
							task[i] = (Object) Class.forName(
									GetObjects.miscUtil.getProperties(String
											.valueOf(updatedMetadataMapReport1
													.get(i).getReportID())))
									.newInstance();

							GetObjects.timer.schedule((TimerTask) task[i], 100,
									(Integer) updatedMetadataMapReport1.get(i)
											.getFrequency());
						}

					}
					if (updatedMetadataMapReport1.get(i).getStopFlag()
							.equals("TRUE")) {
						TimerTask t = (TimerTask) task[i];
						t.cancel();
						flag = true;
					}

					if (updatedMetadataMapReport1.get(i).getStopFlag()
							.equals("FALSE")) {
						if (flag == true) {
							task[i] = (Object) Class.forName(
									GetObjects.miscUtil.getProperties(String
											.valueOf(updatedMetadataMapReport1
													.get(i).getReportID())))
									.newInstance();
							GetObjects.timer.schedule((TimerTask) task[i], 100,
									(Integer) updatedMetadataMapReport1.get(i)
											.getFrequency());
						}

					}

				}
				initialMetadataMapReport1 = updatedMetadataMapReport1;

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
