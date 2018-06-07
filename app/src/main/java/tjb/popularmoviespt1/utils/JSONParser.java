package tjb.popularmoviespt1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tjb.popularmoviespt1.model.Movie;

public class JSONParser {

    public ArrayList<Movie> parseString(String jsonData) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        JSONObject object = new JSONObject(jsonData);
        JSONArray results = object.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movie = results.getJSONObject(i);
            movies.add(new Movie()
                    .setId(movie.getInt("id"))
                    .setReleaseDate(movie.getString("release_date"))
                    .setPosterPath(movie.getString("poster_path"))
                    .setVoteAverage(movie.getLong("vote_average"))
                    .setVoteCount(movie.getLong("vote_count"))
                    .setOverview(movie.getString("overview"))
                    .setTitle(movie.getString("title")));
        }
        return movies;
    }
}
