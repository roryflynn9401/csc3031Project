package db;

import java.io.*;

public class EntityCSVStrategy extends AbstractEntityPersistenceStrategy {
    String getFileSuffix() {
        return ".csv";
    }
 
    void save(EntityTable table) throws IOException {
        // code to save table data in CSV format (omitted)
    }
 
    EntityTable restore(EntityTable table) throws IOException {
        // code to restore table data from CSV format (omitted)
        return table;
    }
}
