package by.bsuir.leisure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import by.bsuir.leisure.entities.Event;

/**
 * Created by nastia on 03.05.2016.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = MapFragment.class.getSimpleName();
    private MapView mapView;

    public static Fragment newInstance() {
        return new MapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(53.913574, 27.493634))
                .title("Благотворительный концерт")
                .snippet("Кафе \"Спатканне\", 15.06 в 16:00"));
        map.addMarker(new MarkerOptions().position(new LatLng(53.902957, 27.555020)));
        map.addMarker(new MarkerOptions().position(new LatLng(53.897673, 27.557243)));
        map.addMarker(new MarkerOptions().position(new LatLng(53.907569, 27.605607)));
        map.addMarker(new MarkerOptions().position(new LatLng(53.902520, 27.573411)));
        map.addMarker(new MarkerOptions().position(new LatLng(53.894939, 27.545402)));
        map.addMarker(new MarkerOptions().position(new LatLng(53.919900, 27.540575)));
        LatLng minsk = new LatLng(53.904533, 27.561503);
        CameraPosition camPos = new CameraPosition.Builder().target(minsk).zoom(11).build();
        CameraUpdate cam = CameraUpdateFactory.newCameraPosition(camPos);
        map.animateCamera(cam);
//        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                marker.setTitle("NEW NEW TITLE");
//            }
//        });
    }
}
