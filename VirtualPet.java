import ecs100.*;
import java.awt.Color;
import java.util.HashMap; // Import the hashmap class
/**
 * Support class to create a virtual pet
 *
 * @Allison Satoro
 * 25/05/2026
 */
public class VirtualPet
{
    // Fields needed for the virtual pet 
    
    // Pet statistics
    private double hunger; // Hunger level of the pet
    private double energy; // Energy level of the pet
    private double happiness; // Happiness level of the pet
    
    // Pet visuals
    private double petX; // X coord of pet
    private double petY; // Y coord of pet
    private double petWidth; // Width of the pet
    private double petHeight; // Height of the pet
    
    // Bounding box
    private double petLeft; // Left of the bounding box
    private double petTop; // Top of the bounding box
    private double petBottom; // Bottom of the bounding box
    
    private HashMap<String, Double> foodStorage = new HashMap<String, Double>();

        
    /**
     * Constructor for objects of class virtualpet
     */
    public VirtualPet(String name) {        
        // Regarding the pet's location
        petX = 150.0;
        petY = 150.0;
        petWidth = 100.0;
        petHeight = 100.0;
        
        // The pets statistics
        hunger = 50.0;
        energy = 50.0;
        happiness = 80.0;
        
        // Calling methods to set the pets bounding box
        setLeft();
        setTop();
        setBottom();
    }
    
    /**
     * Set the left of the bounding box
     */
    public void setLeft() {
        this.petLeft = this.petX - this.petWidth/2.0;
    }
    
    /**
     * Set the top of the bounding box
     */
    public void setTop() {
       this.petTop = this.petY - this.petWidth/2.0;
    }
    
    public void setBottom() {
        this.petBottom = this.petY + this.petHeight;
    }
    
    /**
     * Method to draw the pet
     */
    public void draw() {
        UI.setColor(Color.blue);
        UI.fillRect(petLeft, petTop, petWidth, petHeight);
    }
    
    /**
    * Set the hunger of the pet
    */
    public void setHunger(double hunger) {
        this.hunger = hunger;
    }
    
    /**
     * Return the hunger of the pet
     */
    public double getHunger() {
        return this.hunger;
    }
    
    /**
     * Return the energy level of the pet
     */
    public double getEnergy() {
        return this.energy;
    }
    
    /**
     * Return the happiness level of the pet
     */
    public double getHappiness() {
        return this.happiness;
    }
}