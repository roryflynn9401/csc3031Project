package domain;

public abstract class EcuConfiguration {
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
}
