package com.gm.mapper;

import com.gm.entry.TGenerator;
import java.util.List;

public interface TGeneratorMapper {
    /**
    
     * @Date: 2019/04/22
     * @Description: 
     * @Return: int
     */
    int deleteByPrimaryKey(Integer id);

    /**
    
     * @Date: 2019/04/22
     * @Description: 
     * @Return: int
     */
    int insert(TGenerator record);

    /**
    
     * @Date: 2019/04/22
     * @Description: 
     * @Return: com.gm.entry.TGenerator
     */
    TGenerator selectByPrimaryKey(Integer id);

    /**
     * @Date: 2019/04/22
     * @Description: 
     * @Return: java.util.List<com.gm.entry.TGenerator>
     */
    List<TGenerator> selectAll();

    /**
    
     * @Date: 2019/04/22
     * @Description: 
     * @Return: int
     */
    int updateByPrimaryKey(TGenerator record);
}