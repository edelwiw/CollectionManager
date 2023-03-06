package Commands;

import Utils.CollectionManager;

/**
 * Show command. Prints all collection elements.
 * This command uses collectionManager reference to call "show" method.
 */
public class Show implements Command{
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        this.collectionManager.show();
    }

    @Override
    public String getDescription() {
        return "show all collection items";
    }
}
