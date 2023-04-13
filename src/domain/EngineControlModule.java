package domain;


public class EngineControlModule implements Ecu {
	
    private EcuProcessor ecuProcessor;
    private Ecu.Brand brand;
    
    public EngineControlModule(EcuProcessor p) {
        this(p, Ecu.Brand.UNBRANDED);
    }
    public EngineControlModule(EcuProcessor p, Ecu.Brand brand) {
        this.ecuProcessor = p;
        this.brand = brand;
    } 
 
    @Override
	public EcuProcessor getEcuProcessor() {
        return ecuProcessor;
    }
 
    @Override
	public Ecu.Brand getBrand() {
        return this.brand;
    }
 
    @Override
	public void brand(Ecu.Brand brand) {
        this.brand = brand;
    }

    @Override
	public String toString() {
    	return getClass().getSimpleName() + " (" + ecuProcessor + ", " + brand + ")";
    }    
    
    
    
}
