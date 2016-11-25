package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cs.b07.cscb07courseproject.users.Admin;

public class AdminActivity extends AppCompatActivity {

    private EditText detailBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();

        Admin admin = (Admin) intent.getSerializableExtra(LogInActivity.userKey);

        detailBox = (EditText) findViewById(R.id.adminDetailEt);
        detailBox.setText(admin.toString());
    }

}
