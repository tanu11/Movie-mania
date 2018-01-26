package com.example.tanvi.moviemania.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Cast;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by tanvi on 22-01-2018.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {


    private ArrayList<Cast> castArrayList;
    public CastAdapter(ArrayList<Cast> castArrayList) {
        this.castArrayList = castArrayList;
    }
    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_layout,parent,false);
        return new CastViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        Cast cast=castArrayList.get(position);
        holder.castName.setText(cast.getName());
        holder.castCharacter.setText(cast.getCharacter());
        String imagePath=cast.getProfile_path();
        String baseURL ="https://image.tmdb.org/t/p/";
        String size="w185";
        Uri uri = Uri.parse(baseURL+size+imagePath);
        holder.castImage.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return castArrayList.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {

        private TextView castName,castCharacter;
        private SimpleDraweeView castImage;
        public CastViewHolder(View itemView) {

            super(itemView);
            castName=itemView.findViewById(R.id.eCastMemberName);
            castCharacter=itemView.findViewById(R.id.eCastMemberRole);
            castImage=itemView.findViewById(R.id.eCastImage);
        }
    }
}
