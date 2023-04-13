package db;

import java.util.*;

public interface EntityListener extends EventListener {
    public void entityAdded(EntityEvent event);
    public void entityRestored(EntityEvent event);
}
