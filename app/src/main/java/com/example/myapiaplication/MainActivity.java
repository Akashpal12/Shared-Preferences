package com.example.myapiaplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION = 1;
    private static final String MAGENTO_NAMESPACE = "http://tempuri.org/";
    private static final String MAGENTO_URL = "http://wavedev.vibsugar.com/potatodovelopment.asmx/";
    private static final String MAGENTO_METHOD_NAME = "ValidateIMEINO";
    Context context;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    SessionConfig sessionConfig;
    StringBuilder jsonContent;
    TextView txt1;
    Button btnNext;
    List<MyModel> myModelList;
    List<MyModel> myNewModelList = new ArrayList<>();
    List<OwnersModel> ownersModelList = new ArrayList<>();
    List<MyTeacherModel> myTeacherModelList;
    List<String> myStringlist;
    TelephonyManager tm;
    TextView imeitxt;
    Button imeibtn;
    String imei;
    EditText edt_name, edt_Owner_Name, edt_Owner_Number;

 /*  void RetrofitGetLoginApi() {
        RetrofitClient.getClient().getLogin("Admin","123").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    Toast.makeText(context, ""+jsonObject, Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }*/

    public static void getLogin() {
        try {
            String message;
            SoapObject request = new SoapObject(MAGENTO_NAMESPACE, MAGENTO_METHOD_NAME);
            request.addProperty("IMEINO", "201989bc66251dbe");
            request.addProperty("FirbaseRegistrationId", "");
            Log.d("", "doInBackground: " + request);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.implicitTypes = true;
            // Web method call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(MAGENTO_URL, 200000);
            ;
            androidHttpTransport.debug = true;
            androidHttpTransport.call("http://tempuri.org/ValidateIMEINO", envelope);
            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault sf = (SoapFault) envelope.bodyIn;
                message = sf.getMessage();
            } else {
                SoapObject result = (SoapObject) envelope.bodyIn;
                message = result.getPropertyAsString("ValidateIMEINOResult").toString();
            }
            Log.d("Response", message);
        } catch (Exception e) {
            Log.d("Response", e.getMessage());

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        context = MainActivity.this;
        sessionConfig = new SessionConfig(context);
        myModelList = new ArrayList<>();
        myTeacherModelList = new ArrayList<>();
        myStringlist = new ArrayList<>();
        //getLogin();
        ValidateImeuNo();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);
        txt1 = findViewById(R.id.txt1);
        btnNext = findViewById(R.id.btnNext);
        edt_name = findViewById(R.id.edt_name);
        edt_Owner_Name = findViewById(R.id.edt_Owner_Name);
        edt_Owner_Number = findViewById(R.id.edt_Owner_Number);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivty.class);
                sessionConfig.setOwnersModelList(ownersModelList);  // list duplicacy problem resolve solve set the last list
                startActivity(intent);
            }
        });


        sessionConfig.setPhone("6387040456");
        MyModel myModel = new MyModel();
        myModel.setBrotherName("Atul");
        myModel.setName("AkashPal");
        myModel.setFatherName("MyFatherName");
        myModel.setMotherName("MyMotherName");
        myModelList.add(myModel);
        sessionConfig.setMyModelList(myModelList);

        MyModel model = new MyModel();
        model.setName("Sushil");
        model.setMotherName("Avantika");
        model.setBrotherName("Hitesh");
        model.setFatherName("JangBahadur");
        myNewModelList.add(model);
        sessionConfig.setNewMyModelList(myNewModelList);


        MyTeacherModel teacherModel = new MyTeacherModel();
        teacherModel.setId(4);
        teacherModel.setMobileNo("6387040456");
        teacherModel.setTeacherName("Anuradha");
        teacherModel.setStudentName("Ajay");
        myTeacherModelList.add(teacherModel);
        sessionConfig.setMyTeacherModelList(myTeacherModelList);

        myStringlist.add("Hello");
        myStringlist.add("HiBro");
        myStringlist.add("HowAre");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");
        myStringlist.add("FineBro");

        sessionConfig.setMyStringList(myStringlist);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        getMyLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
//RetrofitGetLoginApi();
        //   VolleyGetQuery();
        //VolleyPostResponse();


    }

    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You Are Here");
                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        String currentAddress = getAddressFromLocation(location);
                        if (!currentAddress.isEmpty()) {
                            TextView txt1 = findViewById(R.id.txt1);
                            txt1.setText(currentAddress);
                            // Use the currentAddress variable as needed
                            Log.d("Address", currentAddress);
                        }
                    }
                });
            }
        });
    }

    private String getAddressFromLocation(Location location) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        String addressText = "";
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                addressText = address.getAddressLine(0); // You can customize this to get more address details if needed.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressText;
    }

    void VolleyPostResponse() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://np.assignmart.com/api/User/Login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("status");
                            Toast.makeText(context, "" + data, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map getParams() {
                Map params = new HashMap<>();
                params.put("UserName", "Admin");
                params.put("Password", "123");
                return params;
            }
        };

        queue.add(stringRequest);

    }

    public void AddData(View view) {
        OwnersModel ownersModel = new OwnersModel();
        ownersModel.setOwnerName(edt_Owner_Name.getText().toString().trim());
        ownersModel.setName(edt_name.getText().toString().trim());
        ownersModel.setOwnerNumber(edt_Owner_Number.getText().toString().trim());

        if (edt_name.getText().toString().isEmpty() || edt_Owner_Name.getText().toString().isEmpty() || edt_Owner_Number.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please Fill All The Fields ", Toast.LENGTH_SHORT).show();
        } else {
            ownersModelList.add(ownersModel);
            edt_name.setText("");
            edt_Owner_Name.setText("");
            edt_Owner_Number.setText("");
            // Toast.makeText(context, ""+ownersModelList.size(), Toast.LENGTH_SHORT).show();
            // sessionConfig.setOwnersModelList(ownersModelList);
            setData();
        }


    }

    private void setData() {
        try {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            GridLayoutManager manager = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(manager);
            ListRemedialActivityAdapter listPloughingAdapter = new ListRemedialActivityAdapter(MainActivity.this, ownersModelList);
            recyclerView.setAdapter(listPloughingAdapter);


        } catch (Exception e) {
        }
    }

    void ValidateImeuNo() {
        RetrofitClient.getClient().getValidateIMEINO("2842db819f194616", "jehuahiu").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String xmlResponse = responseBody.string();
                        Log.d("My json", xmlResponse);

                        String jsonResponse = xmlResponse.substring(xmlResponse.indexOf("{"), xmlResponse.lastIndexOf("}") + 1);
                        Log.d("My json3", jsonResponse); }

                    } catch (Exception e){
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private static String extractJsonFromXml(String xmlResponse) {
        try {
            int startIndex = xmlResponse.indexOf("{");
            int endIndex = xmlResponse.lastIndexOf("}") + 1;

            if (startIndex != -1 && endIndex != -1) {
                return xmlResponse.substring(startIndex, endIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}