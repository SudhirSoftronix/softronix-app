package in.ranium.bloodbankmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by durwas on 14/3/16.
 */
public class FAQ extends ActionBarActivity {
    TextView ftext;
    ImageButton logout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        ftext=(TextView)findViewById(R.id.ftext);
        ftext.setText(Html.fromHtml(getResources().getString(R.string.faq)));
        logout=(ImageButton)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FAQ.this, BloodBankDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });
    }
}