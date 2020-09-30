package model

class NorthEast {

    private var lat = 0.0

    private var lng = 0.0

    fun setLat(lat: Double) {
        this.lat = lat
    }

    fun getLat(): Double {
        return lat
    }

    fun setLng(lng: Double) {
        this.lng = lng
    }

    fun getLng(): Double {
        return lng
    }
}