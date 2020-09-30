package model

import android.provider.Contacts.Photos




class Results {

    private var business_status: String? = null

    private var geometry: Geometry? = null

    private var icon: String? = null

    private var name: String? = null

    private var opening_hours: OpeningHours? = null

    private var photos: List<Photos>? = null

    private var place_id: String? = null

    private var plus_code: PlusCode? = null

    private var price_level = 0

    private var rating = 0.0

    private var reference: String? = null

    private var scope: String? = null

    private var types: List<String>? = null

    private var user_ratings_total = 0

    private var vicinity: String? = null

    fun setBusiness_status(business_status: String?) {
        this.business_status = business_status
    }

    fun getBusiness_status(): String? {
        return business_status
    }

    fun setGeometry(geometry: Geometry?) {
        this.geometry = geometry
    }

    fun getGeometry(): Geometry? {
        return geometry
    }

    fun setIcon(icon: String?) {
        this.icon = icon
    }

    fun getIcon(): String? {
        return icon
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getName(): String? {
        return name
    }

    fun setOpening_hours(opening_hours: OpeningHours?) {
        this.opening_hours = opening_hours
    }

    fun getOpening_hours(): OpeningHours? {
        return opening_hours
    }

    fun setPhotos(photos: List<Photos>?) {
        this.photos = photos
    }

    fun getPhotos(): List<Photos>? {
        return photos
    }

    fun setPlace_id(place_id: String?) {
        this.place_id = place_id
    }

    fun getPlace_id(): String? {
        return place_id
    }

    fun setPlus_code(plus_code: PlusCode?) {
        this.plus_code = plus_code
    }

    fun getPlus_code(): PlusCode? {
        return plus_code
    }

    fun setPrice_level(price_level: Int) {
        this.price_level = price_level
    }

    fun getPrice_level(): Int {
        return price_level
    }

    fun setRating(rating: Double) {
        this.rating = rating
    }

    fun getRating(): Double
    {
        return rating
    }

    fun setReference(reference: String?) {
        this.reference = reference
    }

    fun getReference(): String? {
        return reference
    }

    fun setScope(scope: String?) {
        this.scope = scope
    }

    fun getScope(): String? {
        return scope
    }

    fun setTypes(types: List<String>?) {
        this.types = types
    }

    fun getTypes(): List<String>? {
        return types
    }

    fun setUser_ratings_total(user_ratings_total: Int) {
        this.user_ratings_total = user_ratings_total
    }

    fun getUser_ratings_total(): Int {
        return user_ratings_total
    }

    fun setVicinity(vicinity: String?) {
        this.vicinity = vicinity
    }

    fun getVicinity(): String? {
        return vicinity
    }
}