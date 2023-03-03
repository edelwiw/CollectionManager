package Commands;

import Run.LinkedListCollectionManager;
import Utils.CollectionManager;

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
