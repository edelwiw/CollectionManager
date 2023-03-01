package Collection;

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

}



