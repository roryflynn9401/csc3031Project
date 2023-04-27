package domain;

import java.io.Serializable;

public abstract class EcuConfiguration implements Serializable {
    private EcuFactory.Type engineType;
    private int topSpeed;

    public EcuConfiguration(EcuFactory.Type type, int topSpeed){
        this.engineType = type;
        this.topSpeed = topSpeed;
    }

    public EcuFactory.Type getEngineType(){
        return this.engineType;
    }
    public int getTopSpeed(){
        return topSpeed;
    }

    public void setEngineType(EcuFactory.Type type){
        this.engineType = type;
    }
    public void setTopSpeed(int topSpeed){
        this.topSpeed = topSpeed;
    }
}
