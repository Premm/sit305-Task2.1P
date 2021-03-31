package com.example.unitconverter_sit305;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, RecyclerViewAdapter.ItemClickListener {

    // store my recyclerview adapter
    RecyclerViewAdapter adapter;

    // store my calculated values (concatenated with types on the end)
    List<String> calculatedValues = new ArrayList<>();

    // valuesType
    String[] valueTypes = { "Metres", "Celsius", "Kilograms"};

    //store my spinner
    Spinner valueTypesSpinner;

    //store my value input field
    EditText inputValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        valueTypesSpinner = (Spinner) findViewById(R.id.valueTypePicker);
        valueTypesSpinner.setOnItemSelectedListener(this);
        inputValueEditText = (EditText) findViewById((R.id.edtTxtInputValue));

        //Creating the ArrayAdapter instance having the value types list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,valueTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        valueTypesSpinner.setAdapter(aa);

        RecyclerView recyclerView = findViewById(R.id.calculatedValues);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, calculatedValues);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        // do nothing here
        //Toast.makeText(getApplicationContext(),valueTypes[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing here
    }

    @Override
    public void onItemClick(View view, int position) {
        //auto generated
    }

    public void findLength(View view) {
        if(valueTypesSpinner.getSelectedItemPosition() == 0 ){
            calculatedValues.clear();
            ArrayList<String> newValues = new ArrayList<>();
            String pattern="###,###.00";
            DecimalFormat df = new DecimalFormat(pattern);

            try {
                float inputValue = Float.parseFloat(inputValueEditText.getText().toString());
                newValues.add(df.format(inputValue * 100.0) + " Centimetres");
                newValues.add(df.format(inputValue * 3.281) + " Feet");
                newValues.add(df.format(inputValue * 39.37) + " Inches");
                calculatedValues.addAll(newValues);

            }catch (final NumberFormatException e) {
                Toast.makeText(getApplicationContext(),"The value entered must be a number.", Toast.LENGTH_LONG).show();
            }

            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(getApplicationContext(),"Please select the correct conversion icon." , Toast.LENGTH_LONG).show();
        }
    }

    public void findTemperature(View view) {
        if(valueTypesSpinner.getSelectedItemPosition() == 1 ){
            calculatedValues.clear();
            ArrayList<String> newValues = new ArrayList<>();
            String pattern="###,###.00";
            DecimalFormat df = new DecimalFormat(pattern);

            try {
                float inputValue = Float.parseFloat(inputValueEditText.getText().toString());
                newValues.add(df.format((inputValue * 9/5) + 32) + " Fahrenheit");
                newValues.add(df.format(inputValue + 273.15) + " Kelvin");
                calculatedValues.addAll(newValues);

            }catch (final NumberFormatException e) {
                Toast.makeText(getApplicationContext(),"The value entered must be a number.", Toast.LENGTH_LONG).show();
            }

            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(getApplicationContext(),"Please select the correct conversion icon." , Toast.LENGTH_LONG).show();
        }
    }

    public void findWeight(View view) {
        if(valueTypesSpinner.getSelectedItemPosition() == 2){
            calculatedValues.clear();
            ArrayList<String> newValues = new ArrayList<>();
            String pattern="###,###.00";
            DecimalFormat df = new DecimalFormat(pattern);

            try {
                float inputValue = Float.parseFloat(inputValueEditText.getText().toString());
                newValues.add(df.format(inputValue * 1000) + " Gram");
                newValues.add(df.format(inputValue * 35.274) + " Ounce");
                newValues.add(df.format(inputValue * 2.205) + " Pound");
                calculatedValues.addAll(newValues);

            }catch (final NumberFormatException e) {
                Toast.makeText(getApplicationContext(),"The value entered must be a number." , Toast.LENGTH_LONG).show();
            }

            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(getApplicationContext(),"Please select the correct conversion icon." , Toast.LENGTH_LONG).show();
        }
    }
}
