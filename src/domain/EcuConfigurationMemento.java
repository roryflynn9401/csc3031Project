package domain;

public class EcuConfigurationMemento {

    private EcuConfiguration configuration;
    private EcuFactory.Type engineType;
    private int topSpeed;

    private int cylinders;
    private boolean isRemapped;
    private int noMotors;
    private boolean hasFrunk;

    public EcuConfigurationMemento(EcuConfiguration configuration ){
        this.configuration = configuration;
        this.engineType = configuration.getEngineType();
        this.topSpeed = configuration.getTopSpeed();
        if(configuration.getEngineType() == EcuFactory.Type.COMBUSTION){
            this.isRemapped = ((CombustionEcuConfiguration)configuration).isRemapped();
            this.cylinders = ((CombustionEcuConfiguration)configuration).getCylinders();;
        }
        else{
            this.noMotors = ((ElectricEcuConfiguration)configuration).getNoMotors();
            this.hasFrunk = ((ElectricEcuConfiguration)configuration).hasFrunk();

        }
    }

    public void saveState(){
        this.engineType = configuration.getEngineType();
        this.topSpeed = configuration.getTopSpeed();
        if(configuration instanceof ElectricEcuConfiguration){
            this.noMotors = ((ElectricEcuConfiguration) configuration).getNoMotors();
            this.hasFrunk = ((ElectricEcuConfiguration) configuration).hasFrunk();
        }
        else
        {
            this.cylinders = ((CombustionEcuConfiguration) configuration).getCylinders();
            this.isRemapped = ((CombustionEcuConfiguration) configuration).isRemapped();
        }
    }

    public void restoreState(){
        configuration.setEngineType(this.engineType);
        configuration.setTopSpeed(this.topSpeed);
        if(configuration instanceof ElectricEcuConfiguration){
            ((ElectricEcuConfiguration) configuration).setNoMotors(this.noMotors);
            ((ElectricEcuConfiguration) configuration).setHasFrunk(this.hasFrunk);
        }
        else
        {
            ((CombustionEcuConfiguration) configuration).setCylinders(this.cylinders);
            ((CombustionEcuConfiguration) configuration).setRemapped(this.isRemapped);
        }
    }
}
