package com.example.hakeem.kids;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import static com.example.hakeem.kids.R.id.word;

/**
 * Created by hakeem on 7/27/17.
 */

public class LetterAdaptor extends ArrayAdapter<Letter> {


    /**
     * Create a new {@link LetterAdaptor} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param letters is the list of {@link Letter}s to be displayed.
     */
    public LetterAdaptor(Context context, ArrayList<Letter> letters) {
        super(context, 0, letters);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Letter currentLetter = getItem(position);


        ImageView letterImage = (ImageView) listItemView.findViewById(R.id.letter_image);
       // letterImage.setImageResource(currentLetter.getLetterImageResource());

        ImageView wordImage = (ImageView) listItemView.findViewById(R.id.word_image);
       // wordImage.setImageResource(currentLetter.getWordImageResource());

        ImageView word = (ImageView) listItemView.findViewById(R.id.word);
      //  word.setImageResource(currentLetter.getWordResource());


        if(currentLetter.letterOnly())
        {
            word.setVisibility(View.GONE);
            letterImage.setVisibility(View.GONE);

            wordImage.setImageResource(currentLetter.getLetterImageResource());
            wordImage.setVisibility(View.VISIBLE);

        }else{

            letterImage.setImageResource(currentLetter.getLetterImageResource());
            letterImage.setVisibility(View.VISIBLE);

            wordImage.setImageResource(currentLetter.getWordImageResource());
            wordImage.setVisibility(View.VISIBLE);

            word.setImageResource(currentLetter.getWordResource());
            word.setVisibility(View.VISIBLE);

        }

        return listItemView;
    }
}
