package com.u9porn.parser.v9porn.d20210320;

import com.u9porn.MyApplication;
import com.u9porn.data.db.entity.VideoResult;
import com.u9porn.data.model.User;
import com.u9porn.parser.v9porn.BaseVideoPlayUrlParser;
import com.u9porn.parser.v9porn.VideoPlayUrlParser;
import com.u9porn.utils.AppUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

import javax.inject.Inject;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class VideoUrlParser extends BaseVideoPlayUrlParser implements VideoPlayUrlParser {

    @Inject
    public VideoUrlParser() {

    }

    @Override
    public VideoResult parseVideoPlayUrl(String html, User user) {
        VideoResult videoResult = new VideoResult();
        Document document = Jsoup.parse(html);
        Elements metas = document.select("meta");

        if(metas==null||metas.isEmpty()){
            return null;
        }
        String videoUrl = "";
        for (Element element : metas){
            if(element.hasAttr("property")  && Objects.equals(element.attr("property"), "og:video:url")){
                videoUrl = element.attr("content");
            }
        }
        videoResult.setVideoUrl(videoUrl);
//        parserOtherInfo(document, videoResult, user);

        return videoResult;
    }

    public interface JavaScriptInterface {
        String strencode2(String str1);
    }

}
