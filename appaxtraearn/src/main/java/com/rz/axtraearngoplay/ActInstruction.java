package com.rz.axtraearngoplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.rz.varnishview.introviewpager.AdapterViewPager;
import com.rz.varnishview.introviewpager.ModelRowViewHolder;
import com.rz.varnishview.introviewpager.OnIndicatorEventListenerHandler;
import com.rz.varnishview.introviewpager.PagerSkipNextButton;
import com.rz.varnishview.introviewpager.ViewPagerIndicator;

import java.util.ArrayList;

public class ActInstruction extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ViewPager sysViewPagerIntroduction;
    private LinearLayout layPagerIndicator;
    private AdapterViewPager adapterViewPager;
    private ArrayList<ModelPagerContent> adapterListItems = new ArrayList<ModelPagerContent>();
    private ArrayList<ModelRowViewHolder> fieldsRowViewListItems = new ArrayList<ModelRowViewHolder>();
    private AnimTransition animTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_instruction);
        activity = this;
        context = this;
        animTransition = new AnimTransition();
        adapterListItems.add(new ModelPagerContent("file:///android_asset/instruction/instruction_page_01.html"));
        adapterListItems.add(new ModelPagerContent("file:///android_asset/instruction/instruction_page_02.html"));
        adapterListItems.add(new ModelPagerContent("file:///android_asset/instruction/instruction_page_03.html"));
        adapterListItems.add(new ModelPagerContent("file:///android_asset/instruction/instruction_page_04.html"));
        fieldsRowViewListItems.add(ModelRowViewHolder.onGetSetRow(new WebView(context), "sysInstructions", ""));
        sysViewPagerIntroduction = (ViewPager) findViewById(R.id.sysViewPagerIntroduction);
        adapterViewPager = new AdapterViewPager(context, R.layout.lay_pager_item_webview, adapterListItems);
        sysViewPagerIntroduction.setAdapter(adapterViewPager);
        adapterViewPager.onSetEventListenerHandler(new OnIndicatorEventListenerHandler() {
            @Override
            public boolean onIsViewFromObject(View argView, Object argObject) {
                return argView == ((LinearLayout) argObject);
            }

            @Override
            public void onDestroyItem(ViewGroup argContainer, int argPosition, Object argObject) {
                argContainer.removeView((LinearLayout) argObject);
            }

            @Override
            public void onSetFieldsValue(ArrayList<ModelRowViewHolder> argListRowViewFields, Object argObject) {
                WebView rowField = null;
                ModelPagerContent items = (ModelPagerContent) argObject;
                if (argListRowViewFields.size() > 0) {
                    rowField = (WebView) argListRowViewFields.get(0).getFieldObject();
                    rowField.loadUrl(items.getInstructions());
                }
            }
        }, fieldsRowViewListItems);
        sysViewPagerIntroduction.setCurrentItem(0);
        ViewPagerIndicator sysViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.sysViewPagerIndicator);
        sysViewPagerIndicator.setupWithViewPager(sysViewPagerIntroduction);
        sysViewPagerIndicator.addOnPageChangeListener(onPageChangeListener);
        PagerSkipNextButton sysViewPagerSkipNextButton = (PagerSkipNextButton) findViewById(R.id.sysViewPagerSkipNextButton);
        sysViewPagerSkipNextButton.setupWithViewPager(sysViewPagerIntroduction);
        sysViewPagerSkipNextButton.addOnPageChangeListener(onPageChangeListener);
        sysViewPagerSkipNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                int btnId = argView.getId();
                //instruction_page_01.html
                //Toast.makeText(context, "Clicked: " + argView.getId(), Toast.LENGTH_LONG).show();
                Intent intentRedirectWindow = null;
                intentRedirectWindow = new Intent(context, ActAppLanding.class);
                intentRedirectWindow.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intentRedirectWindow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                if (btnId == com.rz.varnishview.introviewpager.R.id.sysBtnPagerViewSkip) {
                    startActivity(intentRedirectWindow);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    finish();
                } else if (btnId == com.rz.varnishview.introviewpager.R.id.sysBtnPagerViewGo) {
                    startActivity(intentRedirectWindow);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    finish();
                } else if (btnId == com.rz.varnishview.introviewpager.R.id.sysBtnPagerViewNext) {
                    int currentIndex = sysViewPagerIntroduction.getCurrentItem();
                    currentIndex++;
                    sysViewPagerIntroduction.setCurrentItem(currentIndex);
                }
            }
        });
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
            //Toast.makeText(context, "Page selected " + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }
    };

    public class ModelPagerContent {
        private String instructions;

        public ModelPagerContent(String argInstructions) {
            this.instructions = argInstructions;
        }

        public String getInstructions() {
            return this.instructions;
        }
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        super.onBackPressed();
    }
}