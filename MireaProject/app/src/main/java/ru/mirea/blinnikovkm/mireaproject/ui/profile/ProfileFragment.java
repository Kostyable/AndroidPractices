package ru.mirea.blinnikovkm.mireaproject.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final SharedPreferences secureSharedPreferences;
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secure_profile_data",
                    masterKeyAlias,
                    requireContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.firstNameEditText.setText(secureSharedPreferences.getString("firstName", ""));
        binding.lastNameEditText.setText(secureSharedPreferences.getString("lastName", ""));
        binding.dateOfBirthEditText.setText(secureSharedPreferences.getString("dateOfBirth", ""));
        binding.emailAddressEditText.setText(secureSharedPreferences.getString("emailAddress", ""));
        binding.maleRadioButton.setChecked(secureSharedPreferences.getBoolean("isMale", true));
        binding.saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = binding.firstNameEditText.getText().toString().trim();
                String lastName = binding.lastNameEditText.getText().toString().trim();
                String dateOfBirth = binding.dateOfBirthEditText.getText().toString().trim();
                String emailAddress = binding.emailAddressEditText.getText().toString().trim();
                boolean isMale = binding.maleRadioButton.isChecked();
                if (firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty() ||
                        emailAddress.isEmpty()) {
                    Toast.makeText(requireContext(), "Пожалуйста, заполните все поля",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = secureSharedPreferences.edit();
                editor.putString("firstName", firstName);
                editor.putString("lastName", lastName);
                editor.putString("dateOfBirth", dateOfBirth);
                editor.putString("emailAddress", emailAddress);
                editor.putBoolean("isMale", isMale);
                editor.apply();
                Toast.makeText(requireContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}