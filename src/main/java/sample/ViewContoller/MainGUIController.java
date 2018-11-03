package sample.ViewContoller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.DataModel.testData;
import sample.Main;
import sample.StageController;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainGUIController implements ControlleredStage, Initializable {
    StageController myController;

    public static String opClass = "query";// update,query and other
    public static String checkDBName = "teaches";
    public static String S_SQL = "select * from " + checkDBName;
    public static String fix_SQL = "select * from " + checkDBName;

    /**
     * 加载数据库
     */
    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static ResultSetMetaData MetDataSet = null;


    static String url = "jdbc:mysql://localhost:3306/smallrelations?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    static String user = "root";
    static String pwd = "root";
    /*
     * 加载数据库*/

    @FXML
    private TableView<testData> testTable;
    @FXML
    private TableColumn<testData,String> Column1;
    @FXML
    private TableColumn<testData,String> Column2;
    @FXML
    private  TableColumn<testData,String> Column3;
    @FXML
    private  TableColumn<testData,String> Column4;
    @FXML
    private  TableColumn<testData,String> Column5;
    @FXML
    private  TableColumn<testData,String> Column6;
    @FXML
    private  TableColumn<testData,String> Column7;

    @FXML
    private Label TableNameText;

    @FXML
    private ComboBox<String> select;

    private ObservableList<testData> tests = FXCollections.observableArrayList();

    private ObservableList<String> options = FXCollections.observableArrayList("advisor","classroom","course",
            "department","instructor","prereq","section","student","takes","teaches","time_slot");

    private ArrayList<TableColumn<testData,String>> cols = new ArrayList<TableColumn<testData, String>>();

    /**
     *在fxml文件完成载入时自动被调用
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //给表格的每一列绑定数据源
        Column1.setCellValueFactory(cellData -> cellData.getValue().StrProperty1());
        Column2.setCellValueFactory(cellData -> cellData.getValue().StrProperty2());
        Column3.setCellValueFactory(cellData -> cellData.getValue().StrProperty3());
        Column4.setCellValueFactory(cellData -> cellData.getValue().StrProperty4());
        Column5.setCellValueFactory(cellData -> cellData.getValue().StrProperty5());
        Column6.setCellValueFactory(cellData -> cellData.getValue().StrProperty6());
        Column7.setCellValueFactory(cellData -> cellData.getValue().StrProperty7());

        //将每一列放入一个列表中便于管理
        cols.add(Column1);
        cols.add(Column2);
        cols.add(Column3);
        cols.add(Column4);
        cols.add(Column5);
        cols.add(Column6);
        cols.add(Column7);

        //绑定数据到TableView
        testTable.setItems(tests);
        //  设置combobox的item
        select.setItems(options);
        select.setValue("teaches");
        TableNameText.setText("Now Table Name is: " + select.getValue());

        select.valueProperty().addListener(new ChangeListener<String>() {//给数据库表下拉选择栏添加监听器
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("old Value is :" + oldValue);
                System.out.println("new Value is :" + newValue);
                checkDBName = newValue;
                S_SQL = "select * from " + checkDBName;
//                System.out.println("now S_SQL is:" + S_SQL);
                opClass = "query";
                readFromDBS();//重新从数据库读取数据
                refreshTable();//刷新TableView
                TableNameText.setText("Now Table Name is:   " + newValue);
            }
        });
        readFromDBS();
        refreshTable();
    }

    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    public void gotoAdd(){
        this.myController.setStage(Main.ManagerViewID);
    }

    public void gotoLogin(){
        this.myController.setStage(Main.loginViewID,Main.MainViewID);
    }

    public void checkNowtable(){
        S_SQL = "select * from " + checkDBName;
        opClass = "query";
        refreshTable();
    }

    //展现消息提示框
    public void hint(String _str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(_str);
        alert.showAndWait();
    }

    public void refreshTable(){
        tests.remove(0,tests.size());
        System.out.println("Now tests' length is: " + tests.size());
        readFromDBS();
        System.out.println("The tests now are:");
        testTable.setItems(tests);
    }

    public  void readFromDBS(){
        /*开始加载数据库*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动加载异常");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url,user,pwd);//打开一个数据库链接
            stmt = conn.createStatement();
            PreparedStatement pStmt;
            if ("query".equals(opClass)){
                pStmt = conn.prepareStatement(S_SQL);
                rs = pStmt.executeQuery();
                MetDataSet = rs.getMetaData();
                String tableName = MetDataSet.getTableName(1);
                checkDBName = tableName;
                select.setValue(tableName);
                //System.out.println("Table Name is " + tableName);
                for (int i = 0; i < MetDataSet.getColumnCount(); i++) {
                    System.out.println("第 " + i + "列是" + MetDataSet.getColumnName(i+1));
                }
            }

            if("update".equals(opClass)){

                pStmt = conn.prepareStatement(S_SQL);
                System.out.println("now update SQL is: "+ S_SQL);
                fix_SQL = "select * from " + checkDBName;
                System.out.println("now update fix_SQL is: "+ fix_SQL);
                pStmt.executeUpdate();
                rs = stmt.executeQuery(fix_SQL);
                MetDataSet = rs.getMetaData();
            }

            while (rs.next()){    //动态改变table的Column的name及cellData
                switch (MetDataSet.getColumnCount()){
                    case 1:tests.add(new testData(rs.getString(1)));
                            reSetAllCols();
                            break;

                    case 2:tests.add(new testData(rs.getString(1),rs.getString(2)));
                           reSetAllCols();
                            break;

                    case 3:tests.add(new testData(rs.getString(1),rs.getString(2),
                            rs.getString(3)));
                            reSetAllCols();
                            break;
                    case 4:tests.add(new testData(rs.getString(1),rs.getString(2),
                            rs.getString(3),rs.getString(4)));
                            reSetAllCols();
                            break;
                    case 5:tests.add(new testData(rs.getString(1),rs.getString(2)
                            ,rs.getString(3),rs.getString(4),rs.getString(5)));
                            reSetAllCols();
                            break;

                    case 6:tests.add(new testData(rs.getString(1),rs.getString(2)
                            ,rs.getString(3),rs.getString(4),rs.getString(5),
                            rs.getString(6)));
                            reSetAllCols();
                            break;
                    case 7:tests.add(new testData(rs.getString(1),rs.getString(2)
                            ,rs.getString(3),rs.getString(4),rs.getString(5),
                            rs.getString(6),rs.getString(7)));
                            reSetAllCols();
                            break;
                    default:System.out.println("The Column num is overflow!!");
                            break;
                }
            }
//            stmt.close();
//            rs.close();
//            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            checkNowtable();
            hint("Sql execute fail,please ensure the sql is correct and that no constraints are violated!");
        }
    }

    //动态改变table的Column的name
    public void reSetAllCols() throws SQLException {
        for (int i = 0; i < cols.size() ; i++) {
            cols.get(i).setText(" ");
        }
        for (int i = 0; i < MetDataSet.getColumnCount() ; i++) {
            cols.get(i).setText(MetDataSet.getColumnName(i+1));
        }
    }
}
