package MVC.Layouts;

import MVC.Widgets.DummyWidget;
import MVC.Widgets.Widget;
import MVC.Widgets.WidgetException;
import Utils.Utils;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Layout {

    private int numWidgets = 0;
    private LinkedList<Widget> widgets = new LinkedList<>();
    private int numCols;
    private int numRows;

    public Layout(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }


    public void addWidget(Widget widget) {
        if (!widgets.contains(widget)) {
            numWidgets++;
            widgets.add(widget);
        }
    }

    public boolean removeWidget(Widget widget) {
        if (widgets.contains(widget)) {
            numWidgets--;
            widgets.remove(widget);
            return true;
        }
        return false;               // Unsuccessful
    }

    public static Layout createLayout(AnchorPane gridAnchorPane, int numRows, int numCols) {
        Layout layout = new Layout(numRows, numCols);
        // TODO: Create a new layout and add all the widgets to it correctly from the grid in the gridAnchorPane
        return layout;
    }

    public Widget getWidget(String widgetName) throws WidgetException {
        for (Widget widget : widgets) {
            if (widget.getName().equals(widgetName))
                return widget;
        }
        throw new WidgetException("Widget with name: " + widgetName + " not found in current layout");
    }

    public static boolean deleteLayout(String fileName) {
        if (fileName.equals("Default1") || fileName.equals("Default2") || fileName.equals("Default3")) {
            System.out.println("Cannot delete a default layout");
            return false;
        }
        File file = new File(fileName + ".txt");
        if (file.delete())  {
            System.out.println("Layout " + fileName + " deleted successfully");
            return true;
        } else {
            System.out.println("Failed to delete layout: " + fileName);
            return false;
        }
    }

    public void saveLayout(String fileName) {
        File file = new File(fileName + ".txt");

        boolean overwritten = false;
        if (file.exists()) {        // Overwrite it
            overwritten = deleteLayout(fileName);

        }

        boolean success = true;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Failed to create new object file for the layout");
            success = false;
        }

        // Update the xml file to mark which layout is the most recently used
        if (success) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse("src/MVC/Layouts/layoutconfig.xml");

                if (!overwritten) {
                    Element newFile = doc.createElement("file");
                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(fileName));
                    Element mostRecent = doc.createElement("mostrecent");
                    mostRecent.appendChild(doc.createTextNode("true"));
                    newFile.appendChild(name);
                    newFile.appendChild(mostRecent);
                    org.w3c.dom.Node files = doc.getElementsByTagName("files").item(0);
                    files.appendChild(newFile);
                }

                NodeList nodeList = doc.getElementsByTagName("file");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    org.w3c.dom.Node currentNode = nodeList.item(i);
                    if (currentNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element fileElement = (Element)currentNode;

                        if (fileElement.getElementsByTagName("name").item(0).getTextContent().equals(fileName)) {
                            fileElement.getElementsByTagName("mostrecent").item(0).setTextContent("true");
                        } else {
                            fileElement.getElementsByTagName("mostrecent").item(0).setTextContent("false");
                        }
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("src/MVC/Layouts/layoutconfig.xml"));
                transformer.transform(source, result);
            } catch (Exception e) {
                System.out.println("Failed to update the xml file with layout data");
            }

        }
    }

    // @params Input a new gridpane and the width and height of the parent
    public GridPane loadOntoGridpane(GridPane gridPane, double width, double height) {
        ArrayList<Widget> addedWidgets = new ArrayList<>();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPrefSize(width, height);
        Utils.addGridConstraints(gridPane, numRows, numCols);

        for (Widget widget : widgets) {
            gridPane.add(widget.getPane(), widget.getRowIndex(), widget.getColIndex(), widget.getRowSpan(), widget.getColSpan());
            addedWidgets.add(widget);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                if (getNodeByRowColumnIndex(i, j, gridPane) == null) {
                    Point coords = null;
                    try {
                        coords = Utils.getNextGridCoords(gridPane, addedWidgets, 1, 1);
                    } catch (WidgetException e) {
                        System.out.println("Cannot add a widget of that size to the grid");
                    }
                    if (coords != null && coords.x == i && coords.y == j) {
                        Pane pane = new Pane();
                        pane.getStyleClass().add("empty-grid-cell");
                        gridPane.add(pane, i, j);
                        addedWidgets.add(new DummyWidget(coords.x, coords.y));
                    }
                }

            }
        }

        return gridPane;
    }

    private Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

}
