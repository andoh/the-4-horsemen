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
		// see asi sai vist k�ll v�ga �mber nurga n��d, aga 
		// ei tahtnud mittet��tavat asja �les laadida.
		// Proovisin k�ll extendida IntroUI'd ennast JFrame-iga, aga
		// siis hakkasid igasugused static errorid tulema...
		// V�ite proovida ise, �kki teil rohkem �nne ja teete midagi 
		// teistmoodi kui mina
		
	}

}
