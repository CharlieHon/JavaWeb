import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Dom4j_ {
    /**
     * 演示如何加载XML文件
     */
    @Test
    public void loadXML() throws DocumentException {
        // 得到一个解析器
        SAXReader reader = new SAXReader();
        // debug看document对象的属性
        // document对象的底层结构
        Document document = reader.read(new File("src/students.xml"));
        System.out.println(document);
    }

    /**
     * 遍历所有的student信息
     */
    @Test
    public void listStus() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));
        // 1. 得到rootElement
        Element rootElement = document.getRootElement();
        // 2. 得到rootElement的student Elements
        List<Element> students = rootElement.elements("student");
//        System.out.println(students.size()); // 2
        for (Element student : students) {   // element就是Student元素/节点
            // 获取Student元素的name Element
            Element name = student.element("name"); // 只有一个元素，所有不加s
            Element age = student.element("age");
            Element gender = student.element("gender");
            Element resume = student.element("resume");
            System.out.println("学生信息：" + name.getText() + " " + gender.getText()
            + " " + age.getText() + " " + resume.getText());
        }
    }

    /**
     * 指定读取第一个学生的信息
     */
    @Test
    public void readOne() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));
        Element rootElement = document.getRootElement();
        // 获取第一个学生
        Element student = (Element) rootElement.elements("student").get(0);
        // 输出该信息
        System.out.println("该学生信息：" + student.element("name").getText()
        + " " + student.element("age").getText() + " " + student.element("gender").getText());

        // 获取Student元素的属性
        System.out.println("id：" + student.attributeValue("id"));
    }

    /**
     * 添加元素
     */
    @Test
    public void add() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));

        // 先创建一个学生节点对象
        Element newStu = DocumentHelper.createElement("student");
        Element newStu_name = DocumentHelper.createElement("name");
        // 给元素添加属性
        newStu.addAttribute("id", "04");
        newStu_name.setText("宋江");
        // 创建age
        Element newStu_age = DocumentHelper.createElement("age");
        newStu_age.setText("45");
        // 创建resume元素
        Element newStu_resume = DocumentHelper.createElement("resume");
        newStu_resume.setText("山东呼保义");

        // 把三个子元素(节点)加到new_Stu下
        newStu.add(newStu_name);
        newStu.add(newStu_age);
        newStu.add(newStu_resume);
        // 再把newStu节点加到根元素
        document.getRootElement().add(newStu);
        // 直接输出会出现中文乱码
        OutputFormat output = OutputFormat.createPrettyPrint();
        output.setEncoding("utf-8");

        // 更新xml文件
        // 使用到ui编程
        XMLWriter writer = new XMLWriter(
                new FileOutputStream(new File("src/students.xml")), output);
        writer.write(document);
        writer.close();
    }

    /**
     * 删除元素
     */
    @Test
    public void delete() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));
        Element stu = (Element) document.getRootElement().elements("student").get(2);
        // 删除元素
        stu.getParent().remove(stu);
        // 更新xml
        OutputFormat output = OutputFormat.createPrettyPrint();
        output.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(
                new FileOutputStream(new File("src/students.xml")), output);
        writer.write(document);
        writer.close();
        System.out.println("删除成功");
    }

    /**
     * 修改元素
     */
    @Test
    public void update() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));
        List<Element> students = document.getRootElement().elements("student");
        for (Element student : students) {
            Element age = student.element("age");
            age.setText(Integer.parseInt(age.getText()) + 3 + "");
        }
        // 更新xml
        OutputFormat output = OutputFormat.createPrettyPrint();
        output.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(
                new FileOutputStream(new File("src/students.xml")), output);
        writer.write(document);
        writer.close();
        System.out.println("修改成功");
    }
}
