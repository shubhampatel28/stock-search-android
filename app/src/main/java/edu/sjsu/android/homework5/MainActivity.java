package edu.sjsu.android.homework5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_clear, button_getQuote;
    private AutoCompleteTextView autoCompleteTextView;
    private Switch switch_autorefresh;
    private ImageView refresh;
    ArrayList<String> autoSuggestions = new ArrayList<>();
    static ArrayList<FavoritesView> rows = new ArrayList<>();
    FavouritesAdapter favouritesAdapter;
    SharedPreferences sharedPref;
    final Handler handler = new Handler();
    public static String API_TOKEN_TIINGO = "1e33ebe32ac12fe52ade5014d756564080b9702e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_clear = (Button) findViewById(R.id.button_clear);
        button_getQuote = (Button) findViewById(R.id.button_GetQuote);
        switch_autorefresh = (Switch) findViewById(R.id.autoRefresh);
        refresh = (ImageView) findViewById(R.id.imageView_refresh);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView_Search);
        button_getQuote.setOnClickListener(this);
        button_clear.setOnClickListener(this);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 10000);
            }

        };

        switch_autorefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_autorefresh.isChecked()) {
                    handler.postDelayed(runnable, 20000);
                } else {
                    handler.removeCallbacks(runnable);
                }
            }
        });
        refresh.setOnClickListener(this);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                String selection = (String) parent.getItemAtPosition(position);
                selection = selection.split("\n")[0];
                autoCompleteTextView.setText(selection);
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText("");
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_GetQuote:

                if (autoCompleteTextView.getText().toString().length() < 1) {

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setMessage("Invalid Entry: Please enter a Stock Name/Symbol");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.show();
                } else {
                    new MyAsyncTask().execute(autoCompleteTextView.getText().toString());
                }
                break;

            case R.id.button_clear:
                autoCompleteTextView.getText().clear();
                break;


        }

    }

    private class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {
        public void execute(String toString) {

        }


        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String JsonURL = "https://api.tiingo.com/tiingo/utilities/search?query=" + strings[0] + "&token=" + API_TOKEN_TIINGO;
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}