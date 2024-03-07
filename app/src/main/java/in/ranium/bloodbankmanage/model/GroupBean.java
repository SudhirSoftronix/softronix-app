package in.ranium.bloodbankmanage.model;

/**
 * Created by durwas on 7/1/16.
 */
public class GroupBean {
    String name,id,archiveid,bloodGroup;
String blood,bname,mobile,address;

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getArchiveid() {
        return archiveid;
    }

    public void setArchiveid(String archiveid) {
        this.archiveid = archiveid;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public  static GroupBean gb=new GroupBean();
    public static GroupBean getInstance()
    {
        return gb;
    }
}
