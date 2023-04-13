package domain;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import db.DatabaseFacade;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvReportManager extends AbstractReportManager{

    private static final String outputFileName = "filteredEngines-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".csv";
    @Override
    public void exportData(List<Object> filteredEngines, IEcuFilter filter) {
        if(!filteredEngines.isEmpty()){
            try (CSVWriter writer = new CSVWriter(new FileWriter(outputFileName))) {
                // Write the header
                writer.writeNext(new String[]{"Processor", "Speed", "Hybrid", "Brand"});

                // Write the data
                for (var engine : filteredEngines) {
                    if(engine instanceof Ecu){
                        var processor = ((Ecu) engine).getEcuProcessor();
                        writer.writeNext(new String[]{processor.getClass().getSimpleName(), String.valueOf(processor.getSpeed()), String.valueOf(processor.isHybrid()), ((Ecu) engine).getBrand().toString()});
                    }
                }
            }
            catch (Exception e){
                //If Fails
            }
        }
    }
}
