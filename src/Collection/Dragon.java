package Collection;

import Exceptions.WrongField;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Dragon {
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

    public Dragon(){
        this.id = nextId;
        nextId += 1;

        this.creationDate = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public Double getWeight() {
        return weight;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public Person getKiller() {
        return killer;
    }


    public void setName(String name) {
        if(name==null) throw new WrongField("Name can not be null");
        if(name.length() == 0) throw new WrongField("Name can not be empty");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates==null) throw new WrongField("Collection.Coordinates con not be null");
        this.coordinates = coordinates;
    }

    public void setAge(long age) {
        if(age <= 0) throw new WrongField("Age should be positive");
        this.age = age;
    }

    public void setDescription(String description) {
        if(description == null) throw new WrongField("Description can not be null");
        this.description = description;
    }

    public void setWeight(Double weight) {
        if(weight == null) throw new WrongField("Weight should be positive number");
        this.weight = weight;
    }

    public void setCharacter(DragonCharacter character) {
        if(character == null) throw new WrongField("Character can not be null");
        this.character = character;
    }

    public void setKiller(Person killer) {
        if(killer == null) throw new WrongField("Killer can not be null");
        this.killer = killer;
    }

    @Override
    public String toString() {
        return "id=" + this.id +
                ", name=" + this.name +
                ", coordinates=" + this.coordinates +
                ", creationDate=" + this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", age=" + this.age +
                ", description=" + this.description + " " +
                ", weight=" + this.weight +
                ", character=" + this.character +
                ", killer=" + this.killer;
    }
}



