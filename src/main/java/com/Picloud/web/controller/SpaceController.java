package com.Picloud.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Picloud.config.SystemConfig;
import com.Picloud.exception.ProcessException;
import com.Picloud.exception.SpaceException;
import com.Picloud.exception.ThreeDImageException;
import com.Picloud.hibernate.dao.impl.SpaceDaoImpl;
import com.Picloud.hibernate.dao.impl.UserDaoImpl;
import com.Picloud.hibernate.entities.Space;
import com.Picloud.hibernate.entities.User;
import com.Picloud.image.GraphicMagick;
import com.Picloud.image.ImageReader;
import com.Picloud.image.ImageUpdate;
import com.Picloud.image.ImageWriter;
import com.Picloud.utils.JspUtil;
import com.Picloud.utils.EncryptUtil;
import com.Picloud.utils.PropertiesUtil;
import com.Picloud.web.dao.impl.ImageDaoImpl;
import com.Picloud.web.dao.impl.InfoDaoImpl;
import com.Picloud.web.dao.impl.LogDaoImpl;
import com.Picloud.web.dao.impl.VisitDaoImpl;
import com.Picloud.web.model.Image;
import com.Picloud.web.model.Log;
import com.Picloud.web.model.PageInfo;
import com.Picloud.web.model.SpaceWithImage;
import com.Picloud.web.model.ThreeDImage;
import com.Picloud.web.model.UploadInfo;
import com.Picloud.web.model.Visit;
import com.Picloud.web.thread.SyncThread;

@Controller
@RequestMapping("/space")
public class SpaceController {
        private String module = "图片服务器";
        @Autowired
        private SpaceDaoImpl mSpaceDaoImpl;
        @Autowired
        private UserDaoImpl mUserDaoImpl;
        @Autowired
        private ImageDaoImpl mImageDaoImpl;
        @Autowired
        private SystemConfig systemConfig;
        @Autowired
        private InfoDaoImpl infoDaoImpl;
        @Autowired
        private VisitDaoImpl visitDaoImpl;
        @Autowired
        private LogDaoImpl mLogDaoImpl;
        private static int pageNum = 6 + 1;

        /**
         * 查看所有空间
         * 
         * @param model
         * @param space
         * @param session
         * @return
         */
        @RequestMapping(value = "/spaces", method = RequestMethod.GET)
        public String list(Model model, @ModelAttribute("space") Space space,
                        HttpSession session) {
                model.addAttribute("module", module);
                model.addAttribute("action", "图片空间");

                User LoginUser = (User) session.getAttribute("LoginUser");
                List<Space> ss = mSpaceDaoImpl.load(LoginUser.getUid());
                List<SpaceWithImage> spaces = new ArrayList<SpaceWithImage>();
                for(int i = 0; i < ss.size(); i++){
                        List<Image> images = mImageDaoImpl.getOtherImages(String.valueOf(ss.get(i).getSid()), "", 1);
                        SpaceWithImage spaceWithImage = new SpaceWithImage(ss.get(i), images);
                        spaces.add(spaceWithImage);
                }
                model.addAttribute("spaces", spaces);
                return "space/list";
        }

        /**
         * 创建空间
         * 
         * @param model
         * @param space
         * @param br
         * @param session
         * @return
         * @throws Exception
         */
        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String add(Model model, @Validated Space space,
                        BindingResult br, HttpSession session) throws Exception {
                model.addAttribute("module", module);
                model.addAttribute("action", "图片空间");

                User LoginUser = (User) session.getAttribute("LoginUser");
                if (br.hasErrors()) {
                        return "space/list";
                }

                Space s = mSpaceDaoImpl.getByName(space.getName());
                if (s != null) {
                        throw new SpaceException("该空间已存在！");
                }

                User u = mUserDaoImpl.find(LoginUser.getUid());
                Space s1 = new Space(u, space.getName(),
                                space.getDescription(), 0, 0, new Date());
                mSpaceDaoImpl.add(s1);
                Space s2 = mSpaceDaoImpl.getByName(space.getName());
                String spaceNum = String.valueOf(LoginUser.getSpaceNum());
                mUserDaoImpl.addSpaceNum(LoginUser.getUid());
                return "redirect:/space/" + s2.getSid();
        }

