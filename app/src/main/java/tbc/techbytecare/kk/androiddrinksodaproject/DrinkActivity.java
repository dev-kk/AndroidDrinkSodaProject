package tbc.techbytecare.kk.androiddrinksodaproject;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import tbc.techbytecare.kk.androiddrinksodaproject.Adapter.DrinkAdapter;
import tbc.techbytecare.kk.androiddrinksodaproject.Common.Common;
import tbc.techbytecare.kk.androiddrinksodaproject.Model.Drink;
import tbc.techbytecare.kk.androiddrinksodaproject.Retrofit.IDrinkShopAPI;

public class DrinkActivity extends AppCompatActivity {

    IDrinkShopAPI mService;

    RecyclerView lst_drink;

    TextView txt_banner_name;

    SwipeRefreshLayout refreshLayout;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        mService = Common.getAPI();

        refreshLayout = findViewById(R.id.swipe_layout);

        lst_drink = findViewById(R.id.recycler_drinks);
        lst_drink.setLayoutManager(new GridLayoutManager(this,2));
        lst_drink.setHasFixedSize(true);

        txt_banner_name = findViewById(R.id.txt_menu_name);
        txt_banner_name.setText(Common.currentCategory.Name);

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);

                loadListDrink(Common.currentCategory.ID);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);

                loadListDrink(Common.currentCategory.ID);
            }
        });

    }

    private void loadListDrink(String menuId) {
        compositeDisposable.add(mService.getDrink(menuId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Drink>>() {
                @Override
                public void accept(List<Drink> drinks) throws Exception {
                    displayDrinkList(drinks);
                }
            }));
    }

    private void displayDrinkList(List<Drink> drinks) {
        DrinkAdapter adapter = new DrinkAdapter(this,drinks);
        lst_drink.setAdapter(adapter);

        refreshLayout.setRefreshing(false);
    }
}
