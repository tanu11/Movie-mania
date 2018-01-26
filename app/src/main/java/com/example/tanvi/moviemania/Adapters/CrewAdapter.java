package com.example.tanvi.moviemania.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Crew;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by tanvi on 24-01-2018.
 */

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.MyCrewAdapter> {


    ArrayList<Crew> crewArrayList;
    public CrewAdapter(ArrayList<Crew> crewArrayList) {
        this.crewArrayList = crewArrayList;
    }

    @Override
    public MyCrewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_layout,parent,false);
        return new MyCrewAdapter(itemview);
    }

    @Override
    public void onBindViewHolder(MyCrewAdapter holder, int position) {
        Crew crew=crewArrayList.get(position);
        holder.crewName.setText(crew.getName());
        holder.crewCharacter.setText(crew.getDepartment());
        String imagePath=crew.getProfile_path();
        String baseURL ="https://image.tmdb.org/t/p/";
        String size="w185";
        Uri uri = Uri.parse(baseURL+size+imagePath);
        holder.crewImage.setImageURI(uri);


    }

    @Override
    public int getItemCount() {
        return crewArrayList.size();
    }

    public class MyCrewAdapter extends RecyclerView.ViewHolder {
        private TextView crewName,crewCharacter;
        private SimpleDraweeView crewImage;

        public MyCrewAdapter(View itemView) {
            super(itemView);
            crewName=itemView.findViewById(R.id.eCastMemberName);
            crewCharacter=itemView.findViewById(R.id.eCastMemberRole);
            crewImage=itemView.findViewById(R.id.eCastImage);
        }
    }
}
