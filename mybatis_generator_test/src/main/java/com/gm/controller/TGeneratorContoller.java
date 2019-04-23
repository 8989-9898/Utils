package com.gm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.entry.TGenerator;
import com.gm.service.T_GeneratorServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @ProjectName: mybatis
 * @Package: com.gm.controller
 * @ClassName: TGeneratorContoller
 * @Author: lin
 * @Description: 测试整个流程接口
 * @Date: 2019-04-23 10:37
 * @Version: 1.0
 */
@RestController
public class TGeneratorContoller {

    @Autowired
    private T_GeneratorServer t_generatorServer;

    @ApiOperation(value = "根据传入的页码和也大小分页获取信息",notes = "测试数据")
    @ApiImplicitParams({@ApiImplicitParam(name="pageNum",defaultValue = "1",required = true,dataType = "int",paramType = "path")
    ,@ApiImplicitParam(name="pageSize",defaultValue = "10",required = true,dataType = "int",paramType = "path")})
    @GetMapping("/generator/{pageNum}/{pageSize}")
    public PageInfo<TGenerator> findPage(@RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum, @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        return t_generatorServer.findPage(pageNum,pageSize);
    }

    @ApiOperation(value = "获取所有信息",notes = "测试数据")
    @GetMapping("/generator")
    public List<TGenerator> findAll(){
        return t_generatorServer.fingAll();
    }

    @PostMapping("/generator")
    @ApiOperation("添加一个数据源")
    public int addTGenerator(TGenerator tGenerator){
        return t_generatorServer.insert(tGenerator);
    }

    @ApiOperation("/根据主键修改一个信息")
    @PutMapping("/generator")
    public int putTGenerator(TGenerator tGenerator){
        return t_generatorServer.updateByPrimaryKey(tGenerator);
    }
    @ApiOperation("根据主键删除一个数据信息")
    @DeleteMapping("/generator")
    public int delTGenerator(int id){
        return t_generatorServer.deleteByPrimaryKey(id);
    }

}
