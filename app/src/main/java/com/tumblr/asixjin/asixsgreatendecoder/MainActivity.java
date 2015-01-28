package com.tumblr.asixjin.asixsgreatendecoder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Switch;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clicked(View v)
    {
        Switch toggle = (Switch) findViewById(R.id.eTog);
        EditText message = (EditText) findViewById(R.id.dialogg);
        EditText numCode = (EditText) findViewById(R.id.encodeNumm);
        TextView endMsg = (TextView) findViewById(R.id.result);

        StringBuilder eMsg = new StringBuilder(message.getText());
        int code;

        try
        {
           code = Integer.parseInt(numCode.getText().toString());
        }
        catch(NumberFormatException e)
        {
           code = 0;
        }


        if(toggle.isChecked())
        {
            endMsg.setText(decode(eMsg, code));
        }
        else
        {
            endMsg.setText(encode(eMsg, code));
        }
    }

    public StringBuilder encode(StringBuilder msg, int shiftValue)
    {
        if(shiftValue > 35 || shiftValue <= 0)
        {
            return new StringBuilder("Invalid number! Please try again!");
        }

        int encodeLength = msg.length() - 1;
        for(int i=0; i <= encodeLength; i++)
        {
            int newCharIndex = Character.digit(msg.charAt(i), 36) + shiftValue;
            if(newCharIndex >= 36)
            {
                newCharIndex -= 36;
            }
            msg.setCharAt(i, Character.forDigit(newCharIndex, 36));
        }
        return msg;
    }

    public StringBuilder decode(StringBuilder msg, int shiftValue)
    {
        if(shiftValue > 35 || shiftValue <= 0)
        {
            return new StringBuilder("Invalid number! Please try again!");
        }
        int encodeLength = msg.length() - 1;
        for(int i=0; i <= encodeLength; i++)
        {
            int newCharIndex = Character.digit(msg.charAt(i), 36) - shiftValue;
            if(newCharIndex < 0)
            {
                newCharIndex += 36;
            }
            msg.setCharAt(i, Character.forDigit(newCharIndex, 36));
        }
        return msg;
    }
}
