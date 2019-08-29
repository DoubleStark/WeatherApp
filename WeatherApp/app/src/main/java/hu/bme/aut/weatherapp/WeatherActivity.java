package hu.bme.aut.weatherapp;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hu.bme.aut.weatherapp.data.WeatherResult;
import hu.bme.aut.weatherapp.network.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity implements OnMapReadyCallback {
    final static String appID = "8e0ca9ed799aa620b637f323639bd526";
    final static String units = "metric";
    final static String TAG = "Weather_ACTIVITY";
    private GoogleMap mMap;
    private ScrollView mScrollView;
    private Double cityLat;
    private Double cityLon;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mScrollView = findViewById(R.id.scrollView);
        toolbar.setNavigationOnClickListener(v -> finish());

        final TextView tvData = findViewById(R.id.tvName);
        final TextView tvTemperature = findViewById(R.id.tvTemperature);
        final TextView tvWeatherMain = findViewById(R.id.tvWeatherMain);
        final TextView tvMin = findViewById(R.id.tvMin);
        final ImageView ivIcon = findViewById(R.id.ivIcon);
        final TextView tvWeatherDescription = findViewById(R.id.tvWeatherDescription);
        final TextView tvMax = findViewById(R.id.tvMax);
        final TextView tvHumidity = findViewById(R.id.tvHumidity);
        final TextView tvPressure = findViewById(R.id.tvPressure);
        final TextView tvSunset = findViewById(R.id.tvSunset);
        final TextView tvSunrise = findViewById(R.id.tvSunrise);
        final TextView tvClouds = findViewById(R.id.tvClouds);
        final TextView tvWindSpeed = findViewById(R.id.tvWindSpeed);
        final TextView tvWindDegrees = findViewById(R.id.tvWindDegrees);
        final TextView tvWindDirection = findViewById(R.id.tvWindDirection);

        //if(getIntent().getExtras().containsKey(MainActivity.CITY_SELECTED))
        if (getIntent().hasExtra(MainActivity.CITY_SELECTED)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
            String city_selected = getIntent().getStringExtra(MainActivity.CITY_SELECTED);
            Call<WeatherResult> callWeather = weatherAPI.getWeather(city_selected, units, appID);


            callWeather.enqueue(new Callback<WeatherResult>() {
                @Override
                public void onResponse(Call<WeatherResult> call, final Response<WeatherResult> response) {
                    if (response.code() == 200) {
                        tvData.setText("" + response.body().getName() + ", " + response.body().getSys().getCountry());
                        tvTemperature.setText("" + response.body().getMain().getTemp() + "°C");
                        tvWeatherMain.setText("" + response.body().getWeather().get(0).getMain());
                        tvWeatherDescription.setText("Description: " + response.body().getWeather().get(0).getDescription());
                        tvMin.setText("Min. temperature: " + response.body().getMain().getTemp_min() + "°C");
                        tvMax.setText("Max. temprerature: " + response.body().getMain().getTemp_max() + "°C");
                        tvPressure.setText("Pressure: " + response.body().getMain().getPressure());
                        tvHumidity.setText("Humidity: " + response.body().getMain().getHumidity() + "%");
                        tvSunrise.setText("Sunrise: " + convertDoubleToTime(response.body().getSys().getSunrise()));
                        tvSunset.setText("Sunset: " + convertDoubleToTime(response.body().getSys().getSunset()));
                        tvClouds.setText("Clouds: " + response.body().getClouds().getAll() + "%");
                        tvWindSpeed.setText("Wind speed: " + response.body().getWind().getSpeed());
                        tvWindDegrees.setText("Wind degrees: " + response.body().getWind().getDeg());
                        tvWindDirection.setText("Wind direction: " + degreesToDirection(response.body().getWind().getDeg()));

                        Glide.with(WeatherActivity.this).load
                                ("http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png")
                                .fitCenter()
                                .into(ivIcon);

                        cityLat = response.body().getCoord().getLat();
                        cityLon = response.body().getCoord().getLon();
                        cityName = response.body().getName() + ", " + response.body().getSys().getCountry();

                        MapFragment mapFragment =
                                (MapFragment) getSupportFragmentManager()
                                        .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(WeatherActivity.this);
                        mapFragment.setListener(() -> mScrollView.requestDisallowInterceptTouchEvent(true));
                    } else {

                        final AlertDialog.Builder errorBuilder = new AlertDialog.Builder(WeatherActivity.this);
                        errorBuilder.setTitle("Error occurred");

                        final LinearLayout layout = new LinearLayout(WeatherActivity.this);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                        final TextView errorMessage = new TextView(WeatherActivity.this);
                        errorMessage.setText("Error " + response.code() + ": " + response.message());
                        errorMessage.setGravity(Gravity.CENTER);
                        layout.addView(errorMessage);

                        errorBuilder.setView(layout);
                        errorBuilder.setCancelable(false);

                        errorBuilder.setPositiveButton("Ok", (dialogInterface, i) -> finish());

                        errorBuilder.show();
                    }
                }

                @Override
                public void onFailure(Call<WeatherResult> call, Throwable t) {
                    tvData.setText(t.getLocalizedMessage());
                }
            });


        }
    }

    public static String convertDoubleToTime(double x) {
        long xLong = (long) (x * 1000);
        Date xDate = new Date(xLong);
        String xDateStr = new SimpleDateFormat("HH:mm:ss").format(xDate);
        return xDateStr;
    }

    public static String degreesToDirection(Double x) {
        if (x != null) {
            String[] directions = {"North", "NorthEast", "East", "SouthEast", "South", "SouthWest", "West", "NorthWest", "North"};
            return directions[(int) Math.round(((x % 360) / 45))];
        } else {
            return "Not Specified";
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng citySelected = new LatLng(cityLat, cityLon);
        mMap.addMarker(new MarkerOptions().position(citySelected).title(cityName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(citySelected));
    }
}
