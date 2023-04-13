package domain;

public class HybridProcessor extends AbstractEcuProcessor {
    public HybridProcessor(String speed) {
        super(speed, true); // its hybrid
    }
}
