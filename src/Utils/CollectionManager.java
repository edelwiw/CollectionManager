package Utils;

/**
 * Interface for collection managers. Contains main methods to work with collection.
 * @param <T> class to store in collection
 */
public interface CollectionManager<T> {
    /**
     * Add object to collection.
     * @param object object to add
     */
    public void add(T object);

    /**
     * Print all collection items
     */
    public void show();

    /**
     * Clears all elements from collection
     */
    public void clearCollection();
}
