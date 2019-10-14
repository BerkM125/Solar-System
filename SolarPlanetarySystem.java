/**
Solar System App Project
This app uses user input to inform the user about
the solar system that they live in. The user can
prompt the name of a planet and view physical 
properties about that planet, including the properties 
of that planet's moons. The amount of moons you can prompt properties
from is only 1, so you cannot access the properties of all of Jupiter's
moons unless the user instentiates a different moon manually. 
**/

import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.*;

class SolarPlanet extends Moon{
   BufferedImage planet_img = null;
   public String planetImgSrc;
 
   public String planet_name;
   //Unit of distance: kilometers.//
   public long diameter;
   //Distance from sun to planet.//
   public long solar_distance;
   String mass;
   public boolean matter; /*'True' is solid, 'False' is gas.*/
   SolarPlanet ( String name, long ldiameter, long lsolar_distance, String lmass, boolean lmatter, String lsrc, String moonImgSrc) {
      planet_name = name;
      solar_distance = lsolar_distance;
      diameter = ldiameter;
      mass = lmass;
      matter = lmatter;
      planetImgSrc = lsrc;
      
      try {
         if(planetImgSrc != null)
            planet_img = ImageIO.read(new File(planetImgSrc));
         if(moonImgSrc != null)
            moon_img = ImageIO.read(new File(moonImgSrc));
      } catch (IOException e) {
      }
   }
   
} 

//SolarPlanet class inherits the fields, methods, and variables of the Moon class.//
class Moon extends CalcPlanetData{

   public long moon_diameter;
   public long distance_from_planet;
   public String moon_mass;
   public String moon_name;
   BufferedImage moon_img = null;
   public String moonImgSrc;
}

class CalcPlanetData {
   public double pi = 3.14159;
   public float getCircumference(long diameter) {
      float return_circumference;
      return_circumference = (float)diameter * (float)pi;
      
      return (return_circumference);
   }
}   
   
class textColor {
   public Color text;
   public Color background;
   textColor (Color ltext, Color lbackground) {
      text = ltext;
      background = lbackground;
   }
}     
   
public class SolarPlanetarySystem extends JPanel{
   static JFrame SolarSystemFrame = new JFrame();  
   static int printPlanetIndent = 0;
   static boolean outputTypeFrame = true;
   static String fontType = new String("Calibri");
   static textColor fontColor = new textColor(Color.BLACK, Color.WHITE);
   
   static void getPlanetStatusJOptionPane ( SolarPlanet planet) { 
      JOptionPane.showMessageDialog(SolarSystemFrame, "PLANET: \nName: " + planet.planet_name + "\nDiameter(km): " + planet.diameter
                                    + "\nMass: " + planet.mass + "\nDistance from the sun(km): " + planet.solar_distance
                                    + "\nRock planet? " + planet.matter + "\nCircumference: " + planet.getCircumference(planet.diameter)
                                    , "Planet Status", JOptionPane.INFORMATION_MESSAGE);
   } 
   static void getPlanetMoonStatusJOptionPane ( SolarPlanet planet) {
      JOptionPane.showMessageDialog(SolarSystemFrame, "MOON: \nName: " + planet.moon_name + "\nDiameter(km): " + planet.moon_diameter
                                    + "\nMass: " + planet.moon_mass + "\nDistance from parent planet(km): " + planet.distance_from_planet
                                    + "\nCircumference: " + planet.getCircumference(planet.moon_diameter), "Moon Status", JOptionPane.INFORMATION_MESSAGE);
   }
 
   static void getPlanetStatusG2 ( SolarPlanet planet, Graphics2D g2) { 
      Font font = new Font(fontType, Font.PLAIN, 18);
      Font Title_Font = new Font(fontType, Font.PLAIN, 30);
      
      g2.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
      g2.setFont(Title_Font);
      g2.drawString("Planet: " + planet.planet_name, 40, 100+printPlanetIndent);
      
      g2.setFont(font);
      g2.drawString("Diameter(km): " + planet.diameter, 40, 140+printPlanetIndent);
      g2.drawString("Mass: " + planet.mass, 40, 160+printPlanetIndent);
      g2.drawString("Distance from the sun(km): " + planet.solar_distance, 40, 180+printPlanetIndent);
      g2.drawString("Rock Planet? " + planet.matter, 40, 200+printPlanetIndent);
      g2.drawString("Circumference: " + planet.getCircumference(planet.moon_diameter), 40, 220+printPlanetIndent);
      g2.drawString(planet.planet_name + " Complete.", 40, 240+printPlanetIndent);
      
      g2.drawImage(planet.planet_img, null, 450, 90+printPlanetIndent);
      printPlanetIndent += 180;
      
      if(printPlanetIndent >= 180*3) {      
         outputTypeFrame = false;
      }
   }
   
