package in.ranium.bloodbankmanage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in.ranium.bloodbankmanage.model.DbDonor;
import in.ranium.bloodbankmanage.model.Donor;

/**
 * Created by Durvas on 3/6/2016.
 */
public class BloodDonorList extends ActionBarActivity {
    DbDonor dbDonor;
    TextView name;
    ArrayList<Donor> data;
    ListView listView;
    MyCustomMessageAdapter dataAdapter;
    TextView delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blooddonorlist);
        listView = (ListView) findViewById(R.id.list);
        dbDonor = new DbDonor(getApplicationContext());
        name = (TextView) findViewById(R.id.name);
        data = dbDonor.getindividualuserid();
        dataAdapter = new MyCustomMessageAdapter(BloodDonorList.this, R.layout.country_info_two, data);
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);


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
            TextView name, date, date2, mob, dateofPrev,delete;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));
            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.country_info_two, null);

                holder = new ViewHolder();

                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.date2 = (TextView) convertView.findViewById(R.id.date2);
                holder.mob = (TextView) convertView.findViewById(R.id.mob);
                holder.dateofPrev = (TextView) convertView.findViewById(R.id.dateofPrev);
                holder.delete = (TextView) convertView.findViewById(R.id.delete);

                convertView.setTag(holder);
               // delete.setTag(position); //For passing the list item index


            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final Donor country = originalList.get(position);
            holder.date.setText(country.getName());
            holder.date2.setText(country.getBloodgroup());
            holder.name.setText(country.getAddress());
            holder.mob.setText(country.getMobile());
          //  holder.dateofPrev.setText(country.getDateofpreviousdonation());
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                   dbDonor.deletegroup(country.getId());
                   // dataAdapter.notifyDataSetChanged();
                   // listView.invalidate();
showErrorAlert();
                }
            });
            return convertView;

        }

    }
    public void showErrorAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(BloodDonorList.this);
        TextView textMsg = new TextView(BloodDonorList.this);
        textMsg.setText("Donor is Deleted Successfully.");
        textMsg.setPadding(10, 10, 10, 10);
        textMsg.setGravity(Gravity.CENTER);
        textMsg.setTextSize(18);
        builder.setView(textMsg);
        builder.setTitle("Donor Deletion");
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        Intent intent = new Intent(BloodDonorList.this, BloodDonorList.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                        finish();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
