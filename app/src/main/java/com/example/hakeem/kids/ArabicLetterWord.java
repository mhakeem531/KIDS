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
//    android:descendantFocusability="blocksDescendants"


public class ArabicLetterWord extends AppCompatActivity {

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

        final ArrayList<Letter> arabicLetters = new ArrayList<Letter>();
        arabicLetters.add(new Letter(R.drawable.aleef, R.drawable.aleef_image, R.drawable.word_aleef, R.raw.aleef));
        arabicLetters.add(new Letter(R.drawable.baaa, R.drawable.baaa_image, R.drawable.word_baaa, R.raw.baaa));
        arabicLetters.add(new Letter(R.drawable.taaa, R.drawable.taaa_image, R.drawable.word_taaa, R.raw.taaa));
        arabicLetters.add(new Letter(R.drawable.thaaa, R.drawable.thaaa_image, R.drawable.word_thaaa, R.raw.thaaa));
        arabicLetters.add(new Letter(R.drawable.geem, R.drawable.geem_image, R.drawable.word_geem, R.raw.geem));
        arabicLetters.add(new Letter(R.drawable.haaa, R.drawable.haaa_image, R.drawable.word_haaa, R.raw.haaa));
        arabicLetters.add(new Letter(R.drawable.khaaa, R.drawable.khaaa_image, R.drawable.word_khaaa, R.raw.khaaa));
        arabicLetters.add(new Letter(R.drawable.daal, R.drawable.daal_image, R.drawable.word_daal, R.raw.daal));
        arabicLetters.add(new Letter(R.drawable.zaal, R.drawable.zaal_image, R.drawable.word_zaal, R.raw.zaal));
        arabicLetters.add(new Letter(R.drawable.raaa, R.drawable.raaa_image, R.drawable.word_raaa, R.raw.raaa));
        arabicLetters.add(new Letter(R.drawable.zay, R.drawable.zay_image, R.drawable.word_zay, R.raw.zay));
        arabicLetters.add(new Letter(R.drawable.seen, R.drawable.seen_image, R.drawable.word_seen, R.raw.seen));
        arabicLetters.add(new Letter(R.drawable.sheen, R.drawable.sheen_image, R.drawable.word_sheen, R.raw.sheen));
        arabicLetters.add(new Letter(R.drawable.saad, R.drawable.saad_image, R.drawable.word_saad, R.raw.saad));
        arabicLetters.add(new Letter(R.drawable.daad, R.drawable.daad_image, R.drawable.word_daad, R.raw.daad));
        arabicLetters.add(new Letter(R.drawable.ttaa, R.drawable.ttaa_image, R.drawable.word_ttaa, R.raw.ttaa));
        arabicLetters.add(new Letter(R.drawable.zaaa, R.drawable.zaaa_image, R.drawable.word_zaaa, R.raw.zaaa));
        arabicLetters.add(new Letter(R.drawable.aeen, R.drawable.aeen_image, R.drawable.word_aeen, R.raw.aeen));
        arabicLetters.add(new Letter(R.drawable.gheen, R.drawable.gheen_image, R.drawable.word_gheen, R.raw.gheen));
        arabicLetters.add(new Letter(R.drawable.faaa, R.drawable.faaa_image, R.drawable.word_faaa, R.raw.faaa));
        arabicLetters.add(new Letter(R.drawable.kkaf, R.drawable.kkaf_image, R.drawable.word_kkaf, R.raw.kkaf));
        arabicLetters.add(new Letter(R.drawable.kaaf, R.drawable.kaaf_image, R.drawable.word_kaaf, R.raw.kaaf));
        arabicLetters.add(new Letter(R.drawable.laam, R.drawable.laam_image, R.drawable.word_laam, R.raw.laam));
        arabicLetters.add(new Letter(R.drawable.meem, R.drawable.meem_image, R.drawable.word_meem, R.raw.meem));
        arabicLetters.add(new Letter(R.drawable.noon, R.drawable.noon_image, R.drawable.word_noon, R.raw.noon));
        arabicLetters.add(new Letter(R.drawable.hhaaa, R.drawable.hhaaa_image, R.drawable.word_hhaaa, R.raw.hhaaa));
        arabicLetters.add(new Letter(R.drawable.wawo, R.drawable.wawo_image, R.drawable.word_wawo, R.raw.wawo));
        arabicLetters.add(new Letter(R.drawable.yaaa, R.drawable.yaaa_image, R.drawable.word_yaaa, R.raw.yaaa));


        // Create an {@link LetterAdaptor}, whose data source is a list of {@link letter}s. The
        // adapter knows how to create list items for each item in the list.
        LetterAdaptor adapter = new LetterAdaptor(this, arabicLetters);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Letter letter = arabicLetters.get(position);


                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(ArabicLetterWord.this, letter.getAudioResource());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });

        // Set a click listener to play the audio when the list item is clicked on


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
