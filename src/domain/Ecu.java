package domain;

import java.io.Serializable;

public interface Ecu extends Serializable {
    public enum Brand {UNBRANDED, MCCLAREN, DELPHI, BOSCH, SIEMENS, HONDA, DENSO, MOTOROLA};

    public EcuProcessor getEcuProcessor();
    public Ecu.Brand getBrand();
    public void brand(Ecu.Brand colour);

}
