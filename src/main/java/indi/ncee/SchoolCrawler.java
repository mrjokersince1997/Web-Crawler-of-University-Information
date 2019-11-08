package indi.ncee;

import indi.ncee.entity.University;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchoolCrawler {

    //抓取网页
    public static University getHttpSchool(int i, int[] flag_over) {
        try {
            //配置URL
            URL myUrl = new URL("https://static-data.eol.cn/www/school/"+ i + "/info.json");
            //配置连接
            HttpURLConnection myCon = (HttpURLConnection) myUrl.openConnection();
            myCon.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            myCon.setConnectTimeout(10000);
            myCon.setReadTimeout(1000);
            //建立连接
            myCon.connect();
            //如果连接成功
            if (myCon.getResponseCode() == 200) {
                flag_over[0] = 0;
                //读取返回数据
                InputStream in = myCon.getInputStream();
                System.out.print("ID:" + i + " 数据抓取成功，");
                int cnt = in.available();
                byte[] b = new byte[cnt];
                in.read(b);
                String str = new String(b);
                //System.out.println(str);

                University un = regExpUniversity(str);

                return un;
            }else{
                flag_over[0]++;
                System.out.println("ID:" + i + " 数据抓取失败，回复代码：" + myCon.getResponseCode());
            }
        } catch (MalformedURLException e) {
            flag_over[0]++;
            System.out.println("ID:" + i + " URL无法解析，检查URL格式");
            return null;
        }catch(SocketTimeoutException e){
            flag_over[0]++;
            System.out.println("ID:" + i + " 访问超时，检查网络环境");
            return null;
        } catch (IOException e) {
            flag_over[0]++;
            System.out.println("ID:" + i + " 连接请求错误，检查请求头");
            return null;
        }
        return null;
    }


    //正则匹配
    public static University regExpUniversity(String str){
        //定义实体类
        University un = new University();
        //读取学校ID
        Pattern regex = Pattern.compile("\"school_id\":\"(.*?)\"");

        Matcher matcher = regex.matcher(str);
        if (matcher.find()) {
            int id = Integer.valueOf(matcher.group(1));
            un.setID(id);
        }
        //读取学校名称
        regex = Pattern.compile("\"name\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            String name = unescapeUnicode(matcher.group(1));
            un.setName(name);
        }
        System.out.println("学校：" + un.getName());
        //读取省份ID
        regex = Pattern.compile("\"province_id\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int provinceId =Integer.valueOf(matcher.group(1));
            un.setProvinceId(provinceId);
        }
        //读取省份名称
        regex = Pattern.compile("\"province_name\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            String province = unescapeUnicode(matcher.group(1));
            un.setProvince(province);
        }
        //读取城市ID
        regex = Pattern.compile("\"city_id\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int cityId =Integer.valueOf(matcher.group(1));
            un.setCityId(cityId);
        }
        //读取城市名称
        regex = Pattern.compile("\"city_name\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            String city = unescapeUnicode(matcher.group(1));
            un.setCity(city);
        }
        //读取学校类型
        regex = Pattern.compile("\"type_name\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            String type = unescapeUnicode(matcher.group(1));
            un.setType(type);
        }
        //读取学校隶属
        regex = Pattern.compile("\"belong\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            String belong = unescapeUnicode(matcher.group(1));
            un.setBelong(belong);
        }
        //读取学校描述
        regex = Pattern.compile("\"content\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            String content = unescapeUnicode(matcher.group(1));
            un.setDescription(content);
        }
        //读取学校级别   6000普通本科 6002独立学院
        regex = Pattern.compile("\"school_type\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int level =Integer.valueOf(matcher.group(1));
            un.setLevel(level);
        }
        //读取985
        regex = Pattern.compile("\"f985\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int is985 = Integer.valueOf(matcher.group(1));
            if(is985 == 1) un.setIs985(1);
            else un.setIs985(0);
        }
        //读取211
        regex = Pattern.compile("\"f211\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int is211 = Integer.valueOf(matcher.group(1));
            if(is211 == 1) un.setIs211(1);
            else un.setIs211(0);
        }
        //读取硕士点数量
        regex = Pattern.compile("\"num_master\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int numMaster =Integer.valueOf(matcher.group(1));
            un.setNumMaster(numMaster);
        }
        //读取博士点数量
        regex = Pattern.compile("\"num_doctor\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int numDoctor =Integer.valueOf(matcher.group(1));
            un.setNumDoctor(numDoctor);
        }
        //读取院士数量
        regex = Pattern.compile("\"num_academician\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int numAcademic =Integer.valueOf(matcher.group(1));
            un.setNumAcademic(numAcademic);
        }
        //读取重点学科数量
        regex = Pattern.compile("\"num_subject\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int numSubject =Integer.valueOf(matcher.group(1));
            un.setNumSubject(numSubject);
        }
        //读取重点实验室数量
        regex = Pattern.compile("\"num_library\":\"(.*?)\",");
        matcher = regex.matcher(str);
        if (matcher.find()) {
            int numLibrary =Integer.valueOf(matcher.group(1));
            un.setNumLibrary(numLibrary);
        }

        return un;
    }


    //解码unicode
    public static String unescapeUnicode(String str){
        StringBuffer b=new StringBuffer();
        Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(str);
        while(m.find())
            b.append((char)Integer.parseInt(m.group(1),16));
        return b.toString();
    }

}
