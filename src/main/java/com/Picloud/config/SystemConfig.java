package com.Picloud.config;
import org.springframework.stereotype.Service;
@Service
public class SystemConfig {
//private String fileSystemPath = "localhost:9000";
//private String uploadPath = "hdfs://localhost:9000/upload";
//private static String systemPath = "/home/jeff/workspace";
//private String localUploadPath = "/home/jeff/workspace/upload";
//private String imagePath = "http://localhost:8080/Picloud/server/";
//private static String logoTmpSrc = "/home/jeff/workspace/logoTmp";
//
//private static String nameNodeInfo = "http://localhost:50070/jmx?qry=Hadoop:service=NameNode,name=NameNodeInfo";
//private static String nameNodeStatus = "http://localhost:50070/jmx?qry=Hadoop:service=NameNode,name=NameNodeStatus";
//private static String FSNamesystemState = "http://localhost:50070/jmx?qry=Hadoop:service=NameNode,name=FSNamesystemState";
//private static String memory = "http://localhost:50070/jmx?qry=java.lang:type=Memory";

private double maxSyncSize = 64.0;
private double maxFileSize = 2.0;
private double syncSizeStep = 2.0;
public void addSyncSize(){
this.maxSyncSize = this.maxSyncSize + this.syncSizeStep;
}
public void subSyncSize(){
this.maxSyncSize = this.maxSyncSize - this.syncSizeStep;
}
public double getSyncSizeStep() {
return syncSizeStep;
}
//public static String getNameNodeInfo() {
//	return nameNodeInfo;
//}
//public static void setNameNodeInfo(String nameNodeInfo) {
//	SystemConfig.nameNodeInfo = nameNodeInfo;
//}
//public static String getNameNodeStatus() {
//	return nameNodeStatus;
//}
//public static void setNameNodeStatus(String nameNodeStatus) {
//	SystemConfig.nameNodeStatus = nameNodeStatus;
//}
//
//public static String getLogoTmpSrc() {
//	return logoTmpSrc;
//}
//public void setLogoTmpSrc(String logoTmpSrc) {
//	this.logoTmpSrc = logoTmpSrc;
//}
//public static String getFSNamesystemState() {
//	return FSNamesystemState;
//}
//public static void setFSNamesystemState(String fSNamesystemState) {
//	FSNamesystemState = fSNamesystemState;
//}
//public static String getMemory() {
//	return memory;
//}
//public static void setMemory(String memory) {
//	SystemConfig.memory = memory;
//}
public void setSyncSizeStep(double syncSizeStep) {
this.syncSizeStep = syncSizeStep;
}
//public static String getSystemPath() {
//// return "/home/jeff/workspace";
//return systemPath;
//}
public double getMaxSyncSize() {
return maxSyncSize;
}
public void setMaxSyncSize(double maxSyncSize) {
this.maxSyncSize = maxSyncSize;
}
public double getMaxFileSize() {
return maxFileSize;
}
public void setMaxFileSize(double maxFileSize) {
this.maxFileSize = maxFileSize;
}
//public String getFileSystemPath() {
//return fileSystemPath;
//}
//public void setFileSystemPath(String fileSystemPath) {
//this.fileSystemPath = fileSystemPath;
//}
//public String getUploadPath() {
//return uploadPath;
//}
//public void setUploadPath(String uploadPath) {
//this.uploadPath = uploadPath;
//}
//public String getLocalUploadPath() {
//return localUploadPath;
//}
//public void setLocalUploadPath(String localUploadPath) {
//this.localUploadPath = localUploadPath;
//}
//public void setSystemPath(String systemPath) {
//this.systemPath = systemPath;
//}
//public void setImagePath(String imagePath) {
//this.imagePath = imagePath;
//}
//public String getImagePath() {
//return imagePath;
//}
}