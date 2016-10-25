import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;




public class test {
		public static void main(String[] args) throws IOException {

			
			String filename = "F:/FY20161012023121612.xml";
			
			
				File file = new File(filename);
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String buff = "";
				StringBuffer sb = new StringBuffer();
				while((buff = br.readLine()) != null){
					sb.append(buff);
				}
				
				//生成反馈信息 加密
				String feedbackinfo = StringUtil.encodeXML(sb.toString());
				System.out.println(feedbackinfo);
				
		
		
		}
}
