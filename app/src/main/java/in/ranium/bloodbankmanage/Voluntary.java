package in.ranium.bloodbankmanage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import in.ranium.bloodbankmanage.model.DbDonor;
import in.ranium.bloodbankmanage.model.Donor;
import in.ranium.bloodbankmanage.model.GroupBean;

/**
 * Created by Durvas on 3/6/2016.
 */
public class Voluntary extends ActionBarActivity {
    ImageButton logout;
    EditText name,age,weight,area,mobile,email,address,dateofpreviousdonation;
    TextView add;
    Spinner bloodgroup;
    DbDonor dbDonor;
    String sendDate1;
    private int day;
    private int month;
    private int year;
    String data2;
    Calendar cal;
    String  bdate,ubloodgroup;
    String seen="no";
    private static final int DATE_PICKER_ID = 0;
    String[] bloodGroup = { "A-", "A+", "B+", "B-", "O", "AB" };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voluntary);
        logout=(ImageButton)findViewById(R.id.logout);
        add=(TextView)findViewById(R.id.add);
        name=(EditText)findViewById(R.id.name);
        bloodgroup=(Spinner)findViewById(R.id.bloodgroup);
        mobile=(EditText)findViewById(R.id.mobile);
        address=(EditText)findViewById(R.id.address);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        bloodgroup.setAdapter(aa);
        bloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubloodgroup = bloodGroup[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Voluntary.this, BloodBankDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });
        dbDonor= new DbDonor(getApplicationContext());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = name.getText().toString();

               // String ubloodgroup = bloodgroup.getText().toString();
                String umobile = mobile.getText().toString();
                String uaddress = address.getText().toString();
                if (TextUtils.isEmpty(uname)) {
                    Toast.makeText(Voluntary.this, "Please Enter Name", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(ubloodgroup)) {
                    Toast.makeText(Voluntary.this, "Please Enter BloodGroup", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(umobile)) {
                    Toast.makeText(Voluntary.this, "Please Enter Mobile", Toast.LENGTH_LONG).show();
                    return;
                }
                if (umobile.length()!=10) {
                    Toast.makeText(Voluntary.this, " Enter proper Mobile", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(uaddress)) {
                    Toast.makeText(Voluntary.this, "Please Enter Address", Toast.LENGTH_LONG).show();
                    return;
                }

                if(dbDonor.checkgroup(uname, ubloodgroup) < 1)
                {
                    dbDonor.addContact(new Donor(uname,ubloodgroup,umobile,uaddress));
                    showErrorAlert();
                }

            }
        });
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);


    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case DATE_PICKER_ID:
                // set date picker as current date
                return new DatePickerDialog(this, pickerListener, year, month,
                        day);
            default:
                return null;

        }

    }

    /*date picker dialog with date */
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            view.setMaxDate(cal.getTimeInMillis() - 1000);
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            data2 = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-"
                    + String.valueOf(day);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date convertedCurrentDate = sdf.parse(data2);
                String date = sdf.format(convertedCurrentDate);
                System.out.println(date);
                int days = daysBetween(convertedCurrentDate,Calendar.getInstance().getTime());
                Log.d("days", String.valueOf(days));
                if(days>90)
                {
                    seen="yes";
                }
                dateofpreviousdonation.setText(date);
                sendDate1 = dateofpreviousdonation.getText().toString().trim();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    };
    //This method is called by the above method numberOfDays
    public int daysBetween(Date d1, Date d2)
    {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    public void showErrorAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Voluntary.this);
        TextView textMsg = new TextView(Voluntary.this);
        textMsg.setText("Blood Bank  is added successfully.");
        textMsg.setPadding(10, 10, 10, 10);
        textMsg.setGravity(Gravity.CENTER);
        textMsg.setTextSize(18);
        builder.setView(textMsg);
        builder.setTitle("Bloodbank Blood Added Success");
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        Intent intent = new Intent(Voluntary.this, Voluntary.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                        finish();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
