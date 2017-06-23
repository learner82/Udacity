
package com.example.android.soccercounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import com.example.android.soccercounter.R;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int yellowCardTeamA = 0;
    int redCardTeamA = 0;
    int cornerTeamA = 0;
    int offsideTeamA = 0;

    int yellowCardTeamB = 0;
    int redCardTeamB = 0;
    int cornerTeamB = 0;
    int offsideTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Adds one goal for Team A.
     */
    public void addGoalForBlue(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the goal of Team A.
     */

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Adds one goal for Team B.
     */
    public void addGoalForRed(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the goal of Team B.
     */

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * adds yellow card for Team A
     */
    public void addYellowCardForBlue(View v) {
        yellowCardTeamA = yellowCardTeamA + 1;
        displayForYellowTeamA(yellowCardTeamA);
    }

    /**
     * Displays the yellow card of Team A.
     */

    public void displayForYellowTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.yellowcard_text_view);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * adds yellow card for Team B
     */
    public void addYellowCardForRed(View v) {
        yellowCardTeamB = yellowCardTeamB + 1;
        displayYellowForTeamB(yellowCardTeamB);
    }

    /**
     * Displays the yellow card of Team B.
     */

    public void displayYellowForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.yellowcard_redteam_text_view);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * adds red card for Team A
     */
    public void addRedCardForBlue(View v) {
        redCardTeamA = redCardTeamA + 1;
        displayRedForTeamA(redCardTeamA);
    }

    /**
     * Displays the red card of Team A.
     */

    public void displayRedForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.redcard_text_view);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * adds red card for Team B
     */
    public void addRedCardForRed(View v) {
        redCardTeamB = redCardTeamB + 1;
        displayRedForTeamB(redCardTeamB);
    }

    /**
     * Displays the red card of Team B.
     */

    public void displayRedForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.redcard_redteam_text_view);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * adds corner for Team A
     */
    public void addCornerForBlue(View v) {
        cornerTeamA = cornerTeamA + 1;
        displayCornerForTeamA(cornerTeamA);
    }

    /**
     * Displays the corner  of Team A.
     */

    public void displayCornerForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.corner_text_view);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * adds corner  for Team B
     */
    public void addCornerForRed(View v) {
        cornerTeamB = cornerTeamB + 1;
        displayCornerForTeamB(cornerTeamB);
    }

    /**
     * Displays the corner of Team B.
     */

    public void displayCornerForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.corner_redteam_text_view);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * adds offside for Team A
     */
    public void addOffsideForBlue(View v) {
        offsideTeamA = offsideTeamA + 1;
        displayOffsideForTeamA(offsideTeamA);
    }

    /**
     * Displays the offside  of Team A.
     */

    public void displayOffsideForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.offside_text_view);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * adds offside  for Team B
     */
    public void addOffsideForRed(View v) {
        offsideTeamB = offsideTeamB + 1;
        displayOffsideForTeamB(offsideTeamB);
    }

    /**
     * Displays the offside of Team B.
     */

    public void displayOffsideForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.offside_redteam_text_view);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Reset both teams score to 0
     */
    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        yellowCardTeamA = 0;
        redCardTeamA = 0;
        cornerTeamA = 0;
        offsideTeamA = 0;

        yellowCardTeamB = 0;
        redCardTeamB = 0;
        cornerTeamB = 0;
        offsideTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayOffsideForTeamB(offsideTeamB);
        displayOffsideForTeamA(offsideTeamA);
        displayCornerForTeamB(cornerTeamB);
        displayRedForTeamB(redCardTeamB);

        displayCornerForTeamA(cornerTeamA);
        displayRedForTeamA(redCardTeamA);
        displayYellowForTeamB(yellowCardTeamB);
        displayForYellowTeamA(yellowCardTeamA);


    }
}