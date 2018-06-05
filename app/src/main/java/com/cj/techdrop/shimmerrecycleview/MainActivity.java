package com.cj.techdrop.shimmerrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    public ArrayList<NotificationBean> CustomListViewValuesArr;
    private RecyclerView recyclerView;
    NotificationBean notificationbean;
    private NotificationListAdapter mAdapter;

    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShimmerFrameLayout shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();

        recyclerView = findViewById(R.id.recycler_view);

        CustomListViewValuesArr = new ArrayList<>();

        getNotificationFromServer();

        mAdapter = new NotificationListAdapter(this, CustomListViewValuesArr);
    }

    private void getNotificationFromServer() {
        
    }


    public static String getShortDateTime(String format) {
        String dateFormat;
        dateFormat = format;
        Date date;
        try {
            SimpleDateFormat tempDate = new SimpleDateFormat("MMM d ''yy 'at' h:mm a", Locale.ENGLISH);
            date = tempDate.parse(dateFormat);
            SimpleDateFormat cDate = new SimpleDateFormat("EEE, MMM d",Locale.ENGLISH);
            dateFormat = cDate.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dateFormat;
    }
}
