package com.example.alex.studentsrealm.realmHelper;

import android.content.Context;
import android.util.Log;

import com.example.alex.studentsrealm.data.RealData;
import com.example.alex.studentsrealm.realmHelper.models.StudentRealmObj;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Alex on 15.12.2016.
 */

public class RealmInit {
    private static final String TAG = "log";
    Context context;
    Realm realm = null;

    public RealmInit(Context context) {
        this.context = context;
    }

    public void realmInit() {
        Realm.init(context);
        RealmConfiguration configuration =
                new RealmConfiguration.Builder()
                        .name("students_db.realm")
                        .schemaVersion(1)
                        .build();
        Realm.setDefaultConfiguration(configuration);
        Log.d(TAG, "realmInit: well done");
    }

    public boolean isStudentsEmpty() {
        realm = Realm.getDefaultInstance();
        return realm.where(StudentRealmObj.class).findAll().isEmpty() ? true : false;
    }

    public void realmFirstSet() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        int _id = 0;
        try {
            _id = realm.where(StudentRealmObj.class).findAll().last().getId();
        } catch (IndexOutOfBoundsException e) {

        } finally {
            List<RealmObject> list = new ArrayList<>();

            for (int i = 0; i <= RealData.studentsName.length - 4; i = i + 4) {
                _id = (i / 4);
                StudentRealmObj studentRealmObj = new StudentRealmObj();
                studentRealmObj.setName(RealData.studentsName[i]);
                studentRealmObj.setSearchName(RealData.studentsName[i]);
                studentRealmObj.setLastName(RealData.studentsName[i + 1]);
                studentRealmObj.setSearchLastName(RealData.studentsName[i + 1]);
                studentRealmObj.setId(_id);

                Log.d(TAG, "RealmFirstSet:  " + studentRealmObj.getLastName() + " " + studentRealmObj.getName() + " " + _id);

                list.add(studentRealmObj);
            }
            realm.insertOrUpdate(list);
            realm.commitTransaction();
            Log.d(TAG, "RealmFirstSet: Last id of DB : " + realm.where(StudentRealmObj.class).findAll().last().getId());

            realm.close();
        }
    }


    public RealmResults<StudentRealmObj> realmGetAllStudents() {
        realm = Realm.getDefaultInstance();

        return realm.where(StudentRealmObj.class).findAll().sort("lastName");
    }

    public RealmResults<StudentRealmObj> realmGetSearchStudents(String search) {
        realm = Realm.getDefaultInstance();

        return realm.where(StudentRealmObj.class)
                .contains("searchName", search)
                .or()
                .contains("searchLastName", search)
                .findAll();
    }



    public void closeAllRealm(){
        realm.close();
        realm=null;
    }


}
