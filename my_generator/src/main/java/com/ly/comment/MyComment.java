package com.ly.comment;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * @ProjectName: mybatis
 * @Package: com.ly.comment
 * @ClassName: MyComment
 * @Author: lin
 * @Description: 实现了mybatis-generator的注释接口CommentGenerator，自定义了注释的实现方式
 * @Date: 2019-04-22 9:37
 * @Version: 1.0
 */
public class MyComment implements CommentGenerator {
    /**
     * The properties.
     */
    private Properties properties;

    /**
     * 是否支持时间
     */
    private boolean suppressDate;

    /**
     * 是否不是使用注释
     */
    private boolean suppressAllComments;

    /**
     * 是否添加数据库列的注释为字段的注释
     */
    private boolean addRemarkComments;

    /**
     * 时间格式
     */
    private SimpleDateFormat dateFormat;

    /**
     * 构造方法，初始化参数
     */
    public MyComment() {
        super();
        properties = new Properties();
        suppressDate = false;
        suppressAllComments = false;
        addRemarkComments = false;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 添加java文件的注释
     *
     * @param compilationUnit
     */
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // add no file level comments by default
    }

    /**
     * 对mapper.xml 文件进行注解格式设置
     *
     * @param xmlElement the xml element
     */
    public void addComment(XmlElement xmlElement) {
        if (suppressAllComments) {
            return;
        }
        String name = xmlElement.getName();
        StringBuilder sb = new StringBuilder();
        switch (name) {
            case "resultMap":
                sb.append("<!-- 自定义map集合 ");
                joinStr(xmlElement, sb);
                break;
            case "delete":
            case "insert":
            case "update":
            case "select":
                sb.append("<!-- 对应mapper接口的方法为 ");
                joinStr(xmlElement, sb);
                break;
            default:
                sb.append("<!-- 未知节点 ");
                joinStr(xmlElement, sb);
                break;
        }
        sb.append(" -->");
        System.out.println(sb.toString());
        xmlElement.addElement(new TextElement(sb.toString()));
    }

    private void joinStr(XmlElement xmlElement, StringBuilder sb) {
        xmlElement.getAttributes().forEach(p -> {
        if ("id".equals(p.getName())) {
                sb.append(p.getValue());
            } else if ("parameterType".equals(p.getName())) {
                sb.append("\n\t\t").append("参数类型为: ").append(p.getValue());
            } else if ("resultMap".equals(p.getName()) || "resultType".equals(p.getName())) {
                sb.append("\n\t\t").append("返回类型为: ").append(p.getValue());
            } else if ("type".equals(p.getName())) {
                sb.append("\n\t\t").append("类型为: ").append(p.getValue());
            }
        });
        String s = getDateString();
        if (s != null) {
            sb.append("  Date :  ");
            sb.append(s);
        }
    }

    /**
     * 添加xml文件的头注释
     *
     * @param rootElement
     */
    public void addRootComment(XmlElement rootElement) {
        // add no document level comments by default
    }

    /**
     * 获取配置文件的配置信息，用于确定生成文件的注释样式
     *
     * @param properties
     */
    public void addConfigurationProperties(Properties properties) {

        this.properties.putAll(properties);
        suppressDate = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        addRemarkComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
        suppressAllComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
        if (StringUtility.stringHasValue(dateFormatString)) {
            dateFormat = new SimpleDateFormat(dateFormatString);
        }
    }

