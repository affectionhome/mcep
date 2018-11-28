package net.mcep.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * 方法名称 : net.mcep.common.util.WordUtils
 * 作   者 : admin
 * 创建时间 : 2018/2/1 10:47
 * 方法描述 : word文档生成工具
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class WordUtils {

    //配置信息,代码本身写的还是很可读的,就不过多注解了
    private static Configuration configuration = null;
    //这里注意的是利用WordUtils的类加载器动态获得模板文件的位置
    private static String templateFolder =  WordUtils.class.getClassLoader().getResource("").getPath() + "word/templete/";
    //private static final String templateFolder =  "F:/";
    static {

        configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        try {
            configuration.setDirectoryForTemplateLoading(new File(templateFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static void createWord(String templete, Map map,String fileUrl) throws IOException {
        Template freemarkerTemplate = configuration.getTemplate(templete+".ftl");
        File file = null;
        try {
            // 调用工具类的createDoc方法生成Word文档
            file = createDoc(map,freemarkerTemplate,fileUrl);

        } finally {
        }
    }

    private static File createDoc(Map<?, ?> dataMap, Template template,String fileUrl) {
        File f = new File(fileUrl);
        Template t = template;
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }
}
