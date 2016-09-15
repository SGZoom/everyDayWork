package io.netopen.hotbitmapgg.androideverydaypractice.viewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by gzoom on 2016/9/10.
 */
public class CardFragment extends Fragment {
    private CardView cardView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_card,container,false);
        cardView= (CardView) view.findViewById(R.id.pager_cardview);
        cardView.setMaxCardElevation(cardView.getCardElevation()*8);
        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
