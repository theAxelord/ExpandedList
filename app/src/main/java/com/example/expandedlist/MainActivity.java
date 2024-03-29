package com.example.expandedlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
MyExpandableListAdapter expListAdapter;
ExpandableListView expListView;
List<String> listDataHeader;

HashMap<String,List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView =findViewById(R.id.exp_lst);
        //prepare data for list
        prepareListData();
        expListAdapter= new MyExpandableListAdapter(this,listDataHeader,listDataChild);
        expListView.setAdapter(expListAdapter);

        //*******************step 3*****************************
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int listPos, long id) {
                Toast.makeText(MainActivity.this, listDataHeader.get(listPos)+" List Clicked", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int listPos) {
                Toast.makeText(MainActivity.this, listDataHeader.get(listPos)+" List Expanded", Toast.LENGTH_SHORT).show();
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int listPos) {
                Toast.makeText(MainActivity.this,  listDataHeader.get(listPos)+" List Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int listPos, int childPos, long id) {
                Toast.makeText(MainActivity.this,
                        listDataHeader.get(listPos)+"->"+listDataChild.get(listDataHeader.get(listPos)).get(childPos),
                        Toast.LENGTH_SHORT).show();
               /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(listDataHeader.get(listPos));
                builder.setMessage(listDataChild.get(listDataHeader.get(listPos)).get(childPos));
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setCancelable(true);

                AlertDialog dialog = builder.create();

                //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //style id
                dialog.show();
                */

                // ********************************custom dialog**************************
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);

                // set the custom dialog components - text, image and button
                TextView text1 = dialog.findViewById(R.id.titleText);
                text1.setText(listDataHeader.get(listPos));
                TextView text2 = dialog.findViewById(R.id.text);
                text2.setText(listDataChild.get(listDataHeader.get(listPos)).get(childPos));
                ImageView image =  dialog.findViewById(R.id.image);
                image.setImageResource(R.mipmap.ic_launcher_round);

                Button dialogButton =  dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
                ScaleAnimation anim=new ScaleAnimation(0.1f,1f,0.1f,1f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.ABSOLUTE);
                anim.setDuration(2000);

                // Start animating the image

                dialog.show();
                image.startAnimation(anim);
                // Later.. stop the animation
                //image.setAnimation(null);
                return false;
            }
        });


    }

//*********************step1******************************
    private void prepareListData()
    {
        listDataHeader=new ArrayList<>();
        listDataChild=new HashMap<>();
        //Add header data
        listDataHeader.add("BIMSTEC");
        listDataHeader.add("BRICS");
        listDataHeader.add("G7");
        listDataHeader.add("G20");

        //Adding child data

        List<String> top250=new ArrayList<>();
        top250.add("India");
        top250.add("Indonesia");
        top250.add("Mayanmar");
        top250.add("SriLanka");
        top250.add("Thailand");
        top250.add("Nepal");
        top250.add("Bhutan");

        List<String> top280=new ArrayList<>();
        top280.add("Brazil");
        top280.add("Russia");
        top280.add("India");
        top280.add("China");
        top280.add("South Africa");

        List<String> top290=new ArrayList<>();
        top290.add("France");
        top290.add("USA");
        top290.add("Japan");
        top290.add("Canada");
        top290.add("Italy");
        top290.add("UK");
        top290.add("Germany");

        List<String> top300=new ArrayList<>();
        top300.add("France");
        top300.add("USA");
        top300.add("Japan");
        top300.add("Canada");
        top300.add("Italy");
        top300.add("UK");
        top300.add("Germany");
        top300.add("Argentina");
        top300.add("Australia");
        top300.add("Brazil");
        top300.add("China");
        top300.add("Russia");
        top300.add("Mexico");
        top300.add("Saudi Arabia");
        top300.add("South Africa");
        top300.add("South Korea");
        top300.add("Turkey");
        top300.add("European Union");




        List<String> top=new ArrayList<>();
        listDataChild.put(listDataHeader.get(0),top250);
        listDataChild.put(listDataHeader.get(1),top280);
        listDataChild.put(listDataHeader.get(2),top290);
        listDataChild.put(listDataHeader.get(3),top300);

    }
}
