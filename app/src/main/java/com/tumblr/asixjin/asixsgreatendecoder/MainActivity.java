package com.tumblr.asixjin.asixsgreatendecoder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Switch;


public class MainActivity extends ActionBarActivity {

    private Button mSubmitButton;
    private Switch mToogle;
    private EditText mMessageField;
    private EditText mShiftValue;
    private TextView mResultMessage;

    private boolean toEncode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mToogle = (Switch) findViewById(R.id.eTog);
        mMessageField = (EditText) findViewById(R.id.dialogg);
        mShiftValue = (EditText) findViewById(R.id.encodeNumm);
        mResultMessage = (TextView) findViewById(R.id.result);

        mToogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toEncode = isChecked;
                if(toEncode){
                    mSubmitButton.setText("ENCODE");
                }else{
                    mSubmitButton.setText("DECODE");
                }
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMsg = mMessageField.getText().toString();
                int code;

                try{
                    code = Integer.parseInt(mShiftValue.getText().toString());
                }catch(NumberFormatException e){
                    code = 0;
                }

                if(toEncode){
                    mResultMessage.setText(encode(eMsg, code));
                }else{
                    mResultMessage.setText(decode(eMsg, code));
                }
            }
        });
    }

    public String encode(String text, int shiftValue) {
        StringBuilder msg =  new StringBuilder(text);
        if(isValidShift(shiftValue)) {
            int encodeLength = msg.length() - 1;
            for(int i=0; i <= encodeLength; i++)
            {
                if(msg.charAt(i) == ' '){
                    continue;
                }
                int newCharIndex = Character.digit(msg.charAt(i), 36) + shiftValue;
                if(newCharIndex >= 36)
                {
                    newCharIndex -= 36;
                }
                msg.setCharAt(i, Character.forDigit(newCharIndex, 36));
            }
            return msg.toString();
        }else{
            return "Invalid number! Please try again!";
        }


    }

    public String decode(String text, int shiftValue) {
        StringBuilder msg = new StringBuilder(text);
        if(isValidShift(shiftValue)){
            int encodeLength = msg.length() - 1;
            for(int i=0; i <= encodeLength; i++)
            {
                if(msg.charAt(i) == ' '){
                    continue;
                }
                int newCharIndex = Character.digit(msg.charAt(i), 36) - shiftValue;
                if(newCharIndex < 0)
                {
                    newCharIndex += 36;
                }
                msg.setCharAt(i, Character.forDigit(newCharIndex, 36));
            }
            return msg.toString();
        }else{
            return "Invalid number! Please try again!";
        }

    }

    public boolean isValidShift(int shiftValue){
        if(shiftValue > 35 || shiftValue <= 0)
        {
            return false;
        }else{
            return true;
        }
    }

    //region Old Unneeded Menu Code I might want later
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    //endregion
}
