package Commands;

import Run.CollectionManager;

public class Show extends Command{

    public Show(CollectionManager collectionManager, String name, String description) {
        super(collectionManager, name, description);
    }

    @Override
    public void execute(String[] args) {
        this.collectionManager.show();
    }
}
