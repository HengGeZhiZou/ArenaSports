package team.lw.arena.util;



import team.lw.arena.exception.RequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;


public class ReadRequestMessage {

    @SuppressWarnings("unchecked")
    public static <T> T read(HttpServletRequest request, HttpServletResponse response, Class<T> tClass) throws RequestException {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            StringBuilder json = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return GsonUtil.GsonToBean(json.toString(), tClass);
        } catch (Exception p) {
            throw new RequestException("Request fail...");
        }
    }
}
