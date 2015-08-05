package com.Picloud.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Picloud.config.SystemConfig;
import com.Picloud.exception.ProcessException;
import com.Picloud.hibernate.dao.impl.SpaceDaoImpl;
import com.Picloud.hibernate.dao.impl.UserDaoImpl;
import com.Picloud.hibernate.entities.User;
import com.Picloud.utils.PropertiesUtil;
import com.Picloud.web.dao.impl.ImageDaoImpl;
import com.Picloud.web.dao.impl.InfoDaoImpl;
import com.Picloud.web.dao.impl.LogDaoImpl;
import com.Picloud.web.model.Image;

@Service
public class ImageOffLine {
        @Autowired
        private InfoDaoImpl infoDaoImpl;
        @Autowired
        private SystemConfig systemConfig;
        @Autowired
        private ImageDaoImpl mImageDaoImpl;
        @Autowired
        private UserDaoImpl mUserDaoImpl;
        @Autowired
        private SpaceDaoImpl  mSpaceDaoImpl;
        @Autowired
        private LogDaoImpl mLogDaoImpl;
        
        public ImageOffLine() {
                super();
        }
        
        public void offLine(int num[], String imagekey, User user) throws Exception{
                int uid = user.getUid();
                if(num[0] == 1){
                        scaleUpdate(imagekey,uid);
                }
                if(num[1] == 1){
                        cropUpdate(imagekey, uid);
                }
                if(num[2] == 1){
                        watermarkUpdate(imagekey, uid, user.getImageLogo());
                }
                if(num[3] == 1){
                        textmarkUpdate(imagekey, uid, user.getTextLogo());
                }
                if(num[4] == 1){
                        lomoUpdate(imagekey, uid);
                }
                if(num[5] == 1){
                        gothamUpdate(imagekey, uid);
                }
        }

        public void scaleUpdate(String imageKey, int uid) throws Exception {
                Image image = mImageDaoImpl.find(imageKey);
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                byte[] buffer = imageReader.readPicture(imageKey);
                GraphicMagick gm = new GraphicMagick(buffer, image.getType());
                byte[] bufferOut = gm.scaleImage(200, 200);
                if (bufferOut != null) {
                        ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                        imageUpdate.updateImage(bufferOut, String.valueOf(uid), image.getSpace(),imageKey);
                } else {
                        throw new ProcessException("请输入正确的参数！");
                }
        }
        
        public void cropUpdate(String imageKey, int uid) throws Exception {
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                byte[] buffer = imageReader.readPicture(imageKey);
                GraphicMagick gm = new GraphicMagick(buffer, "jpg");
                byte[] bufferOut = gm.cropImage(200, 200, 0, 0);
                if (bufferOut != null) {
                        Image image = mImageDaoImpl.find(imageKey);
                        ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                        imageUpdate.updateImage(bufferOut, String.valueOf(uid), image.getSpace(),imageKey);
                } else {
                        throw new ProcessException("请输入正确的参数！");
                }
        }
        
        public void  watermarkUpdate(String imageKey,int uid, String logo) throws Exception {
                if(logo == null)
                        return;
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                byte[] buffer = imageReader.readPicture(imageKey);
                GraphicMagick gm = new GraphicMagick(buffer, "jpg");
                String logoSrc = PropertiesUtil.getValue("imagePath") + logo;
                byte[] bufferOut = gm.imgWaterMask(logoSrc, 100, 100, 10, 10, 100);
                if (bufferOut != null) {
                        Image image = mImageDaoImpl.find(imageKey);
                        ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                        imageUpdate.updateImage(bufferOut, String.valueOf(uid), image.getSpace(),imageKey);
                } else {
                        throw new ProcessException("请输入正确的参数！");
                }
        }
        
        public void  textmarkUpdate(String imageKey,int uid, String text) throws Exception {
                if(text == null)
                        return;
                Image image = mImageDaoImpl.find(imageKey);
                ImageReader imageReader = new ImageReader(infoDaoImpl);         
                byte[] buffer = imageReader.readPicture(imageKey);
                GraphicMagick gm = new GraphicMagick(buffer, image.getType());
                byte[] bufferOut = gm.textWaterMask(text, 40, "000000", 10, 10,100);
                if (bufferOut != null) {
                        ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                        imageUpdate.updateImage(bufferOut, String.valueOf(uid), image.getSpace(),imageKey);
                } else {
                        throw new ProcessException("请输入正确的参数！");
                }
        }
        
        public void  lomoUpdate(String imageKey,int uid) throws Exception {
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                byte[] buffer = imageReader.readPicture(imageKey);
                GraphicMagick gm = new GraphicMagick(buffer, "jpg");
                byte[] bufferOut = gm.lomo(buffer, 20480.0);
                if (bufferOut != null) {
                        Image image = mImageDaoImpl.find(imageKey);
                        ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                        imageUpdate.updateImage(bufferOut, String.valueOf(uid), image.getSpace(),imageKey);
                } else {
                        throw new ProcessException("请输入正确的参数！");
                }
        }
        
        public void  gothamUpdate(String imageKey,int uid) throws Exception {
                
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                byte[] buffer = imageReader.readPicture(imageKey);
                GraphicMagick gm = new GraphicMagick(buffer, "jpg");
                byte[] bufferOut = gm.gotham(buffer);
                if (bufferOut != null) {
                        Image image = mImageDaoImpl.find(imageKey);
                        ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                        imageUpdate.updateImage(bufferOut, String.valueOf(uid), image.getSpace(),imageKey);
                } else {
                        throw new ProcessException("请输入正确的参数！");
                }
        }
        
        
}
