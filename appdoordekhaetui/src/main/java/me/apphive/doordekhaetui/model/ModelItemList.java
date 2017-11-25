package me.apphive.doordekhaetui.model;

//import java.util.ArrayList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rz Rasel on 2017-08-31.
 */

public class ModelItemList implements Serializable {
    //public static ArrayList<String, ModelDynamic> initDomainInfoList = new ArrayList<>();
    public static HashMap<String, ModelDynamic> hashMapHostURLIndexing = new HashMap<>();
    public static HashMap<String, ModelDynamic> hashMapChannelIndexing = new HashMap<>();
    /*public static ArrayList<ModelItemList> ipTvChannelList = new ArrayList<ModelItemList>();
    public static ArrayList<ModelItemList> tubeTvChannelList = new ArrayList<ModelItemList>();
    public static ArrayList<ModelItemList> tubeTvMovieList = new ArrayList<ModelItemList>();*/
    public static ArrayList<ModelDynamic> ipTvChannelList = new ArrayList<ModelDynamic>();
    public static ArrayList<ModelDynamic> tubeTvChannelList = new ArrayList<ModelDynamic>();
    public static ArrayList<ModelDynamic> tubeTvMovieList = new ArrayList<ModelDynamic>();
}