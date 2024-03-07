package in.ranium.bloodbankmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.ranium.bloodbankmanage.model.DbRegister;
import in.ranium.bloodbankmanage.model.GroupBean;

/**
 * Created by Durvas on 3/6/2016.
 */
public class BankersLogin extends ActionBarActivity {
    EditText usernameEdittext, passwordEdittext;
    TextView loginTextView,register;
    DbRegister dbRegister;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbRegister= new DbRegister(getApplicationContext());
        usernameEdittext = (EditText) findViewById(R.id.usernameEdittext);
        passwordEdittext = (EditText) findViewById(R.id.passwordEdittext);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        register=(TextView) findViewById(R.id.register);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEdittext.getText().toString();
                String password = passwordEdittext.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(BankersLogin.this, R.string.plz_entr_username, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(BankersLogin.this, R.string.plz_entr_password, Toast.LENGTH_LONG).show();
                    return;
                }
               int userid=dbRegister.getname(username,password);
                Log.d("userid",String.valueOf(userid));
                GroupBean.getInstance().setId(String.valueOf(userid));
                if(userid != 0) {
                    Intent intent = new Intent(BankersLogin.this, BloodBankDetails.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                }
                else
                {
                    Toast.makeText(BankersLogin.this, "Username or password Incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankersLogin.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });

    }
}
