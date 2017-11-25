package me.apphive.doordekhaetui.model;

import java.util.HashMap;

/**
 * Created by Rz Rasel on 2017-08-31.
 */

public class ModelDynamic {
    //public static HashMap<String, ?> staticDataList = new HashMap<>();
    private HashMap<String, ?> dynamicModelList = new HashMap<>();

    public ModelDynamic() {
    }

    public ModelDynamic(HashMap<String, ?> argDynamicModelList) {
        this.dynamicModelList = argDynamicModelList;
    }

    public HashMap<String, ?> getDynamicModelList() {
        return this.dynamicModelList;
    }

    public static HashMap<String, Object> setItemData(String argKey, String argValue) {
        HashMap<String, Object> itemList = new HashMap<>();
        itemList.put(argKey, getStringToObject(argValue));
        return itemList;
    }

    public static Object getStringToObject(String argValue) {
        return (Object) argValue;
    }
}
/*
https://stackoverflow.com/questions/18474379/generics-cant-instantiate-new-hashmapstring-why
*/