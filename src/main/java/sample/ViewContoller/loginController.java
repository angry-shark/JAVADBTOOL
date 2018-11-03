package sample.ViewContoller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.Main;
import sample.StageController;

import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements ControlleredStage, Initializable {
    StageController myController;
    private final String user = "root";
    private final String pwd = "root";

    @FXML
    private TextField username;
    @FXML
    private TextField pwd_str;
    @FXML
    private ComboBox<String> DBSelector;

    private ObservableList<String> DBNameOptions = FXCollections.observableArrayList("smallrelations","largerelations");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBSelector.setItems(DBNameOptions);
        DBSelector.setValue(DBNameOptions.get(0));
        DBSelector.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                MainGUIController.url = "jdbc:mysql://localhost:3306/" + newValue +"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
            }
        });
    }

    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    //展现消息提示框
    public void hint(String _str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText(null);
        alert.setContentText(_str);
        alert.showAndWait();
    }

    public void goToMain(){
        if(user.equals(username.getText()) && pwd.equals(pwd_str.getText())){
            myController.setStage(Main.MainViewID, Main.loginViewID);
        }
        else{
            hint("账号或密码输入错误，请仔细阅读readme.md文件");
            username.setText("");
            pwd_str.setText("");
        }
    }

    public void exit(){
        this.myController.closeStage(Main.loginViewID);
    }
}
