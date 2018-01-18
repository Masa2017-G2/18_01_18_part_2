package com.sheygam.masa_2017_g2_18_01_18_part_2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by gregorysheygam on 18/01/2018.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    private ILoginWebRepository webRepository;
    private IStoreRepository storeRepository;

    public LoginPresenter(ILoginView view, ILoginWebRepository webRepository, IStoreRepository storeRepository) {
        this.view = view;
        this.webRepository = webRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public void onLogin(String email, String password) {
        //TODO
    }

    @Override
    public void onRegistration(String email, String password) {
        view.showProgress();
        new RegTask(new Auth(email,password)).execute();
    }

    private class RegTask extends AsyncTask<Void,Void,String>{
        private boolean isSuccess = true;
        private Auth auth;

        public RegTask(Auth auth) {
            this.auth = auth;
        }


        @Override
        protected String doInBackground(Void... voids) {
            String result = "Registration success";
            try {
                AuthToken authToken = webRepository.registration(auth);
                Log.d("MY_TAG", "doInBackground: token: " + authToken.getToken());
//                storeRepository.saveToken(authToken.getToken());
                //TODO implement store repository!
            } catch (IOException e){
                result = "Connection error!";
                isSuccess = false;
            }catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
                isSuccess = false;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            view.hideProgress();
            if (isSuccess){
                view.onSuccess();
            }else{
                view.showError(s);
            }
        }
    }
}
