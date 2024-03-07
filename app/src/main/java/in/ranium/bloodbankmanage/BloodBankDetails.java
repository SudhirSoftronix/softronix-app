package in.ranium.bloodbankmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import in.ranium.bloodbankmanage.model.GroupBean;

/**
 * Created by Durvas on 3/6/2016.
 */
public class BloodBankDetails extends ActionBarActivity {
    TextView bdetails,blist,voluntary,search,bsearch,ab,o,blocation,cancel,exit;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bankersdetails);
        bdetails=(TextView)findViewById(R.id.bdetails);
        voluntary=(TextView)findViewById(R.id.voluntary);
        search=(TextView)findViewById(R.id.search);
        bsearch=(TextView)findViewById(R.id.faq);
        exit=(TextView)findViewById(R.id.exit);
        ab=(TextView)findViewById(R.id.ab);
        o=(TextView)findViewById(R.id.o);
        bdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBean.getInstance().setBloodGroup("A+");
                Intent intent= new Intent(BloodBankDetails.this,BloodBanksearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        voluntary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBean.getInstance().setBloodGroup("A-");
                Intent intent= new Intent(BloodBankDetails.this,BloodBanksearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBean.getInstance().setBloodGroup("B+");
                Intent intent= new Intent(BloodBankDetails.this,BloodBanksearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBean.getInstance().setBloodGroup("B-");
                Intent intent= new Intent(BloodBankDetails.this,BloodBanksearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBean.getInstance().setBloodGroup("AB");
                Intent intent= new Intent(BloodBankDetails.this,BloodBanksearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBean.getInstance().setBloodGroup("O");
                Intent intent= new Intent(BloodBankDetails.this,BloodBanksearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });

    }

}
