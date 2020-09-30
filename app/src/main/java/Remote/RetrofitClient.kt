package Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit:Retrofit?=null
    fun getClient(baseurl1:String):Retrofit
    {

        if(retrofit==null)
        {
            retrofit= Retrofit.Builder().baseUrl(baseurl1).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }

}