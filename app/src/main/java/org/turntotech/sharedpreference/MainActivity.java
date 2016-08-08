/*
 * Android provides many ways of storing the data of an application. 
 * One of these ways is by using the Shared Preferences class.
 * Shared Preferences allow you to save and retrieve data in the form of key,value pair.
 * This a simple example of Shared Preferences which allows us to save changes
 * to the background color of the app.
 */

package org.turntotech.sharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {

    private RadioGroup radioColorGroup;
    private RadioButton radioColorButton;
    public static final String prefs_name = "MyPreferencesFile";

    int colorGreen, colorRed, colorBlue, colorGreen_g, colorRed_r, colorBlue_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TurnToTech", "Project Name - SharedPreference");

        colorGreen_g = 150;

        colorRed_r = 180;

        colorBlue_b = 170;

        colorGreen = Color.rgb(0, colorGreen_g, 0);
        colorRed = Color.rgb(colorRed_r,0,0);
        colorBlue = Color.rgb(0,0,colorBlue_b);

        radioColorGroup = (RadioGroup) findViewById(R.id.radioColor);
        radioColorGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            // Called when the checked state of a compound button has changed.
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = radioColorGroup.getCheckedRadioButtonId();
                radioColorButton = (RadioButton) findViewById(selectedId);

			    /*
                 * getSharedPreferences() - Retrieve and hold the contents of the
			     * preferences file 'name', returning a SharedPreferences through 
			     * which you can retrieve and modify its values. 
			     */
                SharedPreferences settings = getSharedPreferences(prefs_name, 0);

                // SharedPreferences.Editor - Interface used for modifying values in a SharedPreferences object.
                SharedPreferences.Editor editor = settings.edit();

                // Set a String value in the preferences editor, to be written back once commit() or apply() are called.
                editor.putString("col", radioColorButton.getText().toString());

                editor.apply();
                setBackground();


            }
        });

        setBackground();

        changeBackground();

    }

    private void changeBackground() {

        radioColorGroup.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){

            @Override
            public void onSwipeRight() {

                // Make GREEN Brighter
                if (colorGreen_g >= 70 && colorGreen_g <= 255) colorGreen_g = colorGreen_g + 20;
                if (colorGreen_g > 255) colorGreen_g = 255;

                // Make RED Brighter
                if (colorRed_r >= 80 && colorRed_r <= 255) colorRed_r = colorRed_r + 20;
                if (colorRed_r > 255) colorRed_r = 255;

                // Make BLUE Brighter
                if (colorBlue_b >= 90 && colorBlue_b <= 255) colorBlue_b = colorBlue_b + 20;
                if (colorBlue_b > 255) colorBlue_b = 255;


                colorGreen = Color.rgb(0, colorGreen_g, 0);
                colorRed = Color.rgb(colorRed_r,0,0);
                colorBlue = Color.rgb(0,0,colorBlue_b);

                setBackground();
            }

            @Override
            public void onSwipeLeft() {

                // Make GREEN Darker
                if (colorGreen_g >= 70 && colorGreen_g <= 255) colorGreen_g = colorGreen_g - 20;
                if (colorGreen_g < 70) colorGreen_g = 70;

                // Make RED Darker
                if (colorRed_r >= 80 && colorRed_r <= 255) colorRed_r = colorRed_r - 20;
                if (colorRed_r < 80) colorRed_r = 80;

                // Make BLUE Darker
                if (colorBlue_b >= 90 && colorBlue_b <= 255) colorBlue_b = colorBlue_b - 20;
                if (colorBlue_b < 90) colorBlue_b = 90;

                colorGreen = Color.rgb(0, colorGreen_g, 0);
                colorRed = Color.rgb(colorRed_r,0,0);
                colorBlue = Color.rgb(0,0,colorBlue_b);

                setBackground();

            }
        });

    }

    /*
     * Change the background.
     */
    private void setBackground() {

        SharedPreferences settings = getSharedPreferences(prefs_name, 0);

        // Retrieve a String value from the preferences. default "Green"
        String color_pref = settings.getString("col", "Green");

        // After retrieving change background color accordingly.
        if (color_pref.equals("Green")) {
            radioColorGroup.setBackgroundColor(colorGreen);
            radioColorGroup.check(R.id.radioGreen);
        }
        if (color_pref.equals("Red")) {
            radioColorGroup.setBackgroundColor(colorRed);
            radioColorGroup.check(R.id.radioRed);
        }
        if (color_pref.equals("Blue")) {
            radioColorGroup.setBackgroundColor(colorBlue);
            radioColorGroup.check(R.id.radioBlue);
        }
    }


}
