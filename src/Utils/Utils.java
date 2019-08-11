package Utils;

import Logging.LoggingHandler;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

public class Utils {

    private static HashMap<String, EnumSet<REGION>> regionCodes;

    private enum REGION {
        BR1, EUN1, EUW1, JP1, KR, LA1, LA2, NA1, NA, OC1, TR1, RU, PBE1
    }

    public static Logger initializeLogger(String className) {
        LoggingHandler logHandler = new LoggingHandler();
        logHandler.addHandler(className);
        Runtime.getRuntime().addShutdownHook(new Thread(logHandler::cleanup));
        return logHandler.getLogger();
    }

    public static String getRelativePath() {
        String path = new File("").getAbsolutePath();
        return path.replace("\\", "/");
    }

    public static void initRegionCodes() {
        regionCodes = new HashMap<>();
        regionCodes.put("BR", EnumSet.of(REGION.BR1));
        regionCodes.put("EUNE", EnumSet.of(REGION.EUN1));
        regionCodes.put("EUW", EnumSet.of(REGION.EUW1));
        regionCodes.put("JP", EnumSet.of(REGION.JP1));
        regionCodes.put("KR", EnumSet.of(REGION.KR));
        regionCodes.put("LAN", EnumSet.of(REGION.LA1));
        regionCodes.put("LAS", EnumSet.of(REGION.LA2));
        regionCodes.put("NA", EnumSet.of(REGION.NA, REGION.NA1));
        regionCodes.put("OCE", EnumSet.of(REGION.OC1));
        regionCodes.put("TR", EnumSet.of(REGION.TR1));
        regionCodes.put("RU", EnumSet.of(REGION.RU));
        regionCodes.put("PBE", EnumSet.of(REGION.PBE1));
    }

    public static EnumSet<REGION> getRegionCode(String key) {
        if (regionCodes.isEmpty() || regionCodes.get(key) == null)
            return null;
        return regionCodes.get(key);
    }

    public static Set<String> getRegionCodes() {
        return regionCodes.keySet();
    }

    public static void addGridConstraints(GridPane gridPane, int numCols, int numRows) {
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstr = new ColumnConstraints();
            colConstr.setPercentWidth(100.0 / numCols);
            gridPane.getColumnConstraints().add(colConstr);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstr = new RowConstraints();
            rowConstr.setPercentHeight(100.0 / numRows);
            gridPane.getRowConstraints().add(rowConstr);
        }
    }

}
