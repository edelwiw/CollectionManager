package Commands;

import Run.CollectionManager;

public class Show extends Command{

    public Show(CollectionManager collectionManager) {
        super(collectionManager, "show", "Print all elements from collection");
    }

    @Override
    public void execute(String[] args) {
        this.collectionManager.show();
    }
}
