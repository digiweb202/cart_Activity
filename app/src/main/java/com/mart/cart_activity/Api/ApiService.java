package com.mart.cart_activity.Api;
import com.mart.cart_activity.ApiModel.ContentModel;
import com.mart.cart_activity.ApiModel.GetProductModel;
import com.mart.cart_activity.ApiModel.SingleProductModel;
import com.mart.cart_activity.ApiModel.UpdateUserModel;
import com.mart.cart_activity.ApiModel.UserModel;
import com.mart.cart_activity.ApiResponse.CategoriesResponse;
import com.mart.cart_activity.ApiResponse.LoginResponse;
import com.mart.cart_activity.ApiResponse.UserUpdateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("signup.php")
    Call<String> signup(
            @Field("username") String username,
            @Field("first_name") String firstName,
            @Field("email") String email,
            @Field("password") String password
    );
//
//    @GET("your_php_script_name.php") // Replace with the actual PHP script name
//    Call<LoginResponse> loginUser(
//            @Query("email") String email,
//            @Query("password") String password
//    );
//    @FormUrlEncoded
//    @POST("your_php_script_name_for_update.php") // Replace with the actual PHP script name for user update
//    Call<UserUpdateResponse> updateUser(
//            @Field("email") String email,
//            @Field("username") String username,
//            @Field("password") String password,
//            @Field("fullname") String fullname,
//            @Field("number") String number,
//            @Field("address") String address,
//            @Field("nickname") String nickname
//    );

    @GET("api_all_browse_categories.php") // Replace with the actual path to your PHP script
    Call<List<CategoriesResponse>> getCategories();

    @GET("api_product_data.php") // replace with your actual API endpoint
    Call<List<GetProductModel>> fetchData(
            @Query("count") int count,
            @Query("page") int page
    );

    @GET("api_single_product_data.php")
    Call<List<SingleProductModel>> getData(
            @Query("product_id") String productID,
            @Query("seller_sku") String sellerSKU
    );

    @GET("api_product_type.php")
    Call<ApiResponse> getCombinedData(@Query("productType") String productType);

    @GET("api_getcontent.php") // Replace with your actual API endpoint
    Call<List<ContentModel>> getContent();
    @GET("api_searchquery.php") // Replace with your actual PHP script name
    Call<ApiResponse> getWatchesData(@Query("searchQuery") String searchQuery, @Query("page") int page);

    @GET("api_login.php")
    Call<LoginResponse> login(@Query("email") String email, @Query("password") String password);

    @FormUrlEncoded
    @PUT("api_userprofile_update.php")
    Call<UpdateUserModel> updateUserProfile(
            @Field("userId") int userId,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("fullname") String fullname,
            @Field("nickname") String nickname,
            @Field("number") String number,
            @Field("address") String address,
            @Field("pincode") String pincode
    );

    @FormUrlEncoded
    @POST("api_signup.php") // Adjust the endpoint URL accordingly
    Call<UserModel> signup(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

}
