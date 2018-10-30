package com.example.pritesh.myslideup;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pritesh.myslideup.library.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DemoActivity";

    private SlidingUpPanelLayout mLayout;

    private RecyclerView recyclerview;
    private List<DataHolder> your_array_list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

//        ListView lv = (ListView) findViewById(R.id.list);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        your_array_list = Arrays.asList(
//                "This",
//                "Is",
//                "An",
//                "Example",
//                "ListView",
//                "That",
//                "You",
//                "Can",
//                "Scroll",
//                ".",
//                "It",
//                "Shows",
//                "How",
//                "Any",
//                "Scrollable",
//                "View",
//                "Can",
//                "Be",
//                "Included",
//                "As",
//                "A",
//                "Child",
//                "Of",
//                "SlidingUpPanelLayout"
//        );
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,your_array_list );
//        lv.setAdapter(arrayAdapter);
//
//        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
//        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
//            @Override
//            public void onPanelSlide(View panel, float slideOffset) {
//                Log.d(TAG, "onPanelSlide, offset " + slideOffset);
//            }
//
//            @Override
//            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
//                Log.d(TAG, "onPanelStateChanged " + newState);
//            }
//        });
//        mLayout.setFadeOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
//            }
//        });
//
//        TextView t = (TextView) findViewById(R.id.name);
//        t.setText(Html.fromHtml(getString(R.string.hello)));
//        Button f = (Button) findViewById(R.id.follow);
//        f.setText(Html.fromHtml(getString(R.string.follow)));
//        f.setMovementMethod(LinkMovementMethod.getInstance());
//        f.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("http://www.twitter.com/umanoapp"));
//                startActivity(i);
//            }
//        });

        setupRecyclerview();

    }

    private void setupRecyclerview() {

        for(int i=0;i<10;i++)
        {
            your_array_list.add(i,new DataHolder(""+i));
        }

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerview.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        myAdapter = new MyAdapter(your_array_list,getApplicationContext());
        recyclerview.setAdapter(myAdapter);

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null && (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
