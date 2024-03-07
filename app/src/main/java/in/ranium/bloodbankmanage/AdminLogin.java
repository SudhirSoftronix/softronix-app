package in.ranium.bloodbankmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by durwas on 14/3/16.
 */
public class AdminLogin  extends ActionBarActivity {
    EditText usernameEdittext, passwordEdittext;
    TextView loginTextView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);
        usernameEdittext = (EditText) findViewById(R.id.usernameEdittext);
        passwordEdittext = (EditText) findViewById(R.id.passwordEdittext);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEdittext.getText().toString();
                String password = passwordEdittext.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(AdminLogin.this, R.string.plz_entr_username, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(AdminLogin.this, R.string.plz_entr_password, Toast.LENGTH_LONG).show();
                    return;
                }

if(username.equals("admin") && password.equals("admin")) {
    Intent intent = new Intent(AdminLogin.this, Voluntary.class);
    startActivity(intent);
    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
}else
{
    Toast.makeText(AdminLogin.this, "Invalid Username or password", Toast.LENGTH_LONG).show();
}

            }
        });

    }
}
