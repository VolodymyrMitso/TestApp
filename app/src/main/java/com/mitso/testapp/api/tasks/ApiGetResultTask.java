package com.mitso.testapp.api.tasks;

import android.os.AsyncTask;

import com.mitso.testapp.api.Api;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.models.json_result.JsonResult;
import com.mitso.testapp.models.json_result.Result;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGetResultTask extends AsyncTask<Void, Void, Result> {

    public String LOG_TAG = Constants.API_GET_RESULT_TASK_LOG_TAG;

    public interface Callback {

        void onSuccess(Result _result);

        void onFailure(Throwable _error);
    }

    private String          mEntryId;
    private Result          mResult;
    private Callback        mCallback;
    private Exception       mException;

    public ApiGetResultTask(String _entryId) {

        this.mEntryId = _entryId;
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
    protected Result doInBackground(Void... _voids) {

        try {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_ENTRY_LIST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final Api api = retrofit.create(Api.class);

            final Call<JsonResult> call = api.getResultById(mEntryId);
            final Response<JsonResult> response = call.execute();
            final JsonResult jsonResult = response.body();

            mResult = jsonResult.getResults().get(0);

            return null;

        } catch (Exception _error) {

            _error.printStackTrace();
            mException = _error;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Result _result) {
        super.onPostExecute(_result);

        if (mCallback != null) {
            if (mException == null)
                mCallback.onSuccess(mResult);
            else
                mCallback.onFailure(mException);
        }
    }
}
