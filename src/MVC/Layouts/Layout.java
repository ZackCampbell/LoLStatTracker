package MVC.Layouts;

import MVC.Widgets.DummyWidget;
import MVC.Widgets.Widget;
import MVC.Widgets.WidgetException;
import Utils.Utils;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Layout implements Serializable {

    private int numWidgets = 0;
    private String layoutName;
    private LinkedList<Widget> widgets = new LinkedList<>();
    private ArrayList<Widget> dummyWidgets = new ArrayList<>();
    private int numCols;
    private int numRows;

    private Layout(String name, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.layoutName = name;
    }

    public void addWidget(Widget widget) {
        if (!widgets.contains(widget)) {
            numWidgets++;
            widgets.add(widget);
        }
    }

    public void removeWidget(Widget widget) {
        if (widgets.contains(widget)) {
            numWidgets--;
            widgets.remove(widget);
        } else {
            System.out.println("Unable to remove widget: " + widget.getName());
        }
    }

    public static Layout createLayout(String name, ArrayList<Widget> selectedWidgets, int numRows, int numCols) {
        Layout layout = new Layout(name, numRows, numCols);

        for (Widget widget : selectedWidgets) {
            layout.addWidget(widget);
        }

        return layout;
    }

    public Widget getWidget(String widgetName) throws WidgetException {
        for (Widget widget : widgets) {
            if (widget.getName().equals(widgetName))
                return widget;
        }
        throw new WidgetException("Widget with name: " + widgetName + " not found in current layout");
    }

    public static void deleteLayout(String fileName) {
        if (fileName.equals("Default1") || fileName.equals("Default2") || fileName.equals("Default3")) {
            System.out.println("Cannot delete a default layout");
            return;
        }
        File file = new File("src/MVC/Layouts/" + fileName + ".txt");
        if (file.delete())  {
            try {
                // Update the xml config file
                Document doc = getXMLDocument();
                NodeList nodeList = doc.getElementsByTagName("file");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    org.w3c.dom.Node currentNode = nodeList.item(i);
                    if (currentNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element fileElement = (Element)currentNode;
                        if (fileElement.getElementsByTagName("name").item(0).getTextContent().equals(fileName)) {
                            doc.removeChild(fileElement);
                            break;
                        }
                    }
                }
                toXML(doc);
            } catch (Exception e) {
                System.out.println("Layout Config file corrupted after successful file deletion");
                return;
            }

            System.out.println("Layout " + fileName + " deleted successfully");
        } else {
            System.out.println("Failed to delete layout: " + fileName);
        }
    }

    private static Document getXMLDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse("src/MVC/Layouts/layoutconfig.xml");
    }

    private static void toXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/MVC/Layouts/layoutconfig.xml"));
        transformer.transform(source, result);
    }

    public void saveLayout(String fileName) {
        File file = new File("src/MVC/Layouts/" + fileName + ".txt");

        if (file.exists()) {        // Overwrite it
            deleteLayout(fileName);
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
                Document doc = getXMLDocument();
                addFileToDocument(fileName, doc);

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
                toXML(doc);
            } catch (Exception e) {
                System.out.println("Failed to update the xml file with layout data");
            }
            System.out.println("Saved layout \"" + fileName + "\" successfully");
        }
    }

    private void addFileToDocument(String fileName, Document doc) {
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

    // @params Input a new gridpane and the width and height of the parent
    public GridPane loadOntoGridpane(GridPane gridPane, double width, double height, boolean editEnabled) {
        ArrayList<Widget> addedWidgets = new ArrayList<>();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPrefSize(width, height);
        Utils.addGridConstraints(gridPane, numRows, numCols);

        for (Widget widget : widgets) {
            if (editEnabled) {
                widget.setEditEnabled(true);
            } else {
                widget.setEditEnabled(false);
            }
            gridPane.add(widget.getPane(), widget.getRowIndex(), widget.getColIndex(), widget.getRowSpan(), widget.getColSpan());
            addedWidgets.add(widget);
        }

        dummyWidgets.clear();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (getWidgetByRowColumnIndex(i, j, addedWidgets) == null) {
                    Point coords = Utils.getNextGridCoords(gridPane, addedWidgets, 1, 1);
                    if (coords != null) {
                        DummyWidget dummyWidget = new DummyWidget(coords.x, coords.y);
                        if (editEnabled) {
                            dummyWidget.setEditEnabled(true);
                        } else {
                            dummyWidget.setEditEnabled(false);
                        }
                        addedWidgets.add(dummyWidget);
                        dummyWidgets.add(dummyWidget);
                        gridPane.add(dummyWidget.getPane(), coords.x, coords.y, dummyWidget.getRowSpan(), dummyWidget.getColSpan());
                    }
                }

            }
        }
        addedWidgets.clear();
        return gridPane;
    }

    private Widget getWidgetByRowColumnIndex (int row, int column, ArrayList<Widget> widgetList) {
        Widget result = null;
        if (widgetList.isEmpty())
            return null;

        for (Widget widget : widgetList) {
            if(widget.getRowIndex() == row && widget.getColIndex() == column) {
                result = widget;
                break;
            }
        }

        return result;
    }

    public static Layout getSavedLayout() {
        String fileName = null;
        Layout layout = null;
        try {
            Document doc = getXMLDocument();

            // Find the most recent layout to get from the xml
            NodeList nodeList = doc.getElementsByTagName("file");
            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Node currentNode = nodeList.item(i);
                if (currentNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element fileElement = (Element)currentNode;

                    if (fileElement.getElementsByTagName("mostrecent").item(0).getTextContent().equals("true")) {
                        fileName = fileElement.getElementsByTagName("name").item(0).getTextContent();
                    }
                }
            }
            if (fileName == null) {                 // Couldn't find the most recent layout
                System.out.println("Could not find most recent file.");
                return new Layout("UnknownLayout", 11, 8);
            }

            // Load the most recent layout into an object
            FileInputStream fileInputStream = new FileInputStream(new File(fileName + ".txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            layout = (Layout)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {}
        return layout;
    }

    public ArrayList<Widget> getDummyWidgets() {
        return this.dummyWidgets;
    }

    public String getLayoutName() {
        return this.layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }
}
