package com.example.hoduchieu.login_registerandroidphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("HELLO: "+email);
        Button button = (Button) findViewById(R.id.btnLogout);
        sessionManager = new SessionManager(getApplication());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.SetLogin(false);
                Intent intent1 = new Intent(getApplication(),MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}
