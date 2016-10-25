
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import tdh.util.Basic64;
import tdh.util.xml.XMLDocument;
import tdh.util.xml.XMLException;
import tdh.util.xml.XMLNode;




public class StringUtil {

	/**
	 * <li>判断字符串是否为空值</li>
	 * <li>NULL、空格均认为空值</li>
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return null == value || "".equals(value.trim());
	}

	/**
	 * 重复字符串 如 repeatString("a",3) ==> "aaa"
	 * 
	 * @author uke
	 * @param src
	 * @param repeats
	 * @return
	 */
	public static String repeatString(String src, int repeats) {
		if (null == src || repeats <= 0) {
			return src;
		} else {
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < repeats; i++) {
				bf.append(src);
			}
			return bf.toString();
		}
	}

	/**
	 * 左对齐字符串 * lpadString("X",3); ==>" X" *
	 * 
	 * @param src
	 * @param length
	 * @return
	 */
	public static String lpadString(String src, int length) {
		return lpadString(src, length, " ");
	}

	/**
	 * 以指定字符串填补空位，左对齐字符串 * lpadString("X",3,"0"); ==>"00X"
	 * 
	 * @param src
	 * @param byteLength
	 * @param temp
	 * @return
	 */
	public static String lpadString(String src, int length, String single) {
		if (src == null || length <= src.getBytes().length) {
			return src;
		} else {
			return repeatString(single, length - src.getBytes().length) + src;
		}
	}

	/**
	 * 右对齐字符串 * rpadString("9",3)==>"9 "
	 * 
	 * @param src
	 * @param byteLength
	 * @return
	 */
	public static String rpadString(String src, int byteLength) {
		return rpadString(src, byteLength, " ");
	}

	/**
	 * 以指定字符串填补空位，右对齐字符串 rpadString("9",3,"0")==>"900"
	 * 
	 * @param src
	 * @param byteLength
	 * @param single
	 * @return
	 */
	public static String rpadString(String src, int length, String single) {
		if (src == null || length <= src.getBytes().length) {
			return src;
		} else {
			return src + repeatString(single, length - src.getBytes().length);
		}
	}

	/**
	 * 去除,分隔符，用于金额数值去格式化
	 * 
	 * @param value
	 * @return
	 */
	public static String decimal(String value) {
		if (null == value || "".equals(value.trim())) {
			return "0";
		} else {
			return value.replaceAll(",", "");
		}
	}

	/**
	 * 在数组中查找字符串
	 * 
	 * @param params
	 * @param name
	 * @param ignoreCase
	 * @return
	 */
	public static int indexOf(String[] params, String name, boolean ignoreCase) {
		if (params == null)
			return -1;
		for (int i = 0, j = params.length; i < j; i++) {
			if (ignoreCase && params[i].equalsIgnoreCase(name)) {
				return i;
			} else if (params[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将字符转数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] toArr(String str) {
		String inStr = str;
		String a[] = null;
		try {
			if (null != inStr) {
				StringTokenizer st = new StringTokenizer(inStr, ",");
				if (st.countTokens() > 0) {
					a = new String[st.countTokens()];
					int i = 0;
					while (st.hasMoreTokens()) {
						a[i++] = st.nextToken();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * 字符串数组包装成字符串
	 * 
	 * @param ary
	 * @param s
	 *            包装符号如 ' 或 "
	 * @return
	 */
	public static String toStr(String[] ary, String s) {
		if (ary == null || ary.length < 1)
			return "";
		StringBuffer bf = new StringBuffer();
		bf.append(s);
		bf.append(ary[0]);
		for (int i = 1; i < ary.length; i++) {
			bf.append(s).append(",").append(s);
			bf.append(ary[i]);
		}
		bf.append(s);
		return bf.toString();
	}

	/**
	 * 設置MESSAGE中的變量{0}...{N}
	 * 
	 * @param msg
	 * @param vars
	 * @return
	 */
	public static String getMessage(String msg, String[] vars) {
		for (int i = 0; i < vars.length; i++) {
			msg = msg.replaceAll("\\{" + i + "\\}", vars[i]);
		}
		return msg;
	}

	/**
	 * @param msg
	 * @param var
	 * @return
	 */
	public static String getMessage(String msg, String var) {
		return getMessage(msg, new String[] { var });
	}

	/**
	 * @param msg
	 * @param var
	 * @param var2
	 * @return
	 */
	public static String getMessage(String msg, String var, String var2) {
		return getMessage(msg, new String[] { var, var2 });
	}

	/**
	 * 从Map中取String类型值
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Object getMapValue(Map map, Object key) {
		if (null == map || null == key)
			return "";
		Object value = map.get(key);
		return null == value ? "" : value;
	}

	/**
	 * 从Map中取Integer类型值
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Object getMapIntValue(Map map, Object key) {
		if (null == map || null == key)
			return new Integer(0);
		Object value = map.get(key);
		return null == value ? new Integer(0) : value;
	}

	/**
	 * 將key=value;key2=value2……轉換成Map
	 * 
	 * @param params
	 * @return
	 */
	public static Map gerneryParams(String params) {
		Map args = new HashMap();
		if (!isEmpty(params)) {
			try {
				String map, key, value;
				StringTokenizer st = new StringTokenizer(params, ";");
				StringTokenizer stMap;
				while (st.hasMoreTokens()) {
					map = st.nextToken();
					if (isEmpty(map.trim()))
						break;
					stMap = new StringTokenizer(map, "=");
					key = stMap.hasMoreTokens() ? stMap.nextToken() : "";
					if (isEmpty(key.trim()))
						continue;
					value = stMap.hasMoreTokens() ? stMap.nextToken() : "";
					args.put(key, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return args;
	}

	/**
	 * 字符串加密
	 * 
	 * @param string
	 * @return
	 */
	public static String encrypt(String src) {
		try {
			return DESPlus.getInstance().encrypt(src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 字符串解密
	 * 
	 * @param string
	 * @return
	 */
	public static String decrypt(String src) {
		try {
			return DESPlus.getInstance().decrypt(src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 页面格式化日期:yyyyMMdd ---> yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	/*public static String formatDate(String date) {
		return isEmpty(date) ? "" : DateUtil.format(date, "yyyyMMdd",
				"yyyy-MM-dd");
	}*/
	
	public static String trim(String str){
		if(str == null){
			return "";
		}else{
			return str.trim();
		}
	}
	
	
	/**
	 * 解码XML文件
	 * @param str
	 * @return
	 */
	public static String decodeXML(String xmlStr){
	    // 将编码后的xml内容字符串,转为属性值先转GBK,再Base64解密
	    System.out.println("接收的Base64编码字符串==========="+xmlStr);
	    String baseStr = Basic64.decode(xmlStr); 
	    System.out.println("先对字符串进行Base64解码============="+baseStr);
	    String x1 = decodeXmlNodeGbkBase64(baseStr);
	    System.out.println("对xml文件节点属性值先转GBK,再Base64解密============="+x1);
	    return x1;
	}
	
	
	
	

	 /**
	   * 先GBK转码,再Base64解密
	   * 
	   * @param str
	   * @return
	   */
	 public static String decodeXmlNodeGbkBase64(String xmlString) {
		    XMLDocument doc = new XMLDocument();
		    try {
		      doc.loadFromString(xmlString);
		    } catch (XMLException e) {
		      e.printStackTrace();
		    }
		    XMLNode root = doc.getRoot();
		    decodeNode(root);
		    return doc.toString();
	}
	 
	 /**
	   * 先GBK转码,再Base64解密
	   * 
	   * @param str
	   * @return
	   */
	 public static void decodeNode(XMLNode node) {
		    String[] attrNames = node.getAttributeNames();
		    int size_attrNames = (attrNames == null) ? 0 : attrNames.length;
		    for (int i = 0; i < size_attrNames; i++) {
		    	String attrValue = node.getAttributeValue(attrNames[i]);
		    	String deStr = decodeBase64Gbk(attrValue);
		    	/*String deStr = "";
		    	if("GZZ".equals(attrNames[i]) || "GWZ".equals(attrNames[i])){
		    		deStr = attrValue;
		    	}else{
		    		 deStr = decodeBase64Gbk(attrValue);
		    	}*/
		    	
		        node.setAttribute(attrNames[i], deStr);
		    }
		    XMLNode[] childNodes = node.getAllChildNode();
		    int size_childNodes = (childNodes == null) ? 0 : childNodes.length;
		    for (int i = 0; i < size_childNodes; i++) {
		      decodeNode(childNodes[i]);
		    }
		    
	 }
	 
	 
	 
	  /**
	   * 先Base64解密，再GBK转码,
	   * 
	   * @param str
	   * @return
	   */
	  public static String decodeBase64Gbk(String str) {
	    str = trim(str);
	    str = str.replace(" ", "");
	    BASE64Decoder decoder = new BASE64Decoder();
	    // str = Basic64.decode(str);
	    try {
	    	str = new String(decoder.decodeBuffer(str),"GBK");
	    	//str = new String(str.getBytes(),"GBK");
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return str;
	  }
	  
	  
	  
	  
	  /**
		 * 编码XML文件
		 * @param str
		 * @return
		 */
		public static String encodeXML(String xmlStr) {
		   
		    System.out.println("接收的正常的xml内容字符串==========="+xmlStr);
		    String x1 = encodeXmlNodeGbkBase64(xmlStr);
		    System.out.println("将正常的xml内容字符串节点属性值先转GBK,再Base64加密==========="+x1);
		    // 整个串Base64加密
		    String baseStr = Basic64.encode(x1);
		    System.out.println("对整个字符串进行Base64加密==========="+baseStr);
		    
		    return baseStr;
		  }
		
	 public static String encodeXmlNodeGbkBase64(String xmlString) {
		    XMLDocument doc = new XMLDocument();
		    try {
		      doc.loadFromString(xmlString);
		    } catch (XMLException e) {
		      e.printStackTrace();
		    }
		    XMLNode root = doc.getRoot();
		    encodeNode(root);
		    return doc.toString();
		  }

		  public static void encodeNode(XMLNode node) {
		    String[] attrNames = node.getAttributeNames();
		    
		    int size_attrNames = (attrNames == null) ? 0 : attrNames.length;
		    for (int i = 0; i < size_attrNames; i++) {
		    	String attrValue = node.getAttributeValue(attrNames[i]);
		    	String enStr = encodeGbkBase64(attrValue);
		    	node.setAttribute(attrNames[i], enStr);
		    }
		    XMLNode[] childNodes = node.getAllChildNode();
		    int size_childNodes = (childNodes == null) ? 0 : childNodes.length;
		    for (int i = 0; i < size_childNodes; i++) {
		      encodeNode(childNodes[i]);
		    }
		  }
	 
		  /**
		   * 先GBK转码,再Base64加密
		   * 
		   * @param str
		   * @return
		   */
		  public static String encodeGbkBase64(String str) {
		    str = trim(str);
		    BASE64Encoder encoder = new BASE64Encoder();
		    try {
		    	//str = new String(str.getBytes(),"GBK");
		    	str = encoder.encode(str.getBytes("GBK"));
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		    //str = Basic64.encode(str);
		    return str;
		  }

		
		  
		 

}
