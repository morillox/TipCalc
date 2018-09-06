package solo.morillox.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SeekBar;

import java.text.NumberFormat;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener{

    private EditText editTextBillAmount;
    private TextView textViewBillAmount;
    private TextView percenttext;
    private TextView totalbilltext;
    private SeekBar percentchange;

    private double billAmount = 0.0;
    private double percent = 15;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewBillAmount = (TextView)findViewById(R.id.textView_BillAmount);

        editTextBillAmount = (EditText)findViewById(R.id.editText_BillAmount);
        editTextBillAmount.addTextChangedListener((TextWatcher)this);


        percentchange = (SeekBar)findViewById(R.id.seekBar);
        percentchange.setOnSeekBarChangeListener(this);
        percenttext = (TextView)findViewById(R.id.textView_percent);
        percenttext.setText("Tip percent: " + percentchange.getProgress() + " % ");
        totalbilltext = (TextView)findViewById(R.id.textView_total);
        totalbilltext.setText("Total bill: 0.0");

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1,int i2){

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        Log.d("MainActivity", "inside onTextChanged mehtod: charSequence= "+charSequence);
        try {
            billAmount = parseDouble(charSequence.toString())/100;
            Log.d("MainActivity", "BillAmount = " +billAmount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            billAmount = 0;
        }

        textViewBillAmount.setText(currencyFormat.format(billAmount));
        calculate();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        percent = (double)progress;
        percenttext = (TextView)findViewById(R.id.textView_percent);
        calculate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public void calculate(){

        double tip = billAmount * (percent/100);
        double totalbillamount = tip + billAmount;
        percenttext.setText("Tip percent: " + percent + " %  Tip amount: "+ currencyFormat.format(tip));
        totalbilltext.setText("Total bill: "+ currencyFormat.format(totalbillamount));


    }
}
