package me.apphive.doordekhaetui.adapter;

import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import me.apphive.doordekhaetui.R;
import me.apphive.doordekhaetui.model.ModelULDetails;

/**
 * Created by Rz Rasel 2017-11-19.
 */

public class AdapterULDetails extends ArrayAdapter<ModelULDetails> {
    Context context;
    private int layoutResourceId;
    ArrayList<ModelULDetails> dataModels;

    public AdapterULDetails(Context argContext, int argLayoutResourceId, ArrayList<ModelULDetails> argModelItems) {
        super(argContext, argLayoutResourceId, argModelItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.dataModels = argModelItems;
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argViewGroup) {
        View rowView = argConvertView;
        final RowViewHolder rowViewHolder;
        //mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //view = mInflater.inflate(R.layout.my_list_custom_row, parent, false);
        //myLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        //LayoutInflater li=getLayoutInflater();
        //View rootView=li.inflate(R.layout.activity_main,null);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (rowView == null) {
            //rowView = LayoutInflater.from(context).inflate(R.layout.list_item_drafts_message, viewGroup, false);
            rowView = layoutInflater.inflate(R.layout.lay_row_grid_thumb, argViewGroup, false);
            rowViewHolder = new RowViewHolder(rowView);
            rowView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (RowViewHolder) rowView.getTag();
        }
        ModelULDetails data = dataModels.get(argPosition);
        rowViewHolder.sysTvRowTitle.setText(data.getTitle());
        rowViewHolder.sysTvRowDesc.setText(data.getDescription());
        String logoUrl = data.getChannelLogo();
        //logoUrl = "https://cdn.pixabay.com/photo/2016/09/01/10/23/image-1635747_960_720.jpg";
        /*Glide.with(context).load(logoUrl)
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(rowViewHolder.sysIvLogo);*/
        Glide.with(context)
                .load(logoUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        rowViewHolder.sysProgressBar.setVisibility(View.GONE);
                        rowViewHolder.sysIvLogo.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        rowViewHolder.sysProgressBar.setVisibility(View.GONE);
                        rowViewHolder.sysIvLogo.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .thumbnail(0.5f)
                .crossFade()
                //.override(gridMeasurement.get("col_width_dp"), gridMeasurement.get("col_height_dp"))
                .override(dpToPx(120), dpToPx(120))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(animationObject)
                .into(rowViewHolder.sysIvLogo);
        ///////////////
        //////////////
        lastPosition = argPosition;
        return rowView;
    }

    private class RowViewHolder {
        TextView sysTvRowTitle, sysTvRowDesc;
        ImageView sysIvLogo;
        ProgressBar sysProgressBar;

        public RowViewHolder(View argRootView) {
            sysTvRowTitle = (TextView) argRootView.findViewById(R.id.sysTvRowTitle);
            sysTvRowDesc = (TextView) argRootView.findViewById(R.id.sysTvRowDesc);
            sysIvLogo = (ImageView) argRootView.findViewById(R.id.sysIvLogo);
            sysProgressBar = (ProgressBar) argRootView.findViewById(R.id.sysProgressBar);
        }
    }

    ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            // if it's a custom view class, cast it here
            // then find subviews and do the animations
            // here, we just use the entire view for the fade animation
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(2500);
            fadeAnim.start();
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap blurBitmap(Bitmap argSource) {
        float BLUR_RADIUS = 6f;
        //if (null == image) return null;

        Bitmap outputBitmap = Bitmap.createBitmap(argSource);
        final RenderScript renderScript = RenderScript.create(context);
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, argSource);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

        //Intrinsic Gausian blur filter
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        if (outputBitmap != argSource) {
            //argSource.recycle();
        }
        return outputBitmap;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}