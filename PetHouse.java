import ecs100.*; // Import the ecs100 library
import java.awt.Color; // Import the color class
import java.util.HashMap; // Import the hashmap class
/**
 * Driver class for user to create pets
 *
 * @Allison Satoro
 * @26/05/2026
 */
public class PetHouse {
    private VirtualPet newPet; // Create a new pet

    // Regarding the pets statistics
    private final double MAX_STAT; // The maximum amount any pet stat can be
    private final double LOW_STAT; // What is considered as a low stat
    private final double MIN_STAT; // The minimum amount any pet stat can be

    // Hashmap
    private HashMap < String, Double > foodStorage = new HashMap < String, Double > ();

    /**
     * Constructor for objects of class Pethouse
     */
    public PetHouse() {
        UI.initialise(); // Initialise the program
        UI.addButton("Quit", UI::quit); // Creates button which enables user to quit the program
        UI.addButton("Create", this::createPet); // Button for the user to create their pet
        UI.addButton("Check Statistics", this::checkPetStatistics); // Button to check the pets statistics
        UI.addButton("Feed", this::feedPet); // Button for feeding the pet
        UI.addButton("Play", this::petPlaying); // Button to play with the pet
        UI.addButton("Sleep", this::petSleeping); // Button to put the pet to sleep

        // Initializing the hashmap to store items in the food storage
        foodStorage = new HashMap < > ();
        addFoodItems(foodStorage);

        MAX_STAT = 100.0;
        LOW_STAT = 20.0;
        MIN_STAT = 0.00;

        // Thread modelled after example by virtual_programmer on Stack Overflow
        Thread decayThread = new Thread("decaying") {
            public void run() {
                while (true) { // Whilst the code is running
                    if (newPet != null) { // Check if a pet has been created
                        petDecay(); // Call the decay method to decrease hunger
                    }
                    UI.sleep(5000);
                }
            }
        };
        decayThread.start(); // Start the thread for pet decay
    }

