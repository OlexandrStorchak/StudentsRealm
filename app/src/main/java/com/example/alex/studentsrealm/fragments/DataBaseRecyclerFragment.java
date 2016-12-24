package com.example.alex.studentsrealm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alex.studentsrealm.AddUserActivity;
import com.example.alex.studentsrealm.R;
import com.example.alex.studentsrealm.adapters.RealDataAdapter;
import com.example.alex.studentsrealm.realmHelper.RealmHelper;
import com.example.alex.studentsrealm.realmHelper.models.StudentRealmObj;

import io.realm.RealmResults;

/**
 * Created by Alex on 15.12.2016.
 */

public class DataBaseRecyclerFragment extends Fragment {
    private static final String TAG = "log";
    private static final int REQUEST_CODE_ADD_NEW_USER = 12;
    RecyclerView recyclerView;
    RealmHelper realmHelper = new RealmHelper();
    RealDataAdapter adapter = new RealDataAdapter(null,realmHelper);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.data_base_recycler_view, null);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                adapter.setData(realmHelper.realmGetSearchStudents(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (searchView.getQuery().length()>0) {
                    adapter.setData(realmHelper.realmGetSearchStudents(newText));

                } else if (searchView.getQuery().length()==0){

                    adapter.setData(realmHelper.realmGetAllStudents());

                }

                return false;
            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add :
                Toast.makeText(getContext(),"Add new user",Toast.LENGTH_SHORT).show();
                Intent addNewUser = new Intent(getContext(), AddUserActivity.class);
                startActivityForResult(addNewUser,REQUEST_CODE_ADD_NEW_USER);
                break;
            case R.id.action_remove:
                if (adapter.vis){
                    adapter.setVisibleRemove(false);
                } else {
                    adapter.setVisibleRemove(true);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_ADD_NEW_USER:

                String nameToAdd = data.getExtras().get("addName").toString();
                String lastNameToAdd = data.getExtras().get("addLastName").toString();
                String googleToAdd = data.getExtras().get("addGoogle").toString();
                String gitToAdd = data.getExtras().get("addName").toString();
                Log.d(TAG, "onActivityResult: "+ nameToAdd+" "+lastNameToAdd);

                    realmHelper.realmAddNewStudent(nameToAdd,lastNameToAdd,googleToAdd,gitToAdd);
                adapter.setData(realmHelper.realmGetAllStudents());

                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.data_base_recycler_view_id);

        realmHelper.realmFirstSet();
        recyclerView.setLayoutManager(layoutManager);
        adapter.setData(realmHelper.realmGetAllStudents());
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onDetach() {
        super.onDetach();
        realmHelper.closeAllRealm();
        realmHelper =null;
    }
}
