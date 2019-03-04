package com.automation.until;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetCaseList {
    private static String name;
    private static String path;

    public static List<String> getCaseList(JsonObject json) {
        List<String> caselist = new ArrayList<>();
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = (JsonObject) parser.parse(json.toString());
        JsonArray array = json.get("caselist").getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonObject subObject = array.get(i).getAsJsonObject();
            name = subObject.get("name").getAsString();
            path = subObject.get("path").getAsString();
            if (StringUtils.isNotBlank(path)) {
                path.replace("/",File.separator);
                path.replace("\\",File.separator);
                name = path + File.separator + name;
            }
            caselist.add(name+".xml");
        }
        return caselist;
    }
}
