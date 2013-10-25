package ee.ut.math.tvt.thefourhorsemen;


import java.io.IOException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

public class Intro 
{
	
	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";
	
	public static void main(String[] args) 
	{
		// Initialise the logger
		//Logger log = Logger.getLogger(Intro.class);
		
		try {
			IntroUI.Window();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("UI Window started!");
		
		final SalesDomainController domainController = new SalesDomainControllerImpl();

		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {

			//Enne oli IntroUI
			
			IntroUI introUI = new IntroUI();
			try {
				introUI.Window();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//introUI.setVisible(true);
			//introUI.setAlwaysOnTop(true);
			
			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);

			//introUI.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//introUI.setVisible(false);
		}
	
	}
}
