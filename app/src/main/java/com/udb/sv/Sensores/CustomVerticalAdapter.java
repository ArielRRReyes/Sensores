package com.udb.sv.Sensores;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Eduardo on 6/11/2017.
 */

public class CustomVerticalAdapter extends PagerAdapter {
    private Context context;
    private int parentInt;
    private int childInt;
    private FragmentManager fragmentManager;


    public CustomVerticalAdapter(Context context, int parentInt, int childInt, FragmentManager fragmentManager) {
        this.context = context;
        this.parentInt = parentInt;
        this.childInt = childInt;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return childInt;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int idGen = ViewGroup.generateViewId();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(lpLayout);

        FrameLayout fragmentContainer = new FrameLayout(context);
        ViewGroup.LayoutParams lpContainer = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fragmentContainer.setId(idGen);
        fragmentContainer.setLayoutParams(lpContainer);
        linearLayout.addView(fragmentContainer);
        Log.d("Parents2","parent: "+  parentInt+ "child: "+ childInt);
        if(parentInt == 0 && position == 0)
        {
            fragmentManager.beginTransaction().replace(idGen, PagerFragmentExample.newInstance(String.valueOf(parentInt), String.valueOf(position))).commit();
        }
        else if(parentInt == 1 && position == 0){
            fragmentManager.beginTransaction().replace(idGen, BlankFragment.newInstance(String.valueOf(parentInt), String.valueOf(position))).commit();
        }

        else if(parentInt == 1 && position == 1)
        {
            fragmentManager.beginTransaction().replace(idGen, PagerFragmentExample.newInstance(String.valueOf(parentInt), String.valueOf(position))).commit();
        }
        else if(parentInt == 0 && position == 1){
            fragmentManager.beginTransaction().replace(idGen, BlankFragment.newInstance(String.valueOf(parentInt), String.valueOf(position))).commit();
        }



        container.addView(linearLayout);
        return linearLayout;
    }
}