    /**
     * Method to add items into the food storage
     */
    public void addFoodItems(HashMap < String, Double > items) {
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
                newPet = new VirtualPet(name); // Create a new pet object and assign it the chosen name
                newPet.draw(); // Draw the pet
                UI.println("This is your new pet " + name); // Print the pets name to the user
                UI.println(" "); // Print a blank line to separate print statements

                // Print out the pets vitals to the user
                UI.println("Your pets hunger is at " + newPet.getHunger() + "/100");
                UI.println("Your pets energy is at " + newPet.getEnergy() + "/100");
                UI.println("Your pets happiness is at " + newPet.getMoodLevel() + "/100");

                // Let the user know what to do when their pet gets sick / tired
                UI.println(" "); // Print a blank line to separate print statements
                UI.println(name + " might get sick, sad, or tired \n" +
                    "Do not panic! This is an easy fix \n" +
                    "If " + name + " gets sick, you must feed them to restore health \n" +
                    "If " + name + " gets tired, make them sleep to restore energy! \n" +
                    "If " + name + " gets sad,play with them to make them happy! \n" +
                    "Take care! And have a happy time caring for " + name + " :)!");
            }
        }
    }

    /**
     * Allows the user to check the pets statistics
     */
    public void checkPetStatistics() {
        if (newPet != null) { // Check if a pet has been created
            // Print out the pets vitals to the user
            UI.println("Your pets hunger is at " + newPet.getHunger() + "/100");
            UI.println("Your pets energy is at " + newPet.getEnergy() + "/100");
            UI.println("Your pets happiness is at " + newPet.getMoodLevel() + "/100");

        } else {
            UI.println("You must create a pet first!");
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
        boolean feeding = true; // Create a boolean for the while loop

        if (newPet != null) { // Check if a pet has been created
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
                    if (newPet.getHunger() >= MAX_STAT) {
                        UI.println("Your pet is full! You cannot feed them");
                        feeding = false; // Stops the loop
                    } else {
                        printFoodStorage(foodStorage); // Print the food names
                        String chosenFood = UI.askString("What would you like to feed your pet?").toLowerCase();
                        feedPetFood(foodStorage, chosenFood); // Call the feeding helper method
                    }
                } else if (feedingInput == 3) {
                    feeding = false; // Stops the loop 
                } else {
                    UI.println("That is not a valid option! Please try again");
                }
            }
        } else {
            UI.println("You must create a pet first!");
        }
    }

    /**
     * Helper method to print food storage
     */
    public void printFoodStorage(HashMap < String, Double > items) {
        UI.println("Current Food Storage");
        for (String foodName: foodStorage.keySet()) {
            UI.println("Food: " + foodName); // Print out the hashmap
        }
    }

    /**
     * Method to check the energy level of a food
     * Check if the user input is an existing key in the hashmap
     * If it is, find the associated energy value and print it
     * If not, tell them that the food does not exist in the storage
     */
    public void checkFoodEnergy(HashMap < String, Double > items, String searchInput) {
        if (items.containsKey(searchInput)) { //Check if the input is in the hashmap
            double energy = items.get(searchInput); // Assign a variable to the necessary value
            UI.println("The energy value for this food is " + energy + " units."); // Print out the value
        } else {
            UI.println(searchInput + " is not in the storage. Sorry!");
        }
    }

    /**
     * Method to increase the pets hunger
     * If the inputted food exists, increase hunger according to the energy value
     * If hunger goes beyond min/max value, correct it to be min/max
     * Print the new hunger of the pet
     * If it does not exist, inform the user
     */
    public void feedPetFood(HashMap < String, Double > items, String chosenFood) {
        if (items.containsKey(chosenFood)) { // Check if the input is in the hashmap
            // Set the new hunger of the pet
            newPet.setHunger(newPet.getHunger() + foodStorage.get(chosenFood));
            if (newPet.getHunger() > MAX_STAT) {
                newPet.setPetState("healthy"); // Set the pets state to healthy
                newPet.draw();
                newPet.setHunger(MAX_STAT); // Set the hunger to the maximum
            } else if (newPet.getHunger() > LOW_STAT) { // Check if hunger is above the minimum
                newPet.setPetState("healthy"); // Set the pets state to healthy
                newPet.draw();
                UI.println("Your pets hunger is now " + newPet.getHunger()); // Print the pets new hunger
            } else if (newPet.getHunger() < MIN_STAT) { // Check if the hunger is below the minimum
                newPet.setHunger(MIN_STAT);
                UI.println("Your pets hunger is now " + newPet.getHunger()); // Print the pets new hunger
            } else {
                UI.println("Your pets hunger is now " + newPet.getHunger()); // Print the pets new hunger
            }
        } else {
            UI.println(chosenFood + " is not in the storage. Sorry!");
        }
    }

    /**
     * Method for the user to play with the pet
     * Ask if they would like to play with the pet
     * If they say yes, decrease energy and inform them of the new energy
     * If they say no, end the loop
     * If energy is too low inform the user and end the loop
     * If happiness or energy goes beyond min/max value, correct it to be min/max
     * If the input is not valid inform the user and ask again
     */
    public void petPlaying() {
        boolean keepPlaying = true; // Condition for the while loop

        if (newPet != null) { // If a pet has been created
            while (keepPlaying) {
                String playInput = UI.askString("Would you like to play? Yes or No").toLowerCase();
                if (playInput.equals("yes")) { // If the user has chosen to play
                    if (newPet.getEnergy() <= LOW_STAT) {
                        UI.println("Energy is too low! Your pet cannot play anymore");
                        newPet.setPetState("tired");
                        newPet.draw();
                        keepPlaying = false;
                    } else {
                        newPet.decreaseEnergy();
                        newPet.increaseMood();

                        if (newPet.getEnergy() > MAX_STAT) { // Check to make sure energy is not over maximum
                            newPet.setEnergy(MAX_STAT); // If it is over the maximum, set it to the maximum
                            newPet.setPetState("healthy");
                            newPet.draw();
                            keepPlaying = false;

                        } else {
                            if (newPet.getMoodLevel() > MAX_STAT) {
                                newPet.setMoodLevel(MAX_STAT);
                                newPet.setPetState("healthy");
                                newPet.draw();
                                UI.println("Energy is now at " + newPet.getEnergy());
                                UI.println("Happiness is now at " + newPet.getMoodLevel());
                            } else {
                                newPet.setPetState("healthy");
                                newPet.draw();
                                UI.println("Energy is now at " + newPet.getEnergy());
                                UI.println("Happiness is now at " + newPet.getMoodLevel());
                            }
                        }
                    }
                } else if (playInput.equals("no")) { // If the user chooses to stop playing
                    UI.println("You are now done playing with your pet!");
                    keepPlaying = false;
                } else {
                    UI.println("Not valid input! Please try again");
                }
            }
        } else {
            UI.println("You must create a pet first!");
        }
    }

    /**
     * Method for the pet sleep
     * Ask the user how many hours they would like their pet to sleep
     * If the pet has full energy, tell them that they cannot sleep
     * Inform them how much energy is given for each hour of sleep
     * Increase the energy according to how much sleep they get
     * If energy exceeds maximum, set it to maximum
     */
    public void petSleeping() {
        boolean keepSleeping = true; // Condition for the while loop to keep running
        final int SLEEP_ENERGY = 10; // The amount of energy sleep provides
        final double MAX_TIME = 10; // Maximum amount of time a pet can sleep
        final double MIN_TIME = 1; // Minimum amount of time a pet can sleep

        if (newPet != null) { // If there is a created pet
            if (newPet.getEnergy() >= MAX_STAT) { // Do not allow sleeping if energy is full
                UI.println("Your pet is full of energy! They cannot sleep");
                keepSleeping = false; // Stop the loop
            } else {
                while (keepSleeping) {
                    // Ask the user for their desired sleeping time
                    int sleepingTime = UI.askInt("How many hours would you like your pet to sleep? \n" +
                        "Please pick between 1-10 hours! \n" +
                        "Each hour of sleep will increase energy by 10");
                    if (MIN_TIME <= sleepingTime && sleepingTime <= MAX_TIME) { // If sleepingTime is between accepted range
                        UI.println("Your pet is falling asleep, please wait");
                        newPet.setPetState("sleeping");
                        newPet.draw();
                        UI.sleep(3000); // Wait for 3 seconds
                        newPet.setEnergy(newPet.getEnergy() + (SLEEP_ENERGY * sleepingTime)); // Calculate the new energy

                        if (newPet.getEnergy() > MAX_STAT) { // Check to make sure energy is not exceeding the maximum stat
                            newPet.setEnergy(MAX_STAT); // If it is exceeding the maximum amount, set it to the maximum
                            newPet.setPetState("healthy"); // Set the pets state to healthy
                            newPet.draw();
                            UI.println("Rise and shine!");
                            UI.println("Your pet now has " + newPet.getEnergy() + " energy!");
                            keepSleeping = false; // Stop the loop
                        } else {
                            newPet.setPetState("healthy");
                            newPet.draw();
                            UI.println("Rise and shine!");
                            UI.println("Your pet now has " + newPet.getEnergy() + " energy!");
                            keepSleeping = false; // Stop the loop  
                        }
                    } else {
                        UI.println("Your pet cannot sleep for that amount of time! Please try again");
                    }
                }
            }
        } else {
            UI.println("You must create a pet first!");
        }
    }

    /**
     * Method for the pet statistics to decay
     * For every tick, the pets hunger should decrease
     */
    public void petDecay() {
        newPet.decreaseHunger(); // Decrease hunger as time passes
        newPet.decreaseMood(); // Decrease mood as the time passes

        // Redraw the pet
        UI.clearGraphics(); // Clear the previous drawing
        newPet.draw(); // Redraw the pet
    }
}