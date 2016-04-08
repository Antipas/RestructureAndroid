package org.iflab.wecenterandroid.modal.api.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.home.Home501;
import org.iflab.wecenterandroid.modal.home.Home503;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Converter;

/**
 * Created by Lyn on 15/12/16.
 */
public class HomeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    HomeResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        List<Object> homeList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(value.charStream());
        StringBuilder result = new StringBuilder();
        String line = null;

        Home var3 = null;
        try {
            while((line = bufferedReader.readLine()) != null){
                result.append(line);
            }
            JSONObject obj = new JSONObject(result.toString());
            JSONObject rowsObj = obj.getJSONObject("rsm");
            if(rowsObj.getInt("total_rows") != 0){
                JSONArray rowsArray = rowsObj.getJSONArray("rows");
                int length = rowsArray.length();
                for(int i=0;i < length; i++){
                    JSONObject itemObj = rowsArray.getJSONObject(i);
                    int action = itemObj.getInt("associate_action");
                    switch (action){
                        case 501:
                            var3 = this.gson.fromJson(itemObj.toString(), Home501.class);
                            break;
                        case 502:
                            var3 = this.gson.fromJson(itemObj.toString(), Home501.class);
                            break;
                        case 503:
                            var3 = this.gson.fromJson(itemObj.toString(), Home501.class);
                            break;
                    }
                    homeList.add(var3);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            ConverterUtil.closeQuietly(reader);
            bufferedReader.close();
        }

        return (T)homeList;
    }

}
