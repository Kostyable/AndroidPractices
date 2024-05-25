package ru.mirea.blinnikovkm.mireaproject.ui.ageimage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentAgeImageBinding;

public class AgeImageFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private boolean isWork = false;
    private Uri imageUri;
    private FragmentAgeImageBinding binding;
    private Button blackAndWhiteButton = null;
    private Button sepiaButton = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAgeImageBinding.inflate(inflater, container, false);
        blackAndWhiteButton = binding.bAndWButton;
        sepiaButton = binding.sepiaButton;
        controlButtons(false);
        int	cameraPermissionStatus = ContextCompat.checkSelfPermission(inflater.getContext(), android.Manifest.permission.CAMERA);
        int	storagePermissionStatus = ContextCompat.checkSelfPermission(inflater.getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            requestPermissions(new String[] {android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }
        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    binding.imageView.setImageURI(imageUri);
                    controlButtons(true);
                }
            }
        };
        ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), callback);
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (isWork) {
                    try {
                        File photoFile = createImageFile();
                        String authorities = inflater.getContext().getApplicationContext().getPackageName() + ".fileprovider";
                        imageUri = FileProvider.getUriForFile(inflater.getContext(), authorities, photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        cameraActivityResultLauncher.launch(cameraIntent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        blackAndWhiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap blackAndWhiteBitmap = convertToBlackAndWhite(imageUri);
                binding.imageView.setImageBitmap(blackAndWhiteBitmap);
            }
        });
        sepiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap sepiaBitmap = convertToSepia(imageUri);
                binding.imageView.setImageBitmap(sepiaBitmap);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }

    private Bitmap convertToBlackAndWhite(Uri imageUri) {
        try {
            Bitmap originalBitmap = BitmapFactory.decodeStream(requireContext().getContentResolver().openInputStream(imageUri));
            int width = originalBitmap.getWidth();
            int height = originalBitmap.getHeight();
            Bitmap blackAndWhiteBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = originalBitmap.getPixel(x, y);
                    int r = Color.red(color);
                    int g = Color.green(color);
                    int b = Color.blue(color);
                    int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                    int newColor = Color.rgb(gray, gray, gray);
                    blackAndWhiteBitmap.setPixel(x, y, newColor);
                }
            }
            return blackAndWhiteBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap convertToSepia(Uri imageUri) {
        try {
            Bitmap originalBitmap = BitmapFactory.decodeStream(requireContext().getContentResolver().openInputStream(imageUri));
            int width = originalBitmap.getWidth();
            int height = originalBitmap.getHeight();
            Bitmap sepiaBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            float[] sepiaMatrix = {
                0.393f, 0.769f, 0.189f,
                0.349f, 0.686f, 0.168f,
                0.272f, 0.534f, 0.131f
            };
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = originalBitmap.getPixel(x, y);
                    float r = Color.red(color) / 255.0f;
                    float g = Color.green(color) / 255.0f;
                    float b = Color.blue(color) / 255.0f;
                    float newR = (r * sepiaMatrix[0]) + (g * sepiaMatrix[1]) + (b * sepiaMatrix[2]);
                    float newG = (r * sepiaMatrix[3]) + (g * sepiaMatrix[4]) + (b * sepiaMatrix[5]);
                    float newB = (r * sepiaMatrix[6]) + (g * sepiaMatrix[7]) + (b * sepiaMatrix[8]);
                    newR = Math.min(1.0f, Math.max(0.0f, newR));
                    newG = Math.min(1.0f, Math.max(0.0f, newG));
                    newB = Math.min(1.0f, Math.max(0.0f, newB));
                    int finalR = (int) (newR * 255.0f);
                    int finalG = (int) (newG * 255.0f);
                    int finalB = (int) (newB * 255.0f);
                    sepiaBitmap.setPixel(x, y, Color.rgb(finalR, finalG, finalB));
                }
            }
            return sepiaBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void controlButtons(boolean flag){
        if (flag) {
            blackAndWhiteButton.setEnabled(true);
            sepiaButton.setEnabled(true);
        }
        else {
            blackAndWhiteButton.setEnabled(false);
            sepiaButton.setEnabled(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }
}