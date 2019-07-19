# LoLStatTracker

Make sure to add ApiKey.json in /src/API in the format:<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"api_key": "your-key-here"<br>
}<br>
or you will get errors for invalid request from Riot's API<br>

Highly encouraged to download Postman API Development Kit and Intellij to work on this project

Also need to add the following VM Options to Intellij:<br>
--module-path "*path*/LoLJavaStatProject/lib/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml

Add the maven dependencies listed below to the libraries section of the project structure (Ctrl + Shift + Alt + S):<br>
jackson-core-2.9.9<br>
com.apache.commons:commons-collections4:4.0<br>
de.jensd:fontawesomefx:8.9<br>

If there is anything you want to add, have any great ideas for development, or don't know what to do,
look at the "projects" tab for goals, issues, and to track progress