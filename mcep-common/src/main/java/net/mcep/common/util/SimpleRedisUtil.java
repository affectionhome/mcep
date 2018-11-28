package net.mcep.common.util;

/**
 * 方法名称 : net.mcep.common.util.SimpleRedisUtil
 * 作   者 : admin
 * 创建时间 : 2018/2/8 14:26
 * 方法描述 : 从redis中直接存取一些简单的数据
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class SimpleRedisUtil {


    public static String getUserNameById(String userId){
        String userName = RedisUtil.get("mcep-user-"+userId);
        return userName != null ? userName : "";
    }


}
