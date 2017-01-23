package processing.image.co.tpcreative.restfulsigninandsignupmeanstack;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignUpActivity extends AppCompatActivity {


    private ActionBar actionBar ;
    private EditText edtName , edtEmail,edtPassword ;
    private Button btnSignIn , btnSignUp ;
    private ProgressDialog pDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtName = (EditText) findViewById(R.id.edtName);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams();
                params.add("name",edtName.getText().toString());
                params.add("email",edtEmail.getText().toString());
                params.add("password",edtPassword.getText().toString());

                ManagerNetwork.post("/users/signUp", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        pDialog = new ProgressDialog(SignUpActivity.this);
                        pDialog.setMessage("Signing up please wait...");
                        pDialog.setIndeterminate(false);
                        pDialog.setCancelable(false);
                        pDialog.show();
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String message = response.getString("message");
                            Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();
                            finish();
                            pDialog.dismiss();
                        }
                        catch (JSONException e){

                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                        pDialog.dismiss();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        pDialog.dismiss();
                    }
                });




            }
        });
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if(i == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
