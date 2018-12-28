package com.iecisa.androidseed.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iecisa.androidseed.R;
import com.iecisa.androidseed.domain.SuperHero;
import com.iecisa.androidseed.mvc.heroes.HeroesListViewMvc;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.hero_pic)
    ImageView heroPic;

    @BindView(R.id.hero_name)
    TextView heroName;

    private final HeroesListViewMvc.Listener listener;

    public HeroHolder(View itemView,HeroesListViewMvc.Listener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.listener = listener;
    }

    public void bindHero(final SuperHero hero) {

        //ImageWrapper.loadFromUrlBy4_3Ratio(hero.getPhoto(), heroPic, R.drawable.placeholder);

        heroName.setText(hero.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onHeroClicked(hero);
            }
        });
    }
}
