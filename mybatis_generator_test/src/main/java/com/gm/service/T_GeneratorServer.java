package com.gm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.entry.TGenerator;
import com.gm.mapper.TGeneratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: mybatis
 * @Package: com.gm.service
 * @ClassName: T_GeneratorServer
 * @Author: lin
 * @Description: 测试自动生成的数据层的服务层
 * @Date: 2019-04-22 10:03
 * @Version: 1.0
 */
@Service
public class T_GeneratorServer {

    @Autowired
    private TGeneratorMapper tGeneratorMapper;


    /**
     * @Param: java.lang.Integer id
     * @Date: 2019/04/23
     * @Description:
     * @Return: int
     */
    public int deleteByPrimaryKey(Integer id) {
        return tGeneratorMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Param: com.gm.entry.TGenerator record
     * @Date: 2019/04/23
     * @Description:
     * @Return: int
     */
    public int insert(TGenerator record) {
        return tGeneratorMapper.insert(record);
    }

    /**
     * @Param: java.lang.Integer id
     * @Date: 2019/04/23
     * @Description:
     * @Return: com.gm.entry.TGenerator
     */
    public TGenerator selectByPrimaryKey(Integer id) {
        return tGeneratorMapper.selectByPrimaryKey(id);
    }

    /**
     * @Date: 2019/04/23
     * @Description:
     * @Return: java.util.List<com.gm.entry.TGenerator>
     */
    public PageInfo<TGenerator> findPage(int pageNum,int pageSize) {
        //将分页参数传给这个方法将可以实现物理分页了
        PageHelper.startPage(pageNum,pageSize);
        List<TGenerator> tGenerators = tGeneratorMapper.selectAll();
        return new PageInfo(tGenerators);
    }

    /**
     * 获取所有数据
     * @return 数据集合添加数据
     */
    public List<TGenerator> fingAll(){

        System.out.println("测试热启动是否生效");
        return tGeneratorMapper.selectAll();
    }

    /**
     * @Param: com.gm.entry.TGenerator record
     * @Date: 2019/04/23
     * @Description:
     * @Return: int
     */
    public int updateByPrimaryKey(TGenerator record) {
        return tGeneratorMapper.updateByPrimaryKey(record);
    }


}
