package com.as.AdFitness.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Participation;
import com.as.AdFitness.pojo.Session;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewAdapter> {
    private Context mContext;
    private List<Session> SessionList;
    private int userid;



    public SessionAdapter(Context context, List<Session> Sessions,int id) {
        mContext = context;
        SessionList = Sessions;
        userid  = id;

    }

    @Override
    public SessionViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.session_card_with_button_layout, null);
        return new SessionViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(final SessionViewAdapter holder, int position) {
        final Session Session = SessionList.get(position);

        holder.sessionName.setText(Session.getName());
        holder.sessionTime.setText(Session.getDate().substring(11));
        holder.sessionCurrentNb.setText(String.valueOf(Session.getCurrentNb()));
        holder.sessionMaxNb.setText("/ "+String.valueOf(Session.getMaxNb()));
        holder.sessionImage.setImageResource(R.drawable.recipe_3);
        holder.sessionSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String btnValue = holder.sessionSubBtn.getText().toString();
                ParticipationService Ps = Api.getInstance().getParticipationService();
                if(btnValue.equals("S'inscrire"))
                {
                    Call<String> call = Ps.subToSession(userid,Session.getId());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body().equals("true")){
                                Log.d("onResponse", "true");
                                holder.sessionSubBtn.setText("Se Désabonner");
                                holder.sessionCurrentNb.setText(String.valueOf(Session.getCurrentNb()+1));
                                Toast.makeText(view.getContext(), "Vous avez été affecter a ce cours.", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            }
                            else
                                Log.d("onResponse", "False");
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("Failure", t.getLocalizedMessage());

                        }
                    });
                }else
                {
                    Call<String> call =  Ps.unsubToSession(userid,Session.getId());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body().equals("true")){
                                Log.d("onResponse", "true");
                                holder.sessionSubBtn.setText("S'inscrire");
                                holder.sessionCurrentNb.setText(String.valueOf(Session.getCurrentNb()-1));
                                Toast.makeText(view.getContext(), "Vous avez été désabonner a ce cours.", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            }
                            else
                                Log.d("onResponse", "False");
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("Failure", t.getLocalizedMessage());

                        }
                    });
                }


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
