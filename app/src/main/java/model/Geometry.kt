package model

class Geometry {

    private var location: Location? = null

    private var viewport: ViewPort? = null

    fun setLocation(location: Location?) {
        this.location = location
    }

    fun getLocation(): Location? {
        return location
    }

    fun setViewport(viewport: ViewPort?) {
        this.viewport = viewport
    }

    fun getViewport(): ViewPort? {
        return viewport
    }
}