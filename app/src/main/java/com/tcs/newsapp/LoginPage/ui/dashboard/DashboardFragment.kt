package com.tcs.newsapp.LoginPage.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tcs.newsapp.LoginPage.R
import com.tcs.newsapp.LoginPage.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_maps.*


class DashboardFragment : Fragment(), OnMapReadyCallback {
    lateinit var mMapView: MapView


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mMap: GoogleMap

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


//        mMapView = root.findViewById(R.id.mapView)
//        mMapView.onCreate(savedInstanceState);
//        mMapView.getMapAsync(this); //this is important
        // Gets the MapView from the XML layout and creates it

        // Gets the MapView from the XML layout and creates it
        mMapView = root.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)

        mMapView.onResume()
        mMapView.getMapAsync(this)

        return root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap=googleMap
        val sydney=LatLng(27.2048,77.4975)
        mMap.addMarker(MarkerOptions().position(sydney).title("sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0F))
    }
}




