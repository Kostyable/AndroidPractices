package ru.mirea.blinnikovkm.mireaproject.ui.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {

    private FragmentWebViewBinding binding;

    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWebViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl("https://www.google.ru/");
        binding.webView.getSettings().setJavaScriptEnabled(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}