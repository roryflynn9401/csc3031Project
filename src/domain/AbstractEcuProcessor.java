package domain;

public abstract class AbstractEcuProcessor implements EcuProcessor {
    private String speed;
    private boolean hybrid;

    private EcuConfiguration configuration;
 
    public AbstractEcuProcessor(String s, boolean hyb) {
        this.speed = s;
        this.hybrid = hyb;
    }
    @Override
	public String getSpeed() {
        return speed;
    }
 
    @Override
	public boolean isHybrid() {
        return hybrid;
    }
 
    @Override
	public String toString() {
        return getClass().getSimpleName() + " (" + speed + ")";
    }

    public EcuConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(EcuConfiguration configuration) {
        this.configuration = configuration;
    }

}
