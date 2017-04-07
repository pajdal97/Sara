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

public class SpeechRecognition extends Activity implements RecognitionListener
{
    public void startListen()
    {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        speechRecognizer.setRecognitionListener(this);

        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());

        speechRecognizer.startListening(speechRecognizerIntent);
    }

    @Override
    public void onBeginningOfSpeech()
    {
        Log.i("Begin","BeginOfSpeech");
    }

    @Override
    public void onBufferReceived(byte[] arg0)
    {
        Log.i("Speech",arg0.toString());
    }

    @Override
    public void onEndOfSpeech()
    {
        Log.i("End","EndOfSpeech");
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
        // TODO Auto-generated method stub
    }

    @Override
    public void onPartialResults(android.os.Bundle partialResults)
    {
        Log.i("Speech",partialResults.toString());
    }

    @Override
    public void onReadyForSpeech(android.os.Bundle params)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResults(android.os.Bundle results)
    {
        String result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
        Log.i("Result", result);

        startListen();
    }

    @Override
    public void onRmsChanged(float rmsdB)
    {
    }

}
