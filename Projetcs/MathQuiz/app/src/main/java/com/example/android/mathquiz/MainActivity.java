package com.example.android.mathquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View.OnClickListener;

import static android.R.attr.checked;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    int results ;
    int results1 ;
    int results2 ;
    int results3 ;
    int results4 ;

    CheckBox answ1Q3;
    CheckBox answ2Q3;
    CheckBox answ3Q3;
    CheckBox answ4Q3;
    private RadioGroup radioQuestion1;
    private RadioButton radio1Button;
    private RadioGroup radioQuestion2;
    private RadioButton radio2Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answ1Q3 = (CheckBox) findViewById(R.id.answer1_question3);
        answ2Q3 = (CheckBox) findViewById(R.id.answer2_question3);
        answ3Q3 = (CheckBox) findViewById(R.id.answer3_question3);
        addListenerOnButton();
        addListenerOnButton2();
    }

    // assing result on the correct answer of the first question
    public void addListenerOnButton() {
        radioQuestion1 = (RadioGroup) findViewById(R.id.radio_question1);
        Button btnDisplay1 = (Button) findViewById(R.id.answer_1_question_1);
        btnDisplay1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioQuestion1.getCheckedRadioButtonId();
                radio1Button = (RadioButton) findViewById(selectedId);
                results1 = 1;
            }

        });
    }

    //checking which button is checked
    public void onRadioButtonClicked(View view) {
        // is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            // Show an instruction message as a toast
            makeText(this, "Only one answer is correct", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        switch (view.getId()) {
            case R.id.answer_1_question_1:
                if (checked)
                    // correct answer
                    return;
        }
    }


    // assign value to correct answer of question2
    public void addListenerOnButton2() {
        final RadioGroup radioQuestion2 = (RadioGroup) findViewById(R.id.radio_question2);
        Button btnDisplay2 = (Button) findViewById(R.id.answer_3_question_2);
        btnDisplay2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioQuestion2.getCheckedRadioButtonId();
                RadioButton radio2Button = (RadioButton) findViewById(selectedId);
                results2 = 1;
            }

        });
    }

    //see which answer was checked
    public void onRadio2ButtonClicked(View view) {
        // is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            // Show an instruction message as a toast
            makeText(this, "Only one answer is correct", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }


        switch (view.getId()) {
            case R.id.answer_3_question_2:
                if (checked)
                    // correct answer
                    break;
        }
    }

    /**
     * This method is called when the submit button is clicked
     */

    public void submitForm(View view) {
        //getting the name andsurname of user
        EditText nameField = (EditText) findViewById(R.id.name_surname_add);
        String name = nameField.getText().toString();

        //figure out if the user answer the question 3 with answer 1
        CheckBox answer1Q3 = (CheckBox) findViewById(R.id.answer1_question3);
        boolean hasanswered1Q3 = answer1Q3.isChecked();

        //figure out if the user answer the question 3 with answer 2
        CheckBox answer2Q3 = (CheckBox) findViewById(R.id.answer2_question3);
        boolean hasanswered2Q3 = answer2Q3.isChecked();
        //figure out if the user answer the question 3 with answer 3
        CheckBox answer3Q3 = (CheckBox) findViewById(R.id.answer3_question3);
        boolean hasanswered3Q3 = answer3Q3.isChecked();

        //calculating total results
        int results = calculateResults(hasanswered1Q3, hasanswered2Q3, hasanswered3Q3) + results1 + results2;
        //sending dats via email
        String finals = createSummary(name, results);
        String adresses = "anodos@windowslive.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:anodos@windowslive.com"));
        intent.putExtra(intent.EXTRA_EMAIL, adresses);
        intent.putExtra(intent.EXTRA_SUBJECT, "Test score of " + name);
        intent.putExtra(intent.EXTRA_TEXT, finals);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //Display the total score as toast on submit
        makeText(this,"Current score " + ( calculateResults(hasanswered1Q3, hasanswered2Q3, hasanswered3Q3)+ results1 + results2)+ "/6", Toast.LENGTH_SHORT).show();


    }

    // creating the message of the email
    private String createSummary(String name, int results) {
        String finals = "Name Surname: " + name;
        finals += "\n Your results are: " + results + "/6";
        finals += "\nThank you for your efforts!";
        return finals;

    }

    /*
    * Calculate the results of question 3
    * @ return total results
    * @param wasAsn1Q3
    * @param wasAsn2Q3
    * @param wasAsn3Q3
    *
     */


    private int calculateResults(boolean wasAns1Q3, boolean wasAns2Q3, boolean wasAns3Q3) {

        //getting the answer of question 4
        EditText answer4 = (EditText) findViewById(R.id.answer_question_4);
        String ans4Q4 = answer4.getText().toString();
        // its not correct i was trying to check if the data was filled and if the string matches
        // the answer for question 4

        if (ans4Q4.equals("TRUE")|| ans4Q4.equals("true"))
            results4 = 1;
        else
            results4 = 0;

        // add 3 in the results if Question 3 is correct
        if (wasAns1Q3 && wasAns2Q3 && wasAns3Q3)

            results3 = 3;

            else
                results3=0;


         if (! (wasAns1Q3 && wasAns2Q3 && wasAns3Q3))


            results3 =0;


            return results3 + results4;
    }


}
