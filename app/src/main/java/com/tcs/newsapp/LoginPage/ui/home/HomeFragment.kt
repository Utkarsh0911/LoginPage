package com.tcs.newsapp.LoginPage.ui.home

import Remote.IGoogleApiService
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Build
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.internal.service.Common
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.tcs.newsapp.LoginPage.R
import common.common
import kotlinx.android.synthetic.main.fragment_home.*
import model.MapModel

import model.MyPlaces
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder
import android.util.Log.d as d1


class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap



    private  var latitude: Double = 0.0

    private    var longitude: Double = 0.0
    private lateinit var mLastLocation: Location
    private  var marker:Marker?=null
    private lateinit var homeViewModel: HomeViewModel

    lateinit var mService:IGoogleApiService
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    internal var currentPlace: MyPlaces ?=null
    private lateinit var locationRequest: LocationRequest
companion object{
    private val MY_PERMISSION_CODE:Int=1000
    lateinit var mMapView: MapView
}

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

mService=common.googleApiServices
          if(checkLocationPermission()){
              buildLocationRequest()
              buildLocationCallback()
              fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(requireContext())
              fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())

          }
           else
          {
              buildLocationRequest()
              buildLocationCallback()
              fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(requireContext())
              fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())

          }




       mMapView = root.findViewById(R.id.mapView)
       mMapView.onCreate(savedInstanceState)

mMapView.onResume()
        mMapView.getMapAsync(this)

   // nearbyPlaces("hospital")
     return root
    }

    private fun nearbyPlaces(typePlace:String)
    {
         mMap.clear()
Log.d("NEARBY_PLACES_mMap",""+mMap)
        val url=getUrl(latitude,longitude,typePlace)
        mService.getNearbyPlaces(url)
            .enqueue(object :Callback<MyPlaces>{
                override fun onFailure(call: Call<MyPlaces>?, t: Throwable?) {
                    Toast.makeText(requireContext(),""+ t!!.message,Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<MyPlaces>?, response: Response<MyPlaces>?) {
                    currentPlace= response!!.body()
                    val markerOptions:MarkerOptions?=null
                    if(response.isSuccessful)
                    {
                        for (i in 0 until response.body()!!.getResults()!!.size)
                        {

                            val googleplace= response.body()!!.getResults()!![i]
                            val lat= googleplace.getGeometry()!!.getLocation()!!.getLat()
                            val lng= googleplace.getGeometry()!!.getLocation()!!.getLng()
                            val placeName=googleplace.getName()
                            val latlng=LatLng(lat,lng)
                            markerOptions!!.position(latlng)
                            markerOptions.title(placeName)


                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_local_hospital))

                            markerOptions.snippet(i.toString())


                        }
                        mMap.addMarker(markerOptions)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude,longitude), 16.0F))

                    }

                }

            })
    }


    private fun buildLocationCallback() {
   locationCallback=object :LocationCallback(){
       override fun onLocationResult(p0: LocationResult?) {
           mLastLocation= p0!!.locations.get(p0.locations.size-1)
           if(marker!=null)
           {
              marker!!.remove()
           }

           latitude=mLastLocation.latitude
           longitude=mLastLocation.longitude
 val latLng=LatLng(latitude,longitude)
           val markerOptions=MarkerOptions().position(latLng).title("postion").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

//          if(mMap!=null)
//              MapModel.setmMap(mMap)
           marker= mMap.addMarker(markerOptions)
           mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0F))



       }
   }
    }


    override fun onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onStop()
    }
    private fun buildLocationRequest() {

        locationRequest= LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=5000
locationRequest.fastestInterval=3000
        locationRequest.smallestDisplacement=10f
    }


    private fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(

                        android.Manifest.permission.ACCESS_FINE_LOCATION

                    ), MY_PERMISSION_CODE
                )
             else
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(

                        android.Manifest.permission.ACCESS_FINE_LOCATION

                    ), MY_PERMISSION_CODE
                )
                return false
        }
else return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode)
        {
            MY_PERMISSION_CODE->
            {

                if(grantResults.size>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                if (checkLocationPermission())
                {
                    mMap.isMyLocationEnabled=true
                }
                }
                else
                {
                    Toast.makeText(requireContext(),"PERMISSION DENIED",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }






    override fun onMapReady(googleMap: GoogleMap) {
      mMap=googleMap

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

mMap.isMyLocationEnabled=true

            }else
            {

                mMap.isMyLocationEnabled=true
                mMap.uiSettings.isZoomControlsEnabled=true
            }

        }

//        val sydney=LatLng(27.2048,77.4975)
//        mMap.addMarker(MarkerOptions().position(sydney).title("sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0F))

        }





    private fun getUrl(latitude: Double, longitude: Double, typePlace: String): String {
val googlePlaceUri=StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$latitude,$longitude&radius=10000&types=$typePlace&key=AIzaSyAyao_OSrICWqcCMo3XUL9qQurWAYq0yJM")

   return googlePlaceUri.toString()
    }

}





