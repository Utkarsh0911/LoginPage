package model

import com.google.android.gms.maps.GoogleMap

class MapModel{
companion object {
    private lateinit var mMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    fun getmMap(): GoogleMap {
        return mMap;
    }

    fun setmMap(mMap: GoogleMap) {
        this.mMap = mMap
    }


    fun getLatitude(): Double {
        return latitude;
    }

    fun setLatitude(latitude: Double) {
        this.latitude = latitude
    }

    fun getLongitude(): Double {
        return longitude;
    }

    fun setLongitude(longitude: Double) {
        this.longitude = longitude
    }

}
}