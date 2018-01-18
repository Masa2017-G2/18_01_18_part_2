package com.sheygam.masa_2017_g2_18_01_18_part_2;

/**
 * Created by gregorysheygam on 18/01/2018.
 */

public interface ILoginView {
    void showProgress();
    void hideProgress();
    void onSuccess();
    void showError(String error);
}
