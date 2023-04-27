package domain;

public class ElectricEcuConfiguration extends EcuConfiguration{
    private int noMotors;
    private boolean hasFrunk;

    public ElectricEcuConfiguration(int noMotors, boolean hasFrunk, int topSpeed) {
        super(EcuFactory.Type.ELECTRIC, topSpeed);
        this.noMotors = noMotors;
        this.hasFrunk = hasFrunk;
    }

    public int getNoMotors(){
        return this.noMotors;
    }

    public boolean hasFrunk(){
        return this.hasFrunk;
    }

    public void setNoMotors(int noMotors){
        this.noMotors = noMotors;
    }

    public void setHasFrunk(boolean hasFrunk){
        this.hasFrunk = hasFrunk;
    }
}
