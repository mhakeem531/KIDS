package com.example.hakeem.kids;

/**
 * Created by hakeem on 7/27/17.
 */

public class Letter {

    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * for image the the letter
     */
    private int mLetterImageSource;

    /**
     * for image the associated to the letter
     * if mWordImageSource still equal -1 :
     *    that mean the list item will be contain letter only
     */
    private int mWordImageSource = NO_IMAGE_PROVIDED;


    /**
     * for word the associated to the image of letter
     * if mWordSource still equal -1 :
     *    that mean the list item will be contain letter only
     */
    private int mWordSource = NO_IMAGE_PROVIDED;


    /**
     * for audio pronounce the associated to the letter and word
     * */
    private int mAudioFileId;

    public Letter(int LetterID , int ImageID , int WordID , int AudioID)
    {
        mLetterImageSource = LetterID;
        mWordImageSource = ImageID;
        mWordSource = WordID;
        mAudioFileId = AudioID;

    }

    public Letter(int LetterID , int AudioID)
    {
        mLetterImageSource = LetterID;
        mAudioFileId = AudioID;
    }

    public int getLetterImageResource()
    {
        return mLetterImageSource;
    }

    public int getWordImageResource()
    {
        return mWordImageSource;
    }

    public int getWordResource()
    {
        return mWordSource;
    }

    public int getAudioResource()
    {
        return mAudioFileId;
    }

    /**
     * will return true if we use letters only
     * will return false if we use letters with words and words images
     **/
    public boolean letterOnly()
    {
         if(mWordImageSource == -1 && mWordSource == -1)
             return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mLetterImageSource='" + mLetterImageSource + '\'' +
                ", mWordImageSource='" + mWordImageSource + '\'' +
                ", mWordSource=" + mWordSource +
                ", audioFileId=" + mAudioFileId +
                '}';
    }



}