   static void getMoonStatusG2 ( SolarPlanet planet, Graphics2D g2) { 
      Font font = new Font(fontType, Font.PLAIN, 13);
      Font Title_Font = new Font(fontType, Font.PLAIN, 25);
      
      g2.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
      g2.setFont(Title_Font);
      g2.drawString("Moon: " + planet.moon_name, 60, 100+printPlanetIndent);
      
      g2.setFont(font);
      g2.drawString("Diameter(km): " + planet.moon_diameter, 60, 140+printPlanetIndent);
      g2.drawString("Mass: " + planet.moon_mass, 60, 160+printPlanetIndent);
      g2.drawString("Distance from the parent planet(km): " + planet.distance_from_planet, 60, 180+printPlanetIndent);
      g2.drawString("Circumference: " + planet.getCircumference((long)planet.moon_diameter), 60, 200+printPlanetIndent);
      g2.drawString(planet.moon_name + " Complete.", 60, 220+printPlanetIndent);
      
      g2.drawImage(planet.moon_img, null, 450, 90+printPlanetIndent);
      printPlanetIndent += 180;
      
      if(printPlanetIndent >= 180*3) {      
         outputTypeFrame = false;
      }
   }   

   public void paint(Graphics g) {
      Graphics2D SolarSystemG2 = (Graphics2D)g;
      
      long diameter, solar_distance; 
      String access_planet; 
      Object [] options = {"Moon", "Planet", "Cancel"}; 
      Object [] planet_names = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Neptune", "Uranus"};
      int planet_or_moon_val; 
      SolarSystemG2.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
      fontType = (String)JOptionPane.showInputDialog(SolarSystemFrame, "Font: ", "Font", JOptionPane.QUESTION_MESSAGE);
                  
      SolarPlanet earth = new SolarPlanet ( "Earth", 12472, 149600000, "5.972x10^21(tons)", true, "Earth.jpg", "earth_moon.jpg");
         earth.moon_name = "Moon"; 
         earth.moon_diameter = 3474;
         earth.distance_from_planet = 384400;
         earth.moon_mass = "8.09942316x10^19(tons)";
      SolarPlanet mercury = new SolarPlanet ( "Mercury", 4879, 57910000, "3.285x10^20(tons)", true, "Mercury.jpg", null); 
         mercury.moon_name = "Hypothetical"; 
         mercury.moon_diameter = 0;
         mercury.distance_from_planet = 0;
         mercury.moon_mass = "Unknown";   
      SolarPlanet venus = new SolarPlanet ( "Venus", 12104, 67240000, "4.867x10^21(tons)", true, "Venus.jpg", null);   
         venus.moon_name = "Unknown"; 
         venus.moon_diameter = 3474;
         venus.distance_from_planet = 384400;
         venus.moon_mass = "Unknown"; 
      SolarPlanet mars = new SolarPlanet ( "Mars", 6779, 227900000, "6.39x10^20(tons)", true, "Mars.jpg", "mars_moon.jpg");
         mars.moon_name = "Phobos"; 
         mars.moon_diameter = 23;
         mars.distance_from_planet = 9380;
         mars.moon_mass = "1.0659x1016(kg)";
      SolarPlanet jupiter = new SolarPlanet ( "Jupiter", 139822, 778500000, "1.898x10^24(tons)", false, "Jupiter.jpg", "jupiter_moon.png");
         jupiter.moon_name = "Ganymede";
         jupiter.moon_diameter = 5268;
         jupiter.distance_from_planet = 1070213;
         jupiter.moon_mass = "2.4766e-02(kg)";
      SolarPlanet saturn = new SolarPlanet ( "Saturn", 116464, 1434000000, "5.683x10^23(tons)", false, "Saturn.jpg", "saturn_moon.jpg");
         saturn.moon_name = "Titan";
         saturn.moon_diameter = 5149;
         saturn.distance_from_planet = 1221850;
         saturn.moon_mass = "1.3452x10^23(kg)";
     //Planets below contain values (representing solar distance) that are too big.
     /* SolarPlanet uranus = new SolarPlanet ("Uranus", 50724, 2871000000, "8.681x10^22(tons)", false);
         uranus.moon_name = "Titania";
         uranus.moon_diameter = 1577;
         uranus.distance_from_planet = 436000;
         uranus.moon_mass = "3.527(+/-)0.09x10^21(kg)";
      SolarPlanet neptune = new SolarPlanet ("Neptune", 49244, 4495000000, "1.024x10^23(tons)", false);
         neptune.moon_name = "Triton";
         neptune.moon_diameter = 2707;
         neptune.distance_from_planet = 354760;
         neptune.moon_mass = "2.14x10^22(kg)";*/
         
      while (true) {     
         planet_or_moon_val = JOptionPane.showOptionDialog(SolarSystemFrame,
                                                  "Accessing a planet or a moon?", 
                                                  "Planet or Moon", 
                                                  JOptionPane.YES_NO_CANCEL_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null,
                                                  options,
                                                  options[1]);
         
         if (planet_or_moon_val == 1) {         
               access_planet = (String)JOptionPane.showInputDialog(SolarSystemFrame, 
                                                   "Planet Name: ",
                                                   "Choose Planet",
                                                   JOptionPane.QUESTION_MESSAGE,
                                                   null,
                                                   planet_names,
                                                   planet_names[0]);
                                                   
               if(Objects.equals(access_planet, "Earth")) 
                  if(outputTypeFrame) 
					  getPlanetStatusG2 (earth, SolarSystemG2); 
				  else getPlanetStatusJOptionPane(earth);  
			   
               if(Objects.equals(access_planet, "Mercury")) 
                 if(outputTypeFrame) 
					 getPlanetStatusG2 (mercury, SolarSystemG2); 
				 else 
					 getPlanetStatusJOptionPane(mercury);
				 
               if(Objects.equals(access_planet, "Venus"))
                  if(outputTypeFrame) 
					  getPlanetStatusG2 (venus, SolarSystemG2); 
				  else 
					  getPlanetStatusJOptionPane(venus);
				  
               if(Objects.equals(access_planet, "Mars"))
                  if(outputTypeFrame) 
					  getPlanetStatusG2 (mars, SolarSystemG2); 
				  else 
					  getPlanetStatusJOptionPane(mars);
				  
               if(Objects.equals(access_planet, "Jupiter"))
                  if(outputTypeFrame)
					  getPlanetStatusG2 (jupiter, SolarSystemG2); 
				  else 
					  getPlanetStatusJOptionPane(jupiter); 
				  
               if(Objects.equals(access_planet, "Saturn"))
                  if(outputTypeFrame) 
					  getPlanetStatusG2 (saturn, SolarSystemG2); 
				  else 
					  getPlanetStatusJOptionPane(saturn);   
         }

         else if (planet_or_moon_val == 0) {         
               access_planet = (String)JOptionPane.showInputDialog(SolarSystemFrame, 
                                                   "Planet (containing moon) Name: ",
                                                   "Choose Moon",
                                                   JOptionPane.QUESTION_MESSAGE,
                                                   null,
                                                   planet_names,
                                                   planet_names[0]);
               if(Objects.equals(access_planet, "Earth") || Objects.equals(access_planet, "earth")) 
                  if(outputTypeFrame) 
					  getMoonStatusG2 (earth, SolarSystemG2); 
				  else 
					  getPlanetMoonStatusJOptionPane (earth);
				  
               if(Objects.equals(access_planet, "Mercury") || Objects.equals(access_planet, "mercury")) 
                  if(outputTypeFrame) 
					  getMoonStatusG2 (mercury, SolarSystemG2); 
				  else 
					  getPlanetMoonStatusJOptionPane (mercury);
				  
               if(Objects.equals(access_planet, "Venus") || Objects.equals(access_planet, "venus"))
                  if(outputTypeFrame) 
					  getMoonStatusG2 (venus, SolarSystemG2); 
				  else 
					  getPlanetMoonStatusJOptionPane (venus);
				  
               if(Objects.equals(access_planet, "Mars") || Objects.equals(access_planet, "mars"))
                  if(outputTypeFrame) 
					  getMoonStatusG2 (mars, SolarSystemG2); 
				  else 
					  getPlanetMoonStatusJOptionPane (mars);
				  
               if(Objects.equals(access_planet, "Jupiter") || Objects.equals(access_planet, "jupiter"))
                  if(outputTypeFrame) 
					  getMoonStatusG2 (jupiter, SolarSystemG2); 
				  else 
					  getPlanetMoonStatusJOptionPane (jupiter);        
               if(Objects.equals(access_planet, "Saturn") || Objects.equals(access_planet, "saturn"))
                  if(outputTypeFrame) 
					  getMoonStatusG2 (saturn, SolarSystemG2); 
				  else 
					  getPlanetMoonStatusJOptionPane (saturn);    
         }   
         
         else
            System.exit(0);
      }
   }
   
   public static void main (String[] args) {                   
      SolarSystemFrame.getContentPane().add(new SolarPlanetarySystem());
      SolarSystemFrame.setSize(800, 800);
      SolarSystemFrame.setLocation(500, 0);
      SolarSystemFrame.setBackground(fontColor.background);
      SolarSystemFrame.setForeground(fontColor.text);
      SolarSystemFrame.setVisible(true);
      SolarSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
         
}