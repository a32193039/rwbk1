package top.lrshuai.plus.springbootmybatisplus.test.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RwbkUtil {

    public static void main(String[] args) {

        Map dataMap = new HashMap();
        Map map = new HashMap();
        map.put("id","1");
        map.put("name","中四道口附近");
        map.put("ml","简介,名称,老师肯定,势力扩大飞机");
        map.put("ctbq","简介,名称,老师肯定,势力扩大飞机");
        map.put("rw",new ArrayList<>().add("1"));
        dataMap.put("vo",map);
        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件统一放至 com.lun.template 包下面
            //configuration.setClassForTemplateLoading(WordUtil.class,"/com/dhcc/modules/utils/word/freemarker");
            configuration.setDirectoryForTemplateLoading(new File("F:\\soft\\rwbk\\rwbk_static\\"));
            //获取模板
            Template template = configuration.getTemplate("template.html");

            //输出文件
            File outFile = new File("F:\\soft\\rwbk\\rwbk_static\\"+File.separator+"11.html");

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));


            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
