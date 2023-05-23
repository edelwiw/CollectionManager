package Utils;

import Collection.*;
import Exceptions.WrongField;
import java.util.Scanner;

/**
 * Helps to get values from terminal.
 */
public class CLIManager {

    /**
     * Read Strong from terminal.
     * @return CLI read result. Can be empty string.
     */
    private String requestString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Read integer from terminal.
     * @return Integer number or null, if input is empty.
     * @throws NumberFormatException if input does not integer number.
     */
    private Integer requestInt() throws NumberFormatException{
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
    private Float requestFloat() throws NumberFormatException{
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
    private Double requestDouble() throws NumberFormatException{
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
    private Long requestLong() throws NumberFormatException{
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
    private DragonCharacter requestCharacter() throws IllegalArgumentException{
        for(DragonCharacter character : DragonCharacter.values()){
            System.out.print(character + " ");
        }
        System.out.println();
        String option = this.requestString().strip();
        if(option.length() == 0) return null;
        return DragonCharacter.valueOf(option.toUpperCase());
    }

    /**
     * Request Color from terminal. Method will show all the options. Not case-sensitive.
     * @return Color enum object or null, if input is empty.
     * @throws IllegalArgumentException if input does not match to any option.
     * @see Color
     */
    private Color requestColor() throws IllegalArgumentException{
        for(Color color : Color.values()){
            System.out.print(color + " ");
        }
        System.out.println();
        String option = this.requestString().strip();
        if(option.length() == 0) return null;
        return Color.valueOf(option.toUpperCase());
    }

    /**
     * Requests Coordinates from terminal.
     * @return Coordinates object
     * @see Coordinates
     */
    private Coordinates requestCoordinates(){
        Coordinates coordinates = new Coordinates();

        // request X
        while (true){
            try{
                System.out.println("Enter X dragon coordinate");
                coordinates.setX(this.requestDouble());
                break;
            } catch (WrongField e){
                System.out.println("Wrong X. " + e.getMessage() + ". Enter again");
            } catch (NumberFormatException e){
                System.out.println("X should be a double number. Enter again");
            }
        }

        // request Y
        while (true){
            try{
                System.out.println("Enter Y dragon coordinate");
                coordinates.setY(this.requestLong());
                break;
            }catch (WrongField e){
                System.out.println("Wrong Y. " + e.getMessage() + ". Enter again");
            }catch (NumberFormatException e){
                System.out.println("Y should be a long int number. Enter again");
            }
        }

        return coordinates;
    }

    /**
     * Request location from terminal.
     * @return Location object
     * @see Location
     */
    private Location requestLocation(){
        Location location = new Location();

        // request X
        while (true){
            try{
                System.out.println("Enter X coordinate");
                location.setX(this.requestFloat());
                break;
            } catch (WrongField e){
                System.out.println("Wrong X. " + e.getMessage() + ". Enter again");
            } catch (NumberFormatException e){
                System.out.println("X should be a float number. Enter again");
            }
        }

        // request Y
        while (true){
            try{
                System.out.println("Enter Y coordinate");
                location.setY(this.requestInt());
                break;
            }catch (WrongField e){
                System.out.println("Wrong Y. " + e.getMessage() + ". Enter again");
            }catch (NumberFormatException e){
                System.out.println("Y should be an int number. Enter again");
            }
        }

        // request name
        while (true){
            try{
                System.out.println("Enter a location name");
                location.setName(this.requestString());
                break;
            } catch (WrongField e){
                System.out.println("Wrong name. " + e.getMessage() + ". Enter again.");
            }
        }

        return location;
    }

    /**
     * Request Person from terminal.
     * @return Person object
     * @see Person
     */
    private Person requestPerson(){
        Person person = new Person();

        // request name
        while (true){
            try{
                System.out.println("Enter a killer name");
                person.setName(this.requestString());
                break;
            } catch (WrongField e){
                System.out.println("Wrong name. " + e.getMessage() + ". Enter again.");
            }
        }

        // request hair color
        while (true){
            try {
                System.out.println("Select hair color from this options.");
                person.setHairColor(this.requestColor());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Color should be selected from options. Enter again");
            }
        }

        //request passport ID
        while (true){
            try{
                System.out.println("Enter a passport ID");
                person.setPassportID(this.requestString());
                break;
            } catch (WrongField e){
                System.out.println("Wrong passport ID. " + e.getMessage() + ". Enter again.");
            }
        }


        // request location
        person.setLocation(this.requestLocation());

        return person;
    }


    /**
     * Get dragon by fields from CLI
     * Method will ask each field. If input value is incorrect method will ask to enter it again.
     * @param blank blank object to add fields
     * @return Dragon object
     */
    public Dragon requestDragon(Dragon blank){
        // request name
        while (true) {
            try {
                System.out.println("Enter a dragon name");
                blank.setName(this.requestString());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong name. " + e.getMessage() + ". Enter again");
            }
        }

        // request age
        while (true) {
            try {
                System.out.println("Enter an age");
                blank.setAge(this.requestLong());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong age. " + e.getMessage() + ". Enter again");
            } catch (NumberFormatException e) {
                System.out.println("Age should be long integer number. Enter again");
            }
        }

        // request description
        while (true) {
            try {
                System.out.println("Enter a description");
                blank.setDescription(this.requestString());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong description. " + e.getMessage() + ". Enter again");
            }
        }

        // request weight
        while (true) {
            try {
                System.out.println("Enter a weight");
                blank.setWeight(this.requestDouble());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong weight. " + e.getMessage() + ". Enter again");
            } catch (NumberFormatException e) {
                System.out.println("Weight should be floating point number. Enter again");
            }
        }

        // request character
        while (true) {
            try {
                System.out.println("Select character from this options.");
                blank.setCharacter(this.requestCharacter());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Character should be selected from options. Enter again");
            }
        }

        // request coordinates
        blank.setCoordinates(this.requestCoordinates());

        // request killer
        blank.setKiller(this.requestPerson());

        return blank;
    }



}
