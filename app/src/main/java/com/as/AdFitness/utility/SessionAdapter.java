package com.as.AdFitness.utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Session;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewAdapter> {
    private Context mContext;
    private List<Session> SessionList;


    public SessionAdapter(Context context, List<Session> Sessions) {
        mContext = context;
        SessionList = Sessions;
    }

    @Override
    public SessionViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.session_card_with_button_layout, null);
        return new SessionViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(SessionViewAdapter holder, int position) {
        final Session Session = SessionList.get(position);

        holder.sessionName.setText(Session.getName());
        holder.sessionTime.setText(Session.getDate().substring(11));
        holder.sessionCurrentNb.setText(String.valueOf(Session.getCurrentNb()));
        holder.sessionMaxNb.setText("/ "+String.valueOf(Session.getMaxNb()));
        holder.sessionImage.setImageResource(R.drawable.recipe_3);
        holder.sessionSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Session",Session.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return SessionList.size();
    }

    public class SessionViewAdapter extends RecyclerView.ViewHolder {
        private ImageView sessionImage;
        private TextView sessionName;
        private TextView sessionTime;
        private TextView sessionCurrentNb;
        private TextView sessionMaxNb;
        private Button sessionSubBtn;

        public SessionViewAdapter(View itemView) {
            super(itemView);

            sessionImage = (ImageView) itemView.findViewById(R.id.sessionImage);
            sessionName = (TextView) itemView.findViewById(R.id.sessionName);
            sessionTime = (TextView) itemView.findViewById(R.id.sessionTime);
            sessionCurrentNb = (TextView) itemView.findViewById(R.id.sessionCurrentNb);
            sessionMaxNb = (TextView) itemView.findViewById(R.id.sessionMaxNb);
            sessionSubBtn = (Button) itemView.findViewById(R.id.sessionSubBtn);
        }


    }
}
