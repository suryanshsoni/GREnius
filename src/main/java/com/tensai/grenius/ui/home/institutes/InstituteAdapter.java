package com.tensai.grenius.ui.home.institutes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.view.SlideTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tensai.grenius.util.AppConstants.API_BASE_URL;


/**
 * Created by rishabhpanwar on 25/06/17.
 */

public class InstituteAdapter extends RecyclerView.Adapter<InstituteAdapter.ViewHolder> {

    Context ctx;
    List<Institute> institutes;

    public InstituteAdapter(Context context, List<Institute> institutes) {
        this.ctx = context;
        this.institutes = institutes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_institute, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.tvInstiLocation.setText(institutes.get(position).getLocation());
            holder.tvInstiName.setText(institutes.get(position).getName());
            holder.tvInstiShortDesc.setText(institutes.get(position).getShort_desc());

            if (position % 4 == 0) {
                holder.instiColourLeftMargin.setBackgroundResource(R.color.wl_red);
                holder.instiColourRightMargin.setBackgroundResource(R.color.wl_red);
            } else if (position % 4 == 1) {
                holder.instiColourLeftMargin.setBackgroundResource(R.color.wl_yellow);
                holder.instiColourRightMargin.setBackgroundResource(R.color.wl_yellow);
            } else if (position % 4 == 2) {
                holder.instiColourLeftMargin.setBackgroundResource(R.color.wl_green);
                holder.instiColourRightMargin.setBackgroundResource(R.color.wl_green);
            } else {
                holder.instiColourLeftMargin.setBackgroundResource(R.color.wl_purple);
                holder.instiColourRightMargin.setBackgroundResource(R.color.wl_purple);
            }

        final String URL = API_BASE_URL + institutes.get(position).getImagePath();


            holder.instiContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(ctx, InstituteIndividual.class);

                    /*intent.putExtra("title", institutes.get(position).getName());
                    intent.putExtra("desc", institutes.get(position).getLong_desc());
                    intent.putExtra("url", institutes.get(position).getUrl());
                    intent.putExtra("location", institutes.get(position).getLocation());*/

                    InstituteIndividual.desc = institutes.get(position).getLong_desc();
                    intent.putExtra("imagePath", "" + URL);

                    ctx.startActivity(intent);
                }
            });

        }

    @Override
    public int getItemCount() {
        try {
            return institutes.size();
        } catch (Exception e) {
            return 0;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.insti_colour_left_margin)
        View instiColourLeftMargin;
        @BindView(R.id.tv_insti_location)
        SlideTextView tvInstiLocation;
        @BindView(R.id.insti_location_layout)
        RelativeLayout instiLocationLayout;
        @BindView(R.id.tv_insti_name)
        SlideTextView tvInstiName;
        @BindView(R.id.tv_insti_short_desc)
        SlideTextView tvInstiShortDesc;
        @BindView(R.id.insti_container)
        RelativeLayout instiContainer;
        @BindView(R.id.insti_colour_right_margin)
        View instiColourRightMargin;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback {
    }
}
