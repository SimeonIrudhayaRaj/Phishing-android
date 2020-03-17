package com.example.phishing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;

import com.example.phishing.Assemblers.ViewModelAssembler;
import com.example.phishing.Interfaces.MainView;
import com.example.phishing.Interfaces.ViewModel;

public class MainActivity extends AppCompatActivity implements MainView {
    ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelAssembler.createInstance(this);
        if (getIntent().getData() != null) {
            String url = getIntent().getData().toString();
            viewModel.verifyButtonPressed(url);
        }
    }

    public void verifyButtonClicked(View view) {
        viewModel.verifyButtonPressed(getUrl());
    }

    public void feedbackButtonClicked(View view) {
        viewModel.feedBackButtonPressed(getUrl());
    }

    @Override
    public void showVerifyResult(String message, final String url) {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage("The url is "+ message);
        builder.setTitle("Result");
        builder.setCancelable(true);
        if (message.equals("safe")) {
            builder.setPositiveButton(
                            "Proceed",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {
                                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setPackage("com.android.chrome");
                                    try {
                                        startActivity(intent);
                                    } catch (ActivityNotFoundException ex) {
                                        // Chrome browser presumably not installed and open Kindle Browser
                                        intent.setPackage("com.amazon.cloud9");
                                        startActivity(intent);
                                    }
                                }
                            });

        } else if (message.equals("unSafe")){
            builder.setPositiveButton(
                            "Proceed",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {
                                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setPackage("com.android.chrome");
                                    try {
                                        startActivity(intent);
                                    } catch (ActivityNotFoundException ex) {
                                        // Chrome browser presumably not installed and open Kindle Browser
                                        intent.setPackage("com.amazon.cloud9");
                                        startActivity(intent);
                                    }
                                }
                            });

            builder.setNegativeButton("StaySafe",new DialogInterface
                    .OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog,
                                    int which)
                {
                    finish();
                }
            });
        } else {
            builder.setMessage("OoPs! SOmEthIng wEnT wrOnG!!");

            builder.setNegativeButton("ok",new DialogInterface
                    .OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog,
                                    int which)
                {
                    finish();
                }
            });
        }

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }

    @Override
    public void showFeedBackAlert() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage("Possibility of Phishing ");
        builder.setTitle("Result");
        builder.setPositiveButton(
                "Low",
                new DialogInterface
                        .OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {
                        viewModel.feedbackAlertButtonpressed(false);
                    }
                });

        builder.setNegativeButton("High",new DialogInterface
                .OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which)
            {
                viewModel.feedbackAlertButtonpressed(true);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public String getUrl() {
        EditText url = findViewById(R.id.url);
        return  url.getText().toString().trim();
    }

    @Override
    public void makeInvalidUrlAlert() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage("Enter vaild URL");
        builder.setTitle("Warning!");
        builder.setPositiveButton(
                "ok",
                new DialogInterface
                        .OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}