package ru.mirea.blinnikovkm.resultapifragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {
    private FragmentListener listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try	{
            listener = (FragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement onSomeEventListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ImageView image = view.findViewById(R.id.imageView);
        image.setOnClickListener(view1 -> listener.sendResult("image pushed"));
        return view;
    }
}
