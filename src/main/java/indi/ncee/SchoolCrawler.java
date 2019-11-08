package indi.ncee;

import indi.ncee.entity.University;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchoolCrawler {

    //抓取网页
    public static University getHttpSchool(int i) {
        try {
            //配置URL
            URL myUrl = new URL("https://static-data.eol.cn/www/school/"+ i + "/info.json");
            //配置连接
            HttpURLConnection mycon = (HttpURLConnection) myUrl.openConnection();
            mycon.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            mycon.setConnectTimeout(10000);
            mycon.setReadTimeout(1000);
            //建立连接
            mycon.connect();
            //如果连接成功
            if (mycon.getResponseCode() == 200) {
                //读取返回数据
                InputStream in = mycon.getInputStream();
                System.out.print("ID为" + i + "的数据抓取成功，");
                int cnt = in.available();
                byte[] b = new byte[cnt];
                in.read(b);
                String str = new String(b);
                //System.out.println(str);

                University un = regExpUniversity(str);

                return un;
            }else{
                System.out.println("ID为" + i + "的数据抓取失败，数据可能不存在；");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }catch(SocketTimeoutException e){
            System.out.println("ID为" + i + "的数据访问连接超时，已跳过；");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println("学校为" + un.getName() + "；");
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
