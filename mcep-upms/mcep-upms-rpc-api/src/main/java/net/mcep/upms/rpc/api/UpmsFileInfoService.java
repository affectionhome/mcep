package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsFileInfo;
import net.mcep.upms.dao.model.UpmsFileInfoExample;

import java.io.InputStream;
import java.util.List;

/**
* UpmsFileInfoService接口
* Created by shuzheng on 2018/1/12.
*/
public interface UpmsFileInfoService extends BaseService<UpmsFileInfo, UpmsFileInfoExample> {

    /**
     * 根据fileToken查多
     * @param fileToken
     * @return
     */
    List<UpmsFileInfo> selectByFileToken(String fileToken);

    /**
     * 上传
     * @param fileInfo
     * @param inputStream
     * @return
     */
    String upload(UpmsFileInfo fileInfo, InputStream inputStream);

    /**
     * 根据guid查询下载
     * @param id
     * @return
     */
    InputStream downloadById(Integer id);

    /**
     * 根据fileId 下载
     * @param fileId
     * @return
     */
    InputStream downloadByFileId(String fileId);

    /**
     * 删除文件
     * @param id
     * @return
     */
    boolean deleteRecoredAndFile(String id);

}