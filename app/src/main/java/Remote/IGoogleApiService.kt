package Remote

import model.MyPlaces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleApiService {
@GET
fun getNearbyPlaces(@Url url:String):Call<MyPlaces>

}