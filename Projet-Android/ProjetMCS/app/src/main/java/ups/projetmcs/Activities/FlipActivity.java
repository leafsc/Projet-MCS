package ups.projetmcs.Activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ups.projetmcs.GComponents.PlayButton;
import ups.projetmcs.GComponents.RecordButton;
import ups.projetmcs.R;

public class FlipActivity extends Activity {

    private static final String LOG_TAG = "FlipActivity";
    private static final String NAME_FILE = "faisunflip.wav";
    RecordButton mRecordButton = null;
    PlayButton mPlayButton = null;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.flip_layout);
        setPoliceTitles();
        mRecordButton = (RecordButton) findViewById(R.id.btnRecord);
        mPlayButton = (PlayButton) findViewById(R.id.btnPlay);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupFlip);
        mRecordButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
        mPlayButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
        mRecordButton.setNameFile(NAME_FILE);
        mPlayButton.setNameFile(NAME_FILE);

        TextView tv =(TextView) findViewById(R.id.textViewChoix);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Classic_Robot.otf");
        tv.setTypeface(typeFace);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton radioChecked = (RadioButton) findViewById(checkedId);
                switch (checkedId){
                    case R.id.radioBruite:
                        Log.d(LOG_TAG, "bruite");
                        mRecordButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
                        mPlayButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
                        break;
                    case R.id.radioNonBruite:
                        Log.d(LOG_TAG, "non bruite");
                        mRecordButton.setCorpusFolder(AccueilActivity.CORPUS_NON_BRUITE);
                        mPlayButton.setCorpusFolder(AccueilActivity.CORPUS_NON_BRUITE);
                        break;


                }

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecordButton.getmRecorder() != null) {
            mRecordButton.getmRecorder().release();
            mRecordButton.setmRecorder(null);
        }
        if (mPlayButton.getmPlayer() != null) {
            mPlayButton.getmPlayer().release();
            mPlayButton.setmPlayer(null);
        }
    }

    public void setPoliceTitles(){

        TextView tv =(TextView) findViewById(R.id.textViewChoix);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Classic_Robot.otf");
        tv.setTypeface(typeFace);

        tv =(TextView) findViewById(R.id.textViewCommand);
        typeFace = Typeface.createFromAsset(getAssets(),"fonts/Classic_Robot_Bold.otf");
        tv.setTypeface(typeFace);

    }
}
