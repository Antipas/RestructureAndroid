package org.iflab.wecenterandroid.modal.api.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okio.Buffer;
import retrofit.Converter;

/**
 * Created by Lyn on 15/12/16.
 */
public class RequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson gson;
    private final Type type;

    RequestBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        OutputStreamWriter writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);

        try {
            this.gson.toJson(value, this.type, writer);
            writer.flush();
        } catch (IOException var5) {
            throw new AssertionError(var5);
        }

        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}