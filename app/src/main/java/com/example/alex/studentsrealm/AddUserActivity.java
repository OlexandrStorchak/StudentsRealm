package com.example.alex.studentsrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        final EditText name, lastName, google, git;
        Button btnCommit;

        name = (EditText) findViewById(R.id.add_new_user_name_edit);
        lastName = (EditText) findViewById(R.id.add_new_user_last_name_edit);
        google = (EditText) findViewById(R.id.add_new_user_google_edit);
        git = (EditText) findViewById(R.id.add_new_user_git_edit);
        btnCommit = (Button) findViewById(R.id.add_new_user_btn_commit);

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().length() == 0
                        || lastName.getText().length() == 0
                        && git.getText().length() == 0
                        && google.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter data", Toast.LENGTH_SHORT).show();
                } else {

                    Intent result = new Intent();


                    result.putExtra("addName", name.getText());
                    result.putExtra("addLastName", lastName.getText());
                    result.putExtra("addGoogle", google.getText());
                    result.putExtra("addGit", git.getText());

                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });
    }
}