        /**
         * 查看某个空间下的所有图片
         * 
         * @param spaceKey
         * @param model
         * @param session
         * @return
         * @throws Exception
         */
        @RequestMapping(value = "/{spaceKey} ", method = RequestMethod.GET)
        public String show(@PathVariable String spaceKey, String page,
                        Model model, HttpSession session) throws Exception {
                int p = 0;
                if (page != null) {
                        p = Integer.parseInt(page);
                }
                model.addAttribute("module", module);
                model.addAttribute("action", "图片空间");

                User loginUser = (User) session.getAttribute("LoginUser");
                Space space = mSpaceDaoImpl.find(Integer.parseInt(spaceKey));
                List<Space> spaces = mSpaceDaoImpl.load(loginUser.getUid(), 5);
                PageInfo pi = (PageInfo) session.getAttribute("imagePagePnfo");
                if (pi == null) {
                        pi = new PageInfo();
                        pi.setNum(1);
                        pi.setPage(0);
                        pi.getStartKeys().add(" ");
                }
                pi.setPage(p);
                List<Image> images = mImageDaoImpl.imagePageByKey(String
                                .valueOf(loginUser.getUid()), pi.getStartKeys()
                                .get(pi.getPage()), spaceKey, pageNum);
                if (images == null || images.size() < pageNum) {
                        pi.setIfHaveNext(false);
                } else {
                        pi.setIfHaveNext(true);
                        pi.setNum(pi.getNum() + 1);
                        pi.getStartKeys().add(images.get(pageNum - 1).getKey());
                        images.remove(pageNum - 1);
                }
                session.setAttribute("imagePagePnfo", pi);
                model.addAttribute("images", images);
                model.addAttribute("activeSpace", space);
                model.addAttribute(space);
                model.addAttribute("spaces", spaces);
                return "space/show";
        }

        /**
         * 快速上传页面
         * 
         * @param model
         * @param session
         * @return
         */
        @RequestMapping(value = "/upload", method = RequestMethod.GET)
        public String upload(Model model, HttpSession session) {
                model.addAttribute("module", module);
                model.addAttribute("action", "上传图片");

                User loginUser = (User) session.getAttribute("LoginUser");
                List<Space> spaces = mSpaceDaoImpl.load(loginUser.getUid());
                model.addAttribute("spaces", spaces);
                return "space/upload";
        }

