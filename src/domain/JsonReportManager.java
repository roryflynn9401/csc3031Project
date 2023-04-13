package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import db.DatabaseFacade;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonReportManager extends AbstractReportManager{

    private static Gson gson = new GsonBuilder().create();
    private static final String outputFileName = "filteredEngines-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".json";

    @Override
    public void exportData(List<Object> filteredEngines , IEcuFilter filter) {
        if(!filteredEngines.isEmpty()){
            try(FileWriter writer = new FileWriter(outputFileName)){
                gson.toJson(filteredEngines, writer);
            }
            catch (IOException e){
                //If Fails
            }
        }
    }
}
