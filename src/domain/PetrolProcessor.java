package domain;

public class PetrolProcessor extends AbstractEcuProcessor {
    public PetrolProcessor(String s) {
        super(s, false); // not hybrid
    }
}