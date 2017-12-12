package com.capitalone.squadron.ayalytics.main;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.capitalone.squadron.ayalytics.constants.SQLConstants;
import com.capitalone.squadron.ayalytics.constants.SchedularConstants;

public class Test {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub

		/*
		 * System.out.println("-------"+SQLConstants.schedularack);
		 * 
		 * for(int i=1; i<10; i++) { System.out.println(i); }
		 * SQLConstants.schedularack="touch";
		 * 
		 * 
		 * for(int i=1; i<10; i++) {
		 * System.out.println(i+SQLConstants.schedularack); }
		 */

		Object task =  (Object)Class.forName("com.capitalone.squadron.ayalytics.main.RunMeTask").newInstance();



		Timer timer = new Timer();
		timer.schedule((TimerTask) task, 1000, 600);

	}

}

class RunMeTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!SchedularConstants.schedularack.equals("DONE")) {
			SchedularConstants.schedularack = "DONE";
		}
		System.out.println(SchedularConstants.schedularack+"---------");

	}

}