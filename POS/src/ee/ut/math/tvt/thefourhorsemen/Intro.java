package ee.ut.math.tvt.thefourhorsemen;

import java.io.IOException;

import org.apache.log4j.Logger;


public class Intro 
{

	public static void main(String[] args) 
	{
		// Initialise the logger
		Logger log = Logger.getLogger(Intro.class);
		
		try {
			IntroUI.Window();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("UI Window started!");
		
	}

}
