package com.qniti.qhadir;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PastVisitedAdapter extends RecyclerView.Adapter<PastVisitedAdapter.PastVisitedViewHolder> {


    private Context mCtx;
    private List<Log> logList;
    private OnItemClicked onClick;


    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public PastVisitedAdapter(Context mCtx, List<Log> logList) {
        this.mCtx = mCtx;
        this.logList = logList;
    }

    @Override
    public PastVisitedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.past_visited_list, null);
        return new PastVisitedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PastVisitedViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log log = logList.get(position);

        int paddingDp = 5;
        float density = mCtx.getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);


         //getName
        holder.placeName.setText(log.getPlaceName()); //GetICnum
        holder.logStatus.setText(log.getLogStatus().toUpperCase());
        holder.enterDate.setText(log.getEnterDate());
        holder.enterTime.setText(log.getEnterTime());

        if(!"00/00/0000".equalsIgnoreCase(log.getExitDate())){
            holder.exitDate.setText(log.getExitDate());
        }

        if(!"".equalsIgnoreCase(log.getExitDate())){
            holder.exitTime.setText(log.getExitTime());

        }
        android.util.Log.d("ACCESS TOKEN",log.getExitTime());

        if("Inside".equalsIgnoreCase(log.getLogStatus())){

            holder.logStatus.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.roundedyellow));
            holder.logStatus.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel);
            holder.logStatus.setGravity(Gravity.CENTER);

        }else if("Visited".equalsIgnoreCase(log.getLogStatus())){

            holder.logStatus.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.buttoncornerbluefont));
            holder.logStatus.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel);
            holder.logStatus.setGravity(Gravity.CENTER);


        }else {

            holder.logStatus.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.roundedpink));
            holder.logStatus.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel);
            holder.logStatus.setGravity(Gravity.CENTER);
        }

      /*  if("Inside".equalsIgnoreCase(log.getLogStatus())){
            holder.enterDate.setText("Enter On "+log.getEnterDate()+" "+log.getEnterTime());
            holder.test.setBackgroundColor(ContextCompat.getColor(mCtx, R.color.colorNavIcon));
            holder.logStatus.setTextColor(ContextCompat.getColor(mCtx,R.color.colorLightGreen));
        }else if("Visited".equalsIgnoreCase(log.getLogStatus())){
            holder.enterDate.setText("Visited On "+log.getEnterDate()+" "+log.getEnterTime());
            holder.logStatus.setTextColor(ContextCompat.getColor(mCtx,R.color.red));
        } */

        holder.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    class PastVisitedViewHolder extends RecyclerView.ViewHolder {

        TextView enterDate,enterTime, exitDate,exitTime, placeName,logStatus;
        // ImageView imageView;
        RelativeLayout test;

        public PastVisitedViewHolder(View itemView) {
            super(itemView);

            test=itemView.findViewById(R.id.testing);
            enterDate = itemView.findViewById(R.id.enterDate);
            enterTime = itemView.findViewById(R.id.enterTime);
            exitDate = itemView.findViewById(R.id.exitDate);
            exitTime = itemView.findViewById(R.id.exitTime);
            placeName = itemView.findViewById(R.id.placeName);
            logStatus = itemView.findViewById(R.id.log_status);
        }
    }
    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}
