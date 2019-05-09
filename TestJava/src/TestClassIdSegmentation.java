import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TestClassIdSegmentation {

    public static void main (String[] str) {
        ArrayList<VO> list = new ArrayList<VO>();
        list.add(new VO("213、3211、22",null,"耐克"));
        list.add(new VO("213、22",null,"阿迪"));
        list.add(new VO("33",null,"李宁"));

        HashSet<Integer> ids = new HashSet<Integer>();
        for (VO vo : list) {
            String[] strs = vo.getClassId().split("、");
            for (String id : strs) {
                System.out.println(id);
                ids.add(Integer.parseInt(id));
            }
        }
        System.out.println("------------------------");

        for (Integer id : ids) {
            System.out.println(id);
        }

        ArrayList<Obj> responseList = new ArrayList<Obj>();
        responseList.add(new Obj(22,"衣服"));
        responseList.add(new Obj(33,"鞋子"));
        responseList.add(new Obj(213,"帽子"));
        responseList.add(new Obj(3211,"袜子"));

        for (VO vo : list) {
            String[] strs = vo.getClassId().split("、");
            for (String id : strs) {
                Integer classId = Integer.parseInt(id);

                for (Obj obj : responseList) {
                    if(classId.intValue() == obj.getId().intValue()){
                        vo.setClassName(vo.getClassName() == null ? obj.getName() : vo.getClassName() + "、" + obj.getName());
                    }
                }

            }

            System.out.println(vo.toString());
        }


    }



}

class VO {

    String classId;
    String className;
    String brandName;

    public VO(String classId, String className, String brandName) {
        this.classId = classId;
        this.className = className;
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "VO{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", brandName='" + brandName + '\'' +
                '}';
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}

class Obj {
    Integer id;
    String name;

    public Obj(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
