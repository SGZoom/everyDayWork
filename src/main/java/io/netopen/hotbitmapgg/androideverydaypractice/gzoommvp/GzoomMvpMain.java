package io.netopen.hotbitmapgg.androideverydaypractice.gzoommvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by gzoom on 2016/9/10.
 */
public class GzoomMvpMain  extends AppCompatActivity implements VtoP{

    @Bind(R.id.mvp_main_editname)
    EditText edittextName;
    @Bind(R.id.mvp_main_editpassword)
    EditText edittextPassword;
    @Bind(R.id.mvp_main_login)
    Button buttonLogin;
    @Bind(R.id.mvp_main_clear)
    Button buttonClear;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gzoommvp_main);
        ButterKnife.bind(this);
        progressDialog= new ProgressDialog(this);
    }



    @Override
    public String getName() {
        return edittextName.getText().toString();
    }

    @Override
    public String getPassword() {
        return edittextPassword.getText().toString();
    }

    @Override
    public void clear() {
        edittextName.setText("");
        edittextPassword.setText("");
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        progressDialog.dismiss();
    }
}
