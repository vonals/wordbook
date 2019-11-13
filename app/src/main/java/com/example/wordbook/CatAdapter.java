package com.example.wordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CatAdapter extends ArrayAdapter<Cat> {
    private int resourceId;
    public CatAdapter(Context context, int textViewResourceId, List<Cat> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Cat cat=getItem(position);
        View view;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view=convertView;
        }

        ImageView catImage=(ImageView)view.findViewById(R.id.cat_image);
        TextView catName=(TextView)view.findViewById(R.id.cat_name);
        TextView catSentence=(TextView)view.findViewById(R.id.cat_sentence);
        TextView catMeaning=(TextView)view.findViewById(R.id.cat_meaning);
        catImage.setImageResource(cat.getImageId());
        catName.setText(cat.getName());
        catSentence.setText(cat.getSentence());
        catMeaning.setText(cat.getMeaning());
        return view;
    }
}
