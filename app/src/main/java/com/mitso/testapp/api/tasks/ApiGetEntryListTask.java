package com.mitso.testapp.api.tasks;

import android.os.AsyncTask;

import com.mitso.testapp.api.Api;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.models.Entry;
import com.mitso.testapp.models.JsonData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGetEntryListTask extends AsyncTask<Void, Void, List<Entry>> {

    public String               LOG_TAG = Constants.API_GET_ENTRY_LIST_TASK_LOG_TAG;

    public interface Callback {

        void onSuccess(List<Entry> _result);
        void onFailure(Throwable _error);
    }

    private String              mContentType;
    private List<Entry>         mEntryList;
    private Callback            mCallback;
    private Exception           mException;

    public ApiGetEntryListTask(String _contentType) {

        this.mEntryList = new ArrayList<>();
        this.mContentType = _contentType;
    }

    public void setCallback(Callback _callback) {

        if (mCallback == null)
            mCallback = _callback;
    }

    public void releaseCallback() {

        if (mCallback != null)
            mCallback = null;
    }

    @Override
    protected List<Entry> doInBackground(Void... _voids) {

        try {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final Api api = retrofit.create(Api.class);

            if (mContentType.equals(Constants.CONTENT_TYPE_AUDIOBOOK)) {

                final Call<JsonData> call = api.getAudiobooks();
                final Response<JsonData> response = call.execute();
                final JsonData jsonData = response.body();

                mEntryList.addAll(jsonData.getFeed().getEntry());
                return null;
            }

            if (mContentType.equals(Constants.CONTENT_TYPE_MOVIE)) {

                final Call<JsonData> call = api.getMovies();
                final Response<JsonData> response = call.execute();
                final JsonData jsonData = response.body();

                mEntryList.addAll(jsonData.getFeed().getEntry());
                return null;
            }

            if (mContentType.equals(Constants.CONTENT_TYPE_PODCAST)) {


                final Call<JsonData> call = api.getPodcasts();
                final Response<JsonData> response = call.execute();
                final JsonData jsonData = response.body();

                mEntryList.addAll(jsonData.getFeed().getEntry());
                return null;
            }

        } catch (Exception _error) {

            _error.printStackTrace();
            mException = _error;
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Entry> _entryList) {
        super.onPostExecute(_entryList);

        if (mCallback != null) {
            if (mException == null)
                mCallback.onSuccess(mEntryList);
            else
                mCallback.onFailure(mException);
        }
    }
}
