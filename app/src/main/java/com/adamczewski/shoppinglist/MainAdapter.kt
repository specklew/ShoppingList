package com.adamczewski.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainAdapter(var mainActivity: MainActivity,var titles: ArrayList<String>) :
    BaseAdapter() {

    lateinit var animation : Animation;


    override fun getCount(): Int {
        return titles.size;
    }

    override fun getItem(p0: Int): Any {
        return p0;
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(i: Int, p1: View?, viewGroup: ViewGroup?): View {
        val view = LayoutInflater.from(mainActivity).inflate(R.layout.new_item_layout, viewGroup, false);
        animation = AnimationUtils.loadAnimation(mainActivity, R.anim.animation1);
        val textView = view.findViewById<TextView>(R.id.text_view);
        val ll_bg = view.findViewById<LinearLayout>(R.id.ll_bg);

        val resourceName = "gradient_" + (1..8).random();
        val resource = mainActivity.resources.getIdentifier(resourceName, "drawable", mainActivity.packageName);

        ll_bg.background = ContextCompat.getDrawable(mainActivity, resource);
        textView.text = titles[i];
        textView.animation = animation;

        return view;
    }
}