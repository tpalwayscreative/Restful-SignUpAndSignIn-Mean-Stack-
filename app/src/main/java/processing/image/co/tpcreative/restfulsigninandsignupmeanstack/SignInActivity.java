package processing.image.co.tpcreative.restfulsigninandsignupmeanstack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignInActivity extends AppCompatActivity {

    private EditText edtEmail,edtPassword ;
    private Button btnSignIn , btnSignUp ;
    private ProgressDialog pDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams();
                params.add("email",edtEmail.getText().toString());
                params.add("password",edtPassword.getText().toString());

                ManagerNetwork.post("/users/signIn", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        pDialog = new ProgressDialog(SignInActivity.this);
                        pDialog.setMessage("Signing in please wait...");
                        pDialog.setIndeterminate(false);
                        pDialog.setCancelable(false);
                        pDialog.show();
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONObject userObject  = response.getJSONObject("data").getJSONObject("user") ;
                            String name = userObject.getString("name");
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra("name",name);
                            startActivity(i);
                            pDialog.dismiss();
                            finish();
                        }
                        catch (JSONException e){

                            Log.d("action here",e.getMessage());

                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                        Log.d("action here",errorResponse.toString());

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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });

    }
}
