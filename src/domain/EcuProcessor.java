package domain;

import java.io.*;
public interface EcuProcessor extends Serializable {
    public String getSpeed();
    public boolean isHybrid();

    EcuConfiguration getConfiguration();
    void setConfiguration(EcuConfiguration configuration);
}
