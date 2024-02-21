package top.lrshuai.plus.springbootmybatisplus.rwbk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lrshuai.plus.springbootmybatisplus.rwbk.entity.CnRwbk;
import top.lrshuai.plus.springbootmybatisplus.rwbk.service.ICnRwbkService;
import top.lrshuai.plus.springbootmybatisplus.test.entity.dto.TestDTO;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2024-02-21
 */
@RestController
@RequestMapping("/rwbk")
public class CnRwbkController {

    @Autowired
    ICnRwbkService cnRwbkService;

    @GetMapping("/test")
    public Object test(TestDTO dto) throws IllegalAccessException {
        QueryWrapper<CnRwbk> wrapper = new QueryWrapper<>();
        wrapper.eq("isflag",0).or().isNull("isflag");
        List<CnRwbk> list = cnRwbkService.list(wrapper);

        for (CnRwbk cnRwbk : list) {
            Map<String, Object> map = convert(cnRwbk);

            // 遍历 Map 打印字段名和字段值
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            Map datamap = new HashMap();
            datamap.put("vo",map);
            createHtml(datamap);
        }
        return "success";
    }

    public void createHtml(Map dataMap) {

        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件统一放至 com.lun.template 包下面
            //configuration.setClassForTemplateLoading(WordUtil.class,"/com/dhcc/modules/utils/word/freemarker");
            ClassPathResource cpr = new ClassPathResource("template");
            File file = cpr.getFile();
            configuration.setDirectoryForTemplateLoading(file);
            //获取模板
            Template template = configuration.getTemplate("template.html");

            //输出文件
            File outFile = new File("E:\\github\\rwbk\\"+File.separator+"11.html");

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

    public Map<String, Object> convert(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(obj);
            map.put(fieldName, fieldValue);
        }

        return map;
    }
}
