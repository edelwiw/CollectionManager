package Collection;

import Exceptions.WrongArgument;
import Exceptions.WrongField;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Dragon class. Main collection class
 * @author Alexander Ivanov @edelwiw
 */
public class Dragon implements Comparable<Dragon>{
    private static Long nextId = 1L;

    private Long id; // Value should be positive, Field value should be unique, value should be generating automatically
    private String name; // Value can not be null, String can not be empty
    private Coordinates coordinates; // Value can not be null
    private final java.time.ZonedDateTime creationDate; // Value can not be null, value should be generated automatically
    private long age; // Value should be positive
    private String description; // Value can not be null
    private Double weight; // Value should be positive
    private DragonCharacter character; // Value can not be null
    private Person killer; // Value can be null

    /**
     * Constructor. Creates an empty object of Dragon class.
     */
    public Dragon(){
        this.id = nextId;
        nextId += 1;

        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Returns Dragon ID
     * @return Dragon ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns Dragon name
     * @return Dragon name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Dragon coordinates
     * @return Dragon Coordinates
     * @see Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Returns Dragon creation date
     * @return Creation date
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Returns Dragon age
     * @return Dragon age
     */
    public long getAge() {
        return age;
    }

    /**
     * Returns Dragon description
     * @return Dragon description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns Dragon weight
     * @return Dragon weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Returns Dragon Character
     * @return Dragon Character
     * @see DragonCharacter
     */
    public DragonCharacter getCharacter() {
        return character;
    }

    /**
     * Returns Dragon killer
     * @return Dragon killer
     * @see Person
     */
    public Person getKiller() {
        return killer;
    }

    /**
     * Set name for Dragon.
     * Name can't be null.
     * Name can't be empty.
     * @param name name to set
     * @throws WrongField if field value does not math requirements
     */
    public void setName(String name) throws WrongField {
        if(name==null) throw new WrongField("Name can not be null");
        if(name.length() == 0) throw new WrongField("Name can not be empty");
        this.name = name;
    }

    /**
     * Set coordinates for Dragon. Coordinates can't be null
     * @param coordinates coordinates to set
     * @see Coordinates
     * @throws WrongField if field value does not math requirements
     */
    public void setCoordinates(Coordinates coordinates) throws WrongField{
        if(coordinates==null) throw new WrongField("Coordinates con not be null");
        this.coordinates = coordinates;
    }

    /**
     * Set age for Dragon. Age should be positive
     * @param age age to set
     * @throws WrongField if field value does not math requirements
     */
    public void setAge(Long age) throws WrongField{
        if(age == null) throw new WrongField("Age can not be null");
        if(age <= 0) throw new WrongField("Age should be positive");
        this.age = age;
    }

    /**
     * Set description for Dragon.
     * Description can't be null
     * @param description description to set
     * @throws WrongField if field value does not math requirements
     */
    public void setDescription(String description) throws WrongField {
        if(description == null) throw new WrongField("Description can not be null");
        this.description = description;
    }

    /**
     * Set weight for Dragon.
     * Weight should be positive number
     * @param weight dragon weight to set.
     * @throws WrongField if field value does not math requirements
     */
    public void setWeight(Double weight) throws WrongField{
        if(weight == null) throw new WrongField("Weight can not be null");
        this.weight = weight;
    }

    /**
     * Set character for Dragon.
     * Character can't be null
     * @param character dragon character to set
     * @throws WrongField if field value does not math requirements
     * @see Character
     */
    public void setCharacter(DragonCharacter character) throws WrongField{
        if(character == null) throw new WrongField("Character can not be null");
        this.character = character;
    }

    /**
     * Set killer for Dragon
     * Killer can not be null
     * @param killer dragon killer to set
     * @throws WrongField if field value does not math requirements
     * @see Person
     */
    public void setKiller(Person killer) throws WrongField {
        if(killer == null) throw new WrongField("Killer can not be null");
        this.killer = killer;
    }


    /**
     * Get dragon fields
     * @return String with dragon object fields
     */
    @Override
    public String toString() {
        return "id=" + this.id +
                ", name=" + this.name +
                ", coordinates=" + this.coordinates +
                ", creationDate=" + this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", age=" + this.age +
                ", description=" + this.description +
                ", weight=" + this.weight +
                ", character=" + this.character +
                ", killer=" + this.killer;
    }


    @Override
    public int compareTo(@NotNull Dragon other) {
        return this.name.compareTo(other.name);
    }
}



