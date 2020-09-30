package model

class ViewPort {


    private var northeast: NorthEast? = null

    private var southwest: Southwest? = null

    fun setNortheast(northeast: NorthEast?) {
        this.northeast = northeast
    }

    fun getNortheast(): NorthEast? {
        return northeast
    }

    fun setSouthwest(southwest: Southwest?) {
        this.southwest = southwest
    }

    fun getSouthwest(): Southwest? {
        return southwest
    }
}