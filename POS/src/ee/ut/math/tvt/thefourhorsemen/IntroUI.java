package ee.ut.math.tvt.thefourhorsemen;

import javax.swing.JLabel;
import javax.swing.JFrame;

class Window extends JFrame
{
	Window()
	{
		setTitle("WindowTEST2"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		/* Nii saab lisada teksti koos ikooniga 
		 * ImageIcon icon = createImageIcon("path/to/the/image.gif");
		 * JLabel icon_and_text = new JLabel ("Blablabla", icon, JLabel.CENTER);
		 * 
		 * Ja et muuta teksti positsiooni ikooni suhtes
		 * icon_and_text.setVerticalTextPosition(JLabel.BOTTOM);
		 * icon_and_text.setHorizontalTextPosition(JLabel.CENTER);
		 * 
		 *
		 */
		
		JLabel text_ = new JLabel("Testing text area");	// tekitab teksti sildi	
		add(text_); // ja lisab
		
		setVisible(true);
		
	}
}

public class IntroUI
{

		public static void Window() 
		{
			Window frame = new Window();			
		}

		
		// Siltidega tundus kõige mõistlikum teha seda, aga võib-olla
		// saab teisti paremini kuidagi? JTextField näiteks? Mis arvate`?
}
