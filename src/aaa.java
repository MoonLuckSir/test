import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.ServletUtils;


public class aaa {
	public void downloadFile(File downloadFile,ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		//判断是否存在该下载文件
		if(!downloadFile.exists()){
			//request.setAttribute("msg", "无法下载,"+downloadFile.getAbsolutePath()+"路径下文件未找到!");
			//return mapping.findForward(FAILURE);
		}
		
		try {
			response.reset();
			response.setContentType("application/octet-stream");
			//下载默认文件名，可以写中文
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadFile.getName(), "UTF-8"));
			ServletOutputStream out = null;
			out = response.getOutputStream();
			ServletUtils.returnFile(downloadFile.getAbsolutePath(), out);// 下载文件
			out.close();

		} catch (UnsupportedEncodingException ex) {// iso8559_1编码异常
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	public static void main(String[] args) {
		File file = new File("C:/Users/Andy/Desktop/123/20161020104837_jymxjg.xls");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getName());
	}
}
