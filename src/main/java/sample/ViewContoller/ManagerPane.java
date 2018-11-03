package sample.ViewContoller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import sample.Main;
import sample.StageController;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerPane implements ControlleredStage, Initializable {
    private StageController myController;

    private String SQL = "Select * from teaches";
    private String SQL_a;

    @FXML
    private TextArea sqlInputArea;

    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    public void close(){
        this.myController.closeStage(Main.ManagerViewID);
    }

    public void hint(String _str){
        Alert _alert = new Alert(Alert.AlertType.INFORMATION);
        _alert.setTitle("Tips");
        _alert.setHeaderText(null);
       _alert.setContentText(_str);
        _alert.showAndWait();
    }


    //pass the SQL to MainGUIController
    public void passSQL(){
        SQL = sqlInputArea.getText();
        SQL_a = SQL.toLowerCase();
        System.out.println(SQL_a);
        if(SQL_a.length() < 6){
            SQL_a += "      ";
        }
        /*过滤Sql语句，确定SQL语句的类型*/
        if("select".equals(SQL_a.substring(0,6))){
           MainGUIController.opClass = "query";
           MainGUIController.S_SQL = SQL;
        }
        if("insert".equals(SQL_a.substring(0,6))){
            MainGUIController.opClass = "update";
            MainGUIController.S_SQL = SQL;
        }
        if("update".equals(SQL_a.substring(0,6))){
            MainGUIController.opClass = "update";
            MainGUIController.S_SQL = SQL;
        }
        if("delete".equals(SQL_a.substring(0,6))){
            MainGUIController.opClass = "update";
            MainGUIController.S_SQL = SQL;
        }
        hint("when return TableView,please click refresh table button to see the result");
        this.myController.closeStage(Main.ManagerViewID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sqlInputArea.setText(SQL);
    }
}
