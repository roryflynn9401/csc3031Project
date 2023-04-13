package domain;

public class PetrolProcessor extends AbstractEcuProcessor {
    public PetrolProcessor(String s, CombustionEcuConfiguration configuration) {
        super(s, false); // not hybrid
        this.setConfiguration(configuration);
    }
}