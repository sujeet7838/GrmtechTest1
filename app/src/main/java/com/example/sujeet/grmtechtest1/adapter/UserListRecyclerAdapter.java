package com.example.sujeet.grmtechtest1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sujeet.grmtechtest1.R;
import com.example.sujeet.grmtechtest1.models.UserListModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sujeet on 11/29/2017.
 */

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.ViewHolder> {


  //  private ArrayList<UserListModel> android;
    //private Context context;

    private List<UserListModel.DataBean> audioLists;
    private Context context;

    public UserListRecyclerAdapter(Context context, List<UserListModel.DataBean> audioLists) {
        this.audioLists = audioLists;
        this.context = context;
    }
    @Override
    public UserListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_userlist, viewGroup, false);
        return new UserListRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListRecyclerAdapter.ViewHolder holder, int position) {
        UserListModel.DataBean item = audioLists.get(position);
        // Toast.makeText(context,"id"+item.getId(),Toast.LENGTH_LONG).show();
        holder.audio_tittle.setText(item.getFirst_name());
       // holder.audioAuther.setText(item.getAuthor());
        /*Picasso.with(context)
                .load(item.getCoverimage())
                .into(holder.audioImage);*/

    }

    @Override
    public int getItemCount() {
        return audioLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView audio_tittle;
//        private ImageView audioImage;

        public ViewHolder(View view) {
            super(view);

            audio_tittle = (TextView) view.findViewById(R.id.tv_book_tittle);
           // audioAuther=(TextView)view.findViewById(R.id.bookAuther);
            //audioImage = (ImageView) view.findViewById(R.id.image_book);
        }
    }

}
