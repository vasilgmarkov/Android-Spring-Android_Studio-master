package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;

/**
 * A fragment representing a single Player detail screen.
 * This fragment is either contained in a {@link PlayerListActivity}
 * in two-pane mode (on tablets) or a {@link PlayerDetailActivity}
 * on handsets.
 */
public class PlayerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The player content this fragment is presenting.
     */
    private Player mItem;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlayerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            String id = getArguments().getString(ARG_ITEM_ID);
            mItem = PlayerManager.getInstance(this.getContext()).getPlayer(id);
            assert mItem != null;
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.basket)).setText("Baskets: " + mItem.getBaskets().toString());
            ((TextView) rootView.findViewById(R.id.rebotes)).setText("Rebotes: " + mItem.getRebotes().toString());
            ((TextView) rootView.findViewById(R.id.asistencias)).setText("Asistencias: " + mItem.getAsistencias().toString());
            ((TextView) rootView.findViewById(R.id.fechaDetail)).setText("Fecha de nacimiento: " + mItem.getFechaNacimiento().toString());
            ((TextView) rootView.findViewById(R.id.pos)).setText("Posicion: " + mItem.getPos());
            ((TextView) rootView.findViewById(R.id.equipo)).setText("Posicion: " + mItem.getEquipo().getName());

            ((TextView) rootView.findViewById(R.id.UpdateButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),UpdatePlayerActivity.class);
                    i.putExtra("id", mItem.getId().toString());
                    i.putExtra("name", mItem.getName());
                    i.putExtra("basket", mItem.getBaskets().toString());
                    i.putExtra("rebotes", mItem.getRebotes().toString());
                    i.putExtra("asistencias", mItem.getAsistencias().toString());
                    i.putExtra("posicion", mItem.getPos());
                    i.putExtra("fechaNacimiento", mItem.getFechaNacimiento());
                    startActivity(i);
                }
            });

        }

        return rootView;
    }
}
