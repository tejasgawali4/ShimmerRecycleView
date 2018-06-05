package com.cj.techdrop.shimmerrecycleview;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    StringRequest stringRequest;
    ShimmerFrameLayout shimmerContainer;
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

        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();

        recyclerView = findViewById(R.id.recycler_view);

        CustomListViewValuesArr = new ArrayList<>();

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        mAdapter = new NotificationListAdapter(this, CustomListViewValuesArr);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        getNotificationFromServer();
    }

    private void getNotificationFromServer() {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "https://topschool.prisms.in/rest/index.php/staff_list.json";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("fun_name", "getTopSchoolNotifications");
            jsonBody.put("applicationid", "NjgyLy0vUk9ISVQtWUFEQVY=");
            jsonBody.put("uid", "682");
            jsonBody.put("sid", "496");

            JsonObjectRequest request_json = new JsonObjectRequest(URL, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Process os success response
                            Log.d("responce :- ","" + response);

                            if (response != null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                    JSONArray jsonArray = jsonObject.getJSONArray("record");
                                    if (jsonArray != null) {

                                        for (int i = 0; i < jsonArray.length(); i++)
                                        {
                                            notificationbean = new NotificationBean();
                                            JSONObject jobject = jsonArray.getJSONObject(i);
                                            notificationbean.setNotificationDetails(jobject.getString("anntext"));
                                            notificationbean.setCreatedDTTM(jobject.getString("created"));
                                            notificationbean.setFile_attach(jobject.getString("file_attach"));
                                            notificationbean.setNotificationType(jobject.getString("an_type"));
                                            //notificationbean.setIsRead(1);
                                            notificationbean.setReceivedDTTM(jobject.getString("id"));
                                            //notificationbean.setNotificationId(Integer.parseInt(jobject.getString("id")));
                                            notificationbean.setUserId("682");
                                            //updating un received notificaiton from sever
                                            CustomListViewValuesArr.add(notificationbean);
                                        }

                                        mAdapter = new NotificationListAdapter(getApplicationContext(), CustomListViewValuesArr);
                                        // refreshing recycler view
                                        mAdapter.notifyDataSetChanged();

                                        shimmerContainer.stopShimmer();
                                        shimmerContainer.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            });

            requestQueue.add(request_json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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


    public static String getDateAndTimeformat(String format) {
        String dateFormat;
        dateFormat = format;
        Date date;
        try {
            SimpleDateFormat tempDate = new SimpleDateFormat("dd-MM-yyyy hh:mm aa",Locale.ENGLISH);
            date = tempDate.parse(dateFormat);
            SimpleDateFormat cDate = new SimpleDateFormat("MMM d ''yy 'at' hh:mm aa",Locale.ENGLISH);
            dateFormat = cDate.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dateFormat;
    }
}
