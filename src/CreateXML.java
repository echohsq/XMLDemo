import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;

/**
 * 建立一个XML文档,文档名由输入属性决定
 * @author hsqzs
 */
public class CreateXML {

    public static void main(String [] args)throws Exception{
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("Team");
            Element firstEle = root.addElement("TeamMember");
            Element person = firstEle.addElement("Empno")
                    .addAttribute("value", "30772");
            Element name = firstEle.addElement("name")
                    .addAttribute("value", "Manjeet Singh");
            Element design = firstEle.addElement("Designation")
                    .addAttribute("value", "Team Leader");
        //把生成的xml文档存放在硬盘上  true代表是否换行
        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("Person.xml"),format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
