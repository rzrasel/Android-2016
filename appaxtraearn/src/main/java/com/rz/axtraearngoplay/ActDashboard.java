package com.rz.axtraearngoplay;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rz.varnishview.spinallistdrawer.ModelDrawerList;
import com.rz.varnishview.spinallistdrawer.SpinalRowDrawerDraw;
import com.rz.varnishview.spinallistdrawer.adapter.SharkArrayAdapter;
import com.rz.varnishview.spinallistdrawer.adapter.SharkModelRowScope;
import com.rz.varnishview.spinallistdrawer.adapter.SharkModelRowViewFields;

import java.util.ArrayList;
import java.util.HashMap;

public class ActDashboard extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private SpinalRowDrawerDraw spinalRowDrawerDraw;
    private Toolbar sysToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dashboard);
        activity = this;
        context = this;
        sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
        //|------------------------------------------------------------|
        new OnDrawerSetup(activity, context)
                .onSetToolbar(sysToolBar)
                .onSetDrawerMenuFields()
                .onSetDrawerMenuItems()
                .onSetDrawerAdapter(true, Gravity.LEFT);
        //|------------------------------------------------------------|
        //initWindowDecorActionBar();
        //|------------------------------------------------------------|
    }

    private void initWindowDecorActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        Window window = getWindow();

        // Initializing the window decor can change window feature flags.
        // Make sure that we have the correct set before performing the test below.
        window.getDecorView();

        if (isChild() || !window.hasFeature(Window.FEATURE_ACTION_BAR) || actionBar != null) {
            //actionBar.hide();
            return;
        }
        //actionBar = new WindowDecorActionBar(activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem argMenuItem) {
        switch (argMenuItem.getItemId()) {
            case android.R.id.home:
                spinalRowDrawerDraw.onOptionsItemSelected(argMenuItem);
                return true;
            default:
                return super.onOptionsItemSelected(argMenuItem);
        }
        //System.out.println("----------------------");
        //return super.onOptionsItemSelected(argMenuItem);
    }

    //|------------------------------------------------------------|
    @Override
    public void onConfigurationChanged(Configuration argNewConfig) {
        super.onConfigurationChanged(argNewConfig);
        spinalRowDrawerDraw.onConfigurationChanged(argNewConfig);
    }
    //|------------------------------------------------------------|

    @Override
    public void onBackPressed() {
        if (!spinalRowDrawerDraw.onSetBackPressed()) {
            super.onBackPressed();
        }
    }

    //|------------------------------------------------------------|
    public class OnDrawerSetup {
        private Activity activity;
        private Context context;
        //private SpinalRowDrawerDraw spinalRowDrawerDraw;
        private ArrayList<SharkModelRowScope> modelDrawerListItems = new ArrayList<SharkModelRowScope>();

        public OnDrawerSetup(Activity argActivity, Context argContext) {
            this.activity = argActivity;
            this.context = argContext;
            spinalRowDrawerDraw = new SpinalRowDrawerDraw(activity, context);
        }

        public OnDrawerSetup onSetToolbar(Toolbar argToolbar) {
            spinalRowDrawerDraw.spinalToolBar.onHideActionBar()
                    .initToolBar(argToolbar)
                    .onSetActionBar()
                    .onSetTitleText("Dashboard")
                    .onSetTitleTextColor("#E0C0F9")
                    //.onSetSubTitleText("Sub Title Spinal Row Drawer")
                    //.onSetSubTitleTextColor("#ffffff")
                    .onSetTitleFont("fonts/encode_sans_extrabold.ttf")
                    //.onSetSubTitleFont("fonts/alex-brush-regular.ttf")
                    .onShowHomeButton()
                    .onSetStatusBarDark(true);
                    /*sysToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View argView) {
                            System.out.println("DEBUG_ID: ");
                        }
                    });*/
            return this;
        }

        public OnDrawerSetup onSetDrawerMenuFields() {
            //ArrayList<SharkModelRowViewFields> rowViewFieldListItems = new ArrayList<SharkModelRowViewFields>();
            spinalRowDrawerDraw.spinalDrawerMenu.onSetRowViewField(SpinalRowDrawerDraw.FIELD_TYPE.IMAGE_VIEW, "sysImgViewDrawerIcon");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetRowViewField(SpinalRowDrawerDraw.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle");
            return this;
        }

        public OnDrawerSetup onSetDrawerMenuItems() {
            HashMap<String, String> eachRowDataItems = null;
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_dashboard + "");
            eachRowDataItems.put("sysDrawerTitle", "Dashboard");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_profile_male + "");
            eachRowDataItems.put("sysDrawerTitle", "Profile");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_settings + "");
            //https://www.flaticon.com/free-icon/settings_295968#term=settings&page=1&position=65
            //https://www.flaticon.com/search?word=profile
            eachRowDataItems.put("sysDrawerTitle", "Settings");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragUserRegistration.class);
            modelDrawerListItems = spinalRowDrawerDraw.spinalDrawerMenu.onGetDataList();
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_report + "");
            eachRowDataItems.put("sysDrawerTitle", "Report");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_rules + "");
            eachRowDataItems.put("sysDrawerTitle", "Rules");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);
            //|------------------
            return this;
        }

        public OnDrawerSetup onSetDrawerAdapter(boolean argIsPushActivity, int argGravity) {
            SharkArrayAdapter adapterDrawerListAdapter;
            ListView sysDrawerList = (ListView) findViewById(R.id.sysDrawerList);
            adapterDrawerListAdapter = new SharkArrayAdapter(context, R.layout.lay_spinal_drawer_row, modelDrawerListItems);
            spinalRowDrawerDraw.spinalDrawerMenu.onSetAdapterListener(adapterDrawerListAdapter)
                    .onSetDrawerLayout((DrawerLayout) findViewById(R.id.sysDrawerLayout), (RelativeLayout) findViewById(R.id.sysIdDrawerContainer), R.id.sysFrameContainer)
                    .onSetDefaultDrawerLayout(0);
            sysDrawerList.setAdapter(adapterDrawerListAdapter);
            spinalRowDrawerDraw.spinalDrawerMenu.onSetDrawerItemClickListener(sysDrawerList);
            if (argIsPushActivity) {
                spinalRowDrawerDraw.onSetFrameLayoutParent((LinearLayout) findViewById(R.id.idLinLayMainContainerView))
                        .onSetGravity(argGravity)
                        .onConfigureDrawer();
            } else {
                spinalRowDrawerDraw.onSetGravity(argGravity)
                        .onConfigureDrawer();
            }
            return this;
        }
    }
}