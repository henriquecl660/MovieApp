package lopes.henrique.movieapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Telephony;
import android.util.Log;

import java.util.ArrayList;

import lopes.henrique.movieapp.model.RespostaServidor;

/**
 * Created by henri on 21/02/2018.
 */

public class MovieApp_db extends SQLiteOpenHelper {

    public  static final String BANCO = "DB_MOVIES_APP";
    public  static final int VERSAO = 3;
    
    public MovieApp_db(Context context) {
        super(context, BANCO, null , VERSAO);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE movies_table (_id integer primary key autoincrement, Movie_name TEXT, Title TEXT, Year TEXT , Rated TEXT , Released TEXT," +
                "Runtime TEXT, Genre TEXT, Director TEXT, Writer TEXT, Actors TEXT , Plot TEXT , Language TEXT , Country TEXT , Awards TEXT, Poster Text," +
                "Ratings TEXT, Metascore TEXT , imdbRating TEXT , imdbVotes TEXT, imdbID TEXT , Type TEXT , DVD TEXT , BoxOffice TEXT , Production TEXT," +
                "Website TEXT  );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS movies_table");
        onCreate(db);

    }

    public void insere_registro(
                                String Title,String Year, String Rated, String Released, String Runtime , String Genre , String Director,
                                String Writer, String Actors , String Plot , String Language , String Country , String Awards , String Poster, String Ratings ,
                                String Metascore, String imdbRating, String imdbVotes , String imdbID , String Type , String DVD , String BoxOffice ,
                                String Production , String Website
    ){

        Log.i("movies_table", "Inserindo registro");
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues valores_para_inserir_no_banco = new ContentValues();

        valores_para_inserir_no_banco.put("Title",Title);
        valores_para_inserir_no_banco.put("Year", Year);
        valores_para_inserir_no_banco.put("Rated", Rated);
        valores_para_inserir_no_banco.put("Released", Released);
        valores_para_inserir_no_banco.put("Runtime", Runtime);
        valores_para_inserir_no_banco.put("Genre", Genre);
        valores_para_inserir_no_banco.put("Director", Director);
        valores_para_inserir_no_banco.put("Writer", Writer);
        valores_para_inserir_no_banco.put("Actors", Actors);
        valores_para_inserir_no_banco.put("Plot", Plot);
        valores_para_inserir_no_banco.put("Language", Language);
        valores_para_inserir_no_banco.put("Country", Country);
        valores_para_inserir_no_banco.put("Awards", Awards);
        valores_para_inserir_no_banco.put("Poster", Poster);
        valores_para_inserir_no_banco.put("Ratings", Ratings);
        valores_para_inserir_no_banco.put("Metascore",Metascore);
        valores_para_inserir_no_banco.put("imdbRating",imdbRating);
        valores_para_inserir_no_banco.put("imdbVotes",imdbVotes);
        valores_para_inserir_no_banco.put("imdbID",imdbID);
        valores_para_inserir_no_banco.put("Type",Type);
        valores_para_inserir_no_banco.put("DVD", DVD);
        valores_para_inserir_no_banco.put("BoxOffice",BoxOffice);
        valores_para_inserir_no_banco.put("Production",Production);
        valores_para_inserir_no_banco.put("Website",Website);

        database.insert("movies_table",null,valores_para_inserir_no_banco);
        database.close();

    }

        public ArrayList<RespostaServidor> recupera_registro() {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query("movies_table", new String[]{"_id", "Title", "Year",
                            "Rated", "Released", "Runtime", "Genre", "Director", "Writer", "Actors", "Plot", "Language", "Country",
                            "Awards", "Poster", "Ratings", "Metascore", "imdbRating", "imdbVotes", "imdbID", "Type", "DVD", "BoxOffice",
                            "Production", "Website"},
                    null, null, null, null, null);

            ArrayList<RespostaServidor> registros = new ArrayList<RespostaServidor>();

            if (cursor.moveToFirst()) {
                do {

                   int id = cursor.getInt(cursor.getColumnIndex("_id"));


                  //  String Movie_name = cursor.getString(cursor.getColumnIndex("Movie_name"));
                    String Title = cursor.getString(cursor.getColumnIndex("Title"));
                    String Year = cursor.getString(cursor.getColumnIndex("Year"));
                    String Rated = cursor.getString(cursor.getColumnIndex("Rated"));
                    String Released = cursor.getString(cursor.getColumnIndex("Released"));
                    String Runtime = cursor.getString(cursor.getColumnIndex("Runtime"));
                    String Genre = cursor.getString(cursor.getColumnIndex("Genre"));
                    String Director = cursor.getString(cursor.getColumnIndex("Director"));
                    String Writer = cursor.getString(cursor.getColumnIndex("Writer"));
                    String Actors = cursor.getString(cursor.getColumnIndex("Actors"));
                    String Plot = cursor.getString(cursor.getColumnIndex("Plot"));
                    String Language = cursor.getString(cursor.getColumnIndex("Language"));
                    String Country = cursor.getString(cursor.getColumnIndex("Country"));
                    String Awards = cursor.getString(cursor.getColumnIndex("Awards"));
                    String Poster = cursor.getString(cursor.getColumnIndex("Poster"));
                    String Ratings = cursor.getString(cursor.getColumnIndex("Ratings"));
                    String Metascore = cursor.getString(cursor.getColumnIndex("Metascore"));
                    String imdbRating = cursor.getString(cursor.getColumnIndex("imdbRating"));
                    String imdbVotes = cursor.getString(cursor.getColumnIndex("imdbVotes"));
                    String imdbID = cursor.getString(cursor.getColumnIndex("imdbID"));
                    String Type = cursor.getString(cursor.getColumnIndex("Type"));
                    String DVD = cursor.getString(cursor.getColumnIndex("DVD"));
                    String BoxOffice = cursor.getString(cursor.getColumnIndex("BoxOffice"));
                    String Production = cursor.getString(cursor.getColumnIndex("Production"));
                    String Website = cursor.getString(cursor.getColumnIndex("Website"));

                    RespostaServidor registro = new RespostaServidor();

                    registro.setId(String.valueOf(id));  //If necessary (To do it , you have store the variavel in model);
                    //registro.setMovie_name(Movie_name);
                    registro.setTitle(Title);
                    registro.setYear(Year);
                    registro.setRated(Rated);
                    registro.setReleased(Released);
                    registro.setRuntime(Runtime);
                    registro.setGenre(Genre);
                    registro.setDirector(Director);
                    registro.setWriter(Writer);
                    registro.setActors(Actors);
                    registro.setPlot(Plot);
                    registro.setLanguage(Language);
                    registro.setCountry(Country);
                    registro.setAwards(Awards);
                    registro.setPoster(Poster);
                    registro.setRatings(Ratings);
                    registro.setMetascore(Metascore);
                    registro.setImdbRating(imdbRating);
                    registro.setImdbVotes(imdbVotes);
                    registro.setImdbID(imdbID);
                    registro.setType(Type);
                    registro.setDVD(DVD);
                    registro.setBoxOffice(BoxOffice);
                    registro.setProduction(Production);
                    registro.setWebsite(Website);

                    registros.add(registro);

            } while (cursor.moveToNext()) ;

            }

             cursor.close();
             db.close();

             return registros;
        }

}
