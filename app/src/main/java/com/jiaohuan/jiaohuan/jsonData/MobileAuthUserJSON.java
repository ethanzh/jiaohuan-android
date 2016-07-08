
package com.jiaohuan.jiaohuan.jsonData; import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

;

@Generated("org.jsonschema2pojo")
public class MobileAuthUserJSON {

    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Authenticated")
    @Expose
    private Boolean authenticated;

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The authenticated
     */
    public Boolean getAuthenticated() {
        return authenticated;
    }

    /**
     * 
     * @param authenticated
     *     The Authenticated
     */
    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

}
