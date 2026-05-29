import ecs100.*; // Import the ecs100 library
import java.awt.Color; // Import the color class
import java.util.HashMap; // Import the hashmap class
import java.util.Map;
/**
 * Driver class for user to create pets
 *
 * @Allison Satoro
 * @26/05/2026
 */
public class PetHouse
{
    private VirtualPet newPet; // create a new pet
    
    // Hashmap
    private HashMap<String, Double> foodStorage = new HashMap<String, Double>();
    
    // Final double to store the energy amounts of food items
    private final double TOMATOENERGY;
    private final double PASSIONFRUITENERGY;
    private final double KIBBLEENERGY;
    private final double STEAKENERGY;
    
    /**
     * Constructor for objects of class Pethouse
     */
    public PetHouse()
    {
        UI.initialise(); // Initialise the program
        UI.addButton("Quit", UI::quit); // Creates button which enables user to quit the program
        UI.addButton("Create Pet", this::createPet);  // Button for the user to create their pet
        UI.addButton("Feed Pet", this::feedPet); // Button for feeding the pet
        // Button to put the pet to sleep
        // Button to play with the pet
        
        // Initializing the hashmap to store items in the food storage
        HashMap<String, Double> foodStorage = new HashMap<String, Double>();
        
        // Food Storage amounts
        TOMATOENERGY = 5.0;
        PASSIONFRUITENERGY = 10.0;
        KIBBLEENERGY = 15.0;
        STEAKENERGY = 20.0;   
    }
    
    public void addFoodItems(HashMap<String, Double> items) {
        // Add items to the HashMap (Food name, Energy restoration value)
        items.put("tomato", TOMATOENERGY);
        items.put("passionfruit", PASSIONFRUITENERGY);
        items.put("kibble", KIBBLEENERGY);
        items.put("steak", STEAKENERGY);     
    }
    
    /**
     * Ask the user for the pets name
     * If they put a name that is not valid, prompt them to try again
     * If the name is valid, create a new pet and draw it
     * Tell the user the pets name and vitals
     */
    public void createPet() {
        boolean petValid = false; // Create a boolean for the while loop
        final int MIN_LENGTH = 1; // Minimum length for the pets name
        final int MAX_LENGTH = 15; // Maximum length for the pets name
    
        while (!petValid) {
            // Ask the user for the pets name
            String name = UI.askString("What would you like your pet to be named? Please keep it between 1-15 letters").toLowerCase();
            
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
     * Prompt user to choose whether they want to check storage or feed the pet
     * If they want to check the food storage call the helper method
     * If they want to feed the pet ask what they would like to feed their pet
     * Increase the hunger level accordingly with the food item
     * If the pet is already full (hunger = 100) tell the user they cannot feed the pet anymore
     */
    public void feedPet() {
        PetHouse obj = new PetHouse();
        final double MAXHUNGER = 100.0; // Set a max hunger level
        boolean feeding = true; // Create a boolean for the while loop
        
        while (feeding = true) {
            int feedingInput = UI.askInt("What would you like to do? Please answer with a numerical value \n" +
                                            "1. Check your food storage \n" +
                                            "2. Feed your pet");
            if (feedingInput == 1) {
                for (String i : foodStorage.keySet()) {
                    UI.println("Food: " +  i);
                }
                String searchInput = UI.askString("Which item would you like to check?").toLowerCase();
                obj.checkFoodEnergy(foodStorage, searchInput);
            }                        
        }       
    }
    
    /**
     * Helper method to check the energy level of a food
     */
    public void checkFoodEnergy(HashMap<String, Double> items, String searchInput) {
        if (items.containsKey(searchInput)) {
            double energy = items.get(searchInput);
            UI.println("The energy value for this food is " + energy + "units.");
        } else {
            UI.println(searchInput + " does not exist. Sorry!");
        }
    }
}