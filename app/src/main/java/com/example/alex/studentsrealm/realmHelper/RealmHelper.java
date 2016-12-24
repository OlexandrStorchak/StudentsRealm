package com.example.alex.studentsrealm.realmHelper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.alex.studentsrealm.data.RealData;
import com.example.alex.studentsrealm.realmHelper.models.StudentRealmObj;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Alex on 15.12.2016.
 */

public class RealmHelper {
    private static final String TAG = "log";
    Realm realm = Realm.getDefaultInstance();


    public RealmHelper() {
    }


    public boolean isStudentsEmpty() {
        return realm.where(StudentRealmObj.class).findAll().isEmpty() ? true : false;
    }

    public void realmFirstSet() {

        if (isStudentsEmpty()) {
            int _id;
            realm.beginTransaction();

            List<RealmObject> list = new ArrayList<>();

            for (int i = 0; i <= RealData.studentsName.length - 4; i = i + 4) {
                _id = (i / 4);
                StudentRealmObj studentRealmObj = new StudentRealmObj();
                studentRealmObj.setName(RealData.studentsName[i]);
                studentRealmObj.setLastName(RealData.studentsName[i + 1]);
                studentRealmObj.setGoogle(RealData.studentsName[i + 2]);
                studentRealmObj.setGit(RealData.studentsName[i + 3]);
                studentRealmObj.setId(_id);
                list.add(studentRealmObj);
            }
            realm.insertOrUpdate(list);
            realm.commitTransaction();
        } else

        {
            Log.d(TAG, "realmFirstSet: REALM NOT EMPTY");
        }
    }


    public void deleteUser(int id) {
        realm.beginTransaction();
        StudentRealmObj res = realm.where(StudentRealmObj.class).equalTo("id", id).findFirst();
        res.deleteFromRealm();
        realm.commitTransaction();
        Log.d(TAG, "deleteUser: OK");

    }


    public RealmResults<StudentRealmObj> realmGetAllStudents() {

        return realm.where(StudentRealmObj.class).findAll().sort("lastName");
    }

    public RealmResults<StudentRealmObj> realmGetSearchStudents(String search) {

        return realm.where(StudentRealmObj.class)
                .contains("searchName", search)
                .or()
                .contains("searchLastName", search)
                .findAll();
    }

    public void realmAddNewStudent(String name, String lastName, String google, String git) {
        int _id = realm.where(StudentRealmObj.class).findAll().sort("id").last().getId();
        realm.beginTransaction();
        StudentRealmObj newUser = new StudentRealmObj();

        newUser.setId(_id + 1);
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setGoogle(google);
        newUser.setGit(git);

        realm.insert(newUser);
        realm.commitTransaction();


    }


    public void closeAllRealm() {
        realm.close();
        realm = null;
    }


}
