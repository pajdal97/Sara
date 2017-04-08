package com.example.marti.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

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
        String[] commands = new String[]{"turn on", "turn off", "light on", "light off", "switch on", "switch off"};

        System.out.println("OnResult");

        for (String command : results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION))
        {
            command = command.toLowerCase();
            Log.i("SARA", command);
            command = command.replace(" sara ", "");
            for (int i = 0; i < commands.length; i++)
            {
                if (command.contains(commands[i]))
                {
                    command.replace(commands[i], "");
                    Toast.makeText(context.getApplicationContext(), command, Toast.LENGTH_SHORT);
                    System.out.println("COMMAND");
                    if (command.contains("on"))
                        if (command.contains("first"))
                        {
                            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("lightType", "0");
                            editor.putString("switchType", "ON");
                            editor.apply();
                        } else if (command.contains("second"))
                        {
                            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("lightType", "1");
                            editor.putString("switchType", "ON");
                            editor.apply();
                        } else if (command.contains("third"))
                        {
                            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("lightType", "2");
                            editor.putString("switchType", "ON");
                            editor.apply();
                        }

                    //request for light

                    Intent postState = new Intent(context.getApplicationContext(), POSTtoMainServer.class);
                    context.getApplicationContext().startService(postState);
                    context.getApplicationContext().stopService(postState);

                    //request for light
                }
                else if (command.contains("off"))
                {
                    if (command.contains("first"))
                    {
                        SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("lightType", "0");
                        editor.putString("switchType", "ON");
                        editor.apply();
                    } else if (command.contains("second"))
                    {
                        SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("lightType", "1");
                        editor.putString("switchType", "ON");
                        editor.apply();
                    } else if (command.contains("third"))
                    {
                        SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("lightType", "2");
                        editor.putString("switchType", "ON");
                        editor.apply();
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
