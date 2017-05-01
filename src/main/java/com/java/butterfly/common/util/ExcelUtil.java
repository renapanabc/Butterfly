package com.java.butterfly.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 
 * excel导出类    
 * @ClassName: ExcelUtil    
 * @author jiangqiubai    
 * @date: 2016年5月30日 下午4:54:36    
 * @version  v 1.0
 * @param <T>
 */
public class ExcelUtil<T> {
    
    /**
    * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
    *
    *注意：该方法导出的以".xls" office2003版的excel文档，若以".xlsx"后缀命名文件将不能正常打开。若要使用"xlsx"后缀，则
    *该方法中的"HSSFWorkbook类"换成"XSSFWorkbook", 相应的单元格组件、样式都需要改正
    *
    * @param title
    *            表格标题名
    * @param headers
    *            表格属性列名数组
    * @param props
    *            属性数组，长度与头部数组一样
    * @param dataset
    *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
    *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
    * @param out
    *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
    * @param pattern
    *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
    * @throws IOException io异常
    */
    @SuppressWarnings("unchecked")
    public HSSFWorkbook exportExcel(String title, String[] headers, String[] props, Collection<T> dataset,
        String pattern) throws IOException {
        if (headers == null || headers.length == 0 || props == null || props.length == 0
            || props.length != headers.length) {
            throw new IllegalArgumentException("参数错误，headers与props参数不能空，且长度必须相同.");
        }
        
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        // 把字体应用到当前的样式
        style.setFont(font);
        
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        
        // 把字体应用到当前的样式
        style2.setFont(font2);
        
        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            org.apache.poi.hssf.usermodel.HSSFRichTextString text =
                new org.apache.poi.hssf.usermodel.HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        //遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);
        try {
            while (it.hasNext()) {
                index++;
                T t = (T) it.next();
                row = sheet.createRow(index);
                
                //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                for (int i = 0; i < props.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    String getMethodName = "get" + props[i].substring(0, 1).toUpperCase() + props[i].substring(1);
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    
                    //判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value == null) {
                        textValue = "";
                    } else {
                        //其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }
                    
                    //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            //是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            org.apache.poi.hssf.usermodel.HSSFRichTextString richString =
                                new org.apache.poi.hssf.usermodel.HSSFRichTextString(textValue);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}