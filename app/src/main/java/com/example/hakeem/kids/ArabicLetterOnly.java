package com.example.hakeem.kids;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class ArabicLetterOnly extends AppCompatActivity {



    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter_word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Letter> arabicLettersOnly = new ArrayList<Letter>();
        arabicLettersOnly.add(new Letter(R.drawable.aleef, R.raw.aleef_only));
        arabicLettersOnly.add(new Letter(R.drawable.baaa, R.raw.baaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.taaa, R.raw.taaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.thaaa, R.raw.thaaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.geem, R.raw.geem_only));
        arabicLettersOnly.add(new Letter(R.drawable.haaa, R.raw.haaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.khaaa, R.raw.khaaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.daal, R.raw.daal_only));
        arabicLettersOnly.add(new Letter(R.drawable.zaal, R.raw.zaal_only));
        arabicLettersOnly.add(new Letter(R.drawable.raaa, R.raw.raaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.zay, R.raw.zay_only));
        arabicLettersOnly.add(new Letter(R.drawable.seen, R.raw.seen_only));
        arabicLettersOnly.add(new Letter(R.drawable.sheen,R.raw.sheen_only));
        arabicLettersOnly.add(new Letter(R.drawable.saad, R.raw.saad_only));
        arabicLettersOnly.add(new Letter(R.drawable.daad, R.raw.daad_only));
        arabicLettersOnly.add(new Letter(R.drawable.ttaa, R.raw.ttaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.zaaa, R.raw.zaaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.aeen, R.raw.aeen_only));
        arabicLettersOnly.add(new Letter(R.drawable.gheen, R.raw.gheen_only));
        arabicLettersOnly.add(new Letter(R.drawable.faaa, R.raw.faaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.kkaf, R.raw.kkaf_only));
        arabicLettersOnly.add(new Letter(R.drawable.kaaf, R.raw.kaaf_only));
        arabicLettersOnly.add(new Letter(R.drawable.laam, R.raw.laam_only));
        arabicLettersOnly.add(new Letter(R.drawable.meem, R.raw.meem_only));
        arabicLettersOnly.add(new Letter(R.drawable.noon, R.raw.noon_only));
        arabicLettersOnly.add(new Letter(R.drawable.hhaaa, R.raw.hhaaa_only));
        arabicLettersOnly.add(new Letter(R.drawable.wawo, R.raw.wawo_only));
        arabicLettersOnly.add(new Letter(R.drawable.yaaa, R.raw.yaaa_only));


        // Create an {@link LetterAdaptor}, whose data source is a list of {@link letter}s. The
        // adapter knows how to create list items for each item in the list.
        LetterAdaptor adapter = new LetterAdaptor(this, arabicLettersOnly);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Letter letter = arabicLettersOnly.get(position);


                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(ArabicLetterOnly.this, letter.getAudioResource());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
