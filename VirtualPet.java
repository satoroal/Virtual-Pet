import ecs100.*; // Import the ECS library
import java.awt.Color; // Import the java color library
import java.util.HashMap; // Import the hashmap class
/**
 * Support class to create a virtual pet
 *
 * @Allison Satoro
 * 25/05/2026
 */
public class VirtualPet
{    
    // Pet statistics
    private double hunger; // Hunger level of the pet
    private double energy; // Energy level of the pet
    private double moodLevel; // moodLevel level of the pet
    
    // Pet visuals
    private double petX; // X coord of pet
    private double petY; // Y coord of pet
    private double petWidth; // Width of the pet
    private double petHeight; // Height of the pet
    
    // Bounding box
    private double petLeft; // Left of the bounding box
    private double petTop; // Top of the bounding box
    private double petBottom; // Bottom of the bounding box
    
    // Pet colors
    private Color color;
    private Color healthyBlue = new Color(0, 150, 255); // Color for when the pet is healthy
    private Color sicklyBlue = new Color(115, 147, 179); // Color for when the pet gets sick
    
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
        hunger = 100.0;
        energy = 50.0;
        moodLevel = 80.0;
        
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
        final double DANGEROUS_HUNGER = 20.0; // minimum hunger lever for the pet
        
        if (hunger <= DANGEROUS_HUNGER) {
            UI.setColor(sicklyBlue); // Set the color to the sickly blue when hunger is too low
            UI.println("Your pet is now sick! \n" +
                        "Feed your pet to restore its health!");
        } else {
            UI.setColor(healthyBlue); // Default color is the healthy blue
        }
        
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
     * Set the energy level of the pet
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    
    /**
     * Method to decrease the pets energy
     */
    public void decreaseEnergy() {
        energy -= 5;
    }
    
    /**
     * Return the mood level of the pet
     */
    public double getMoodLevel() {
        return this.moodLevel;
    }
    
    /**
     * Method to set the mood level of the pet
     */
    public void setMoodLevel(double moodLevel) {
        this.moodLevel = moodLevel;
    }
    
    /**
     * Method to decrease the pets mood level
     */
    public void decreaseMood() {
        moodLevel -= 30;
    }
    
    /**
     * Method to increase the pets mood level
     */
    public void increaseMood() {
        moodLevel += 5;
    }
    
    /**
     * Method to decrease the hunger as time passes
     */
    public void passTime() {
        hunger -= 10;
    }
}