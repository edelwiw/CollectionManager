package Utils;

import Collection.Color;
import Collection.DragonCharacter;

import java.util.Scanner;

/**
 * Helps to get values from terminal.
 */
public class CLIManager {

    /**
     * Read Strong from terminal.
     * @return CLI read result. Can be empty string.
     */
    public String requestString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Read integer from terminal.
     * @return Integer number or null, if input is empty.
     * @throws NumberFormatException if input does not integer number.
     */
    public Integer requestInt() throws NumberFormatException{
        Scanner scanner = new Scanner(System.in);
        String raw = scanner.nextLine();
        if(raw.length() == 0) return null;
        return Integer.parseInt(raw);
    }

    /**
     * Read float from terminal.
     * @return Float number or null, if input is empty.
     * @throws NumberFormatException if input does not floating point number.
     */
    public Float requestFloat() throws NumberFormatException{
        Scanner scanner = new Scanner(System.in);
        String raw = scanner.nextLine();
        if(raw.length() == 0) return null;
        return Float.parseFloat(raw);
    }

    /**
     * Read double from terminal.
     * @return Double number or null, if input is empty.
     * @throws NumberFormatException if input does not floating point number.
     */
    public Double requestDouble() throws NumberFormatException{
        Scanner scanner = new Scanner(System.in);
        String raw = scanner.nextLine();
        if(raw.length() == 0) return null;
        return Double.parseDouble(raw);
    }

    /**
     * Read long int from terminal.
     * @return Long number or null, if input is empty.
     * @throws NumberFormatException if input does not long integer number.
     */
    public Long requestLong() throws NumberFormatException{
        Scanner scanner = new Scanner(System.in);
        String raw = scanner.nextLine();
        if(raw.length() == 0) return null;
        return Long.parseLong(raw);
    }

    /**
     * Request DragonCharacter from terminal. Method will show all the options. Not case-sensitive.
     * @return DragonCharacter enum object or null, if input is empty.
     * @throws IllegalArgumentException if input does not match to any option.
     * @see DragonCharacter
     */
    public DragonCharacter requestCharacter() throws IllegalArgumentException{
        for(DragonCharacter character : DragonCharacter.values()){
            System.out.print(character + " ");
        }
        System.out.println();
        String option = this.requestString();
        if(option.length() == 0) return null;
        return DragonCharacter.valueOf(option.toUpperCase());
    }

    /**
     * Request Color from terminal. Method will show all the options. Not case-sensitive.
     * @return Color enum object or null, if input is empty.
     * @throws IllegalArgumentException if input does not match to any option.
     * @see Color
     */
    public Color requestColor() throws IllegalArgumentException{
        for(Color color : Color.values()){
            System.out.print(color + " ");
        }
        System.out.println();
        String option = this.requestString();
        if(option.length() == 0) return null;
        return Color.valueOf(option.toUpperCase());
    }


}
