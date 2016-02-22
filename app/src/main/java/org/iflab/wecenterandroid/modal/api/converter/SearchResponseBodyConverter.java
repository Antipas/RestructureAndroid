package org.iflab.wecenterandroid.modal.api.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.ExploreArticle;
import org.iflab.wecenterandroid.modal.explore.ExploreQuestion;
import org.iflab.wecenterandroid.modal.search.Search;
import org.iflab.wecenterandroid.modal.search.SearchArticle;
import org.iflab.wecenterandroid.modal.search.SearchQuestion;
import org.iflab.wecenterandroid.modal.search.SearchTopic;
import org.iflab.wecenterandroid.modal.search.SearchUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit.Converter;

/**
 * Created by Lyn on 16/2/10.
 */
public class SearchResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    SearchResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        List<Search> searchList = null;
        BufferedReader bufferedReader = new BufferedReader(value.charStream());
        StringBuilder result = new StringBuilder();
        String line = null;

        Search var3 = null;
        try {
            while((line = bufferedReader.readLine()) != null){
                result.append(line);
            }
            JSONObject obj = new JSONObject(result.toString());
            if (!obj.getString("err").equals("null")){
                Logger.getGlobal().severe(obj.getString("err"));
                return null;
            }
            JSONObject rowsObj = obj.getJSONObject("rsm");
            if(rowsObj.getInt("total_rows") != 0){
                searchList = new ArrayList();
                JSONArray rowsArray = rowsObj.getJSONArray("rows");
                int length = rowsArray.length();
                for(int i=0;i < length; i++){
                    JSONObject itemObj = rowsArray.getJSONObject(i);
                    String type = itemObj.getString("type");
                    switch (type){
                        case "questions":
                            var3 = this.gson.fromJson(itemObj.toString(), SearchQuestion.class);
                            break;
                        case "articles":
                            var3 = this.gson.fromJson(itemObj.toString(), SearchArticle.class);
                            break;
                        case "users":
                            var3 = this.gson.fromJson(itemObj.toString(), SearchUser.class);
                            break;
                        case "topics":
                            var3 = this.gson.fromJson(itemObj.toString(), SearchTopic.class);
                            break;
                        default:
                            break;
                    }
                    searchList.add(var3);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            ConverterUtil.closeQuietly(reader);
            bufferedReader.close();
        }

        return (T)searchList;
    }
}

