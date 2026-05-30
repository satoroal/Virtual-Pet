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
        foodStorage = new HashMap<>();
        addFoodItems(foodStorage);
  
    }
    
    /**
     * Method to add items into the food storage
     */
    public void addFoodItems(HashMap<String, Double> items) {
        // Add items to the HashMap (Food name, Energy restoration value)
        items.put("tomato", 5.0);
        items.put("passionfruit", 10.0);
        items.put("kibble", 15.0);
        items.put("steak", 20.0);     
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
            String name = UI.askString("What would you like your pet to be named? \n" +
                                        "Please keep it between 1-15 letters").toLowerCase();
            
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
     * If they want to check the food storage call the appropriate method
     * If they want to feed the pet call the appropriate method
     * End loop when they choose to stop
     */
    public void feedPet() {
        final double MAXHUNGER = 100.0; // Set a max hunger level
        boolean feeding = true; // Create a boolean for the while loop
        
        while (feeding) {
            int feedingInput = UI.askInt("What would you like to do? Please answer with a number \n" +
                                            "1. Check your food storage \n" +
                                            "2. Feed your pet \n" +
                                            "3. Stop");
            if (feedingInput == 1) {
                printFoodStorage(foodStorage); // Print the foodnames
                
                // Ask the user which food item they would like to check the energy of
                String searchInput = UI.askString("Which item would you like to check the value of?").toLowerCase();
                checkFoodEnergy(foodStorage, searchInput); // Call the checking storage helper method
            } else if (feedingInput == 2) {
                printFoodStorage(foodStorage); // Print the food names
                
                // Ask the user the name of the food they would like to feed their pet
                String chosenFood = UI.askString("What would you like to feed your pet?").toLowerCase();
                if (newPet.getHunger() == MAXHUNGER) {
                    UI.println("Your pet is full! You cannot feed them");
                    feeding = false; // Stops the loop
                } else {
                    feedPetFood(foodStorage, chosenFood); // Call the feeding helper method
                }
            } else if (feedingInput == 3) {
                feeding = false; // Stops the loop 
                UI.println("Print to show loop has ended for testing purposes");
            } else {
                UI.println("That is not a valid option! Please try again");
            }
        }
    }
    
    /**
     * Helper method to print food storage
     */
    public void printFoodStorage(HashMap<String, Double> items) {
        UI.println("Current Food Storage");
        for (String foodName : foodStorage.keySet()) {
            UI.println("Food: " + foodName); // Print out the hashmap
        }
    }
    
    /**
     * Method to check the energy level of a food
     * Check if the user input is an existing key in the hashmap
     * If it is, find the associated energy value and print it
     * If not, tell them that the food does not exist in the storage
     */
    public void checkFoodEnergy(HashMap<String, Double> items, String searchInput) {
        if (items.containsKey(searchInput)) { //Check if the input is in the hashmap
            double energy = items.get(searchInput); // Assign a variable to the necessary value
            UI.println("The energy value for this food is " + energy + "units."); // Print out the value
        } else {
            UI.println(searchInput + " is not in the storage. Sorry!");
        }
    }
    
    /**
     * Method to increase the pets hunger
     * If the inputted food exists, increase hunger according to the energy value
     * Print the new hunger of the pet
     * If it does not exist, inform the user
     */
    public void feedPetFood(HashMap<String, Double> items, String chosenFood) {
    if (items.containsKey(chosenFood)) {
        double currentHunger = newPet.getHunger(); // Create a new variable for the pets current hunger
        currentHunger += foodStorage.get(chosenFood); // Add the foods energy value to the current hunger
        newPet.setHunger(currentHunger); // Set the new hunger of the pet
        UI.println("Your pets hunger is now " + newPet.getHunger()); // Print the pets new hunger
    } else {
        UI.println(chosenFood + " is not in the storage. Sorry!");
    }
    }
}