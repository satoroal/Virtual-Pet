import ecs100.*; // Import the ecs100 library
import java.awt.Color; // Import the color class
import java.util.HashMap; // Import the hashmap class
/**
 * Driver class for user to create pets
 *
 * @Allison Satoro
 * @26/05/2026
 */
public class PetHouse
{
    private VirtualPet newPet; // create a new pet

    /**
     * Constructor for objects of class Pethouse
     */
    public PetHouse()
    {
        // Fields needed for the pet house
        UI.initialise(); // Initialise the program
        UI.addButton("Quit", UI::quit); // Creates button which enables user to quit the program
        UI.addButton("Create Pet", this::createPet);  // Button for the user to create their pet
        // Button for feeding the pet
        // Button to put the pet to sleep
        // Button to play with the pet
        
        // Hashmap to store items in the food storage
        HashMap<String, Double> foodStorage = new HashMap<String, Double>();
        
        // Add keys and values (Food name, energy provided)
        foodStorage.put("Tomato", 5.0);
        foodStorage.put("Passionfruit", 10.0);
        foodStorage.put("Kibble", 15.0);
        foodStorage.put("Steak", 20.0);        
    }
    
    /**
     * Ask the user for the pets name
     * If they put a name that is not valid, prompt them to try again
     * If the name is valid, create a new pet and draw it
     * Tell the user the pets name and vitals
     */
    public void createPet() {
        boolean petValid = false;
        final int MIN_LENGTH = 1; // Minimum length for the pets name
        final int MAX_LENGTH = 15; // Maximum length for the pets name
    
        while (petValid) {
            // Ask the user for the pets name
            String name = UI.askString("What would you like your pet to be named? Please keep it between 1-15 letters");
            
            if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
                UI.println("This is not a valid name. Please try again"); // Repeat if name is invalid
            } else {
                petValid = true; // Break the while loop
                newPet = new VirtualPet(name); // Create a new pet object and assign it to the name
                newPet.draw(); // Draw the pet
                UI.println("This is your new pet " + name); // Print the pets name to the user
                
                // Print out the pets vitals to the user
                UI.println("Your pets hunger is at " + newPet.getHunger() + "/100");
                UI.println("Your pets energy is at " + newPet.getEnergy() + "/100");
                UI.println("Your pets happiness is at " + newPet.getHappiness() + "/100");
            }
        }
    }
    
    /**
     * Method to feed the pet
     * Prompt user to choose which item in food storage to feed the put
     * Increase the hunger level accordingly with the food item
     * If the pet is already full (hunger = 100) tell the user they cannot feed the pet anymore
     */
    public void feedPet() {
        
    }
}