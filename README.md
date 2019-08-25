# LoLStatTracker

Make sure to add `ApiKey.json` in `/src/API` in the format:<br>
```
{
    "api_key": "your-key-here"
}
```
or you will get errors for invalid request from Riot's API<br>

Highly encouraged to download Postman API Development Kit and Intellij to work on this project

Also need to add the following VM Options to Intellij:<br>
```
--module-path "*path*/LoLJavaStatProject/lib/javafx-sdk-11.0.2/lib" 
--add-modules javafx.controls,javafx.fxml 
--add-opens javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED
--add-opens javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED
```

Add the maven dependencies listed below to the libraries section of the project structure (`Ctrl + Shift + Alt + S`):<br>
```
com.fasterxml.jackson.core:jackson-core:2.9.9
com.fasterxml.jackson.core:jackson-databind:2.0.4
com.jfoenix:jfoenix:9.0.9
de.jensd:fontawesome-commons:9.1.2
de.jensd:fontawesome-fontawesome:4.7.0-9.1.2
com.apache.commons:commons-collections4:4.0
org.projectlombok:lombok:1.18.6
dev.morphia.morphia:core:1.5.3
```

For Lombok to work, enable `Annotation Preprocessors` in your Build preferences. Also, install the Lombok plugin (otherwise IntelliJ will complain about some code).

If there is anything you want to add, have any great ideas for development, or don't know what to do,
look at the "projects" tab for goals, issues, and to track progress