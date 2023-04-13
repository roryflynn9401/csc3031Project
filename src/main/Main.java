package main;

import db.DatabaseFacade;
import domain.*;
import ui.EcusFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        EcuInventoryFacade.INSTANCE.addEcu(40, true);
        EcuInventoryFacade.INSTANCE.addEcu(50, false);
        EcuInventoryFacade.INSTANCE.addEcu(100, true);

        SwingUtilities.invokeLater(() -> new EcusFrame().setVisible(true));


        //var json = new JsonReportManager();
        //var csv = new CsvReportManager();
        //csv.generateReport(filter -> true);
        //json.generateReport(filter -> filter instanceof Ecu && Arrays.asList("40","50").contains(((Ecu) filter).getEcuProcessor().getSpeed()));
    }
}