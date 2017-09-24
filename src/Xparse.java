import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
/**
 * 读入library.xml文件,取出文件并打印
 * @author hsqzs
 */
public class Xparse {
    public static void main(String [] args){
        readBook("C:\\Users\\hsqzs\\IdeaProjects\\XMLDemo\\src\\library.xml");
    }
    /**
     * 读入指定的xml文件,取出数据并打印
     */
    private static void readBook(String filename){
        //1.读入指定的文件,构造Document对象
        File file = new File(filename);
        SAXReader reader = new SAXReader(); //xml解析器
        try {
            Document doc = reader.read(file);//解析器开始解析xml文件
            //2.获得根元素
            Element root = doc.getRootElement();
            //3.递归搜素子元素
            Iterator<Element> it0 = root.elementIterator("book");
            System.out.println("书籍信息:");
            while(it0.hasNext()){
                Element bookElmt = it0.next();//bookElmt是book的元素
                System.out.println(bookElmt.elementText("title"));//取 子元素  书名 的内容
                List<Element> authorList = bookElmt.elements("author");
                for (Element element : authorList) {
                    //打印 作者 元素内容
                    System.out.println("作者: "+element.getText());
                }
                System.out.println("价格: "+bookElmt.elementText("price"));
                System.out.println("出版商: "+bookElmt.elementText("publisher"));
                System.out.println("成本: "+bookElmt.elementText("cost"));
                //取 book 的 属性
                String isbnValue = bookElmt.attributeValue("id");
                System.out.println("id="+isbnValue);
                System.out.println("-----------------");
            }
            Iterator<Element> it1 = root.elementIterator("member");
            System.out.println("会员信息:");
            while(it1.hasNext()){
                Element memElmt = it1.next();//memElmt是member的元素
                Element nameElmt = memElmt.element("name");
                Element addElmt = memElmt.element("address");
                System.out.println("姓名: "+nameElmt.elementText("firstName")+
                        "·"+nameElmt.elementText("middleName")+"·"+nameElmt.elementText("lastName"));
                System.out.println("地址: "+addElmt.elementText("city")+" "+addElmt.elementText("street")+
                        " "+addElmt.elementText("houseNumber"));
                System.out.println("-----------------");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            }
        }
    }