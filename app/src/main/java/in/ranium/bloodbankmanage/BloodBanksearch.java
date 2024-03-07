package in.ranium.bloodbankmanage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.ranium.bloodbankmanage.model.DbDonor;
import in.ranium.bloodbankmanage.model.Donor;
import in.ranium.bloodbankmanage.model.GroupBean;

/**
 * Created by Durvas on 3/6/2016.
 */
public class BloodBanksearch extends ActionBarActivity {
    ImageButton logout;
    Spinner spinner;
    EditText area;
    ListView listView;
    MyCustomMessageAdapter dataAdapter = null;
    DbDonor dbDonor;
    String BloodGroup;
    TextView search;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodbanksearch);
        dbDonor= new DbDonor(getApplicationContext());
        logout=(ImageButton)findViewById(R.id.logout);
        listView=(ListView)findViewById(R.id.donorlist);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBanksearch.this, BloodBankDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });




BloodGroup= GroupBean.getInstance().getBloodGroup();
                ArrayList<Donor> data = dbDonor.getSearchData(BloodGroup);
                if (data.size() > 0) {
                    dataAdapter = new MyCustomMessageAdapter(BloodBanksearch.this, R.layout.country_info, data);
                    listView.setAdapter(dataAdapter);
                    listView.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(BloodBanksearch.this, "No Blood Bank found", Toast.LENGTH_LONG).show();
                }

    }
    private class MyCustomMessageAdapter extends ArrayAdapter<Donor> {

        private ArrayList<Donor> originalList;

        public MyCustomMessageAdapter(Context context, int textViewResourceId,
                                      ArrayList<Donor> countryList) {
            super(context, textViewResourceId, countryList);

            this.originalList = new ArrayList<Donor>();
            this.originalList.addAll(countryList);
        }


        @Override
        public int getCount() {
            return originalList.size();
        }

        private class ViewHolder {
            TextView  date;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));
            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.country_info, null);

                holder = new ViewHolder();


                holder.date = (TextView) convertView.findViewById(R.id.date);


                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final Donor country = originalList.get(position);
            holder.date.setText(country.getName());
  convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent i= new Intent(BloodBanksearch.this,BloodDetails.class);
          GroupBean.getInstance().setBlood(country.getBloodgroup());
          GroupBean.getInstance().setAddress(country.getAddress());
          GroupBean.getInstance().setBname(country.getName());
          GroupBean.getInstance().setMobile(country.getMobile());
          startActivity(i);
      }
  });

            return convertView;

        }

    }
}
