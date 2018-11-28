package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsFileInfoMapper;
import net.mcep.upms.dao.model.UpmsFileInfo;
import net.mcep.upms.dao.model.UpmsFileInfoExample;

import java.io.InputStream;
import java.util.List;

/**
* 降级实现UpmsFileInfoService接口
* Created by shuzheng on 2018/1/12.
*/
public class UpmsFileInfoServiceMock extends BaseServiceMock<UpmsFileInfoMapper, UpmsFileInfo, UpmsFileInfoExample> implements UpmsFileInfoService {

    @Override
    public List<UpmsFileInfo> selectByFileToken(String fileToken) {
        return null;
    }

    @Override
    public String upload(UpmsFileInfo fileInfo, InputStream inputStream) {
        return null;
    }

    @Override
    public InputStream downloadById(Integer id) {
        return null;
    }

    @Override
    public InputStream downloadByFileId(String fileId) {
        return null;
    }

    @Override
    public boolean deleteRecoredAndFile(String id) {
        return false;
    }

}
