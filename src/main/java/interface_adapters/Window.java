package interface_adapters;

import java.util.Scanner;

public abstract class Window {
    /**
     * Informal Note: This interface may be useful for graphics (later phases), but for now I couldn't think of any
     * benefits of this interface when interacting with the console - Eren
     */
    protected Scanner scanner;
    public Window(Scanner scanner){
        this.scanner = scanner;
    }

    public abstract String[] getUserInput();

}




