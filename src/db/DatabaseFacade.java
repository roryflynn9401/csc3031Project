package db;

import java.io.*;
public enum DatabaseFacade {
    INSTANCE;

    private EntityTable engines;
    private AbstractEntityPersistenceStrategy persistenceStrategy;

    DatabaseFacade() {
        engines = new EntityTable(EntityKeyGenerator.ENGINE);

        // Set which persistence strategy to use
        // (maybe get from configuration settings somewhere)

        persistenceStrategy = new EntitySerializationStrategy();
        //persistenceStrategy = new EntityCSVStrategy();
    }

    public Object[] getAllEngines() {
        return engines.getAll().toArray();
    }

    public Object getEngine(Integer key) {
        return engines.getByKey(key);
    }

    public Integer addEngine(Object engine) {
        return engines.addEntity(engine);
    }

    public void addEngineListener(EntityListener listener) {
        engines.addEntityListener(listener);
    }

    public void removeEngineListener(EntityListener listener) {
        engines.removeEntityListener(listener);
    }

    public void saveEngines() throws IOException {
        persistenceStrategy.save(engines);
    }

    public void restoreEngines() throws IOException {
        EntityTable restoredEngines = persistenceStrategy.restore(engines);
        engines.restore(restoredEngines);
    }
}