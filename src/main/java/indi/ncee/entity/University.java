package indi.ncee.entity;

//大学实体类
public class University {

    private int ID;
    private String name;
    private String province;
    private int provinceId;
    private String city;
    private int cityId;
    private String type;
    private String belong;
    private int level;
    private int is985;
    private int is211;
    private String description;
    private int numMaster;
    private int numDoctor;
    private int numAcademic;
    private int numSubject;
    private int numLibrary;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIs985() {
        return is985;
    }

    public void setIs985(int is985) {
        this.is985 = is985;
    }

    public int getIs211() {
        return is211;
    }

    public void setIs211(int is211) {
        this.is211 = is211;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumMaster() {
        return numMaster;
    }

    public void setNumMaster(int numMaster) {
        this.numMaster = numMaster;
    }

    public int getNumDoctor() {
        return numDoctor;
    }

    public void setNumDoctor(int numDoctor) {
        this.numDoctor = numDoctor;
    }

    public int getNumAcademic() {
        return numAcademic;
    }

    public void setNumAcademic(int numAcademic) {
        this.numAcademic = numAcademic;
    }

    public int getNumSubject() {
        return numSubject;
    }

    public void setNumSubject(int numSubject) {
        this.numSubject = numSubject;
    }

    public int getNumLibrary() {
        return numLibrary;
    }

    public void setNumLibrary(int numLibrary) {
        this.numLibrary = numLibrary;
    }

    @Override
    public String toString() {
        return "University{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", provinceId=" + provinceId +
                ", city='" + city + '\'' +
                ", cityId=" + cityId +
                ", type='" + type + '\'' +
                ", belong='" + belong + '\'' +
                ", level=" + level +
                ", is985=" + is985 +
                ", is211=" + is211 +
                ", description='" + description + '\'' +
                ", numMaster=" + numMaster +
                ", numDoctor=" + numDoctor +
                ", numAcademic=" + numAcademic +
                ", numSubject=" + numSubject +
                ", numLibrary=" + numLibrary +
                '}';
    }
}
