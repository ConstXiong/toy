package constxiong;

import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;

import java.io.File;

/**
 * 测试 jodconverter
 * @author constxiong
 */
public class Test {

    public static void main(String[] args) throws OfficeException {
        LocalOfficeManager officeManager = LocalOfficeManager.builder().officeHome("E:\\install\\LibreOffice").install().build();
        //启动OpenOffice服务
        officeManager.start( );
        //执行转换
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//        converter.convert(new File("E:/ppdf/7217381ca7714e118a0d1aec9988175f-2.docx"), new File("E:/ppdf/jar.pdf"));
        converter.convert(new File("E:/ppdf/test.docx"), new File("E:/ppdf/test-jar.pdf"));
        //停止服务
        officeManager.stop( );
    }

}
