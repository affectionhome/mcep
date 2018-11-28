package net.mcep.upms.rpc.service.impl;

import com.caucho.hessian.io.HessianInput;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsFileInfoMapper;
import net.mcep.upms.dao.model.UpmsFileInfo;
import net.mcep.upms.dao.model.UpmsFileInfoExample;
import net.mcep.upms.rpc.api.UpmsFileInfoService;
import net.mcep.upms.utils.FastDFSClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* UpmsFileInfoService实现
* Created by shuzheng on 2018/1/12.
*/
@Service
@Transactional
@BaseService
public class UpmsFileInfoServiceImpl extends BaseServiceImpl<UpmsFileInfoMapper, UpmsFileInfo, UpmsFileInfoExample> implements UpmsFileInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsFileInfoServiceImpl.class);

    @Autowired
    UpmsFileInfoMapper upmsFileInfoMapper;





    @Override
    public List<UpmsFileInfo> selectByFileToken(String fileToken) {

        if(StringUtils.isBlank(fileToken))
            return null;

        UpmsFileInfoExample upmsFileInfoExample = new UpmsFileInfoExample();
        upmsFileInfoExample.or().andFileTokenEqualTo(fileToken);
        List<UpmsFileInfo> rows = selectByExample(upmsFileInfoExample);
        return rows;
    }

    @Override
    public String upload(UpmsFileInfo fileInfo,InputStream inputStream) {
        //上传到FastDFS
        //HessianInput hi = new HessianInput(inputStream);
        String fileId = FastDFSClient.uploadFile(inputStream,Integer.valueOf(fileInfo.getFileSize().toString()),fileInfo.getFileName());
        fileInfo.setFileId(fileId);
        //保存记录到数据库
        insertSelective(fileInfo);
        int id = fileInfo.getId();
        return id+"";
    }

    @Override
    public InputStream downloadById(Integer id) {
        //从数据库读取文件的相关信息
        UpmsFileInfo file = selectByPrimaryKey(id);
        if(file == null)
            return null;
        String fileId = file.getFileId();

        //从fastDFS上下载文件
        InputStream inputStream = FastDFSClient.downloadFile(fileId);
        return inputStream;
    }

    @Override
    public InputStream downloadByFileId(String fileId) {
        if(StringUtils.isBlank(fileId))
            return null;
        InputStream inputStream = FastDFSClient.downloadFile(fileId);
        return inputStream;
    }

    @Override
    public boolean deleteRecoredAndFile(String id) {
        UpmsFileInfo info = selectByPrimaryKey(Integer.valueOf(id));
        String fileId = info.getFileId();
        FastDFSClient.deleteFile(fileId);
        deleteByPrimaryKey(Integer.valueOf(id));
        return true;
    }


}