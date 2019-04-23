package com.gm.mapper;

import com.gm.entry.TGenerator;
import java.util.List;

public interface TGeneratorMapper {
    /**
     * @Param: java.lang.Integer id
     * @Date: 2019/04/23
     * @Description: 
     * @Return: int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Param: com.gm.entry.TGenerator record
     * @Date: 2019/04/23
     * @Description: 
     * @Return: int
     */
    int insert(TGenerator record);

    /**
     * @Param: java.lang.Integer id
     * @Date: 2019/04/23
     * @Description: 
     * @Return: com.gm.entry.TGenerator
     */
    TGenerator selectByPrimaryKey(Integer id);

    /**
     * @Date: 2019/04/23
     * @Description: 
     * @Return: java.util.List<com.gm.entry.TGenerator>
     */
    List<TGenerator> selectAll();

    /**
     * @Param: com.gm.entry.TGenerator record
     * @Date: 2019/04/23
     * @Description: 
     * @Return: int
     */
    int updateByPrimaryKey(TGenerator record);
}