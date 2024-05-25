package ru.mirea.blinnikovkm.mireaproject.ui.establishments;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import ru.mirea.blinnikovkm.mireaproject.R;
import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentEstablishmentsBinding;

public class EstablishmentsFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION = 300;
    private MapView mapView = null;
    private boolean isWork;
    private FragmentEstablishmentsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstablishmentsBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        Configuration.getInstance().load(requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext()));

        mapView = binding.mapView;
        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(11.0);
        GeoPoint startPoint = new GeoPoint(55.794295, 37.701571);
        mapController.setCenter(startPoint);

        if (ContextCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
            setupMap();
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION);
        }

        return rootView;
    }

    private void setupMap() {
        if (isWork) {
            MyLocationNewOverlay locationNewOverlay =
                    new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), mapView);
            locationNewOverlay.enableMyLocation();
            mapView.getOverlays().add(locationNewOverlay);
        }

        CompassOverlay compassOverlay = new CompassOverlay(requireContext(),
                new InternalCompassOrientationProvider(requireContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        final Context context = requireContext();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.getOverlays().add(scaleBarOverlay);
        addMarker("РТУ МИРЭА", 55.794295, 37.701571);
        addMarker("РТУ МИРЭА",  55.669956, 37.480225);
        addMarker("РТУ МИРЭА", 55.731578, 37.574958);
        addMarker("РТУ МИРЭА", 55.764968, 37.742128);
        addMarker("РТУ МИРЭА (МИТХТ)", 55.661465, 37.476816);
        addMarker("Колледж РТУ МИРЭА", 55.724505, 37.631755);
        addMarker("ВУЦ РТУ МИРЭА", 55.728700, 37.573057);
        addMarker("Филиал РТУ МИРЭА (г. Фрязино)", 55.966839, 38.050608);
    }
    private void addMarker(String desc, double latitude, double longitude) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setTitle(desc);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Drawable icon = ResourcesCompat.getDrawable(getResources(),
                R.drawable.mirea_logo, null);
        marker.setIcon(icon);
        mapView.getOverlays().add(marker);
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                showMarkerInfoDialog(desc, marker.getPosition());
                return true;
            }
        });
        mapView.invalidate();
    }

    private void showMarkerInfoDialog(String title, GeoPoint point) {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_marker_info);
        TextView textViewTitle = dialog.findViewById(R.id.textViewTitle);
        TextView textViewDescription = dialog.findViewById(R.id.textViewDescription);
        textViewTitle.setText(title);
        getAddressFromCoordinates(point.getLatitude(), point.getLongitude(), new GeocodingListener() {
            @Override
            public void onSuccess(String address) {
                textViewDescription.setText(address);
            }

            @Override
            public void onError(String errorMessage) {
                textViewDescription.setText("Ошибка получения адреса: " + errorMessage);
            }
        });
        dialog.show();
    }

    private void getAddressFromCoordinates(double latitude, double longitude, GeocodingListener listener) {
        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon="
                + longitude + "&zoom=18&addressdetails=1";

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject addressObject = response.getJSONObject("address");
                            String address = addressObject.optString("road", "") + ", " +
                                    addressObject.optString("house_number", "") + ", " +
                                    addressObject.optString("suburb", "") + ", " +
                                    addressObject.optString("city", "");
                            listener.onSuccess(address);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("Ошибка при обработке ответа");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError("Ошибка при выполнении запроса");
                    }
                });

        queue.add(request);
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (isWork) {
                setupMap();
            } else {
                Toast.makeText(requireContext(), "Не был получен доступ к геолокации",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}