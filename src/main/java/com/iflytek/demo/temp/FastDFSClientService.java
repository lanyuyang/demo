//package com.iflytek.demo.temp;//package com.iflytek;
//
//import com.iflytek.scene.adapter.common.exception.FastDfsException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.io.FileUtils;
//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.util.StringUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//
//@Slf4j
//@Service
//public class FastDFSClientService {
//
//	@Value("${fastdfs.group}")
//	private String group;
//	@Value("${fastdfs.host}")
//	private String host;
//	@Value("${fastdfs.secret.Key}")
//	private String secretKey;
//
//	@Value("${fastdfs.conf.file}")
//	private String fastdfsConfFile;
//
//	public FastDFSClientService(@Value("${fastdfs.conf.file}") String fastdfsConfFile) throws Exception {
//		String conf = this.getClass().getResource("/" + fastdfsConfFile).getPath();
//		ClientGlobal.init(conf);
//	}
//
//	/**
//	 * 上传文件方法
//	 * <p>
//	 * Title: uploadFile
//	 * </p>
//	 * <p>
//	 * Description:
//	 * </p>
//	 *
//	 * @param fileContent
//	 *            文件的内容，字节数组
//	 * @param extName
//	 *            文件扩展名
//	 * @param metas
//	 *            文件扩展信息
//	 * @return
//	 * @throws Exception
//	 */
//	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
//		log.debug("uploadFile extName{}",extName);
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = null;
//		try {
//			trackerServer = trackerClient.getConnection();
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			return storageClient.upload_file1(fileContent, extName, metas);
//		} catch (IOException e) {
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			return storageClient.upload_file1(fileContent, extName, metas);
//		} finally {
//			if (trackerServer != null) {
//				trackerServer.close();
//			}
//		}
//	}
//
//	public String uploadFile(byte[] fileContent) throws Exception {
//		return uploadFile(fileContent, null, null);
//	}
//
//	public String uploadFile(byte[] fileContent, String extName) throws Exception {
//		return uploadFile(fileContent, extName, null);
//	}
//
//	/**
//	 * 上传图片文件
//	 *
//	 * @param imgContent
//	 * @return
//	 * @throws Exception
//	 */
//	public String uploadImg(String imgContent) throws Exception {
//		// FIXME 临时解决文件上传IOException: recv package size -1 != 10问题
//		String[] content = imgContent.split(";");
//		String[] date = content[0].split(":");
//		String[] extName = date[1].split("/");
//		String[] base64Img = content[1].split(",");
//
//		log.debug("uploadImg extName{}",extName[1]);
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = null;
//		try {
//			trackerServer = trackerClient.getConnection();
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			return storageClient.upload_file1(null, Base64.decodeBase64(base64Img[1]), extName[1], null);
//		} catch (IOException e) {
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			return storageClient.upload_file1(null, Base64.decodeBase64(base64Img[1]), extName[1], null);
//		} finally {
//			if (trackerServer != null) {
//				trackerServer.close();
//			}
//		}
//
//	}
//
//	/**
//	 * 上传图片
//	 *
//	 * @param imgFile
//	 *            图片文件
//	 * @return
//	 * @throws Exception
//	 */
//	public String uploadImgFile(File imgFile) throws Exception {
//		String fileName = imgFile.getName();
//		String extName = StringUtils.substring(fileName, fileName.lastIndexOf(".") + 1);
//		byte[] fileBytes = FileUtils.readFileToByteArray(imgFile);
//		log.debug("uploadImgFile extName{}",extName);
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = null;
//		try {
//			trackerServer = trackerClient.getConnection();
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			return storageClient.upload_file1(null, fileBytes, extName, null);
//		} catch (IOException e) {
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			return storageClient.upload_file1(null, fileBytes, extName, null);
//		} finally {
//			if (trackerServer != null) {
//				trackerServer.close();
//			}
//		}
//	}
//
//	/**
//	 * 获取访问Url
//	 *
//	 * @param fileName
//	 * @return
//	 * @throws MyException
//	 * @throws NoSuchAlgorithmException
//	 * @throws UnsupportedEncodingException
//	 */
//	public String getTokenUrl(String fileName) {
//		int ts = (int) (System.currentTimeMillis() / 1000);
//		String token;
//		try {
//			token = ProtoCommon.getToken(fileName.replace(group, ""), ts, secretKey);
//		} catch (Exception e) {
//			throw new FastDfsException(1001, "获取链接失败！");
//		}
//		StringBuffer sb = new StringBuffer().append(host).append(fileName);
//		sb.append("?ts=").append(ts).append("&token=").append(token);
//		log.debug("tokenUrl:{}",sb.toString());
//		return sb.toString();
//	}
//
//	/**
//	 * 删除文件
//	 *
//	 * @param fileName
//	 * @throws IOException
//	 * @throws MyException
//	 */
//	public void delFile(String fileName) throws IOException, MyException {
//		log.debug("delFile:{}",fileName);
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = null;
//		try {
//			trackerServer = trackerClient.getConnection();
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			int i = storageClient.delete_file1(fileName);
//			if (i != 0) {
//				throw new FastDfsException(1001, "删除文件失败！");
//			}
//		} catch (IOException e) {
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			int i = storageClient.delete_file1(fileName);
//			if (i != 0) {
//				throw new FastDfsException(1001, "删除文件失败！");
//			}
//		} finally {
//			if (trackerServer != null) {
//				trackerServer.close();
//			}
//		}
//	}
//
//	/**
//	 * 下载图片并转为base64字符串
//	 *
//	 * @param fileName
//	 * @return
//	 * @throws IOException
//	 * @throws MyException
//	 */
//	public String downLoadFile(String fileName) throws IOException, MyException {
//		log.debug("downLoadFile:{}",fileName);
//		String[] content = fileName.split("\\.");
//		byte[] b = null;
//
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = null;
//		try {
//			trackerServer = trackerClient.getConnection();
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			b = storageClient.download_file1(fileName);
//		} catch (IOException e) {
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			b = storageClient.download_file1(fileName);
//		} finally {
//			if (trackerServer != null) {
//				trackerServer.close();
//			}
//		}
//
//		String imgContent = Base64.encodeBase64String(b);
//		return "data:image/" + content[1] + ";base64," + imgContent;
//	}
//
//	/**
//	 * 下载图片字节
//	 *
//	 * @param fileName
//	 * @return
//	 * @throws IOException
//	 * @throws MyException
//	 */
//	public byte[] downLoadFileByte(String fileName) throws IOException, MyException {
//		log.debug("downLoadFileByte:{}",fileName);
//		byte[] b = null;
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = null;
//		try {
//			trackerServer = trackerClient.getConnection();
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			b = storageClient.download_file1(fileName);
//		} catch (IOException e) {
//			StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//			b = storageClient.download_file1(fileName);
//		} finally {
//			if (trackerServer != null) {
//				trackerServer.close();
//			}
//		}
//
//		return b;
//	}
//
//}