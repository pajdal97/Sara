package com.example.marti.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;

public class SpeechRecognition implements RecognitionListener
{
    Context context;
    public void startListen()
    {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context.getApplicationContext());
        speechRecognizer.setRecognitionListener(this);

        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());

        speechRecognizer.startListening(speechRecognizerIntent);
    }

    @Override
    public void onBeginningOfSpeech()
    {
        //System.out.println("onEvent");
    }

    @Override
    public void onBufferReceived(byte[] arg0)
    {
        //System.out.println("onEvent");
    }

    @Override
    public void onEndOfSpeech()
    {
        System.out.println("onEndOfSpeech");
    }

    @Override
    public void onError(int arg0)
    {
        if(arg0 == 7)
            startListen();
        else
            Log.e("SpeechError", String.valueOf(arg0));

    }

    @Override
    public void onEvent(int arg0, Bundle arg1)
    {
        //System.out.println("onEvent");
    }

    @Override
    public void onPartialResults(android.os.Bundle partialResults)
    {
        //System.out.println("onPartialResults");
    }

    @Override
    public void onReadyForSpeech(android.os.Bundle params)
    {
        //System.out.println("onReadyForSpeech");
    }

    @Override
    public void onResults(android.os.Bundle results)
    {
        String fResult = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0).toLowerCase();
        String[] commands = new String[] { "turn on", "turn off", "light on", "light off", "switch on", "switch off" };

        System.out.println("OnResult");

        for (String result : results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) )
        {
            result = result.toLowerCase();
            if (result.contains("sara"))
            {
                Log.i("SARA",result);
                String command = result.replace(" sara ", "");
                for (int i = 0; i < commands.length; i++)
                {
                    if (command.contains(commands[i]))
                    {
                        command.replace(commands[i],"");
                        Toast.makeText(context.getApplicationContext(),command,Toast.LENGTH_SHORT);
                        System.out.println("COMMAND");
                    }
                }
            }
        }

        startListen();
    }

    @Override
    public void onRmsChanged(float rmsdB)
    {
    }
}
