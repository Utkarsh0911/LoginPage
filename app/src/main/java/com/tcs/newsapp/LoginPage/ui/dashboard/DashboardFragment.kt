package com.tcs.newsapp.LoginPage.ui.dashboard

import Remote.IGoogleApiService
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.tcs.newsapp.LoginPage.R


import common.common
import kotlinx.android.synthetic.main.activity_maps.*
import model.MyPlaces
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class DashboardFragment : Fragment(), OnMapReadyCallback {
    lateinit var mMapView: MapView
    private lateinit var mLastLocation: Location
    private var latitude: Double = 0.0

    private var longitude: Double = 0.0
    private var marker: Marker? = null
    lateinit var mService: IGoogleApiService
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    internal var currentPlace: MyPlaces? = null
    private lateinit var locationRequest: LocationRequest

    companion object {
        private val MY_PERMISSION_CODE: Int = 1000

    }

    private lateinit var dashboardViewModel: DashboardViewModel
    private var mMap: GoogleMap? = null

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        mService = common.googleApiServices

        buildLocationRequest()
        buildLocationCallback()
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )


        mMapView = root.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)

        mMapView.onResume()
        mMapView.getMapAsync(this)


        return root
    }

    private fun nearbyPlaces(typePlace: String) {
        mMap!!.clear()

        val url = getUrl(latitude, longitude, typePlace)
        mService.getNearbyPlaces(url)
            .enqueue(object : Callback<MyPlaces> {
                override fun onFailure(call: Call<MyPlaces>?, t: Throwable?) {
                    Toast.makeText(requireContext(), "" + t!!.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<MyPlaces>?, response: Response<MyPlaces>?) {
                    currentPlace = response!!.body()
//var markerOptions:MarkerOptions=MarkerOptions()
                    if (response.isSuccessful) {
                        for (i in 0 until response.body()!!.getResults()!!.size) {

                            val googleplace = response.body()!!.getResults()!![i]
                            val lat = googleplace.getGeometry()!!.getLocation()!!.getLat()
                            val lng = googleplace.getGeometry()!!.getLocation()!!.getLng()
                            val placeName = googleplace.getName()
                            val latlng = LatLng(lat, lng)
                            val markerOptions = MarkerOptions().position(latlng)
                                .title(placeName)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                .snippet(i.toString())
                            mMap!!.addMarker(markerOptions)
                            mMap!!.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        latitude,
                                        longitude
                                    ), 14.0F
                                )
                            )

                        }


                    }

                }

            })
    }


    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                mLastLocation = p0!!.locations.get(p0.locations.size - 1)
                if (marker != null) {
                    marker!!.remove()
                }

                latitude = mLastLocation.latitude
                longitude = mLastLocation.longitude
                val latLng = LatLng(latitude, longitude)
                val markerOptions = MarkerOptions().position(latLng).title("postion")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))

//          if(mMap!=null)
//              MapModel.setmMap(mMap)

                if (mMap != null) {
                    nearbyPlaces("ATM")
                    marker = mMap!!.addMarker(markerOptions)
                    mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0F))
                } else {

                    val ft =
                        fragmentManager!!.beginTransaction()
                    if (Build.VERSION.SDK_INT >= 26) {
                        ft.setReorderingAllowed(false)
                    }
                    ft.detach(this@DashboardFragment).attach(this@DashboardFragment).commit()
                }


            }

        }

    }

    override fun onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onStop()
    }

    private fun buildLocationRequest() {

        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }


    private fun getUrl(latitude: Double, longitude: Double, typePlace: String): String {
        val googlePlaceUri =
            StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$latitude,$longitude&radius=3000&types=$typePlace&key=AIzaSyAyao_OSrICWqcCMo3XUL9qQurWAYq0yJM")

        return googlePlaceUri.toString()
    }
}




