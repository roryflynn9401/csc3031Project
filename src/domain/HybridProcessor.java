package domain;

public class HybridProcessor extends AbstractEcuProcessor {

    public HybridProcessor(String speed, ElectricEcuConfiguration configuration) {
        super(speed, true); // its hybrid
        this.setConfiguration(configuration);
    }

}
