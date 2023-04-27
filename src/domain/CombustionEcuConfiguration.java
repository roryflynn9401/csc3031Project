package domain;

public class CombustionEcuConfiguration extends EcuConfiguration{
    private int cylinders;
    private boolean isRemapped;

    public CombustionEcuConfiguration(int cylinders, boolean isRemapped, int topSpeed) {
        super(EcuFactory.Type.COMBUSTION, topSpeed);
        this.cylinders = cylinders;
        this.isRemapped = isRemapped;
    }

    public int getCylinders(){
        return this.cylinders;
    }

    public boolean isRemapped(){
        return this.isRemapped;
    }

    public void setCylinders(int cylinders){
        this.cylinders = cylinders;
    }

    public void setRemapped(boolean isRemapped){
        this.isRemapped = isRemapped;
    }
}
