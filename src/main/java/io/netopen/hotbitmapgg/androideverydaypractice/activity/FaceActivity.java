package io.netopen.hotbitmapgg.androideverydaypractice.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.adapter.FaceAdapter;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.model.ExpressionPackage;
import io.netopen.hotbitmapgg.androideverydaypractice.network.ExpressionPackageApi;
import io.netopen.hotbitmapgg.androideverydaypractice.network.RetrofitHelper;
import io.netopen.hotbitmapgg.androideverydaypractice.recycleview.AbsRecyclerViewAdapter;
import io.netopen.hotbitmapgg.androideverydaypractice.utils.GlideDownloadImageUtil;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class FaceActivity extends AbsBaseActivity
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

 

    @Bind(R.id.edit)
    EditText mQueryEdit;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String content;

    private InputMethodManager mInputManager;

    private CompositeSubscription mCompositeSubscription;

    private List<ExpressionPackage> datas = new ArrayList<>();

    public static FaceActivity newInstance()
    {

        return new FaceActivity();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_face;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {


        mCompositeSubscription = new CompositeSubscription();

        mInputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        RxTextView.textChangeEvents(mQueryEdit)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>()
                {

                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent)
                    {

                        content = textViewTextChangeEvent.text().toString();
                    }
                });


        mQueryEdit.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {

                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    //加载数据
                    showProgress();
                    //隐藏键盘
                    hideKeyBord();
                    //清空输入内容
                    mQueryEdit.setText("");
                }
                return false;
            }
        });
    }

    @Override
    public void initToolBar()
    {

    }


    private void getZhuangBiList(String text)
    {

        ExpressionPackageApi expressionPackageApi = RetrofitHelper.getExpressionPackageApi();

        expressionPackageApi.search(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ExpressionPackage>>()
                {

                    @Override
                    public void call(List<ExpressionPackage> expressionPackages)
                    {

                        datas.clear();
                        if (expressionPackages != null && expressionPackages.size() > 0)
                        {

                            datas.addAll(expressionPackages);
                            finishGetZhuangBiList();
                        } else
                        {
                            hideSwipeRefreshLayout();

                        }
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {


                        hideSwipeRefreshLayout();

                    }
                });
    }

    private void finishGetZhuangBiList()
    {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(FaceActivity.this, 2));
        FaceAdapter mAdapter = new FaceAdapter(mRecyclerView, datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(final int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                new AlertDialog.Builder(FaceActivity.this)
                        .setMessage("是否保存到本地?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                ExpressionPackage expressionPackage = datas.get(position);
                                saveImageToGallery(expressionPackage);
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        mAdapter.setOnItemLongClickListener(new AbsRecyclerViewAdapter.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(final int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                new AlertDialog.Builder(FaceActivity.this)
                        .setMessage("是否分享给好友?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                shareImage(datas.get(position));
                                dialog.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });
    }

    private void saveImageToGallery(final ExpressionPackage expressionPackage)
    {

        Subscription s = Observable.just("")
                .compose(RxPermissions.getInstance(FaceActivity.this).ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .observeOn(Schedulers.io())
                .filter(new Func1<Boolean,Boolean>()
                {

                    @Override
                    public Boolean call(Boolean aBoolean)
                    {

                        return aBoolean;
                    }
                })
                .flatMap(new Func1<Boolean,Observable<Uri>>()
                {

                    @Override
                    public Observable<Uri> call(Boolean aBoolean)
                    {

                        return GlideDownloadImageUtil.saveImageToLocal(FaceActivity.this, expressionPackage.image_url, expressionPackage.description, "gif");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {

                        File appDir = new File(Environment.getExternalStorageDirectory(), "android");
                        String msg = String.format("图片已保存至 %s 文件夹", appDir.getAbsolutePath());
                        Toast.makeText(FaceActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        Toast.makeText(FaceActivity.this, "保存失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(s);
    }


    public void shareImage(final ExpressionPackage expressionPackage)
    {

        Subscription subscribe = Observable.just("")
                .compose(RxPermissions.getInstance(FaceActivity.this).ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .observeOn(Schedulers.io())
                .filter(new Func1<Boolean,Boolean>()
                {

                    @Override
                    public Boolean call(Boolean aBoolean)
                    {

                        return aBoolean;
                    }
                })
                .flatMap(new Func1<Boolean,Observable<Uri>>()
                {

                    @Override
                    public Observable<Uri> call(Boolean aBoolean)
                    {

                        return GlideDownloadImageUtil.saveImageToLocal(FaceActivity.this, expressionPackage.image_url, expressionPackage.description, "gif");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {

                        share(uri, expressionPackage.description);
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        Toast.makeText(FaceActivity.this, "分享失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(subscribe);
    }


    /**
     * 分享图片
     *
     * @param uri
     */
    private void share(Uri uri, String desc)
    {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, desc));
    }


    public void showProgress()
    {

        mSwipeRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {

                mRecyclerView.setVisibility(View.GONE);
                mSwipeRefreshLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mSwipeRefreshLayout.setRefreshing(true);
                getZhuangBiList(content);
            }
        });
    }


    /**
     * 隐藏软键盘
     */
    public void hideKeyBord()
    {

        mInputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void hideSwipeRefreshLayout()
    {

        mSwipeRefreshLayout.post(new Runnable()
        {

            @Override
            public void run()
            {

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroy()
    {

        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed())
        {
            mCompositeSubscription.unsubscribe();
        }
        datas.clear();
        super.onDestroy();
    }
}
