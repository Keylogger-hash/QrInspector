package com.pavelmorozov.scannercode.http

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

fun makeHttpRequest(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val response = client.newCall(request).execute()
    return response.body!!.string()
}

fun parseJsonData(jsonString: String): List<JSONObject> {
    val jsonArray = JSONArray(jsonString)
    val dataItems = mutableListOf<JSONObject>()
    for (i in 0 until jsonArray.length()) {
        dataItems.add(jsonArray.getJSONObject(i))
    }
    return dataItems
}
fun makeHttpPostRequest(url: String,content: String): String {
    val client = OkHttpClient()
    val jsonObject = JSONObject()
    jsonObject.put("error",content)

    val mediaType = "application/json".toMediaType()
    val body = jsonObject.toString().toRequestBody(mediaType)


    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    val response = client.newCall(request).execute()
    return response.body!!.string()
}
