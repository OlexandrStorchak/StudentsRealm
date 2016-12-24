package com.example.alex.studentsrealm.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.studentsrealm.R;
import com.example.alex.studentsrealm.realmHelper.RealmHelper;
import com.example.alex.studentsrealm.realmHelper.models.StudentRealmObj;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Alex on 15.12.2016.
 */

public class RealDataAdapter extends RecyclerView.Adapter<RealDataAdapter.RealHolder> {

    private static final String TAG = "log";
    List<StudentRealmObj> realNamesList;
    RealmHelper realmHelper;


    public RealDataAdapter(List<StudentRealmObj> realNamesList, RealmHelper realm) {
        this.realNamesList = realNamesList;
        this.realmHelper = realm;
    }
    public void setData(RealmResults<StudentRealmObj> data){
        realNamesList=data;
        notifyDataSetChanged();
    }

    @Override
    public RealHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_base_recycler_row, parent, false);
        return new RealHolder(view);
    }

    @Override
    public void onBindViewHolder(final RealHolder holder, final int position) {

        holder.name.setText(realNamesList.get(position).getName());

        holder.lastName.setText(realNamesList.get(position).getLastName());
        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.delete.setVisibility(View.VISIBLE);

                Log.d(TAG, "onLongClick: "+position+" "+holder.getAdapterPosition());
                return false;
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    realmHelper.deleteUser(realNamesList.get(position).getId());
                notifyDataSetChanged();
               // Log.d(TAG, "onClick: onBind"+holder.lastName.getText()+"  "+realNamesList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return realNamesList == null ? 0 : realNamesList.size();

    }

    class RealHolder extends RecyclerView.ViewHolder {
        TextView name, lastName;
        ImageView google, git, delete;

        RealHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.real_names_recycler_row_name);
            lastName = (TextView) itemView.findViewById(R.id.real_names_recycler_row_last_name);
            delete = (ImageView) itemView.findViewById(R.id.data_base_recycler_row_delete);


        }
    }


}
