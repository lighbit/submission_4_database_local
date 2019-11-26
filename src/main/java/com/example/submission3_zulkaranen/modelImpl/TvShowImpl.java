package com.example.submission3_zulkaranen.modelImpl;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission3_zulkaranen.R;
import com.example.submission3_zulkaranen.model.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;
/**
 * @author zulkarnaen
 */
public class TvShowImpl extends ViewModel {
    private static final String MY_API_KEY = "d10721892b71839178ae7c4597123c84";
    private MutableLiveData<ArrayList<TvShow>> listTvShow = new MutableLiveData<>();

    public void setTvShow(Context context) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItem = new ArrayList<>();

        String language = context.getString(R.string.API);

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + MY_API_KEY + language;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {

                        JSONObject tv = list.getJSONObject(i);
                        TvShow tvShowItems = new TvShow(tv);
                        listItem.add(tvShowItems);
                    }
                    listTvShow.postValue(listItem);
                } catch (Exception e) {
                    Log.d("Exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }

        });

    }

    public LiveData<ArrayList<TvShow>> getTvShow() {
        return listTvShow;
    }
}
