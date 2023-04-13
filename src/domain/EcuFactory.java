package domain;

public class EcuFactory {
    public enum Type {COMBUSTION, ELECTRIC};

    static Ecu create(Type type, int speed){

        return type == Type.COMBUSTION ?
                new EngineControlModule(new PetrolProcessor(String.valueOf(speed), new CombustionEcuConfiguration(2, true, 40))) :
                new GeneralElectronicModule(new HybridProcessor(String.valueOf(speed), new ElectricEcuConfiguration(2, true, 40)));
    }

    static Ecu create(int speed, boolean isHybrid){
        return create(isHybrid ? Type.ELECTRIC : Type.COMBUSTION, speed);
    }

    private EcuFactory() {}
}
