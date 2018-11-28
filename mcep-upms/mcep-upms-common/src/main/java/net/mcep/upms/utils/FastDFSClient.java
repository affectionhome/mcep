package net.mcep.upms.utils;

import com.caucho.hessian.io.HessianInput;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * 
 * FastDFS分布式文件系统操作客户端 .
 */
public class FastDFSClient
{

	private static final String CONF_FILENAME = "/fdfs_client.conf";
	private static StorageClient1 storageClient1 = null;

	private static final Logger LOG = LoggerFactory.getLogger(FastDFSClient.class);

	/**
	 * 只加载一次.
	 */
	static
	{
		try
		{
			ClientGlobal.init(CONF_FILENAME);
			TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
			TrackerServer trackerServer = trackerClient.getConnection();
			if (trackerServer == null)
			{
				LOG.error("getConnection return null");
			}
			StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
			if (storageServer == null)
			{
				LOG.error("getStoreStorage return null");
			}
			storageClient1 = new StorageClient1(trackerServer, storageServer);
			ProtoCommon.activeTest(storageServer.getSocket());
		} catch (Exception e)
		{
			e.printStackTrace();
			LOG.error("load conf error {}", e);
		}
	}

	public static String uploadFile(InputStream fis, int length, String fileName)
	{
		try
		{
			NameValuePair[] meta_list = null; // new NameValuePair[0];
			byte[] file_buff = null;
			if (fis != null)
			{
				file_buff = new byte[length];
				fis.read(file_buff);
			}
			String fileid = storageClient1.upload_file1(file_buff, getFileExt(fileName), meta_list);
			return fileid;
		} catch (Exception ex)
		{
			LOG.error("uploadFile error, fileName={}.", fileName, ex);
			return null;
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					LOG.error("FileInputStream close error", e);
				}
			}
		}
	}

	public static String uploadFile(InputStream fis, String fileName)
	{
		try
		{
			NameValuePair[] meta_list = null; // new NameValuePair[0];
			byte[] file_buff = null;
			if (fis != null)
			{
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}
			String fileid = storageClient1.upload_file1(file_buff, getFileExt(fileName), meta_list);
			return fileid;
		} catch (Exception ex)
		{
			LOG.error("uploadFile error, fileName={}.", fileName, ex);
			return null;
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					LOG.error("FileInputStream close error", e);
				}
			}
		}
	}

	/**
	 * 
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名
	 * @return 返回Null则为失败
	 */
	public static String uploadFile(File file, String fileName)
	{
		FileInputStream fis = null;
		try
		{
			NameValuePair[] meta_list = null; // new NameValuePair[0];
			fis = new FileInputStream(file);
			byte[] file_buff = null;
			if (fis != null)
			{
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}
			String fileid = storageClient1.upload_file1(file_buff, getFileExt(fileName), meta_list);
			return fileid;
		} catch (Exception ex)
		{
			LOG.error("uploadFile error, fileName={}.", fileName, ex);
			return null;
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					LOG.error("FileInputStream close error", e);
				}
			}
		}
	}

	/**
	 * 根据组名和远程文件名来删除一个文件
	 * 
	 * @param groupName
	 *            例如 "group1" 如果不指定该值，默认为group1
	 * @param fileName
	 *            例如"M00/00/00/wKgxgk5HbLvfP86RAAAAChd9X1Y736.jpg"
	 * @return 0为成功，非0为失败，具体为错误代码
	 */
	public static int deleteFile(String groupName, String fileName)
	{
		try
		{
			int result = storageClient1.delete_file(groupName == null ? "group1" : groupName, fileName);
			return result;
		} catch (Exception ex)
		{
			LOG.error("deleteFile error, fileName={}.", fileName, ex);
			return 0;
		}
	}

	/**
	 * 根据fileId来删除一个文件（我们现在用的就是这样的方式，上传文件时直接将fileId保存在了数据库中）
	 * 
	 * @param fileId
	 *            file_id源码中的解释file_id the file id(including group name and
	 *            filename);例如
	 *            group1/M00/00/00/ooYBAFM6MpmAHM91AAAEgdpiRC0012.xml
	 * @return 0为成功，非0为失败，具体为错误代码
	 */
	public static int deleteFile(String fileId)
	{
		try
		{
			int result = storageClient1.delete_file1(fileId);
			return result;
		} catch (Exception ex)
		{
			LOG.error("deleteFile error, fileId={}.", fileId, ex);
			return 0;
		}
	}

	/**
	 * 修改一个已经存在的文件
	 * 
	 * @param oldFileId
	 *            原来旧文件的fileId, file_id源码中的解释file_id the file id(including group
	 *            name and filename);例如
	 *            group1/M00/00/00/ooYBAFM6MpmAHM91AAAEgdpiRC0012.xml
	 * @param file
	 *            新文件
	 * @param filePath
	 *            新文件路径
	 * @return 返回空则为失败
	 */
	public static String modifyFile(String oldFileId, File file, String filePath)
	{
		String fileid = null;
		try
		{
			// 先上传
			fileid = uploadFile(file, filePath);
			if (fileid == null)
			{
				return null;
			}
			// 再删除
			int delResult = deleteFile(oldFileId);
			if (delResult != 0)
			{
				return null;
			}
		} catch (Exception ex)
		{
			LOG.error("modifyFile error, oldFileId={}.", oldFileId, ex);
			return null;
		}
		return fileid;
	}

	/**
	 * 文件下载
	 * 
	 * @param fileId
	 * @return 返回一个流
	 */
	public static InputStream downloadFile(String fileId)
	{
		try
		{
			InputStream inputStream = null;
			byte[] bytes = storageClient1.download_file1(fileId);
			if (bytes != null)
			{
				inputStream = new ByteArrayInputStream(bytes);
			}
			return inputStream;
		} catch (Exception ex)
		{
			LOG.error("downloadFile error, fileId={}.", fileId, ex);
			return null;
		}
	}

	/**
	 * 获取文件后缀名（不带点）.
	 * 
	 * @return 如："jpg" or "".
	 */
	private static String getFileExt(String fileName)
	{
		if (StringUtils.isEmpty(fileName) || !fileName.contains("."))
		{
			return "";
		} else
		{
			return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
		}
	}
}
