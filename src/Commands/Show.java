package Commands;

import Run.CollectionManager;

public class Show implements Command{
    CollectionManager collectionManager;

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
