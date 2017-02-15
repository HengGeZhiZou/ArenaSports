package team.lw.arena.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import team.lw.arena.exception.RequestException;

class GsonUtil {

    private static Gson gson=new GsonBuilder().create();

     static <T> T GsonToBean(String gsonString, Class<T> cls) throws RequestException {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

}
