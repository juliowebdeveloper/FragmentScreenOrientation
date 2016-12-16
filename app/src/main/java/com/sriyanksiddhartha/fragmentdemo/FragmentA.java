package com.sriyanksiddhartha.fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentA extends Fragment {

	private static final String TAG = FragmentA.class.getSimpleName();

    private Button button;
    private TextView textView;
    int score;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_a, container, false);
		Log.i(TAG, "onCreateView()");
            button = (Button) view.findViewById(R.id.button);
            textView = (TextView) view.findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    textView.setText("Dummy Text");
                button.setText("Logout");
                    score=45;
                Toast.makeText(getActivity(), "Score : "+ score, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            score = savedInstanceState.getInt("SCORE");
        }else {
            score = 0;
        }
        Toast.makeText(getActivity(), "Score: "+ score, Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("SCORE", score);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstance");
    }
}