        /**
         * 快速上传处理
         * 
         * @param session
         * @param request
         * @return
         * @throws Exception
         */
        @RequestMapping(value = "/upload", method = RequestMethod.POST)
        public String upload(HttpSession session, HttpServletRequest request)
                        throws Exception {
                String spaceName = null;
                String spaceKey = null;
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(request);
                Iterator iter = items.iterator();
                User loginUser = (User) session.getAttribute("LoginUser");
                final String LocalPath = PropertiesUtil
                                .getValue("localUploadPath")
                                + "/"
                                + String.valueOf(loginUser.getUid())
                                + '/'
                                + spaceKey + '/';
                try {
                        boolean flag = false;
                        while (iter.hasNext()) {
                                FileItem item = (FileItem) iter.next();

                                if (item.isFormField()) { // 若为普通表单
                                        String name = item.getFieldName();
                                        if (name.equals("space")) {

                                                spaceName = item.getString();
                                                spaceName = new String(
                                                                spaceName.getBytes("iso8859-1"),
                                                                "utf-8");
                                                spaceKey = EncryptUtil
                                                                .spaceEncryptKey(
                                                                                spaceName,
                                                                                String.valueOf(loginUser
                                                                                                .getUid()));
                                        }
                                } else {
                                        ImageWriter imageWriter = new ImageWriter(
                                                        infoDaoImpl);
                                        imageWriter.write(
                                                        item,
                                                        String.valueOf(loginUser
                                                                        .getUid()),
                                                        spaceKey, LocalPath);
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return "test";
        }

        /**
         * 上传图片页面
         * 
         * @param spaceKey
         * @param model
         * @return
         */
        @RequestMapping(value = "/{spaceKey}/upload", method = RequestMethod.GET)
        public String upload(@PathVariable String spaceKey, Model model) {
                model.addAttribute("module", module);
                model.addAttribute("action", "上传图片");

                Space space = mSpaceDaoImpl.find(Integer.parseInt(spaceKey));
                model.addAttribute("activeSpace", space);
                model.addAttribute(space);
                return "space/upload";
        }

    	/**
    	 * 上传图片
    	 * 
    	 * @param space
    	 *            图片所在空间
    	 * @param attachs
    	 *            图片附件数组
    	 * @throws Exception 
    	 */
    	@RequestMapping(value = "/{spaceKey}/upload", method = RequestMethod.POST)
    	@ResponseBody
    	public UploadInfo upload(@PathVariable String spaceKey,
    			HttpServletRequest request, HttpServletResponse response,
    			HttpSession session) throws Exception {
    		FileItemFactory factory = new DiskFileItemFactory();
    		ServletFileUpload upload = new ServletFileUpload(factory);
    		List items = upload.parseRequest(request);
    		Iterator iter = items.iterator();
    		User loginUser = (User) session.getAttribute("LoginUser");
    		final String LocalPath = PropertiesUtil.getValue("localUploadPath")+ "/"
    				+ String.valueOf(loginUser.getUid()) + '/' + spaceKey + '/';
    		boolean flag = false;
    		String imageName = null;
    		FileItem  imageItem = null;
    		try {
    			while (iter.hasNext()) {
    				FileItem item = (FileItem) iter.next();
    				if (item.isFormField()) {
    					System.out.println(item.getName() + item.getFieldName() + item.getString());
    					if(item.getFieldName().equals("fileName")){
    						imageName = item.getString();
    					}
    				} else {
    					imageItem = item;
    				}
    			}
    			ImageWriter imageWriter = new ImageWriter(infoDaoImpl);
    			flag = imageWriter.write(imageItem, imageName,String.valueOf(loginUser.getUid()),
    					spaceKey, LocalPath);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		UploadInfo uploadInfo = new UploadInfo();
    		
    		uploadInfo.setInfo(EncryptUtil.imageEncryptKey(imageName, String.valueOf(loginUser.getUid())));
    		if (flag) {
    			response.setContentType("text/html;charset=gb2312");
    			response.setStatus(200);
    			uploadInfo.setStatus(1);
    		} else {
    			response.setContentType("text/html;charset=gb2312");
    			response.setStatus(302);
    			uploadInfo.setStatus(0);
    		}
    		return uploadInfo;
    	}

        @RequestMapping(value = "/{spaceName}/delete", method = RequestMethod.GET)
        public String delete(@PathVariable String spaceName, Model model,
                        HttpSession session) {
                model.addAttribute("module", module);
                model.addAttribute("action", "图片空间");
                model.addAttribute("activeSpace", spaceName);

                User LoginUser = (User) session.getAttribute("LoginUser");
                mSpaceDaoImpl.delete(spaceName, LoginUser.getUid());

                return "redirect:/space/spaces";
        }

        @ExceptionHandler(value = (SpaceException.class))
        public String handlerException(SpaceException e, HttpServletRequest req) {
                req.setAttribute("e", e);
                return "error";
        }

        /**
         * 读取空间信息
         * 
         * @param spaceKey
         * @return Space信息JSON
         */
        @RequestMapping(value = "/{spaceKey}.json", method = RequestMethod.GET)
        @ResponseBody
        public Space show(@PathVariable String spaceKey) {
                Space space = new Space();
                space = mSpaceDaoImpl.find(Integer.parseInt(spaceKey));
                return space;
        }

        /**
         * 读取空间内所有图片信息
         * 
         * @param spaceKey
         * @return Space所有图片信息JSON
         */
        @RequestMapping(value = "/{spaceKey}/images.json", method = RequestMethod.GET)
        @ResponseBody
        public List<Image> getAllImages(@PathVariable String spaceKey) {
                List<Image> images = mImageDaoImpl.load(spaceKey);
                return images;
        }

        /**
         * 读取用户 所有空间
         * 
         * @param uid
         * @return
         */
        @RequestMapping(value = "/spaces.json", method = RequestMethod.GET)
        @ResponseBody
        public List<Space> getAllSpace(HttpSession session) {
                User LoginUser = (User) session.getAttribute("LoginUser");
                List<Space> spaces = mSpaceDaoImpl.load(LoginUser.getUid());
                return spaces;
        }

        // @RequestMapping(value = "/{spaceKey}/test", method =
        // RequestMethod.POST)
        // public String uploadTest(@PathVariable String spaceKey,
        // HttpServletRequest request, HttpSession session)
        // throws FileUploadException {
        // FileItemFactory factory = new DiskFileItemFactory();
        // ServletFileUpload upload = new ServletFileUpload(factory);
        // @SuppressWarnings("rawtypes")
        // List items = upload.parseRequest(request);
        // @SuppressWarnings("rawtypes")
        // Iterator iter = items.iterator();
        // User loginUser = new User("test", "1", "", "", "", "test", "123456",
        // "0", "0", "0");
        // final String LocalPath = PropertiesUtil.getValue("localUploadPath")+
        // "/"
        // + String.valueOf(LoginUser.getUid()) + '/' + spaceKey + '/';
        // try {
        // boolean flag = false;
        // while (iter.hasNext()) {
        // FileItem item = (FileItem) iter.next();
        // ImageWriter imageWriter = new ImageWriter(infoDaoImpl);
        // imageWriter
        // .write(item, String.valueOf(LoginUser.getUid()), spaceKey,
        // LocalPath);
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // // 同步线程
        // return "test";
        // }
        //
        // @RequestMapping(value = "/{spaceKey}/test2", method =
        // RequestMethod.POST)
        // public String test(@PathVariable String spaceKey, HttpSession
        // session,
        // HttpServletRequest request) throws FileUploadException {
        // FileItemFactory factory = new DiskFileItemFactory();
        // ServletFileUpload upload = new ServletFileUpload(factory);
        // // User loginUser = (User) session.getAttribute("LoginUser");
        // User loginUser = new User("test", "", "", "", "", "test", "123456",
        // "0", "0", "0");
        // String hdfsPath = "/upload" + "/" +
        // String.valueOf(LoginUser.getUid());
        // List items = upload.parseRequest(request);
        // Iterator iter = items.iterator();
        // try {
        // boolean flag = false;
        // while (iter.hasNext()) {
        // FileItem item = (FileItem) iter.next();
        // String filePath = hdfsPath + "/BigFile/";
        // ImageWriter imageWriter = new ImageWriter(infoDaoImpl);
        // flag = imageWriter.uploadToHdfs(filePath, item,
        // String.valueOf(LoginUser.getUid()));
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // return "test";
        // }

        /**
         * 搜索空间下的图片
         * 
         * @param spaceKey
         * @param subStr
         * @param model
         * @param session
         * @return
         */
        @RequestMapping(value = "/{spaceKey}/search", method = RequestMethod.GET)
        public String search(@PathVariable String spaceKey, String key,
                        Model model, HttpSession session) {
                model.addAttribute("module", module);
                model.addAttribute("action", "图片空间");
                User LoginUser = (User) session.getAttribute("LoginUser");
                List<Image> images = mSpaceDaoImpl.search(LoginUser.getUid(),
                                spaceKey, key);
                Space space = mSpaceDaoImpl.find(Integer.parseInt(spaceKey));
                model.addAttribute("images", images);
                model.addAttribute("space", space);
                return "space/show";
        }

        @RequestMapping(value = "test/{sid}/{space}", method = RequestMethod.GET)
        public String test(@PathVariable int sid, @PathVariable String space,
                        Model model, HttpSession session) {
                List<Visit> visits = visitDaoImpl.get(sid, space);
                System.out.println(visits.size());
                return "space/show";
        }

        @RequestMapping(value = "/{spaceId}/imageLogo/", method = RequestMethod.POST)
        public String imageLogo(@PathVariable int spaceId, int startX,
                        int startY, int width, int height, int optical,
                        Model model, HttpSession session,
                        HttpServletResponse response) throws Exception {
                User loginUser = (User) session.getAttribute("LoginUser");
                String logo = loginUser.getImageLogo();
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                List<Image> images = mImageDaoImpl
                                .load(String.valueOf(spaceId));
                for (int i = 0; i < images.size(); i++) {
                        Image image = images.get(i);
                        byte[] buffer = imageReader.readPicture(image.getKey());
                        GraphicMagick gm = new GraphicMagick(buffer, "jpg");
                        String logoSrc = PropertiesUtil.getValue("imagePath")
                                        + logo;
                        byte[] bufferOut = gm.imgWaterMask(logoSrc, width,
                                        height, startX, startY, optical);

                        if (bufferOut != null) {

                                // Image image = mImageDaoImpl.find(imageKey);
                                ImageUpdate imageUpdate = new ImageUpdate(
                                                infoDaoImpl);
                                imageUpdate.updateImage(bufferOut, String
                                                .valueOf(loginUser.getUid()),
                                                image.getSpace(), image
                                                                .getKey());

                                Log log = new Log(
                                                String.valueOf(loginUser
                                                                .getUid()),
                                                loginUser.getNickname()
                                                                + "按照Logo坐标："
                                                                + startX
                                                                + "，"
                                                                + startY
                                                                + "Logo宽度："
                                                                + width
                                                                + "Logo高度："
                                                                + height
                                                                + "透明度"
                                                                + optical
                                                                + "添加了水印图片"
                                                                + image.getName());
                                mLogDaoImpl.add(log);

                                response.setStatus(200);
                        } else {
                                throw new ProcessException("请输入正确的参数！");
                        }
                }

                return "redirect:/space/" + spaceId;
        }

        @RequestMapping(value = "/{spaceId}/imageLogo/", method = RequestMethod.POST)
        public String textLogo(@PathVariable int spaceId, int startX,
                        int startY, int fontSize, int alpha, String color,
                        Model model, HttpSession session,
                        HttpServletResponse response) throws Exception {
                User loginUser = (User) session.getAttribute("LoginUser");
                String text = loginUser.getTextLogo();
                ImageReader imageReader = new ImageReader(infoDaoImpl);
                List<Image> images = mImageDaoImpl
                                .load(String.valueOf(spaceId));
                for (int i = 0; i < images.size(); i++) {
                        Image image = images.get(i);
                        byte[] buffer = imageReader.readPicture(image.getKey());
                        GraphicMagick gm = new GraphicMagick(buffer, image.getType());
                        byte[] bufferOut = gm.textWaterMask(text, fontSize, color, startX, startY,alpha);
                        
                        if (bufferOut != null) {
                                ImageUpdate imageUpdate=new ImageUpdate(infoDaoImpl);
                                imageUpdate.updateImage(bufferOut, String.valueOf(loginUser.getUid()), image.getSpace(),image.getKey());
                                Log log=new Log(String.valueOf(loginUser.getUid()),loginUser.getNickname() +"按照坐标："+startX+"，"+startY+ "大小："+fontSize+"颜色："+color+"添加了水印文字"+text);
                                mLogDaoImpl.add(log);
                                response.setStatus(200);
                        } else {
                                throw new ProcessException("请输入正确的参数！");
                        }
                }

                return "redirect:/space/" + spaceId;
        }
>>>>>>> 4fa2d25c7638510f1f3af5df9d00a9636b032b56
}
