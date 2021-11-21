package com.example.medicinescheduler;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associating variables with their corresponding TextInputs and buttons from the UI
        EditText nameInput = (EditText) findViewById(R.id.name);
        EditText passInput = (EditText) findViewById(R.id.Password);
        Button sumbitButton = (Button) findViewById(R.id.submitButton);

        // If the submit button is clicked
        sumbitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Store whatever the user inputted
                name = nameInput.getText().toString();
                password = passInput.getText().toString();

                // throw a toast (similar to printing in console)
                Toast.makeText(MainActivity.this, "Thanks for inputting your information. YOU GOT SCAMMED", Toast.LENGTH_SHORT).show();
            }
        });


    }
}