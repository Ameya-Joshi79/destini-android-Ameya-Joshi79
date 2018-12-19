package com.londonappbrewery.destini;
/*
We are using this MainActivity2 object as our Main Activity and the launcher activity and the activity_main2.XML for the layout
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    //Reference for our Story Text
    TextView storyTextView;

    //Reference for our top Button
    Button answer1Button;

    //Reference for our bottom button
    Button answer2Button;

    //Variable to track the current story text
    String currentStoryText;

    //Variable to track the current button text of the top button
    String button1Text;

    //Variable to track the current button text of the bottom button
    String button2Text;

    //variable to track the visibility of the top button
    int buttonIsVisible1;

    //variable to track the visibility of the botton button
    int buttonIsVisible2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Retrieving our tracking values from the Bundle object if it exists
        if (savedInstanceState != null){
            /*
            Retrieving the current story text, top button text, bottom  button text and visibility values for the buttons
            if the Activity object is being recreated after previously being destroyed
             */
            currentStoryText = savedInstanceState.getString("STORYTEXT");
            button1Text = savedInstanceState.getString("1STBUTTONTEXT");
            button2Text = savedInstanceState.getString("2NDBUTTONTEXT");
            buttonIsVisible1 = savedInstanceState.getInt("1STBUTTONVISIBLE");
            buttonIsVisible2 = savedInstanceState.getInt("2NDBUTTONVISIBLE");
        }else {
            //Set default values to our tracking variables if the Bundle object is null i.e. our Activity object is created for the
            //very 1st time
            currentStoryText = getResources().getString(R.string.T1_Story);
            button1Text = getResources().getString(R.string.T1_Ans1);
            button2Text = getResources().getString(R.string.T1_Ans2);
            buttonIsVisible1 = View.VISIBLE;
            buttonIsVisible2 = View.VISIBLE;
        }

        //Referencing our TextView object from the XML
        storyTextView = (TextView)findViewById(R.id.T1_story);

        //Setting the text of our TextView object depending on the value stored in the currentStoryText String variable
        storyTextView.setText(currentStoryText);

        //Referencing our top Button object from our layout
        answer1Button = (Button)findViewById(R.id.ans1_button);

        //Setting the text of the top button to the text value stored in the button1Text String
        answer1Button.setText(button1Text);

        //Setting the visibility of the top Button depending on the value stored in the buttonIsVisible1 int variable
        //0 is visivle and 1 is invisible
        answer1Button.setVisibility(buttonIsVisible1);

        //Getting a reference to our botton Button object from our layout
        answer2Button = (Button)findViewById(R.id.ans2_button);

        //Setting the text of this bottom Button object to the text value stored in the button2Text String
        answer2Button.setText(button2Text);

        //Setting the visibility of the top Button depending on the value stored in the buttonIsVisible1 int variable
        //0 is visivle and 1 is invisible
        answer2Button.setVisibility(buttonIsVisible2);


        //Setting a listener for our answer1Button
        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateStoryView(answer1Button.getText().toString());
            }
        });

        //Setting a listener for our answer2Button
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateStoryView(answer2Button.getText().toString());
            }
        });

    }

    //overriding the onPause() method that calls our trackStoryTextAndButtonText() method to record the state of our
    //MainActivity object to the respective variables
    @Override
    protected void onPause() {
        super.onPause();
        //Calling the method to record the state of our Activity object
        trackStoryTextAndButtonText();
    }

    //Oveririding  the onSaveInstanceState() method that gets called when our MainActivity object is going to
    //be destroyed as a result of change in the Device Configuration e.e. Screen orientation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the state of our MainActivity object i.e. it's member variables in the Bundle object using the
        //put*() methods
        outState.putString("STORYTEXT", currentStoryText);
        outState.putString("1STBUTTONTEXT", button1Text);
        outState.putString("2NDBUTTONTEXT", button2Text);
        outState.putInt("1STBUTTONVISIBLE", buttonIsVisible1);
        outState.putInt("2NDBUTTONVISIBLE", buttonIsVisible2);
    }

    //Method that gets called when the user clicks on any of the answer buttons (top or bottom). The method has logic to
    //update our TextView object's text to the text from our String resource file depending on the Button object that is
    //pressed
    private void updateStoryView(String buttonText){

        //Conditional statement to check if the story currently being displayed is the T1_Story String resource text from our
        //String resource file and the text of the Button that is pressed is T1_Ans1
        if ((storyTextView.getText().toString().equals(getResources().getString(R.string.T1_Story))) && buttonText.equals(getResources().getString(R.string.T1_Ans1))){

            //If yes -> update the text of our TextView object to the text from the String resource T3_Story and update the Button texts to
            //T3_Ans1 and T3_Ans2 respectively
            storyTextView.setText(R.string.T3_Story);
            answer1Button.setText(R.string.T3_Ans1);
            answer2Button.setText(R.string.T3_Ans2);

            //Calling the method to track/record the text values of the Buttons and the Story TextView objects and also to
            //record the state of the visibility of the Buttons
            trackStoryTextAndButtonText();

        } else if ((storyTextView.getText().toString().equals(getResources().getString(R.string.T1_Story))) && buttonText.equals(getResources().getString(R.string.T1_Ans2))){

            storyTextView.setText(R.string.T2_Story);
            answer1Button.setText(R.string.T2_Ans1);
            answer2Button.setText(R.string.T2_Ans2);

            trackStoryTextAndButtonText();
        }

        if ((storyTextView.getText().toString().equals(getResources().getString(R.string.T3_Story))) && buttonText.equals(getResources().getString(R.string.T3_Ans1))){

            storyTextView.setText(R.string.T6_End);
            answer1Button.setVisibility(View.INVISIBLE);
            answer2Button.setVisibility(View.INVISIBLE);
            trackStoryTextAndButtonText();

        } else if ((storyTextView.getText().toString().equals(getResources().getString(R.string.T3_Story))) && buttonText.equals(getResources().getString(R.string.T3_Ans2))){

            storyTextView.setText(R.string.T5_End);
            answer1Button.setVisibility(View.INVISIBLE);
            answer2Button.setVisibility(View.INVISIBLE);
            trackStoryTextAndButtonText();
        }

        if ((storyTextView.getText().toString().equals(getResources().getString(R.string.T2_Story))) && buttonText.equals(getResources().getString(R.string.T2_Ans1))){

            storyTextView.setText(R.string.T3_Story);
            answer1Button.setText(R.string.T3_Ans1);
            answer2Button.setText(R.string.T3_Ans2);

            trackStoryTextAndButtonText();

        } else if ((storyTextView.getText().toString().equals(getResources().getString(R.string.T2_Story))) && buttonText.equals(getResources().getString(R.string.T2_Ans2))){


            storyTextView.setText(R.string.T4_End);
            //Setting the visibility of the Buttons to INVISIBLE as we have reached the end of the story and
            //there are no further branches to the story so we hide our Buttons
            answer1Button.setVisibility(View.INVISIBLE);
            answer2Button.setVisibility(View.INVISIBLE);

            //We record the state of our Variables
            trackStoryTextAndButtonText();
        }

    }

    //Declaring the method to record the state of our Text values that are currently being displayed on the
    //TextView object and the visibility of the Buttons
    private void trackStoryTextAndButtonText(){

        currentStoryText = storyTextView.getText().toString();
        button1Text = answer1Button.getText().toString();
        button2Text = answer2Button.getText().toString();
        buttonIsVisible1 = answer1Button.getVisibility();
        buttonIsVisible2 = answer2Button.getVisibility();

    }



}
