package com.capitalone.squadron.ayalytics.constants;

import java.util.Timer;
import java.util.TimerTask;

import com.capitalone.squadron.ayalytics.dao.SquadronAnalyticsDAO;
import com.capitalone.squadron.ayalytics.dao.SquadronAnalyticsDAOImpl;
import com.capitalone.squadron.ayalytics.schedular.DemonTaskDriver;
import com.capitalone.squadron.ayalytics.schedular.Report1Task;
import com.capitalone.squadron.ayalytics.service.SuadronReportService;
import com.capitalone.squadron.ayalytics.service.SuadronReportServiceImpl;
import com.capitalone.squadron.ayalytics.util.MiscUtil;

public class GetObjects {

	public static final SuadronReportService suadronReportService = new SuadronReportServiceImpl();
	public static final SquadronAnalyticsDAO squadronAnalyticsDAO = new SquadronAnalyticsDAOImpl();
	public static final MiscUtil miscUtil = new MiscUtil();
	public static final TimerTask driverSchedulartask = new DemonTaskDriver();
	public static final Timer timer = new Timer();
	
}
