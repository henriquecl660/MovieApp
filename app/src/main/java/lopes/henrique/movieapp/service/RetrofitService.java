package lopes.henrique.movieapp.service;

import android.database.Observable;
import android.widget.EditText;

import lopes.henrique.movieapp.R;
import lopes.henrique.movieapp.model.RespostaServidor;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by henri on 18/02/2018.
 */

// Consumir Json no Android

public interface RetrofitService {






    @Headers("apikey: 50c5a567")

   // @FormUrlEncoded
   // @POST("{t}")
    //Call<RespostaServidor> buscarInfo(@Field("t") String Title);
    @GET("/?apikey=50c5a567")
    Call<RespostaServidor> buscarInfo(@Query(value = "t", encoded = true) String title);




}
