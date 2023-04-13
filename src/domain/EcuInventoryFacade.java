package domain;

import db.DatabaseFacade;
import db.EntityListener;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public enum EcuInventoryFacade {
    INSTANCE;

    public Object[] getEcuTypes(){
        return EcuFactory.Type.values();
    }
    public Object[] getAllEcus(){
        return DatabaseFacade.INSTANCE.getAllEngines();
    }

    public Object addEcu(int speed, boolean isHybrid){
        var engine = EcuFactory.create(speed, isHybrid);
        DatabaseFacade.INSTANCE.addEngine(engine);
        return engine;
    }

    public void saveEcus() throws IOException {
        DatabaseFacade.INSTANCE.saveEngines();
    }

    public void restoreEcus() throws IOException{
        DatabaseFacade.INSTANCE.restoreEngines();
    }

    public void addEcuListener(EntityListener listener){
        DatabaseFacade.INSTANCE.addEngineListener(listener);
    }

    public void removeEcuListener(EntityListener listener){
        DatabaseFacade.INSTANCE.removeEngineListener(listener);
    }

    public void generateCsv(IEcuFilter filter){
        CsvReportManager reportManager = new CsvReportManager();
        reportManager.generateReport(filter);
    }
    public void generateJson(IEcuFilter filter){
        JsonReportManager reportManager = new JsonReportManager();
        reportManager.generateReport(filter);
    }
    public List<Object> getFilteredEngines(IEcuFilter filter){
        return List.of(getAllEcus()).stream()
                .filter(filter::filter)
                .collect(Collectors.toList());
    }
}
