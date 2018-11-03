package sample.DataModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class testData {
    private StringProperty strProperty1,strProperty2,strProperty3,strProperty4,strProperty5,strProperty6,strProperty7;

    public testData(String _str1){
        this.setStrProperty1(_str1);
    }

    public testData(String _str1, String _str2){
        this.setStrProperty1(_str1);
        this.setStrProperty2(_str2);
    }

    public testData(String _str1, String _str2, String _str3){
        this.setStrProperty1(_str1);
        this.setStrProperty2(_str2);
        this.setStrProperty3(_str3);
    }


    public testData(String _str1, String _str2, String _str3,String _str4){
        this.setStrProperty1(_str1);
        this.setStrProperty2(_str2);
        this.setStrProperty3(_str3);
        this.setStrProperty4(_str4);
    }

    public testData(String _str1, String _str2, String _str3,String _str4,String _str5){
        this.setStrProperty1(_str1);
        this.setStrProperty2(_str2);
        this.setStrProperty3(_str3);
        this.setStrProperty4(_str4);
        this.setStrProperty5(_str5);
    }

    public testData(String _str1, String _str2, String _str3,String _str4,String _str5,String _str6){
        this.setStrProperty1(_str1);
        this.setStrProperty2(_str2);
        this.setStrProperty3(_str3);
        this.setStrProperty4(_str4);
        this.setStrProperty5(_str5);
        this.setStrProperty6(_str6);
    }

    public testData(String _str1, String _str2, String _str3,String _str4,String _str5,String _str6,String _str7){
        this.setStrProperty1(_str1);
        this.setStrProperty2(_str2);
        this.setStrProperty3(_str3);
        this.setStrProperty4(_str4);
        this.setStrProperty5(_str5);
        this.setStrProperty6(_str6);
        this.setStrProperty7(_str7);
    }

    public StringProperty StrProperty1(){
        if(strProperty1 == null) {strProperty1 = new SimpleStringProperty(this,"strProperty1");}
        return strProperty1;
    }

    public StringProperty StrProperty2(){
        if(strProperty2 == null) {strProperty2 = new SimpleStringProperty(this,"strProperty2");}
        return strProperty2;
    }

    public StringProperty StrProperty3(){
        if(strProperty3 == null) {strProperty3 = new SimpleStringProperty(this,"strProperty3");}
        return strProperty3;
    }

    public StringProperty StrProperty4(){
        if(strProperty4 == null) {strProperty4 = new SimpleStringProperty(this,"strProperty4");}
        return strProperty4;
    }

    public StringProperty StrProperty5(){
        if(strProperty5 == null) {strProperty5= new SimpleStringProperty(this,"strProperty5");}
        return strProperty5;
    }

    public StringProperty StrProperty6(){
        if(strProperty6 == null) {strProperty6 = new SimpleStringProperty(this,"strProperty6");}
        return strProperty6;
    }

    public StringProperty StrProperty7(){
        if(strProperty7 == null) {strProperty7 = new SimpleStringProperty(this,"strProperty7");}
        return strProperty7;
    }

    public void setStrProperty1(String _str) {
         StrProperty1().set(_str);
    }

    public void setStrProperty2(String _str) {
        StrProperty2().set(_str);
    }
    public void setStrProperty3(String _str) {
        StrProperty3().set(_str);
    }
    public void setStrProperty4(String _str) {
        StrProperty4().set(_str);
    }
    public void setStrProperty5(String _str) {
        StrProperty5().set(_str);
    }
    public void setStrProperty6(String _str) {
        StrProperty6().set(_str);
    }
    public void setStrProperty7(String _str) {
        StrProperty7().set(_str);
    }

}
