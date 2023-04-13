package domain;

public class EcuFactory {
    public enum Type {COMBUSTION, ELECTRIC};

    static Ecu create(Type type, int speed){

        return type == Type.COMBUSTION ?
                new EngineControlModule(new PetrolProcessor(String.valueOf(speed))) :
                new GeneralElectronicModule(new HybridProcessor(String.valueOf(speed)));
    }

    static Ecu create(int speed, boolean isHybrid){
        return create(isHybrid ? Type.ELECTRIC : Type.COMBUSTION, speed);
    }

    private EcuFactory() {}
}
