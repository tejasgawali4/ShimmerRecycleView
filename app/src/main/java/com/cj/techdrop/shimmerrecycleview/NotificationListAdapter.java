package com.cj.techdrop.shimmerrecycleview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {

    private Context mcontext;
    NotificationBean notificationbean;
    private ArrayList<NotificationBean> messageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public String text;
        private TextView titleText, createdDate, textView, attachmentText;
        private ImageButton reminderButton, notificationDelete;
        public Button typeButton;
        private ImageView displayIcon;
        private RelativeLayout notificationLayout;
        private LinearLayout attachment_button;

        private MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.list_item_textview);
            createdDate = view.findViewById(R.id.list_item_date_textview);
            reminderButton = view.findViewById(R.id.reminder_lsit_button);
            displayIcon = view.findViewById(R.id.notification_icon);
            notificationLayout = view.findViewById(R.id.display_all_relative_layout);
            attachment_button = view.findViewById(R.id.attachement_button);
            attachmentText = view.findViewById(R.id.attachmentText);
            typeButton = view.findViewById(R.id.typeButton);
            titleText = view.findViewById(R.id.list_item_detail_textview);
        }
    }

    NotificationListAdapter(Context mcontext, ArrayList<NotificationBean> templist) {
        this.mcontext = mcontext;
        this.messageList = templist;
        Log.d("templist", "" + templist);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_placeholder_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {

        try {
            viewHolder.titleText.setTypeface(null, Typeface.NORMAL);
            Log.d("type", "" + messageList.get(position).getNotificationType());
            Integer type = Integer.valueOf(messageList.get(position).getNotificationType());
            Log.d("log", "" + type);
            if (type == 1 || type == 6) {
                //Home Work
                viewHolder.typeButton.setText("H");
                viewHolder.typeButton.setBackgroundColor(Color.parseColor("#d0e9c6"));
            } else if (type == 2) {
                //Annoucement
                viewHolder.typeButton.setText("A");
                viewHolder.typeButton.setBackgroundColor(Color.parseColor("#b8daff"));
            } else if (type == 3 || type == 11) {
                //Student Specific
                viewHolder.typeButton.setText("S");
                viewHolder.typeButton.setBackgroundColor(Color.parseColor("#faf2cc"));
            }

            if (("null").equals(messageList.get(position).getNotificationDetails())) {
                viewHolder.titleText.setText("");
            } else {
                viewHolder.titleText.setText(messageList.get(position).getNotificationDetails() + "...");
            }
            //getting file name and extendtion from Url
            //gfdfgdfgh
            String fileName = messageList.get(position).getFile_attach();

            String attachementFile = "";
            if (fileName.contains("https://s3.amazonaws.com")) {
                //String baseName = FilenameUtils.getBaseName(messageList.get(position).getFile_attach());
                //String extension = FilenameUtils.getExtension(messageList.get(position).getFile_attach());

                //attachementFile = baseName + "." + extension;
                viewHolder.attachmentText.setText(attachementFile);
                //System.out.println("Basename : " + baseName);
                // System.out.println("extension : " + extension);
            } else {
                attachementFile = messageList.get(position).getFile_attach();
                viewHolder.attachmentText.setText(attachementFile);
            }
//            final File file = new File(destination, attachementFile);
//            if (file.exists()) {
//                //viewHolder.attachmentText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.attachmentsave, 0);
//
//                notifyDataSetChanged();
//                //notifyDataSetInvalidated();
//            } else {
//                //notifyDataSetChanged();
//                //notifyDataSetInvalidated();
//                //viewHolder.attachmentText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.attach, 0);
//
//                notifyDataSetChanged();
//                //notifyDataSetInvalidated();
//            }
//            if ((("null").equals(messageList.get(position).getFile_attach()) || ("0").equals(messageList.get(position).getFile_attach()) || ("").equals(messageList.get(position).getFile_attach()))){
//                viewHolder.attachment_button.setVisibility(View.GONE);
//            }
//            else
//            {
//                viewHolder.attachment_button.setVisibility(View.VISIBLE);
//
//            }

            String temp = MainActivity.getShortDateTime(messageList.get(position).getCreatedDTTM());
            Log.d("Converted Date :- ", "log" + temp);
            viewHolder.createdDate.setText(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
