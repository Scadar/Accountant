package ru.scadarnull.model;

import ru.scadarnull.saveload.SaveData;

abstract public class Common {

    public String getValueForComboBox(){
        return null;
    }

    public void postAdd(SaveData sd){}

    public void postEdit(SaveData sd){}

    public void postDelete(SaveData sd){}
}
