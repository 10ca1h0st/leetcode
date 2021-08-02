输入：简化的一段xml文件，用字符串表示，如下（属性名字不包含引号和等号，也不包含大于小于等特殊字符，详细规则见下面的答题说明）

string in = "<?xml version=\"1.0\" ?><Books><Book><Name = \"The C++ Programming Language\" Author=\"Bjarne Stroustrup\" /></Book><Book><Name = \"Effective C++\" Author = \"Scott Meyers\" /></Book></Books>";

输出：对输入的xml字符串解析，得到输出如下：

string out = "Books\r\n\tBook 1\r\n\t\tName:The C++ Programming Language\r\n\t\tAuthor:Bjarne Stroustrup\r\n\tBook 2\r\n\t\tName:Effective C++\r\n\t\tAuthor:Scott Meyers";


我的结果：

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class XMLparse 
{ 
   public static String ParsingXML(String in)
    {
        in = removeHeader(in);
        StringBuilder sb = new StringBuilder();
        String level_1 = getLevelTag(in);
        sb.append(level_1+"\r\n");
        String level_1_Str = getXmlValue(in, level_1);
        String level_2 = getLevelTag(level_1_Str);
        List<String> level_2_list = getXmlList(in, level_2);
        for(String level_2_Str : level_2_list){
            sb.append("\t"+level_2+"\r\n");
            sb.append(getAttrVal(level_2_Str));
        }
       return sb.toString();
    }
    
    
    private static String getLevelTag(String xml){
        Pattern p = Pattern.compile("<.*?>", Pattern.DOTALL);
        Matcher m = p.matcher(xml);
        String retStr = null;
        if(m.find()){
            retStr = m.group();
        }
        return null != retStr ? retStr.replace("<", "").replace(">",""):"";
        
    }
    
    private static StringBuilder getAttrVal(String xml){
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("(\\w+)\\s*=\\s*\"([^\"]+)\"", Pattern.DOTALL);
        Matcher m = p.matcher(xml);
        String retStr = null;
        while(m.find()){
            retStr = m.group();
            if(retStr.indexOf("=")>0){
                sb.append("\t\t"+retStr.split("=")[0]+":"+retStr.split("=")[1]+"\r\n");
            }
        }
        return sb;
    }
    
    private static String removeHeader(String in){
        Pattern p = Pattern.compile("<\\?.*?xml.*? \\?>", Pattern.DOTALL);
        Matcher m = p.matcher(in);
        String retStr = null;
        if(m.find()){
            retStr = in.replace(m.group(), "");
        }
        return retStr;
    }
    
    private static String getXmlValue(String xml, String filePath) {
        String rtnMsg = "";
        if (null == xml || null == filePath || "".equals(xml) || "".equals(filePath)) {
            return rtnMsg;
        }
        filePath = filePath.replace("\r\n", "");
        filePath = filePath.replace("\t", "");
        String[] paths = filePath.split("/");
        String xmlRes = "(.*?)";
        String path;
        for (int i = paths.length - 1; i >= 0; i--) {
            path = paths[i];
            if (i == paths.length - 1) {
                xmlRes = "<" + path.trim() + ">" + xmlRes + "</" + path.trim() + ">";
            } else {
                xmlRes = "<" + path.trim() + ">.*?" + xmlRes + ".*?</" + path.trim() + ">";
            }
        }
        Pattern pattern = Pattern.compile(xmlRes, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            rtnMsg = matcher.group(1);
            return rtnMsg;
        }
        return rtnMsg;
    }
    
    private static List<String> getXmlList(String xml, String filePath) {
        List<String> list = new ArrayList<String>();
        if (null == xml || null == filePath || "".equals(xml) || "".equals(filePath)) {
            return list;
        }
        filePath = filePath.replace("\r\n", "");
        filePath = filePath.replace("\t", "");
        String[] paths = filePath.split("/");
        String xmlRes = "(.*?)";
        String path;
        for (int i = paths.length - 1; i >= 0; i--) {
            path = paths[i];
            if (i == paths.length - 1) {
                xmlRes = "<" + path.trim() + ">" + xmlRes + "</" + path.trim() + ">";
            } else {
                xmlRes = "<" + path.trim() + ">.*?" + xmlRes + ".*?</" + path.trim() + ">";
            }
        }
        Pattern pattern = Pattern.compile(xmlRes, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }


    //start 提示：自动阅卷起始唯一标识，请勿删除或增加。 
    public static void main(String args[]) 
    { 
        String in =  "<?xml version=\"1.0\" ?><Books><Book><Name = \"The C++ Programming Language\" Author=\"Bjarne Stroustrup\" /></Book><Book><Name = \"Effective C++\" Author = \"Scott Meyers\" /></Book></Books>";
        System.out.println(ParsingXML(in));


    } 
    //end //提示：自动阅卷结束唯一标识，请勿删除或增加。
}