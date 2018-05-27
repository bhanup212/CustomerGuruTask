package bhanupro.customergurutask;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText fullName, phoneNumber, email;
    Button submit;
    Spinner dropDown;
    List<String> cities= new ArrayList<>();

    String selectedCity = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        dropDown = findViewById(R.id.spinner);
        dropDown.setOnItemSelectedListener(this);

        cities.add("Select city");
        cities.add("Bengaluru");
        cities.add("Mumbai");
        //cities.add("Pune");
        cities.add("Delhi");
        cities.add("Kolkata");
        cities.add("Chennai");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cities);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(arrayAdapter);

        submit = findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fullName.getText().length()<1){
                    fullName.setError("Please enter name");
                }else if (email.getText().length()<0){
                    email.setError("Invalid email");
                }else if (phoneNumber.getText().length()<0){
                    phoneNumber.setError("invalid phone number");
                }else if (selectedCity.length()<2){
                    Toast.makeText(MainActivity.this,"Please select city",Toast.LENGTH_LONG).show();
                }else if (fullName.getText().length()>0 && email.getText().length()>0 && phoneNumber.getText().length()>0 &&
                        selectedCity.length()>2 ){
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.dialog_layout);
                    TextView nameTxt = dialog.findViewById(R.id.dialog_name);
                    TextView emailTxt = dialog.findViewById(R.id.dialog_email);
                    TextView phoneTxt = dialog.findViewById(R.id.dialog_phone);
                    TextView cityTxt = dialog.findViewById(R.id.dialog_city);
                    Button okk = dialog.findViewById(R.id.ok);

                    nameTxt.setText(fullName.getText().toString());
                    emailTxt.setText(email.getText().toString());
                    phoneTxt.setText(phoneNumber.getText().toString());
                    cityTxt.setText(selectedCity);
                    dialog.show();

                    okk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            reset();
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }
            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (fullName.getText().length()<1){
                    fullName.setError("Please enter name");
                    fullName.requestFocus();
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isValidEmailId(email.getText().toString())){
                    email.setError("Enter valid email");
                    email.requestFocus();
                }
            }
        });
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isValidPhoneNumber(phoneNumber.getText().toString())){
                    phoneNumber.setError("Enter valid phone number");
                    phoneNumber.requestFocus();
                }
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (i == 0){
            selectedCity = "";
        }else {
           selectedCity = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(MainActivity.this,"Please select city",Toast.LENGTH_LONG).show();
    }

    public boolean isValidEmailId(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();}


    public  boolean isValidPhoneNumber(CharSequence target) {
        return android.util.Patterns.PHONE.matcher(target).matches();}

    public void reset(){
        fullName.setText("");
        phoneNumber.setText("");
        email.setText("");
        dropDown.setSelection(0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        reset();
    }
}
