package ee.ut.math.tvt.thefourhorsemen;

import org.apache.log4j.Logger;


public class Intro 
{

	public static void main(String[] args) 
	{
		// Initialise the logger
		Logger log = Logger.getLogger(Intro.class);
		
		IntroUI.Window();
		log.info("UI Window started!");
		// see asi sai vist küll väga ümber nurga nüüd, aga 
		// ei tahtnud mittetöötavat asja üles laadida.
		// Proovisin küll extendida IntroUI'd ennast JFrame-iga, aga
		// siis hakkasid igasugused static errorid tulema...
		// Võite proovida ise, äkki teil rohkem õnne ja teete midagi 
		// teistmoodi kui mina
		
	}

}
