package lopes.henrique.movieapp;

import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import lopes.henrique.movieapp.db.MovieApp_db;
import lopes.henrique.movieapp.model.RespostaServidor;
import lopes.henrique.movieapp.service.RetrofitService;
import lopes.henrique.movieapp.service.ServiceGenerator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_search, bt_salvar, bt_anterior, bt_proximo, bt_recuperar;
    private TextView tv_title, tv_year, tv_rated, tv_released, tv_runtime, tv_genre, tv_director, tv_writer, tv_actors, tv_plot, tv_language, tv_country, tv_awards, tv_poster,
            tv_ratings, tv_metascore, tv_imdbRating, tv_imdbVotes, tv_imdbID, tv_type, tv_DVD, tv_boxOffice, tv_production, tv_website, tv_response;
    private EditText tv_entradaNomeFilme;


    RespostaServidor resposta = new RespostaServidor();
    ProgressDialog progress;
    int indiceRegistroAserExibido;


    private MovieApp_db myDB;
    ArrayList<RespostaServidor> registros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDB = new MovieApp_db(this);


        tv_entradaNomeFilme = findViewById(R.id.et_entradaNomeFilme);


        // Variáves a serem retornadas

        tv_title = findViewById(R.id.tv_title);
        tv_year = findViewById(R.id.tv_year);
        tv_rated = findViewById(R.id.tv_rated);
        tv_released = findViewById(R.id.tv_released);
        tv_runtime = findViewById(R.id.tv_runtime);
        tv_genre = findViewById(R.id.tv_genre);
        tv_director = findViewById(R.id.tv_director);
        tv_writer = findViewById(R.id.tv_writer);
        tv_actors = findViewById(R.id.tv_actors);
        tv_plot = findViewById(R.id.tv_plot);
        tv_language = findViewById(R.id.tv_language);
        tv_country = findViewById(R.id.tv_country);
        tv_awards = findViewById(R.id.tv_awards);
        tv_poster = findViewById(R.id.tv_poster);
        tv_ratings = findViewById(R.id.tv_ratings);
        tv_metascore = findViewById(R.id.tv_metascore);
        tv_imdbRating = findViewById(R.id.tv_imdbRating);
        tv_imdbVotes = findViewById(R.id.tv_imdbVotes);
        tv_imdbID = findViewById(R.id.tv_imdbID);
        tv_type = findViewById(R.id.tv_type);
        tv_DVD = findViewById(R.id.tv_DVD);
        tv_boxOffice = findViewById(R.id.tv_boxOffice);
        tv_production = findViewById(R.id.tv_production);
        tv_website = findViewById(R.id.tv_website);

        bt_salvar = (Button) findViewById(R.id.btn_salvar);
        bt_anterior = findViewById(R.id.btn_anterior);
        bt_proximo = findViewById(R.id.btn_proximo);
        bt_recuperar = findViewById(R.id.btn_recuperar);

        bt_anterior.setEnabled(false);
        bt_proximo.setEnabled(false);


    }


    public void on_clickSearch(View view) {

        bt_salvar.setEnabled(true);
        bt_recuperar.setEnabled(true);
        bt_proximo.setEnabled(false);
        bt_anterior.setEnabled(false);

        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("enviando ...");
        progress.show();


        //pega os valores dos editextos
        String filmeProcurado = tv_entradaNomeFilme.getText().toString();

        // chama o retrofit para fazer a requisição no webservice
        retrofitMethod(filmeProcurado);
    }

    public void retrofitMethod(String filmeProcurado) {

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);



        Call<RespostaServidor> call = service.buscarInfo(filmeProcurado);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {






                if (response.isSuccessful()) {

                    RespostaServidor respostaServidor = response.body();





                    if (respostaServidor != null) {

                        if (respostaServidor.trueResponsed()) {
                            resposta.setMovie_name(respostaServidor.getMovie_name());
                            //
                            resposta.setTitle(respostaServidor.getTitle());
                            resposta.setYear(respostaServidor.getYear());
                            resposta.setRated(respostaServidor.getRated());
                            resposta.setReleased(respostaServidor.getReleased());
                            resposta.setRuntime(respostaServidor.getRuntime());
                            resposta.setGenre(respostaServidor.getGenre());
                            resposta.setDirector(respostaServidor.getDirector());
                            resposta.setWriter(respostaServidor.getWriter());
                            resposta.setActors(respostaServidor.getActors());
                            resposta.setPlot(respostaServidor.getPlot());
                            resposta.setLanguage(respostaServidor.getLanguage());
                            resposta.setCountry(respostaServidor.getCountry());
                            resposta.setAwards(respostaServidor.getAwards());
                            resposta.setPoster(respostaServidor.getPoster());
                            resposta.setRatings(respostaServidor.getRatings());
                            resposta.setMetascore(respostaServidor.getMetascore());
                            resposta.setImdbRating(respostaServidor.getImdbRating());
                            resposta.setImdbVotes(respostaServidor.getImdbVotes());
                            resposta.setImdbID(respostaServidor.getImdbID());
                            resposta.setType(respostaServidor.getType());
                            resposta.setDVD(respostaServidor.getDVD());
                            resposta.setBoxOffice(respostaServidor.getBoxOffice());
                            resposta.setProduction(respostaServidor.getProduction());
                            resposta.setWebsite(respostaServidor.getWebsite());
                            resposta.setResponse(respostaServidor.trueResponsed());








                            progress.dismiss();
                            try {
                                setaValores();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {

                            Toast.makeText(getApplicationContext(), "Nome inválido ou filme não encontrado", Toast.LENGTH_SHORT).show();
                            progress.dismiss();

                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "Resposta nula do servidor", Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Resposta não foi bem sucedida", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }

            }


            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // setar os valores da resposta do servidor nos textviews
    public void setaValores() throws IOException {

        tv_title.setText(resposta.getTitle());
        tv_year.setText(resposta.getYear());
        tv_rated.setText(resposta.getRated());
        tv_released.setText(resposta.getReleased());
        tv_runtime.setText(resposta.getRuntime());
        tv_genre.setText(resposta.getGenre());
        tv_director.setText(resposta.getDirector());
        tv_writer.setText(resposta.getWriter());
        tv_actors.setText(resposta.getActors());
        tv_plot.setText(resposta.getPlot());

        tv_language.setText(resposta.getLanguage());
        tv_country.setText(resposta.getCountry());
        tv_awards.setText(resposta.getAwards());

        tv_poster.setText(resposta.getPoster());


        String formatRatings = resposta.getRatings().toString();
        formatRatings = formatRatings.replace("{","").replace("}","").replace("[","").replace("]","")+".";
        tv_ratings.setText(formatRatings);

        tv_metascore.setText(resposta.getMetascore());

        tv_imdbRating.setText(resposta.getImdbRating());
        tv_imdbVotes.setText(resposta.getImdbVotes());
        tv_imdbID.setText(resposta.getImdbID());

        tv_type.setText(resposta.getType());
        tv_DVD.setText(resposta.getDVD());
        tv_boxOffice.setText(resposta.getBoxOffice());
        tv_production.setText(resposta.getProduction());
        tv_website.setText(resposta.getWebsite());

        // exibir link da foto
        if(tv_poster.getText().toString().contains("http")) {
            tv_poster.setClickable(true);
            tv_poster.setMovementMethod(LinkMovementMethod.getInstance());
            String link = tv_poster.getText().toString();
            String text = "<a href='" + link + "'> Clique aqui para visualizar (Requer Internet) </a>";
            tv_poster.setText(Html.fromHtml(text));
        }
        //


    }

    public void salvarRegistro(View view) {

        bt_salvar.setEnabled(false);
        //int id = 1;
        //String str_id = id+"";


        // recolocando link no tv poster
        tv_poster.setText(resposta.getPoster());
        //

        //Insere registros

        myDB.insere_registro(tv_title.getText().toString(), tv_year.getText().toString(), tv_rated.getText().toString(),
                tv_released.getText().toString(), tv_runtime.getText().toString(), tv_genre.getText().toString(), tv_director.getText().toString(),
                tv_writer.getText().toString(), tv_actors.getText().toString(), tv_plot.getText().toString(), tv_language.getText().toString(),
                tv_country.getText().toString(), tv_awards.getText().toString(), tv_poster.getText().toString(), tv_ratings.getText().toString(),
                tv_metascore.getText().toString(), tv_imdbRating.getText().toString(), tv_imdbVotes.getText().toString(), tv_imdbID.getText().toString(),
                tv_type.getText().toString(), tv_DVD.getText().toString(), tv_boxOffice.getText().toString(), tv_production.getText().toString(),
                tv_website.getText().toString());


        if(!bt_recuperar.isEnabled()){
            bt_recuperar.setEnabled(true);
            if(bt_anterior.isEnabled()){
                bt_anterior.setEnabled(false);
            }
            if(bt_proximo.isEnabled()){
                bt_proximo.setEnabled(false);
            }



        }




        //id++;
    }

    public void recuperarRegistros(View view) {


        registros = myDB.recupera_registro();



        Log.e("QtdeRegistros",registros.size()+"");

        if (registros.size()!=0) {


            bt_proximo.setEnabled(true);


            tv_title.setText(registros.get(indiceRegistroAserExibido).getTitle());
            tv_year.setText(registros.get(indiceRegistroAserExibido).getYear());
            tv_rated.setText(registros.get(indiceRegistroAserExibido).getRated());
            tv_released.setText(registros.get(indiceRegistroAserExibido).getReleased());
            tv_runtime.setText(registros.get(indiceRegistroAserExibido).getRuntime());
            tv_genre.setText(registros.get(indiceRegistroAserExibido).getGenre());
            tv_director.setText(registros.get(indiceRegistroAserExibido).getDirector());
            tv_writer.setText(registros.get(indiceRegistroAserExibido).getWriter());
            tv_actors.setText(registros.get(indiceRegistroAserExibido).getActors());
            tv_plot.setText(registros.get(indiceRegistroAserExibido).getPlot());
            tv_language.setText(registros.get(indiceRegistroAserExibido).getLanguage());
            tv_country.setText(registros.get(indiceRegistroAserExibido).getCountry());
            tv_awards.setText(registros.get(indiceRegistroAserExibido).getAwards());
            tv_poster.setText(registros.get(indiceRegistroAserExibido).getPoster());
            tv_ratings.setText((String) registros.get(indiceRegistroAserExibido).getRatings());
            tv_metascore.setText(registros.get(indiceRegistroAserExibido).getMetascore());
            tv_imdbRating.setText(registros.get(indiceRegistroAserExibido).getImdbRating());
            tv_imdbVotes.setText(registros.get(indiceRegistroAserExibido).getImdbVotes());
            tv_imdbID.setText(registros.get(indiceRegistroAserExibido).getImdbID());
            tv_type.setText(registros.get(indiceRegistroAserExibido).getType());
            tv_DVD.setText(registros.get(indiceRegistroAserExibido).getDVD());
            tv_boxOffice.setText(registros.get(indiceRegistroAserExibido).getBoxOffice());
            tv_production.setText(registros.get(indiceRegistroAserExibido).getProduction());
            tv_website.setText(registros.get(indiceRegistroAserExibido).getWebsite());


            if(tv_poster.getText().toString().contains("http")) {
                    tv_poster.setClickable(true);
                    tv_poster.setMovementMethod(LinkMovementMethod.getInstance());
                    String link = tv_poster.getText().toString();
                    String text = "<a href='" + link + "'> Clique aqui para visualizar (Requer Internet) </a>";
                    tv_poster.setText(Html.fromHtml(text));
            }


        }

        else {

            Toast.makeText(MainActivity.this,"Não há nenhum registro",Toast.LENGTH_SHORT).show();
            bt_recuperar.setEnabled(true);
        }

        bt_recuperar.setEnabled(false);

        bt_salvar.setEnabled(false);

    }

    public void botaoVoltar(View view) {

        bt_proximo.setEnabled(true);






     if(indiceRegistroAserExibido!= 0) {

         if (registros != null && registros.size() > 0) {
             indiceRegistroAserExibido--;




                 bt_anterior.setEnabled(true);


                 tv_title.setText(registros.get(indiceRegistroAserExibido).getTitle());
                 tv_year.setText(registros.get(indiceRegistroAserExibido).getYear());
                 tv_rated.setText(registros.get(indiceRegistroAserExibido).getRated());
                 tv_released.setText(registros.get(indiceRegistroAserExibido).getReleased());
                 tv_runtime.setText(registros.get(indiceRegistroAserExibido).getRuntime());
                 tv_genre.setText(registros.get(indiceRegistroAserExibido).getGenre());
                 tv_director.setText(registros.get(indiceRegistroAserExibido).getDirector());
                 tv_writer.setText(registros.get(indiceRegistroAserExibido).getWriter());
                 tv_actors.setText(registros.get(indiceRegistroAserExibido).getActors());
                 tv_plot.setText(registros.get(indiceRegistroAserExibido).getPlot());
                 tv_language.setText(registros.get(indiceRegistroAserExibido).getLanguage());
                 tv_country.setText(registros.get(indiceRegistroAserExibido).getCountry());
                 tv_awards.setText(registros.get(indiceRegistroAserExibido).getAwards());
                 tv_poster.setText(registros.get(indiceRegistroAserExibido).getPoster());
              //   tv_ratings.setText(registros.get(indiceRegistroAserExibido).getRatings());
                 tv_metascore.setText(registros.get(indiceRegistroAserExibido).getMetascore());
                 tv_imdbRating.setText(registros.get(indiceRegistroAserExibido).getImdbRating());
                 tv_imdbVotes.setText(registros.get(indiceRegistroAserExibido).getImdbVotes());
                 tv_imdbID.setText(registros.get(indiceRegistroAserExibido).getImdbID());
                 tv_type.setText(registros.get(indiceRegistroAserExibido).getType());
                 tv_DVD.setText(registros.get(indiceRegistroAserExibido).getDVD());
                 tv_boxOffice.setText(registros.get(indiceRegistroAserExibido).getBoxOffice());
                 tv_production.setText(registros.get(indiceRegistroAserExibido).getProduction());
                 tv_website.setText(registros.get(indiceRegistroAserExibido).getWebsite());




             // exibir link da foto
             if(tv_poster.getText().toString().contains("http")) {
                 tv_poster.setClickable(true);
                 tv_poster.setMovementMethod(LinkMovementMethod.getInstance());
                 String link = tv_poster.getText().toString();
                 String text = "<a href='" + link + "'> Clique aqui para visualizar (Requer Internet) </a>";
                 tv_poster.setText(Html.fromHtml(text));
             }

             }

         if(indiceRegistroAserExibido==0){

             bt_anterior.setEnabled(false);
         }

     }




    }

    public void botaoProximo(View view) {




        if (registros != null && registros.size() > 0) {


            if (indiceRegistroAserExibido > 0 && !(registros.size() == indiceRegistroAserExibido)) {



                tv_title.setText(registros.get(indiceRegistroAserExibido).getTitle());
                tv_year.setText(registros.get(indiceRegistroAserExibido).getYear());
                tv_rated.setText(registros.get(indiceRegistroAserExibido).getRated());
                tv_released.setText(registros.get(indiceRegistroAserExibido).getReleased());
                tv_runtime.setText(registros.get(indiceRegistroAserExibido).getRuntime());
                tv_genre.setText(registros.get(indiceRegistroAserExibido).getGenre());
                tv_director.setText(registros.get(indiceRegistroAserExibido).getDirector());
                tv_writer.setText(registros.get(indiceRegistroAserExibido).getWriter());
                tv_actors.setText(registros.get(indiceRegistroAserExibido).getActors());
                tv_plot.setText(registros.get(indiceRegistroAserExibido).getPlot());
                tv_language.setText(registros.get(indiceRegistroAserExibido).getLanguage());
                tv_country.setText(registros.get(indiceRegistroAserExibido).getCountry());
                tv_awards.setText(registros.get(indiceRegistroAserExibido).getAwards());
                tv_poster.setText(registros.get(indiceRegistroAserExibido).getPoster());
                tv_ratings.setText((String) registros.get(indiceRegistroAserExibido).getRatings());
                tv_metascore.setText(registros.get(indiceRegistroAserExibido).getMetascore());
                tv_imdbRating.setText(registros.get(indiceRegistroAserExibido).getImdbRating());
                tv_imdbVotes.setText(registros.get(indiceRegistroAserExibido).getImdbVotes());
                tv_imdbID.setText(registros.get(indiceRegistroAserExibido).getImdbID());
                tv_type.setText(registros.get(indiceRegistroAserExibido).getType());
                tv_DVD.setText(registros.get(indiceRegistroAserExibido).getDVD());
                tv_boxOffice.setText(registros.get(indiceRegistroAserExibido).getBoxOffice());
                tv_production.setText(registros.get(indiceRegistroAserExibido).getProduction());
                tv_website.setText(registros.get(indiceRegistroAserExibido).getWebsite());


                // exibir link da foto
                if(tv_poster.getText().toString().contains("http")) {
                    tv_poster.setClickable(true);
                    tv_poster.setMovementMethod(LinkMovementMethod.getInstance());
                    String link = tv_poster.getText().toString();
                    String text = "<a href='" + link + "'> Clique aqui para visualizar (Requer Internet) </a>";
                    tv_poster.setText(Html.fromHtml(text));
                }
                //
            }

            if(registros.size()!=indiceRegistroAserExibido) {
                indiceRegistroAserExibido++;

            }
        }

        if(indiceRegistroAserExibido!=-1){

            bt_anterior.setEnabled(true);
        }

        if (registros.size() == indiceRegistroAserExibido) {
            bt_proximo.setEnabled(false);


        }

    }
}

    // tv_response.setText(resposta.trueResponsed().toString());







