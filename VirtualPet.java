import ecs100.*;
import java.awt.Color;
import java.util.Scanner;
/**
 * Support class to create a virtual pet
 *
 * @Allison Satoro
 * 25/05/2026
 */
public class VirtualPet
{
    // Fields needed for the virtual pet
    private String name; // Pet's name
    
    // Pet statistics
    private int hunger; // Hunger level of the pet
    private int energy; // Energy level of the pet
    private int happiness; // Happiness level of the pet
    
    // Pet visuals
    private double petX; // X coord of pet
    private double petY; // Y coord of pet
    private double width; // Width of the pet
    private double height; // Height of the pet
    
    // Fields for user to give the pet characteristics
    private Scanner userInput = new Scanner(System.in); // Creating scanner object
    
    /**
     * Constructor for objects of class virtualpet
     */
    public VirtualPet(String name) {
        this.name = name;
    }
    
    /**
     * Method to draw the pet
     */
    public void draw() {
        // Draw the pet
        UI.setColor(Color.blue);
        UI.fillRect(60, 30, 100, 100);
    }
    
    public void askPetName() {
        name = UI.askString("What is the name of your pet?"); // Ask the user for the pets name
        UI.println(name); // Test to see if name was registered
    }
}