package db;

import java.util.*;

public class EntityEvent extends EventObject {
    private Object value;
   
    EntityEvent() {
        this(0, null); // used when restoring data
    }
   
    EntityEvent(Integer key, Object value) {
        super(key);
        this.value = value;
    }
    
    public Integer getKey() {
        return (Integer) getSource();
    }
   
    public Object getValue() {
        return value;
    }
}
