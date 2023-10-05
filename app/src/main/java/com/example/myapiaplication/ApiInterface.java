package com.example.myapiaplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("posts")
    Call<ResponseBody> getPost();

    @GET("OnlineCourseStudentDashboard/getTeacherDetails")
    Call<ResponseBody> getQueryParams(@Query("course") String Course,
                                      @Query("board") String Board);
    @FormUrlEncoded
    @POST("api/User/Login")
    Call<JsonObject> getLogin(@Field("UserName") String UserNamw,
                                      @Field("Password") String Password);

    @GET("ValidateIMEINO")
    Call<ResponseBody> getValidateIMEINO(@Query("IMEINO") String IMEINO,
                                      @Query("FirbaseRegistrationId") String FirbaseRegistrationId);







}
