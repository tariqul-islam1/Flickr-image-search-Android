package com.example.retrofit_fastadapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.retrofit_fastadapter.clients.APIClient;
import com.example.retrofit_fastadapter.models.PostModel;
import com.example.retrofit_fastadapter.services.APIServices;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//follow this to solve
// https://medium.com/@paul.stanescu/custom-interface-adapter-to-serialize-and-deserialize-interfaces-in-kotlin-using-gson-8539c04b4c8f

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    Context context;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        progressDialog = new ProgressDialog(context);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        getPosts();

    }

    private void getPosts() {
        final APIServices apiService = APIClient.getInstance().create(APIServices.class);

        apiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Call<List<PostModel>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("rxjava onSubscribe");
                    }

                    @Override
                    public void onNext(Call<List<PostModel>> listCall) {
                        System.out.println("rxjava onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("rxjava onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("rxjava onComplete");
                    }
                });
//                .subscribe(new Observer<Observable<Call<List<PostModel>>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        progressDialog.setCancelable(false);
//                        progressDialog.setMessage("Loading...");
//                        progressDialog.show();
//                    }
//
//                    @Override
//                    public void onNext(Observable<Call<List<PostModel>>> listCall) {
//                        listCall.enqueue(new Callback<Call<List<PostModel>>>() {
//                            @Override
//                            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//                                List<PostModel> posts = response.body();
//
//                                //init fast adapter
//                                FastItemAdapter<PostModel> fastAdapter = new FastItemAdapter<>();
//                                fastAdapter.add(posts);
//
//                                //fill the recycler view
//                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//                                recycler.setLayoutManager(layoutManager);
//                                recycler.setAdapter(fastAdapter);
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<List<PostModel>> call, Throwable t) {
//                                if (progressDialog.isShowing())
//                                    progressDialog.dismiss();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        if (progressDialog.isShowing())
//                            progressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                        if (progressDialog.isShowing())
//                            progressDialog.dismiss();
//                    }
//                });


    }
}
