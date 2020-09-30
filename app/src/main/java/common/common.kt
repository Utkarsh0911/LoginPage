package common

import Remote.IGoogleApiService
import Remote.RetrofitClient

object common {

    private val GOOGLE_API_URL="https://maps.googleapis.com/"
    val googleApiServices:IGoogleApiService
    get()=RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleApiService::class.java)
}