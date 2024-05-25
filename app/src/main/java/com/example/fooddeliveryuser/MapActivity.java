package com.example.fooddeliveryuser;

import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import java.util.Arrays;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private PlacesClient placesClient;
    private EditText placesEditText;
    private Marker selectedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize Places SDK
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_api));
        placesClient = Places.createClient(this);

        // Initialize map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        // Initialize EditText
        placesEditText = findViewById(R.id.placesEditText);
        placesEditText.setOnEditorActionListener((v, actionId, event) -> {
            // Perform search when the user presses the Enter key
            searchPlaces(v.getText().toString());
            return false;
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Handle marker click to adjust its position
        mMap.setOnMarkerClickListener(marker -> {
            selectedMarker = marker;
            return false;
        });

        // Move camera to a default location
        LatLng defaultLocation = new LatLng(0, 0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f));
    }

    private void searchPlaces(String query) {
        // Define the fields to be returned for each place
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

        // Create a new session token
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

        // Create a request object
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setSessionToken(token)
                .setQuery(query)
                .setTypeFilter(TypeFilter.ADDRESS)  // Optional: Specify a type filter
                .setCountry("US")  // Optional: Specify a country code
                .build();

        // Perform autocomplete predictions request
    }

}
