package in.ranium.bloodbankmanage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.ranium.bloodbankmanage.model.DbRegister;
import in.ranium.bloodbankmanage.model.UserRegister;

public class RegisterActivity extends AppCompatActivity {
    TextView buttonLogin;
    ImageButton logout;
    EditText email,password,cpassword;
    private SharedPreferences prefs;
    DbRegister dbRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        buttonLogin=(TextView)findViewById(R.id.buttonLogin);
        logout=(ImageButton)findViewById(R.id.logout);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.cpassword);
        dbRegister= new DbRegister(getApplicationContext());
                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String username = email.getText().toString();
                        String userpassword = password.getText().toString();
                        String usercpassword = cpassword.getText().toString();
                        if (TextUtils.isEmpty(username)) {
                            Toast.makeText(RegisterActivity.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (TextUtils.isEmpty(usercpassword)) {
                            Toast.makeText(RegisterActivity.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (TextUtils.isEmpty(usercpassword)) {
                            Toast.makeText(RegisterActivity.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(userpassword.equals(usercpassword))
                        {
                            if(dbRegister.checkgroup(username, userpassword) < 1)
                            {
                                dbRegister.addContact(new UserRegister(username,userpassword));
                                showErrorAlert();
                            }

                        }



                    }
                });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

      //  String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression ="[a-zA-Z0-9+._%-+]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                "(" +
                "." +
                "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                ")+";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }



    public void showError(String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        TextView textMsg = new TextView(RegisterActivity.this);
        textMsg.setText(Message);
        textMsg.setPadding(10, 10, 10, 10);
        textMsg.setGravity(Gravity.CENTER);
        textMsg.setTextSize(18);
        builder.setView(textMsg);
        builder.setTitle("Registration Alert");
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public void showErrorAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        TextView textMsg = new TextView(RegisterActivity.this);
        textMsg.setText("Registration is successful.");
        textMsg.setPadding(10, 10, 10, 10);
        textMsg.setGravity(Gravity.CENTER);
        textMsg.setTextSize(18);
        builder.setView(textMsg);
        builder.setTitle("Registration Success");
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        Intent intent = new Intent(RegisterActivity.this, BankersLogin.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                        finish();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
