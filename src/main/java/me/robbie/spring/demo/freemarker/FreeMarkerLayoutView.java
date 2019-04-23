package me.robbie.spring.demo.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-22 23:22
 * @since [产品/模块版本]
 */
public class FreeMarkerLayoutView extends FreeMarkerView {

    public static final Logger logger = LoggerFactory.getLogger(FreeMarkerLayoutView.class);

    private String suffix = FreeMarkerProperties.DEFAULT_SUFFIX;

    /**
     * 获取模板后缀名
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

        FreeMarkerProperties  freeMarkerProperties = BeanFactoryUtils.beanOfTypeIncludingAncestors(
                getApplicationContext(), FreeMarkerProperties.class);

        if(freeMarkerProperties != null && freeMarkerProperties.getSuffix() != null){
            suffix = freeMarkerProperties.getSuffix();
        }
    }

    /**
     * 通过视图url 获取fragment文件路径并注入到model
     * @param model
     * @param request
     * @throws Exception
     */
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);

        String name  = getUrl();
        int index =  name.indexOf("@");
        if(index > 0){
            String path = "/" + name.substring(0, index) + suffix;

            model.put("fragment_file_path", path);

            //第2种方式把fragment文件内容作为变量。
            //Locale locale = RequestContextUtils.getLocale(request);
            //String fragment = renderFragment(model,path,locale);
            //model.put("fragment", fragment);
        }
    }


    /*private String renderFragment(Object resultObject, String fragmentTemplate,Locale locale) {
        StringWriter writer = new StringWriter();
        try {

            Template t = getConfiguration().getTemplate(fragmentTemplate,locale);
            t.process(resultObject, writer);
            String html = writer.getBuffer().toString();
            return html;
        } catch (TemplateException e) {
            logger.error("template[{}] parse exception : {}", fragmentTemplate,e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("freemarker template parsing error!");
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }*/

    /**
     * 通过视图名获取真正的模板
     * @param name
     * @param locale
     * @return
     * @throws IOException
     */
    @Override
    protected Template getTemplate(String name, Locale locale) throws IOException {
        int index =  name.indexOf("@");
        if(index <= 0){
            return super.getTemplate(name, locale);
        }

        String layout = name.substring(index+1);

        return super.getTemplate(layout,locale);
    }
}
