package com.mart.cart_activity.Api;

import com.google.gson.annotations.SerializedName;

public class ResponseClass  {

    @SerializedName("status")
    private String status;

    @SerializedName("description")
    private Description description;

    public String getStatus() {
        return status;
    }

    public Description getDescription() {
        return description;
    }

    public static class Description {
        @SerializedName("desc")
        private String desc;

        public String getDesc() {
            return desc;
        }
    }

}
