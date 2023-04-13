package domain;

import db.DatabaseFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractReportManager {

    public void generateReport(IEcuFilter filter){
        var filteredEngines = Filter(List.of(EcuInventoryFacade.INSTANCE.getAllEcus()), filter);
        this.exportData(filteredEngines, filter);
    }
    public abstract void exportData(List<Object> data, IEcuFilter filter);

    public List<Object> Filter(List<Object> engines , IEcuFilter filter){
        return engines.stream()
                .filter(filter::filter)
                .collect(Collectors.toList());
    }
}
