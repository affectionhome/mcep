package net.mcep.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压mcepAdmin-x.x.x.jar到resources目录
 */
public class McepAdminUtil implements InitializingBean, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(McepAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("===== 开始解压mcep-admin =====");
        String version = PropertiesFileUtil.getInstance("mcep-admin-client").get("mcep.admin.version");
        LOGGER.info("mcep-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/mcep-admin-" + version + ".jar");
        LOGGER.info("mcep-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/mcep-admin";
        LOGGER.info("mcep-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压mcep-admin完成 =====");
    }

}
