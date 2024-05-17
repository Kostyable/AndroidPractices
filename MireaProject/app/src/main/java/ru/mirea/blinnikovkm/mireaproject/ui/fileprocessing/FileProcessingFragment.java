package ru.mirea.blinnikovkm.mireaproject.ui.fileprocessing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.blinnikovkm.mireaproject.R;
import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentFileProcessingBinding;

public class FileProcessingFragment extends Fragment {
    private FragmentFileProcessingBinding binding;

    public FileProcessingFragment() {}

    public static FileProcessingFragment newInstance(String param1, String param2) {
        FileProcessingFragment fragment = new FileProcessingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFileProcessingBinding.inflate(inflater, container, false);
        binding.encodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = binding.fileNameEditText.getText().toString().trim();
                String fileText = binding.fileTextEditText.getText().toString();
                if (fileName.isEmpty() || fileText.isEmpty()) {
                    createEncodePopup(fileName, "Пожалуйста, заполните все поля");
                    return;
                }
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(path, fileName);
                try	{
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                    OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                    output.write(fileText);
                    output.close();
                } catch (IOException e) {
                    Log.w(getClass().getSimpleName(), "Ошибка записи " + file, e);
                }
                byte[] encryptedText = getEncrypted(fileText);
                try	{
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                    fileOutputStream.write(new String(encryptedText, StandardCharsets.UTF_8).getBytes());
                } catch (IOException e) {
                    Log.w(getClass().getSimpleName(), "Ошибка записи " + file, e);
                }
                createEncodePopup(fileName, String.format("Файл зашифрован и сохранен по пути Documents/"
                        + fileName));
            }
        });

        binding.decodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = binding.fileNameEditText.getText().toString().trim();
                if (fileName.isEmpty()) {
                    Toast.makeText(requireContext(), "Пожалуйста, введите имя файла",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(path, fileName);
                if (!file.exists()) {
                    Toast.makeText(requireContext(), "Файла с таким именем не существует",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String stringLines = "";
                try	{
                    FileInputStream fileInputStream	= new FileInputStream(file.getAbsoluteFile());
                    InputStreamReader inputStreamReader	= new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    List<String> lines = new ArrayList<String>();
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        lines.add(line);
                        line = reader.readLine();
                    }
                    stringLines = String.join("\n", lines);
                } catch (Exception e) {
                    Log.w(getClass().getSimpleName(), "Ошибка чтения " + file, e);
                }
                String decryptedText = getDecrypted(stringLines);
                binding.fileNameEditText.setText(file.getName());
                binding.fileTextEditText.setText(decryptedText);
                Toast.makeText(requireContext(), "Файл расшифрован успешно",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    private byte[] getEncrypted(String text) {
        try {
            return Base64.encode(text.getBytes(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }
    private String getDecrypted(String content) {
        try {
            return new String(Base64.decode(content, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void createEncodePopup(String fileName, String message) {
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.view_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, 600, 600, true);
        TextView messageText = popupView.findViewById(R.id.messageText);
        messageText.setText(message);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}