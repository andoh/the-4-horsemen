package ee.ut.math.tvt.thefourhorsemen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

class Window extends JFrame
{
	Window() throws IOException
	{
		setTitle("WindowTEST2"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		// create and load app properties
		Properties appProps = new Properties();
		FileInputStream in = new FileInputStream("application.properties");
		appProps.load(in);
		in.close();
		// create and load ver properties
		Properties verProps = new Properties();
		FileInputStream verIn = new FileInputStream("version.properties");
		verProps.load(verIn);
		verIn.close();
		
		
		setLayout(new GridBagLayout());
		
		String[] data = {
				"Tiimi nimi",
		        appProps.getProperty("team_name"),
		        "Tiimi liidri nimi",
		        appProps.getProperty("team_leader"),
		        "Tiimi liidri e-mail",
		        appProps.getProperty("team_leader_email"),
		        "Tiimi liikmed",
		        appProps.getProperty("team_members"),
		        "Versioon",
		        verProps.getProperty("build.number")
		        };
		
		//Gridi asjade paigutamine.
		GridBagConstraints c = new GridBagConstraints();
		JLabel silt = (new JLabel());
		Integer luger = 0;
		for (int i=0;i<5;i++){
			c.gridy = i;
			for (int j=0;j<2;j++){
				c.gridx = j;
				if (j==0){
					c.weightx = 0.3;
				}
				else{
					c.weightx = 0.7;
				}
				silt = new JLabel(data[luger]);
				add(silt,c);
				luger++;		
			}
		}
		
		//Pildi lisamine
		GridBagConstraints d = new GridBagConstraints();
		ImageIcon icon = new ImageIcon("team_image.jpg");
		silt = new JLabel(icon);
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridy = 5;
		d.gridwidth = 2;
		d.anchor = GridBagConstraints.CENTER;
		add(silt,d);
		  
				
		/*
		// Nii saab lisada teksti koos ikooniga 
		  
		  //Ja et muuta teksti positsiooni ikooni suhtes
		 
		  icon_and_text.setVerticalTextPosition(JLabel.BOTTOM);
		  icon_and_text.setHorizontalTextPosition(JLabel.CENTER);
		 /* 
		 *
		 */
		
		//JLabel text_ = new JLabel("Testing text area");	// tekitab teksti sildi	
		//add(text_); // ja lisab
		
		setVisible(true);
		
	}
}

public class IntroUI
{

		public static void Window() throws IOException 
		{
			Window frame = new Window();			
		}

		
		// Siltidega tundus kõige mõistlikum teha seda, aga võib-olla
		// saab teisti paremini kuidagi? JTextField näiteks? Mis arvate`?
}


/* Little girls made this, while still young.
c.weightx = 0.3;
c.gridy = 0;
c.gridx = 0;
add(silt,c);
silt = (new JLabel(appProps.getProperty("team_name")));
c.weightx = 0.7;
c.gridy = 0;
c.gridx = 1;
add(silt,c);
silt = (new JLabel("Tiimi liidri nimi"));
c.weightx = 0.3;
c.gridy = 1;
c.gridx = 0;
add(silt,c);

silt = (new JLabel(appProps.getProperty("team_leader")));
c.weightx = 0.7;
c.gridy = 1;
c.gridx = 1;
add(silt,c);

silt = (new JLabel("Tiimi liidri e-mail"));
c.weightx = 0.3;
c.gridy = 2;
c.gridx = 0;
add(silt,c);

silt = (new JLabel(appProps.getProperty("team_leader_email")));
c.weightx = 0.7;
c.gridy = 2;
c.gridx = 1;
add(silt,c);

silt = (new JLabel("Tiimi liikmed"));
c.weightx = 0.3;
c.gridy = 3;
c.gridx = 0;
add(silt,c);

silt = (new JLabel(appProps.getProperty("team_members")));
c.weightx = 0.7;
c.gridy = 3;
c.gridx = 1;
add(silt,c);

silt = (new JLabel("Versioon"));
c.weightx = 0.3;
c.gridy = 4;
c.gridx = 0;
add(silt,c);

silt = (new JLabel(verProps.getProperty("build.number")));
c.weightx = 0.7;
c.gridy = 4;
c.gridx = 1;
add(silt,c);
*/