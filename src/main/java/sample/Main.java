package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static String loginViewID = "loginController";
    public static String loginViewRes = "/Fxml/loginController.fxml";
    public static String MainViewID = "MainGUIController";
    public static String MainViewRes = "/Fxml/MainGUIController.fxml";

    public static String ManagerViewID = "ManagerPane";
    public static String ManagerViewRes = "/Fxml/ManagerPane.fxml";

    private StageController stageController;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //新建一个StageController控制器
        stageController = new StageController();

        //将主舞台交给控制器处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        //加载6个舞台，每个界面一个舞台
        stageController.loadStage(loginViewID,loginViewRes);
        stageController.loadStage(MainViewID,MainViewRes);
        stageController.loadStage(ManagerViewID,ManagerViewRes);

        //显示Login舞台
        stageController.setStage(loginViewID);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
