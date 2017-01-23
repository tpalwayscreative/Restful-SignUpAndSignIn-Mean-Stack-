package processing.image.co.tpcreative.restfulsigninandsignupmeanstack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSignout ;
    private TextView txtName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignout = (Button) findViewById(R.id.btnSignOut);
        txtName = (TextView) findViewById(R.id.txtName);

        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        txtName.setText("Hi "+name);



        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
