package Utils;

import GameElements.Match;
import Logging.LoggingHandler;
import MVC.Widgets.Widget;
import MVC.Widgets.WidgetException;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;

public class Utils {

    private static HashMap<String, EnumSet<REGION>> regionCodes;

    private enum REGION {
        // TODO: I think we need to be able to convert these to string values
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

    /// Example: 9.16.284.8446
    /// TODO: Consider refactoring patch string to be a simple Patch class w/ validation
    public static boolean doPatchesMatch(String patch1, String patch2, Match.PatchMatchMode mode) {
        if (mode == Match.PatchMatchMode.NONE) {
            /// Early exit for trivial case of we don't care about matching
            return true;
        }

        String[] partsToMatch = patch1.split("\\.");
        String[] partsOther = patch2.split("\\.");

        if (partsToMatch.length >= 1 && partsOther.length >= 1) {
            if (mode == Match.PatchMatchMode.MAJOR_VERSION) {
                return partsToMatch[0].equals(partsOther[0]);
            }
        }

        if (partsToMatch.length >= 2 && partsOther.length >= 2) {
            if (mode == Match.PatchMatchMode.MINOR_VERSION) {
                return partsToMatch[0].equals(partsOther[0]) &&
                        partsToMatch[1].equals(partsOther[1]);
            }
        }

        return false;
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

    public static Point getNextGridCoords(GridPane gridPane, ArrayList<Widget> widgetList, int rowSpan, int colSpan) {
        ArrayList<Pair<Point, Point>> lines = new ArrayList<>();
        for (Widget widget : widgetList) {

            /* Key for adding the lines below
             * 0-------------0
             * |      2      |
             * | 1         3 |
             * |      4      |
             * 0-------------0
             */

            lines.add(new Pair<>(new Point(widget.getRowIndex(), widget.getColIndex()),
                    new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex())));     // 1
            lines.add(new Pair<>(new Point(widget.getRowIndex(), widget.getColIndex()),
                    new Point(widget.getRowIndex(), widget.getColIndex() + widget.getColSpan() - 1)));     // 2
            lines.add(new Pair<>(new Point(widget.getRowIndex(), widget.getColIndex() + widget.getColSpan() - 1),
                    new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex() + widget.getColSpan() - 1)));   // 3
            lines.add(new Pair<>(new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex()),
                    new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex() + widget.getColSpan() - 1)));   // 4

        }
        for (int i = 0; i < gridPane.getColumnCount(); i++) {
            for (int j = 0; j < gridPane.getRowCount(); j++) {
                Point homeIndex = new Point(i, j);
                Pair<Point, Point> line1 = new Pair<>(homeIndex, new Point(i + rowSpan - 1, j));
                Pair<Point, Point> line2 = new Pair<>(homeIndex, new Point(i, j + colSpan - 1));
                Pair<Point, Point> line3 = new Pair<>(new Point(i, j + colSpan - 1), new Point(i + rowSpan - 1, j + colSpan - 1));
                Pair<Point, Point> line4 = new Pair<>(new Point(i + rowSpan - 1, j), new Point(i + rowSpan - 1, j + colSpan - 1));
                boolean intersecting = false;
                for (Pair<Point, Point> line : lines) {
                    if (doIntersect(line.getKey(), line.getValue(), line1.getKey(), line1.getValue()) ||
                            doIntersect(line.getKey(), line.getValue(), line2.getKey(), line2.getValue()) ||
                            doIntersect(line.getKey(), line.getValue(), line3.getKey(), line3.getValue()) ||
                            doIntersect(line.getKey(), line.getValue(), line4.getKey(), line4.getValue())) {
                        intersecting = true;
                        break;
                    }
                }
                if (!intersecting) {
                    return homeIndex;
                }
            }
        }
        System.out.println("No space available for new widget with row span: " + rowSpan + " and colspan: " + colSpan);
        return null;
    }

    // Given three colinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    private static boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    // To find orientation of ordered triplet (p, q, r)
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // colinear

        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    private static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }


}
