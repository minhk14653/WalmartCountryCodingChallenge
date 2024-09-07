package com.example.walmartcountrycodingchallenge.model

import com.example.walmartcountrycodingchallenge.model.Currency
import com.example.walmartcountrycodingchallenge.model.Language
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Country : Serializable {
    @SerializedName("capital") var capital: String? = null
    @SerializedName("code") var code: String? = null
    @SerializedName("currency") var currency: Currency? = null
    @SerializedName("flag") var flag: String? = null
    @SerializedName("language") var language: Language? = null
    @SerializedName("name") var name: String? = null
    @SerializedName("region") var region: String? = null
}