# LoLStatTracker

Make sure to add ApiKey.json in /src/API in the format:<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"api_key": "<your-key-here>"<br>
}<br>

or you will get errors for invalid request from Riot's API<br>

Highly encouraged to download Postman API Development Kit and Intellij to work on this project

Also need to add the following VM Options to Intellij:<br>
--module-path "*path*/LoLJavaStatProject/lib/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml