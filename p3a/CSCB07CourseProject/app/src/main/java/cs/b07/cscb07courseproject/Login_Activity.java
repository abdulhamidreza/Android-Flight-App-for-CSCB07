package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.User;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText emailET = (EditText) findViewById(R.id.emailET);
        String userEmail = emailET.getText().toString();
        EditText passwordET = (EditText) findViewById(R.id.passwordET);
        String userPassword = passwordET.getText().toString();
        User user = new Admin(userEmail, userPassword);
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("Admin", user);
        startActivity(intent);
    }

    public void signUp(View view) {

    }

}
