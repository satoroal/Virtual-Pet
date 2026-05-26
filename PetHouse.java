import ecs100.*;
import java.awt.Color;
import java.util.Scanner;
/**
 * Driver class for user to create pets
 *
 * @Allison Satoro
 * @26/05/2026
 */
public class PetHouse
{
    // Fields for user to give the pet characteristics
    private VirtualPet testPet;
    
    /**
     * Constructor for objects of class Pethouse
     */
    public PetHouse()
    {
        // Fields needed for the pet house
        UI.initialise();
        UI.addButton("Quit", UI::quit);
        UI.addButton("Create Pet", this::createPet); 
        testPet = new VirtualPet(" ");
    }
    
    /**
     * Ask the user for the pets desired name
     * Draw the pet
     */
    public void createPet() {
        testPet.askPetName();
        testPet.draw();
    }
}