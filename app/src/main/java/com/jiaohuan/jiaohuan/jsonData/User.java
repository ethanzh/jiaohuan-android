
package com.jiaohuan.jiaohuan.jsonData;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jiaohuan.jiaohuan.CurrentToken;

import javax.annotation.Generated;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Generated("org.jsonschema2pojo")
public class User {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;
    @SerializedName("date_joined")
    @Expose
    private String dateJoined;

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The isStaff
     */
    public Boolean getIsStaff() {
        return isStaff;
    }

    /**
     *
     * @param isStaff
     * The is_staff
     */
    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    /**
     *
     * @return
     * The dateJoined
     */
    public String getDateJoined() {
        return dateJoined;
    }

    /**
     *
     * @param dateJoined
     * The date_joined
     */
    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }


    private static User ourInstance = getUserDataFromServer();

    public static User getInstance() {

        return ourInstance;

    }

    public static User getUserDataFromServer(){
        final User cu = new User();
        String authentication = "Token " + CurrentToken.getCurrent();

        UserAPI.Factory.getInstance().getPrimaryKey(authentication).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String username = response.body().getUsername();
                Log.wtf("SETTING", "setting username");
                cu.setUsername(username);


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("FAIL",""+t.getMessage());
            }
        });
        return cu;
    }

}