    /**
     * 是否可以删除的类警告
     *
     * @param javaElement       java节点
     * @param markAsDoNotDelete 是否可以删除给予警告
     */
    protected void addJavadocTag(JavaElement javaElement,
                                 boolean markAsDoNotDelete) {
        //sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            javaElement.addJavaDocLine(" * @Warn 该类不能删除 ");
        }
    }

    /**
     * 获取指定格式的时间样式
     *
     * @return 指定格式的时间
     */
    protected String getDateString() {
        return !suppressDate ? dateFormat.format(new Date()) : new Date().toString();
    }

    /**
     * 添加类注解样式
     *
     * @param innerClass
     * @param introspectedTable
     */
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * @author: lin");
        sb.append(" * @Date为: ")
                .append(getDateString())
                .append("\n")
                .append(" * @Table: ")
                .append(introspectedTable.getFullyQualifiedTable())
                .append("\n")
                .append(" * @Description: ")
                .append(introspectedTable.getRemarks());
        innerClass.addJavaDocLine(sb.toString());
        addJavadocTag(innerClass, false);
        innerClass.addJavaDocLine(" */");
    }

    /**
     * 添加实体类的类注释样式
     *
     * @param topLevelClass     类信息
     * @param introspectedTable 数据库表的对象
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        if (suppressAllComments || !addRemarkComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @author: lin");
        sb.append(" * @Date: ")
                .append(getDateString())
                .append("\n")
                .append(" * @Table: ")
                .append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString());
        String remarks = introspectedTable.getRemarks();
        sb.setLength(0);
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            sb.append(" * @Description: ");
            sb.append(remarks);
            topLevelClass.addJavaDocLine(sb.toString());
        }
        addJavadocTag(topLevelClass, true);
        topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 添加枚举类的注释格式
     *
     * @param innerEnum         枚举类型
     * @param introspectedTable 数据库表的对象
     */
    public void addEnumComment(InnerEnum innerEnum,
                               IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        innerEnum.addJavaDocLine(" * @author: lin");
        sb.append(" * @Date: ")
                .append(getDateString())
                .append("\n")
                .append(" * @Table: ")
                .append(introspectedTable.getFullyQualifiedTable())
                .append("\n")
                .append(" * @description: ")
                .append(introspectedTable.getRemarks());
        addJavadocTag(innerEnum, false);
        innerEnum.addJavaDocLine(" */");
    }

    /**
     * 添加实体类属性的注释格式
     *
     * @param field              属性
     * @param introspectedTable  数据库表的对象
     * @param introspectedColumn 数据库表中列的对象
     */
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        field.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * @Column: ");
        sb.append(introspectedColumn.getActualColumnName());
        field.addJavaDocLine(sb.toString());
        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            field.addJavaDocLine(" * @Description: " + remarks);
        }
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    /**
     * 添加属性的注释格式(还未用到)
     *
     * @param field             属性
     * @param introspectedTable 数据库表的对象
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * @Table: ").append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());
        addJavadocTag(field, false);
        field.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 添加接口的方法注释
     *
     * @param method            方法对象
     * @param introspectedTable 数据库表的对象
     */
    public void addGeneralMethodComment(Method method,
                                        IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        List<Parameter> parameters = method.getParameters();
        method.addJavaDocLine("/**");
        if (parameters.size() > 0) {
            parameters.forEach(p -> {
                sb.append(" * @Param: ");
                sb.append(p.getType()).append(" ").append(p.getName());
                method.addJavaDocLine(sb.toString());
                sb.setLength(0);
            });
        }
        method.addJavaDocLine(" * @Date: " + getDateString());
        method.addJavaDocLine(" * @Description: ");
        sb.setLength(0);
        FullyQualifiedJavaType fullyQualifiedJavaType = method.getReturnType();
        if (!"".equals(fullyQualifiedJavaType) && null != fullyQualifiedJavaType) {
            sb.append(" * @Return: ").append(fullyQualifiedJavaType);
            method.addJavaDocLine(sb.toString());
        }
        addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    /**
     * 添加get方法的注释样式
     *
     * @param method             方法对象
     * @param introspectedTable  数据库表的对象
     * @param introspectedColumn 数据库表中的列的对象
     */
    public void addGetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * @Table: ").append(introspectedTable.getFullyQualifiedTable())
                .append("\n\t")
                .append(" * @Column: ").append(introspectedColumn.getActualColumnName())
                .append("\n\t")
                .append(" * @Description: ").append(introspectedColumn.getRemarks())
                .append("\n\t")
                .append(" * @Return:  ").append(method.getReturnType());
        method.addJavaDocLine(sb.toString());
        addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    /**
     * 添加set方法的注释样式
     *
     * @param method             方法对象
     * @param introspectedTable  数据库表的对象
     * @param introspectedColumn 数据库表中的列的对象
     */
    public void addSetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Parameter parameter = method.getParameters().get(0);
        method.addJavaDocLine("/**");
        sb.append(" * @Table: ").append(introspectedTable.getFullyQualifiedTable())
                .append("\n\t")
                .append(" * @Column: ").append(introspectedColumn.getActualColumnName())
                .append("\n\t")
                .append(" * @Param ").append(parameter.getName() + " " + parameter.getType())
                .append("\n\t")
                .append(" * @ Description: ").append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    /**
     * 添加类注释
     *
     * @param innerClass        类对象
     * @param introspectedTable 数据库表的对象
     * @param markAsDoNotDelete 是否添加警告不能删除该类
     */
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * @Author: lin");
        sb.append(" * @Table: ").append(introspectedTable.getFullyQualifiedTable())
                .append("\n")
                .append(" * @Date: ").append(getDateString())
                .append("\n")
                .append(" * @Description: ").append(introspectedTable.getRemarks());
        innerClass.addJavaDocLine(sb.toString());
        addJavadocTag(innerClass, markAsDoNotDelete);
        innerClass.addJavaDocLine(" */");
    }
}
