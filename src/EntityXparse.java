import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
/**
 * 读入文件,取出文件并打印
 * @author hsqzs
 */
public class EntityXparse {
    public static void main(String[] args) throws Exception{
        writeBook("C:\\Users\\hsqzs\\IdeaProjects\\XMLDemo\\src\\5.4.xml");
    }

    /**
     * 读入指定的xml文件,取出数据并打印
     */
    private static void writeBook(String filename) throws Exception{
        //1.读入指定的文件,构造Document对象
        File file = new File(filename);
        SAXReader reader = new SAXReader(); //xml解析器
        try {
            Document document = DocumentHelper.createDocument();//创建新的xml
            Document doc = reader.read(file);//解析器开始解析xml文件
            //2.获得根元素
            Element root = doc.getRootElement();
            Element rt = document.addElement(root.getName());
            //3.递归搜素子元素
            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                Element element = it.next();
                Element ele = rt.addElement(element.getName())
                        .addText(element.getText());//添加到新的xml中
                System.out.println(element.getText());
            }
            //把生成的xml文档存放在硬盘上  true代表是否换行
            OutputFormat format = new OutputFormat("    ",true);
            format.setEncoding("UTF-8");//设置编码格式
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("newXML.xml"),format);

            xmlWriter.write(document);
            xmlWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}