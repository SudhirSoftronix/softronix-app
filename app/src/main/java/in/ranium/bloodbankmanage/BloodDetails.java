package in.ranium.bloodbankmanage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import in.ranium.bloodbankmanage.model.GroupBean;

/**
 * Created by durwas on 27/03/17.
 */
public class BloodDetails extends AppCompatActivity {
    TextView name,mobile,address,btype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_details);
        name=(TextView)findViewById(R.id.name);
        mobile=(TextView)findViewById(R.id.mobile);
        address=(TextView)findViewById(R.id.address);
        btype=(TextView)findViewById(R.id.btype);
        name.setText("Blood Bank Name:  "+ GroupBean.getInstance().getBname());
        mobile.setText("Mobile No:  "+ GroupBean.getInstance().getMobile());
        address.setText("address:  "+ GroupBean.getInstance().getAddress());
        btype.setText("Blood Group:  "+ GroupBean.getInstance().getBlood());
    }
}
